package com.shinetech.taxi.util;

import java.util.List;

import com.google.android.maps.GeoPoint;

public class TaxiDao {
	private SimulatePoint simulatePoint;
	public TaxiDao()
	{
		simulatePoint = new SimulatePoint();
	}
	public List<GeoPoint> getAllPoints(GeoPoint currPoint)
	{
		List<GeoPoint> allPoints = simulatePoint.getPoints(currPoint);
		return allPoints;
	}
	public List<GeoPoint> getRequiredPoints(GeoPoint currPoint, int raidus)
	{
		List<GeoPoint> requiredPoints = simulatePoint.getRequiredPoints(getAllPoints(currPoint), currPoint, raidus);
		return requiredPoints;
	}
}
