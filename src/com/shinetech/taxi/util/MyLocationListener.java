package com.shinetech.taxi.util;
import com.google.android.maps.GeoPoint;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


public class MyLocationListener implements LocationListener {
	private GeoPoint currentPoint;
	private Location currentLocation;
	private MapUtils mapUtils;
	
	public MyLocationListener()
	{
		mapUtils = new MapUtils();
	}

	public GeoPoint getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(GeoPoint currentPoint) {
		this.currentPoint = currentPoint;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
//		setCurrentLocation(location);
		currentPoint = mapUtils.getGeoPoint(location);
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	public void setCurrentLocation(Location location) {
		int currLatitude = (int) (location.getLatitude() * 1E6);
		int currLongitude = (int) (location.getLongitude() * 1E6);
		currentPoint = new GeoPoint(currLatitude, currLongitude);

		currentLocation = new Location("");
		currentLocation.setLatitude(currLatitude / 1e6);
		currentLocation.setLongitude(currLongitude / 1e6);
	}

}
