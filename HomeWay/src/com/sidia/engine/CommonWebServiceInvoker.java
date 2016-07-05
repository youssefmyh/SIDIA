package com.sidia.engine;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.sidia.controller.LoadingFinish;


public class CommonWebServiceInvoker {

	// JSON builder
	static Gson gsonBuilder;
	// Request object
	Object requestObject;
	// response Object
	Object responseObject;
	LoadingFinish loadingFinish;
	
	
	/*
	 @param Object _requestObject request Object you need to sent
	 @param Object _responseObject response Object you should receive  
	 @param Interfce _loadingFinish Interface will be notified when Loading finished or Fail
	 @param String MethodName specify the method name you need to call
	 Brief this method is used to create a thread and call a specific Json Function and return the response throw Loading Finish Interface 
	 */

	public void doRequest(Object _requestObject, Object _responseObject,
			LoadingFinish _loadingFinish,
			final String MethodName) {
		this.requestObject = _requestObject;  
		this.responseObject = _responseObject;
		this.loadingFinish = _loadingFinish;

		Thread thread = new Thread() {
			public void run() {
				String URLString = ApplicationConstant.DIRECTION_SERVICE_URL+MethodName+ApplicationConstant.REQUEST_FORMAT;
				URLString+=Utilites.getGoogleRouteRequest(requestObject);	
				try {
					HttpParams httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams,
							ApplicationConstant.TIMEOUT);
					HttpClient httpClient = new DefaultHttpClient(httpParams);
					HttpGet request = new HttpGet(URLString);
					HttpResponse response = httpClient.execute(request);
					String responses = EntityUtils.toString(response.getEntity());
					Gson gson2 = new Gson();
					responseObject = gson2.fromJson(responses,responseObject.getClass());
					if (loadingFinish != null)
						loadingFinish.loadingFinish(responseObject);
				} catch (Exception ee)
				{
					if (loadingFinish != null)
						loadingFinish.loadingFail(responseObject);
				}
			}
		};

		thread.start();
	}




}
