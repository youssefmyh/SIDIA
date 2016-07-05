package com.sidia.homeway;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

/*
 * SettingsActivity 
 * */
public class SettingsActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Display the fragment as the main content.
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new SettingsFragment()).commit();

	}
}
/*
 * SettingsFragment 
 * */

class SettingsFragment extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.settings);
	}
}
