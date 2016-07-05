package com.sidia.controller;
/*
 * The GPSLocationLoading 
 * brief GPSLocationLoading Fired when detect or location changed
 * */
public interface GPSLocationLoading {
	/*
	 * @param double latitude 
	 * @param double longitude
	 * brief onGPSLocationLoaded fired with latitude and longitude
	 * */
	public void onGPSLocationLoaded(double latitude, double longitude);
}
