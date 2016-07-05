package com.sidia.controller;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
/*
 * SIDIALocationListener class used to detect the GPS Location
 * 
 * */
public class SIDIALocationListener implements LocationListener {

	private GPSLocationLoading gpsLocationLoader; // GPSLocationLoading Interface 

	/*
	 * @param GPSLocationLoading gpsLocationLoader interface to be fired when GPS location detected 
	 * brief initialize SIDIALocationListener
	 * */
	public SIDIALocationListener(GPSLocationLoading gpsLocationLoader) {
		this.gpsLocationLoader = gpsLocationLoader;
	}

	/*
	 * @param Location location 
	 * 
	 * brief when GPS location detected onLocationChanged called with Location Object
	 * */
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if (location != null) {
			gpsLocationLoader.onGPSLocationLoaded(location.getLatitude(),
					location.getLongitude());
		}

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
