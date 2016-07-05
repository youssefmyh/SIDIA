package com.sidia.homeway;

import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sidia.controller.GPSLocationLoading;
import com.sidia.engine.ApplicationConstant;
import com.sidia.engine.Utilites;
import com.sidia.lipwebservice.Start_location;
import com.sidia.lipwebservice.Step;

public class GoogleMapFragment extends Map implements GPSLocationLoading,
		OnMapLongClickListener {

	// Google Map
	private GoogleMap googleMap; // Google Map
	private List<LatLng> listOfPoints; // List of points
	private PolylineOptions polyOptions; // List of encoded Points
	LatLng selectedLatLng; // selected LatLng used to save home location

	/*
	 * brief inflate Map View Fragment from XML
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.mapactivity, container, false);
	}

	/*
	 * brief initlaize Google Map
	 */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((com.google.android.gms.maps.MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			if (googleMap == null) {
				Utilites.showToast(getString(R.string.enabletocratemap),
						this.getActivity());
			} else {
				googleMap.setOnMapLongClickListener(this); // add Map Listener
			}

		}
	}

	/*
	 * brief onViewCreated after view created and inflated from XML
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		registerForContextMenu(getView());
		initilizeMap(); // Initialize Map

	}

	/*
	 * @param double latitude
	 * 
	 * @param double longitude
	 * 
	 * brief onGPSLocationLoaded fired from interface when GPS location deteced
	 */

	@Override
	public void onGPSLocationLoaded(double latitude, double longitude) {
		// TODO Auto-generated method stub
		System.out.println("GPS Loaded : " + latitude + "  : " + longitude);
		currentLocation_lat = latitude;
		currentLocation_long = longitude;
		if (googleMap != null) {
			addMyLocationMarker();
		}
	}

	/*
	 * brief add My location marker
	 */
	private void addMyLocationMarker() {
		addMarker(currentLocation_lat, currentLocation_long,
				getString(R.string.mylocation), -1);
		LatLng latLng = new LatLng(currentLocation_lat, currentLocation_long);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,
				ApplicationConstant.MAP_ZOOM));
	}

	/*
	 * @param double latitude
	 * 
	 * @param double longitude
	 * 
	 * @param String label which shown when click on marker
	 * 
	 * @param int icon -1 if you donnot want to override the default marker icon
	 * brief add Marker
	 */
	public void addMarker(double latitude, double longitude, String label,
			int icon) {
		MarkerOptions markerOptions = new MarkerOptions();
		LatLng latLng = new LatLng(latitude, longitude);
		markerOptions.position(latLng);
		markerOptions.title(label);

		if (icon != -1) {
			markerOptions.icon(BitmapDescriptorFactory.fromResource(icon));

		}
		googleMap.addMarker(markerOptions);
	}

	/*
	 *@param List<LatLng> _listofInformation_points all points to draw polygon
	 * 
	 * brief draw all points 
	 * */
	public void addPolyLines(List<LatLng> _listofInformation_points) {
		if(googleMap==null)
			return;
		googleMap.clear();
		addMyLocationMarker();
		this.listOfPoints = _listofInformation_points;
		// TODO Auto-generated method stub
		polyOptions = new PolylineOptions();

		// TODO Auto-generated method stub
		for (int i = 0; i < listOfPoints.size(); i++) {
			polyOptions.add(listOfPoints.get(i));
			polyOptions.width(10).color(Color.BLUE).geodesic(true);
		}
		googleMap.addPolyline(polyOptions);
		LatLng lat1 = listOfPoints.get(listOfPoints
				.size() - 1);
		addMarker(lat1.latitude, lat1.longitude, getString(R.string.myHome), -1);

	}

	
	/*
	 * @param List<Step> _listofSteps to be added on Map
	 * brief draw steps with markers 
	 * */
	
	public void addSteps(List<Step> _listofSteps) {
		for (int i = 0; i < _listofSteps.size(); i++) {
			Step step = _listofSteps.get(i);
			Start_location startLocation = step.getStart_location();
			String lable = step.getHtml_instructions().replace("<b>", " ")
					.replace("</b>", " ");
			addMarker(startLocation.getLat(), startLocation.getLng(), lable,
					R.drawable.locationicon);
		}
	}
	
	
	/*
	 * brief show Context Menu to save Location
	 * */

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle(getString(R.string.savelocation));
		menu.add(0, ApplicationConstant.SAVE_LOCATION__MENU_ITEM_KEY, 0,
				getString(R.string.savelocation));
	}

	/*
	 * brief when context menu select 
	 * */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (selectedLatLng != null) {
			SettingObject setting = SettingObject.getInstance(this
					.getActivity());
			setting.setLatitude("" + selectedLatLng.latitude);
			setting.setLongitude("" + selectedLatLng.longitude);
			setting.save();
		}
		return true;
	}

	/*
	 * brief get get Current Location as string 
	 * */
	public String getCurrentLocation() {
		return currentLocation_lat + "," + currentLocation_long;
	}

	/*
	 * brief on Long Map click to save Location 
	 * */
	@Override
	public void onMapLongClick(LatLng lat) {
		// TODO Auto-generated method stub
		this.selectedLatLng = lat;
		Toast.makeText(GoogleMapFragment.this.getActivity(), lat.toString(),
				Toast.LENGTH_SHORT).show();
		this.getView().showContextMenu();
	}

}
