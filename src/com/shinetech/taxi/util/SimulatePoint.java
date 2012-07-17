package com.shinetech.taxi.util;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

public class SimulatePoint {
	private List<GeoPoint> pointList = new ArrayList<GeoPoint>();
	private CalculateDistance calculate = new CalculateDistance();

	private static double[] generate(double min, double max, int count) {
		if (min > max) {
			throw new IllegalArgumentException("参数min必须小于max...");
		}
		// 存储要生成的随机数
		double[] target = new double[count];
		for (int i = 0; i < target.length; i++) {
			target[i] =Math.random()*(max-min) +min;
		}
		return target;
	}
	
	public List<GeoPoint> getPoints(GeoPoint currPoint)
	{
		if(pointList.size() == 0)
		{
			double curr_lat = (double)(currPoint.getLatitudeE6())/1e6;
			double curr_lon = (double)(currPoint.getLongitudeE6())/1e6;
			double[] around = calculate.getAround(curr_lat, curr_lon, 1000);
			double[] lat = generate(around[0], around[2], 20);
			double[] lon = generate(around[1], around[3], 20);
			for(int i =0; i<lat.length; i++)
			{
				pointList.add(new GeoPoint((int)(lat[i]*1e6), (int)(lon[i]*1e6)));
			}
		}
		return pointList;
	}
	
	public List<GeoPoint> getRequiredPoints(List<GeoPoint> list, GeoPoint currPoint, double raidus)
	{
		List<GeoPoint> requiredPoints = new ArrayList<GeoPoint>();
		for(GeoPoint point : list)
		{
			double lat = point.getLatitudeE6() / 1e6;
			double lon = point.getLongitudeE6() / 1e6;
			double currLat = (double)(currPoint.getLatitudeE6())/1e6;
			double currLon = (double)(currPoint.getLongitudeE6())/1e6;
			double distance = calculate.getDistance(lat, lon, currLat, currLon);
			if(distance <= raidus)
			{
				requiredPoints.add(point);
			}
		}
		return requiredPoints;
	}
}
