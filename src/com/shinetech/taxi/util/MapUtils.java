package com.shinetech.taxi.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.maps.GeoPoint;

public class MapUtils {
	public GeoPoint getGeoPoint(Location location) {
		int currLatitude = (int) (location.getLatitude() * 1E6);
		int currLongitude = (int) (location.getLongitude() * 1E6);
		GeoPoint geoPoint = new GeoPoint(currLatitude, currLongitude);
		return geoPoint;
	}

	public boolean isGPSProviderAvailable(Context con) {
//		try {
//			ConnectivityManager connectivity = (ConnectivityManager) con
//			.getSystemService(Context.CONNECTIVITY_SERVICE);
//			if (connectivity != null) {
//				// 获取网络连接管理的对象
//				NetworkInfo info = connectivity.getActiveNetworkInfo();
//				if (info == null || !info.isAvailable()) {
//					return false;
//				} else {
//					return true;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
		LocationManager lm = (LocationManager)con.getSystemService(Context.LOCATION_SERVICE);
		return lm.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);
	}
	
	public boolean isNetworkProviderAvailable(Context con)
	{
		LocationManager lm = (LocationManager)con.getSystemService(Context.LOCATION_SERVICE);
		return lm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
	}
}
