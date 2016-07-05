
package com.sidia.lipwebservice;

import java.util.HashMap;
import java.util.Map;
public class Overview_polyline {

    private String points;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
