package com.sidia.communication;

import java.io.Serializable;
import java.util.ArrayList;

public class CommunicationObject implements Serializable{

	/**
	 * communication Object between Remote Service and HomeWay 
	 */
	private static final long serialVersionUID = 1L;
	
	public ArrayList<String> roadsList; // Road List
	public ArrayList<String> polyString; // poly encoded points 
	public ArrayList<String> getRoadsList() {
		return roadsList;
	}
	public void setRoadsList(ArrayList<String> roadsList) {
		this.roadsList = roadsList;
	}
	public ArrayList<String> getPolyString() {
		return polyString;
	}
	public void setPolyString(ArrayList<String> polyString) {
		this.polyString = polyString;
	}
	
	
	
	

}
