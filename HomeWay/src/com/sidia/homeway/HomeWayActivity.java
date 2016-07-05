package com.sidia.homeway;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.maps.model.LatLng;
import com.sidia.communication.CommunicationObject;
import com.sidia.controller.LoadingFinish;
import com.sidia.controller.SIDIALocationListener;
import com.sidia.controller.SidiaServiceConnection;
import com.sidia.engine.ApplicationConstant;
import com.sidia.engine.Utilites;
import com.sidia.lipwebservice.DirectionResponse;
import com.sidia.lipwebservice.Route;
import com.sidia.lipwebservice.caller.DirectionsCaller;

/*
 * HomeWayActivity Main Activity 
 * */
public class HomeWayActivity extends Activity implements LoadingFinish {

	Map mapFragment; // Map Fragment 
	private LocationManager locationManager; // Location Manger 
	private DirectionResponse directionResponse; // Direction Response parsed from Json Direction Response
	FragmentTransaction fragmentTransaction; // Fragment Manger 
	LinearLayout mainMapView;  // Main view that contain the Map 
	List<Route> listOfRoutes; // List of all available roads between two points 
	List<String> listofRoutes_poly;  // list of all points encode as string 
	Button myhomewaylip; // button shown only if LIP provider is installed 
	SidiaServiceConnection sediaConnection; // serviceConnection for Remote Service 
	Receiver reciever; //Receiver for Remote Service 
	
	
	/*
	 * brief inflate activity xml and find main layout  
	 * */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_way);
		myhomewaylip=(Button) findViewById(R.id.myhomewaylip);
		mainMapView = (LinearLayout) findViewById(R.id.mainlayout);
		
	}
	
	/*
	 * brief initialize Location Manger to Get current GPS location 
	 * */
	private void initalizeLocationManager() {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		SIDIALocationListener sidiaLocationListener = new SIDIALocationListener(
				mapFragment);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				ApplicationConstant.DISTANCE_GPS_UPDATE, sidiaLocationListener);
		if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			showGPSEnabledAlert();
		}

	}
	
	/*
	 * brief showGPSEnabledAlert shown if GPS disabled and ask the customer to enable it  
	 * */

	private void showGPSEnabledAlert() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder
				.setMessage(getString(R.string.gpsdisabled))
				.setCancelable(false)
				.setPositiveButton(getString(R.string.enable),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent callGPSSettingIntent = new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								startActivity(callGPSSettingIntent);
							}
						});
		alertDialogBuilder.setNegativeButton(getString(R.string.skip),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}

	/*
	 * brief initialize FragmentManger  
	 * */
	private void initalizeFragmentManger() {
		fragmentTransaction = getFragmentManager().beginTransaction();
	}
	
	/*
	 * brief initialize Map Fragment  
	 * */
	
	public void initalizeMapFragment()
	{
		if(Utilites.isPackageExisted(this, ApplicationConstant.GOOGLE_LIP_PACKAG_NAME))
			myhomewaylip.setVisibility(View.VISIBLE);
		else
		{
			myhomewaylip.setVisibility(View.GONE);
		}
		SettingObject settingObject = SettingObject.getInstance(this);
		MapFactory mapFactory = new MapFactory();
		Map temMapFragment = mapFactory.getMap(settingObject.getEnginekey());
		if (temMapFragment != null
				&& (mapFragment == null || !mapFragment.getMapEngineType()
						.equalsIgnoreCase(temMapFragment.getMapEngineType()))) {
			initalizeFragmentManger();
			if (mapFragment != null)
				fragmentTransaction.remove(mapFragment);
			fragmentTransaction.add(R.id.mainlayout, temMapFragment);
			fragmentTransaction.commit();
			mapFragment = temMapFragment;
			initalizeLocationManager();
		} else if (temMapFragment == null) {
			Utilites.showToast(getString(R.string.enginenotsupported), this);
			openSettings();

		}

	}

	
	/*
	 * brief show Setting Menu Item and inflate it from home_way 
	 * */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_way, menu);
		return true;
	}

	/*
	 * brief when setting item selected 
	 * */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

		if (item.getItemId() != ApplicationConstant.SAVE_LOCATION__MENU_ITEM_KEY) {

			openSettings();
		}
		return super.onMenuItemSelected(featureId, item);
	}

	/*
	 * brief open Setting Fragment 
	 * */
	public void openSettings() {

		Intent settingIntent = new Intent(this, SettingsActivity.class);
		startActivity(settingIntent);

	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		initalizeMapFragment();
		super.onResume();
	}

	/*
	 * brief show Path from current location to Home 
	 * */
	public void goToHome(View view) {
		DirectionsCaller caller = new DirectionsCaller(this);
		SettingObject setting = SettingObject.getInstance(this);
		if (setting.getLatitude() == null || setting.getLongitude() == null) {
			Utilites.showToast(getString(R.string.homelocationisnull), this);
		} else {
			caller.loadDirections(mapFragment.getCurrentLocation(),
					setting.getLatitude() + "," + setting.getLongitude());
		}
	}

	/*
	 * brief show Path from current location to Home using LIP provider 
	 * */
	public void goToHomeLIP(View view) {
		directionResponse = null;
		SettingObject setting = SettingObject.getInstance(this);
		if (setting.getLatitude() == null || setting.getLongitude() == null) {
			Utilites.showToast(getString(R.string.homelocationisnull), this);
		} else {

			sediaConnection = new SidiaServiceConnection(
					mapFragment.getCurrentLocation_lat(),
					mapFragment.getCurrentLocation_long(),
					Utilites.parseDouble(setting.getLatitude()),
					Utilites.parseDouble(setting.getLongitude()));
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(ApplicationConstant.SERVICE_DATA_ACTION);
			reciever=new Receiver();
			registerReceiver(reciever, intentFilter);
			Intent intent = new Intent(
					ApplicationConstant.INTENT_SERVICE_ACTION);
			bindService(intent, sediaConnection, Context.BIND_AUTO_CREATE);
		}
	}

	
	/*
	 * @param Object parsedObject response from google API 
	 * 
	 * brief loadingFinish fired when response received 
	 * */
	@Override
	public void loadingFinish(Object parsedObject) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		if (parsedObject != null && parsedObject instanceof DirectionResponse) {
			this.directionResponse = (DirectionResponse) parsedObject;
			this.runOnUiThread(new Runnable() {
				public void run() {
					listOfRoutes = directionResponse.getRoutes();
					listofRoutes_poly = new ArrayList<String>();
					ArrayList<String> roadTitles = new ArrayList<String>();
					for (int i = 0; i < listOfRoutes.size(); i++) {
						roadTitles.add(listOfRoutes.get(i).getSummary()
								+ " "
								+ listOfRoutes.get(i).getLegs().get(0)
										.getDistance().getText());
						listofRoutes_poly.add(listOfRoutes.get(i)
								.getOverview_polyline().getPoints());
					}
					showRoads(roadTitles);

				}
			});

		}

	}

		
	/*
	 * brief when road selected from Road Activity  
	 * */

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (data != null) {
			int selectedIndex = data.getIntExtra(
					ApplicationConstant.SELECTED_ROAD, -1);
			if (selectedIndex != -1 && listofRoutes_poly != null
					&& selectedIndex < listofRoutes_poly.size()) {
				addPolgon(listofRoutes_poly.get(selectedIndex));
				if (directionResponse != null)
					mapFragment.addSteps(directionResponse.getRoutes()
							.get(selectedIndex).getLegs().get(0).getSteps());
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/*
	 * @param ArrayList<String> roads Titles array
	 * brief show Roads activity  
	 * */
	public void showRoads(ArrayList<String> roadTitles) {
		if (roadTitles != null) {
			Intent intent = new Intent(HomeWayActivity.this, RoadActivity.class);
			intent.putExtra(ApplicationConstant.ROAD_KEY, roadTitles);
			startActivityForResult(intent,
					ApplicationConstant.ACTIVITY_ROADS_REQUEST);
		}

	}


	/*
	 * brief add Points from encoded string encodedString
	 * */
	public void addPolgon(String encodedString) {
		if(encodedString==null)
			return;
		List<LatLng> listoflatLang = Utilites.decodePoly(encodedString);
		if(listoflatLang!=null&&mapFragment!=null)
		mapFragment.addPolyLines(listoflatLang);
	}

	/*
	 * brief when loading fail 
	 * */
	@Override
	public void loadingFail(Object parsedObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onContextItemSelected(item);
	}
	
	/*
	 * brief class Receiver fired when Remote Service called and response come to  onReceive function 
	 * 
	 * */

	private class Receiver extends BroadcastReceiver {

		/*
		 * Brief onReceive fired from Remote Service 
		 * */
		@Override
		public void onReceive(Context arg0, Intent intent) {
			// TODO Auto-generated method stub
			System.out.println("Recieve Data");

			CommunicationObject communication = (CommunicationObject) intent
					.getSerializableExtra(ApplicationConstant.SERVICE_DATA_RESPONSE);

			if (communication != null) {
				showRoads(communication.getRoadsList());
				listofRoutes_poly = communication.getPolyString();
				if(sediaConnection!=null){
					if(reciever!=null)
					unregisterReceiver(reciever);
					unbindService(sediaConnection);
					sediaConnection=null;
					reciever=null;
				}
			}
		}

	}

}
