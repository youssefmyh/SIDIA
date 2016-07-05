package com.sidia.homeway;

import com.sidia.engine.ApplicationConstant;

/*
 * MAPFactory pattern used to detect Map depends on mapType
 * */
public class MapFactory {

	/*
	 * @param String mapType 
	 * Brief rerun MapFragemnt by mapType
	 */
	public Map getMap(String mapType) {
		if (mapType == null)
			return null;
		if (mapType.equalsIgnoreCase(ApplicationConstant.GOOGLE_ENGINE)) {
			GoogleMapFragment googleEngineMap = new GoogleMapFragment();
			googleEngineMap.setMapEngineType(mapType);
			return googleEngineMap;
		} else if (mapType.equalsIgnoreCase(ApplicationConstant.BADUI_ENGINE)) {
			return null;
		}

		return null;
	}

}
