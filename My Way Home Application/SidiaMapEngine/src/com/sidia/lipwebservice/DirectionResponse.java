
package com.sidia.lipwebservice;

import java.util.ArrayList;
import java.util.List;
public class DirectionResponse {

    private List<Route> routes = new ArrayList<Route>();
    private String status;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
