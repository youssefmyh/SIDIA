package com.sidia.engine;

public class ApplicationConstant {

	public static final float DISTANCE_GPS_UPDATE=200f; // distance for GPS Location Update 
	public static final String DIRECTION_SERVICE_URL="http://maps.googleapis.com/maps/api/"; //Service URL For load Direction
	public static final String DIRECTION_METHOD_NAME="directions/"; //direction service name
	public static final String REQUEST_FORMAT="json?";  //Response format json
	
	public static final int TIMEOUT=30000; // timeout exception after 30000
	public static final String METRIC_FIELD="metric";
	public static final int ACTIVITY_ROADS_REQUEST=1000; // timeout exception after 30000
	
	public final static String  SERVICE_DATA_RESPONSE="com.sidia.response";
	public final static String  SERVICE_DATA_ACTION="com.sidia.action";

	
}
