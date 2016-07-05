package com.sidia.lipservice;
/*
IRouteService mapping between Remote Service and HomeWay client 
*/
interface IRouteService {
	void loadRout(double sourceLat,double sourceLong,double destLat,double destLon);
}
