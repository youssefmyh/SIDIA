package com.sidia.lipwebservice;

public class DirectionRequest {

	private String origin;
	private String destination;
	private boolean sensor;
	private String units;
	private boolean alternatives;
	private boolean traffic;
	
	
	
	public boolean isTraffic() {
		return traffic;
	}

	public void setTraffic(boolean traffic) {
		this.traffic = traffic;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isSensor() {
		return sensor;
	}

	public void setSensor(boolean sensor) {
		this.sensor = sensor;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public boolean isAlternatives() {
		return alternatives;
	}

	public void setAlternatives(boolean alternatives) {
		this.alternatives = alternatives;
	}

}
