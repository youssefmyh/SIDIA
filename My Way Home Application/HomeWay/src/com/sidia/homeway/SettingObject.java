package com.sidia.homeway;

import com.sidia.engine.ApplicationConstant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/*
 * Settings Object 
 * */
public class SettingObject {
	private Context context = null;

	private static SettingObject settingObject;

	private String latitude;
	private String longitude;

	private String enginekey;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getEnginekey() {
		return enginekey;
	}

	public void setEnginekey(String enginekey) {
		this.enginekey = enginekey;
	}
	/*
	 * @Brief initialize  settings
	 * */

	public static SettingObject getInstance(Context context) {
		if(context==null)
			return null;
		if (settingObject == null) {
			settingObject = new SettingObject(context);

		}
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		settingObject.latitude = sharedPrefs.getString(
				ApplicationConstant.LATITUDE, null);
		settingObject.longitude = sharedPrefs.getString(
				ApplicationConstant.LONGITUDE, null);
		settingObject.enginekey = sharedPrefs.getString(
				ApplicationConstant.ENGINEKEY, "0");
		return settingObject;
	}
	
	/*
	 * @Brief save settings
	 * */

	public void save() {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this.context);

		Editor editor = sharedPrefs.edit();
		editor.putString(ApplicationConstant.LATITUDE, latitude);
		editor.putString(ApplicationConstant.LONGITUDE, longitude);
		editor.commit();

	}

	private SettingObject(Context context) {
		this.context = context;
	}

}
