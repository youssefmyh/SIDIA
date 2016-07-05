package com.sidia.controller;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.RemoteException;

import com.sidia.lipservice.IRouteService;

/*
 * SidiaServiceConnection 
 * brief SidiaServiceConnection class used for initialize  connection with remote service 
 * 
 * */
public class SidiaServiceConnection implements
		android.content.ServiceConnection {

	IRouteService iService; // IRouteService Remote Service stub
	double LocLat; // My Location Latitude
	double LocLon; // My Location Longitude
	double desLat; // destination Latitude
	double desLon; // destination Longitude

	/*
	 * @param double LocLat 
	 * 
	 * @param double LocLon
	 * 
	 * @param double desLat
	 * 
	 * @param double desLon
	 * 
	 * brief SidiaServiceConnection Constructor used to initialize with Locations 
	 */

	public SidiaServiceConnection(double LocLat, double LocLon, double desLat,
			double desLon) {

		this.LocLat = LocLat;
		this.LocLon = LocLon;
		this.desLat = desLat;
		this.desLon = desLon;

	}
	
	/*
	 * brief when Connection succeed with Remote Service onServiceConnected fired 
	 * */

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		System.out.println("Connected");
		iService = IRouteService.Stub.asInterface(service);

		try {
			iService.loadRout(LocLat, LocLon, desLat, desLon); // loadRouts from iService class

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * brief when Connection closed with Remote Service onServiceDisconnected fired 
	 * */
	@Override
	public void onServiceDisconnected(ComponentName name) {
		System.out.println("Disconnected");

	}

}
