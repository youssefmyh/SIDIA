
package com.sidia.lipwebservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Leg {

    private Distance distance;
    private Duration duration;
    private String end_address;
    private End_location end_location;
    private String start_address;
    private Start_location start_location;
    private List<Step> steps = new ArrayList<Step>();
    private List<Object> via_waypoint = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public End_location getEnd_location() {
        return end_location;
    }

    public void setEnd_location(End_location end_location) {
        this.end_location = end_location;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public Start_location getStart_location() {
        return start_location;
    }

    public void setStart_location(Start_location start_location) {
        this.start_location = start_location;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Object> getVia_waypoint() {
        return via_waypoint;
    }

    public void setVia_waypoint(List<Object> via_waypoint) {
        this.via_waypoint = via_waypoint;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
