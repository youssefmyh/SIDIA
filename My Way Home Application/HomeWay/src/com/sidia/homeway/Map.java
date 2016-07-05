package com.sidia.homeway;

import java.util.List;

import android.app.Fragment;

import com.google.android.gms.maps.model.LatLng;
import com.sidia.controller.GPSLocationLoading;
import com.sidia.lipwebservice.Step;

/*
 * Main Map Fragment 
 * */
public abstract class Map  extends Fragment implements GPSLocationLoading{

	private String mapEngineType; // engine type 
	protected double currentLocation_lat; // current Location Latitude 
	protected double currentLocation_long;// current Location Longitude 
	
	/*
	 *@param List<LatLng> _listofInformation_points all points to draw polygon
	 * brief draw all points 
	 * */
	public abstract void  addPolyLines(List<LatLng> _listoflatLang);
	/*
	 * @param List<Step> _listofSteps to be added on Map
	 * brief draw steps with markers 
	 * */
	public abstract void addSteps(List<Step> _listofSteps);
	/*
	 * brief add My location marker
	 */
	public abstract void addMarker(double latitude, double longitude, String label,int icon);
	/*
	 * brief get get Current Location as string 
	 * */
	public abstract  String getCurrentLocation();
	
	public String getMapEngineType() {
		return mapEngineType;
	}
	public void setMapEngineType(String mapEngineType) {
		this.mapEngineType = mapEngineType;
	}
	
	public double getCurrentLocation_lat() {
		return currentLocation_lat;
	}

	public double getCurrentLocation_long() {
		return currentLocation_long;
	}

	
}
