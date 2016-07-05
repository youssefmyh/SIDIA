package com.sidia.homeaway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.sidia.controller.LoadingFinish;
import com.sidia.homeway.HomeWayActivity;
import com.sidia.homeway.R;
import com.sidia.lipwebservice.caller.DirectionsCaller;

@RunWith(RobolectricTestRunner.class)
public class HomeWayActivityTest implements LoadingFinish{

	@Test
	public void whenTrytoInitializeHomeWayActivity()
	{
		HomeWayActivity homeAwayActivity=Robolectric.buildActivity(HomeWayActivity.class).get();
		String app_name=homeAwayActivity.getResources().getString(R.string.app_name);
			assert(app_name.equalsIgnoreCase("Home Way"));
			
	DirectionsCaller caller=new DirectionsCaller(this);
	caller.loadDirections("29.9667,31.2500", "30.0500,31.3667");
		
	
		
	}

	@Override
	public void loadingFinish(Object parsedObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadingFail(Object parsedObject) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
