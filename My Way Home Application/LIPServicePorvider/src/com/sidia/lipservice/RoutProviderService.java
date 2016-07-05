package com.sidia.lipservice;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.sidia.communication.CommunicationObject;
import com.sidia.controller.LoadingFinish;
import com.sidia.engine.ApplicationConstant;
import com.sidia.lipwebservice.DirectionResponse;
import com.sidia.lipwebservice.Route;
import com.sidia.lipwebservice.caller.DirectionsCaller;

/*
 * RoutProviderService Service used to load available  roads between two points 
 * */
public class RoutProviderService extends Service {

	public class RoutProviderServiceImpl extends IRouteService.Stub implements
			LoadingFinish {

		/*
		 * Brief load Rout 
		 * */
		@Override
		public void loadRout(double sourceLat, double sourceLong,
				double destLat, double destLon) throws RemoteException {
			// TODO Auto-generated method stub

			DirectionsCaller directCaller = new DirectionsCaller(this);
			directCaller.loadDirections(sourceLat + "," + sourceLong, destLat
					+ "," + destLon);

		}

		/*
		 * Loading Finish 
		 * */
		@Override
		public void loadingFinish(Object parsedObject) {
			// TODO Auto-generated method stub
			if (parsedObject != null
					&& parsedObject instanceof DirectionResponse) {
				DirectionResponse directionResponse = (DirectionResponse) parsedObject;
				List<Route> routes = directionResponse.getRoutes();
				ArrayList<String> roadTitles = new ArrayList<String>();
				ArrayList<String> roadpoly = new ArrayList<String>();

				for (int i = 0; i < routes.size(); i++) {
					roadTitles.add(routes.get(i).getSummary()
							+ " "
							+ routes.get(i).getLegs().get(0).getDistance()
									.getText());
					roadpoly.add(routes.get(i).getOverview_polyline()
							.getPoints());
				}
				CommunicationObject communicationObject = new CommunicationObject();
				communicationObject.setRoadsList(roadTitles);
				communicationObject.setPolyString(roadpoly);
				Intent intent = new Intent();
				intent.setAction(ApplicationConstant.SERVICE_DATA_ACTION);

				intent.putExtra(ApplicationConstant.SERVICE_DATA_RESPONSE,
						communicationObject);

				sendBroadcast(intent); // sent data to BraodCast reciever  
			}
		}

		@Override
		public void loadingFail(Object parsedObject) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("Re Bind");
		return new RoutProviderServiceImpl();
	}

}
