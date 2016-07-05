package com.sidia.engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Toast;

public class Utilites {

	/*
	 * @param Android.content.Context Brief return the Unique identifier of
	 * Device
	 */

	public static final String getUDID(Context context) {
		String udid = Settings.Secure.getString(context.getContentResolver(),
				Settings.Secure.ANDROID_ID);
		return udid;
	}

	/*
	 * @param Object Request need to parsed //Request object need to be parsed
	 * Brief return the string request based on parameters inside the Class
	 */

	public static String getGoogleRouteRequest(Object request) {
		try {
			StringBuilder requestBuilder = new StringBuilder();
			if (request == null)
				return null;
			Class<? extends Object> requestClass = request.getClass();
			Field[] valueObjFields = requestClass.getDeclaredFields();

			// compare values now
			for (int i = 0; i < valueObjFields.length; i++) {
				String fieldName = valueObjFields[i].getName();
				valueObjFields[i].setAccessible(true);
				Object newObj = valueObjFields[i].get(request);

				if (fieldName != null && newObj != null) {
					requestBuilder.append(fieldName + "=" + newObj.toString());
					if (i != valueObjFields.length - 1) {
						requestBuilder.append("&");
					}
				}

			}

			return requestBuilder.toString();
		} catch (Exception ee) {
			ee.printStackTrace();
			return null;
		}
	}

	/*
	 * show Toast
	 */
	public static void showToast(final String text, final Activity context) {
		context.runOnUiThread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
				toast.setDuration(120000);
				toast.setGravity(Gravity.CENTER_VERTICAL
						| Gravity.CENTER_HORIZONTAL, 0, 0);
				toast.show();

			}
		});
	}

	/*
	 * @ @param String encoded polygon encoded string from Google Api
	 * 
	 * brief return a locations List from encoded string
	 */

	public static List<LatLng> decodePoly(String encoded) {

		try {
			if (encoded == null)
				return null;
			List<LatLng> poly = new ArrayList<LatLng>();
			int index = 0, len = encoded.length();
			int lat = 0, lng = 0;

			while (index < len) {
				int b, shift = 0, result = 0;
				do {
					b = encoded.charAt(index++) - 63;
					result |= (b & 0x1f) << shift;
					shift += 5;
				} while (b >= 0x20);
				int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
				lat += dlat;

				shift = 0;
				result = 0;
				do {
					b = encoded.charAt(index++) - 63;
					result |= (b & 0x1f) << shift;
					shift += 5;
				} while (b >= 0x20);
				int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
				lng += dlng;

				LatLng p = new LatLng((((double) lat / 1E5)),
						(((double) lng / 1E5)));
				poly.add(p);
			}

			return poly;
		} catch (StringIndexOutOfBoundsException exception) {
			return null;
		}

	}

	/*
	 * Double parser
	 */

	public static double parseDouble(String value) {
		try {
			return Double.parseDouble(value);

		} catch (NumberFormatException e) {
			// TODO: handle exception
			return 0;
		}

	}

	/*
	 * @param Context context
	 * 
	 * @param String targetPackage need to be checked brief Check if package
	 * exist
	 */

	public static boolean isPackageExisted(Context context, String targetPackage) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(targetPackage,
					PackageManager.GET_META_DATA);
			if (info != null)
				;
		} catch (NameNotFoundException e) {
			return false;
		}
		return true;
	}

}
