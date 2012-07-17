package com.shinetech.taxi.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;

public class GetJsonFromServer {

	public GeoPoint getServerJsonDataWithNoType(GeoPoint point) {
		int res = 0;
		GeoPoint offsetPoint = new GeoPoint(34787004, 113666621);
		HttpClient client = new DefaultHttpClient();
		//超时请求
//    	client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
//    	//读取超时
//        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
		StringBuilder str = new StringBuilder();
		String url = "http://www.wengyuli.com/location.php?lat=" + point.getLatitudeE6()/1e6
				+ "&lon=" + point.getLongitudeE6()/1e6;
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse httpRes = client.execute(httpGet);
//			httpRes = client.execute(httpGet);
			res = httpRes.getStatusLine().getStatusCode();
			if (res == 200) {
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(httpRes.getEntity().getContent()));
				for (String s = buffer.readLine(); s != null; s = buffer
						.readLine()) {
					str.append(s);
				}
				// String out =
				// EntityUtils.toString(httpRes.getEntity().getContent(),
				// "UTF-8");
				// StringBuilder sb = new StringBuilder()
				try {
					// JSONObject json = new
					// JSONObject(str.toString()).getJSONObject("content");
					JSONObject json = new JSONObject(str.toString());
					String latString = json.getString("lat");
//					int id = json.getInt("id");
					String lonString = json.getString("lon");
//					editText.setText("Title:" + title + " ID:" + id + " Value:"
//							+ value);
					double offset_lat = Double.parseDouble(latString);
					double offset_lon = Double.parseDouble(lonString);
					int offset_lat_int = (int) (offset_lat * 1E6);
					int offset_lon_int = (int) (offset_lon * 1E6);
					offsetPoint = new GeoPoint(offset_lat_int, offset_lon_int);
//					lat.setText(String.valueOf(offset_lat));
//					lon.setText(String.valueOf(offset_lon));
				} catch (JSONException e) {
					// buffer.close();
					e.printStackTrace();
				}
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offsetPoint;
	}
}
