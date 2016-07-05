package com.sidia.lipwebservice.caller;

import com.sidia.controller.LoadingFinish;
import com.sidia.engine.ApplicationConstant;
import com.sidia.engine.CommonWebServiceInvoker;
import com.sidia.lipwebservice.DirectionRequest;
import com.sidia.lipwebservice.DirectionResponse;

public class DirectionsCaller {

	LoadingFinish loadingFinish;
	public DirectionsCaller(LoadingFinish loadingFinish)
	{
		this.loadingFinish=loadingFinish;
	}
	
	
	public void loadDirections(String orgin,String destination)
	{
		DirectionRequest directionRequest=new DirectionRequest();
		directionRequest.setOrigin(orgin);
		directionRequest.setDestination(destination);
		directionRequest.setSensor(true);
		directionRequest.setTraffic(true);

		directionRequest.setAlternatives(true);
		directionRequest.setUnits(ApplicationConstant.METRIC_FIELD);
		DirectionResponse directionResponse=new DirectionResponse();
		CommonWebServiceInvoker invoker=new CommonWebServiceInvoker();
		invoker.doRequest(directionRequest, directionResponse, loadingFinish, ApplicationConstant.DIRECTION_METHOD_NAME);
	}
	
	
}
