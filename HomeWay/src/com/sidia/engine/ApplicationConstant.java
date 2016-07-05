package com.sidia.engine;

public class ApplicationConstant {

	public static final float DISTANCE_GPS_UPDATE=200f; // distance for GPS Location Update 
	public static final int MAP_ZOOM=12; //Map Zoom
	public static final String DIRECTION_SERVICE_URL="http://maps.googleapis.com/maps/api/"; //Service URL For load Direction
	public static final String DIRECTION_METHOD_NAME="directions/"; //direction service name
	public static final String REQUEST_FORMAT="json?";  //Response format json
	
	
	
	
	public static final int TIMEOUT=30000; // timeout exception after 30000
	public static final String METRIC_FIELD="metric";
	public static final int ACTIVITY_ROADS_REQUEST=1000; // timeout exception after 30000

	
	
	
	/////////////////////////////Settings KEYS//////////////////////
	
	public static final int SETTING__MENU_ITEM_KEY=0;  // setting Menu Item Key
	public static final int SAVE_LOCATION__MENU_ITEM_KEY=1; //save location menu item key  
	
	public static final String LATITUDE="latitude";  // LATITUDE saved Key 
	public static final String LONGITUDE="longitude"; // longitude saved Key
	public static final String ENGINEKEY="enginekey"; // ENGINEKEY saved Key
	public static final String ROAD_KEY="raodkey";// ROAD_KEY saved Key
	public static final String SELECTED_ROAD="selectedRoad"; // SELECTED_ROAD extra key


	///////////////////////////MAP ENGINES KEYS ///////////////
	
	public final static String  GOOGLE_ENGINE="0";  // google Engine value
	public final static String  BADUI_ENGINE="1"; // BADUI ENGINE value
	public final static String  SERVICE_DATA_RESPONSE="com.sidia.response";  // Extra key used to get communication object from intent 
	public final static String  SERVICE_DATA_ACTION="com.sidia.action"; //Action registered 
	public  final static String GOOGLE_LIP_PACKAG_NAME= "com.sidia.lipservice";  // Google package check 
	public static final String INTENT_SERVICE_ACTION="com.sidia.lipservice.RoutProviderService"; //Remote intent service Action 
}
