package com.sidia.homeway;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sidia.engine.ApplicationConstant;

/*
 * RoadActivity used to show all availabe roads 
 * */
public class RoadActivity extends ListActivity {

	/*
	 * @Brief initialize Road Activity with Road List 
	 * */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent=getIntent();
		if(intent!=null&&intent.getExtras()!=null){
		ArrayList<String> roadList = intent.getExtras().getStringArrayList(ApplicationConstant.ROAD_KEY);
		
		setContentView(R.layout.activity_road);
		ArrayAdapter<String> simpleArrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, roadList);
		setListAdapter(simpleArrayAdapter);
		}
	}

	/*
	 * @Brief on Road Select 
	 * */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent resultIntent = new Intent();
		resultIntent.putExtra(ApplicationConstant.SELECTED_ROAD, position);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}

}
