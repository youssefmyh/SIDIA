package com.sidia.controller;

/*
 * LoaingFinish Interface 
 * */
public interface LoadingFinish {

	/*
	 * @param Object parsedObject 
	 * 
	 * Brief LoadingFinish fired when Response back and parsed from CommonWebServiceInvoker
	 * */
	public void loadingFinish(Object parsedObject);
	
	/*
	 * @param Object parsedObject 
	 * 
	 * Brief loadingFail fired when fail due to exception or TimeOut
	 * */
	public void loadingFail(Object parsedObject);
}
