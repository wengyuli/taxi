package com.shinetech.taxi;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.map.test.R;
import com.shinetech.taxi.overlay.ItemizedOverlayMyLocation;
import com.shinetech.taxi.overlay.TaxiItemizedOverlay;
import com.shinetech.taxi.util.GetJsonFromServer;
import com.shinetech.taxi.util.MyLocationListener;

public class MainMapActivity extends MapActivity {
	private MapView mapView;
	private MapController mapController;
	private LocationManager locationManager;
	private Location currentLocation;
	private GeoPoint currentPoint;
//	private SimulatePoint simulatePoint;
	private MyLocationListener myLocationListener;
	private List<Overlay> list;
	private Button listButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		btnFunction = (Button) findViewById(R.id.button_function);
//		btnPosition = (Button) findViewById(R.id.button_position);
//		lat = (TextView) findViewById(R.id.lat);
//		lon = (TextView) findViewById(R.id.lon);
//		btnList = (Button) findViewById(R.id.button_list);
		listButton = (Button)findViewById(R.id.button_list);
		listButton.setOnClickListener(new ListClickListener());
		mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);
		mapView.setStreetView(true);
		mapController = mapView.getController();
		mapController.setZoom(17);
		myLocationListener = new MyLocationListener();
		getLastLocation();
		currentPoint = myLocationListener.getCurrentPoint();
		if(currentPoint == null) {
			Toast.makeText(this, "对不起，暂时无法获取位置信息", Toast.LENGTH_SHORT).show();
		} else {
			GetJsonFromServer getJsonFromServer = new GetJsonFromServer();
			currentPoint = getJsonFromServer.getServerJsonDataWithNoType(currentPoint);
			animateToCurrentLocation();
	        showMyLocation();
	        showTaxi();
		}
		
	}

	private void showMyLocation() {
//		MyLocationOverlay myLocationOverlay = new MyLocationOverlay(this);
//		myLocationOverlay.setCurrentPoint(currentPoint);
//		list = mapView.getOverlays();
//		list.add(myLocationOverlay);
		Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
		ItemizedOverlayMyLocation itemizedOverlay = new ItemizedOverlayMyLocation(drawable, this);
		itemizedOverlay.initiateOverlays(currentPoint);
		list = mapView.getOverlays();
		list.add(itemizedOverlay);
	}

	private void showTaxi() {
		Drawable drawable = this.getResources().getDrawable(
				R.drawable.medium_taxi);
		TaxiItemizedOverlay itemizedoverlay = new TaxiItemizedOverlay(drawable,
				this);
		itemizedoverlay.initiateOverlays(currentPoint, 500);
		for (int i = 0; i < itemizedoverlay.size(); i++) {
			list.add(itemizedoverlay);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(getBestProvider(), 1000, 1,
				myLocationListener);
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(myLocationListener);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void getLastLocation() {
		String provider = getBestProvider();
		currentLocation = locationManager.getLastKnownLocation(provider);
		if (currentLocation != null) {
			myLocationListener.setCurrentLocation(currentLocation);
		} else {
			provider = locationManager.NETWORK_PROVIDER;
			currentLocation = locationManager.getLastKnownLocation(provider);
			myLocationListener.setCurrentLocation(currentLocation);
			Toast.makeText(this, "Location not yet acquired", Toast.LENGTH_LONG)
					.show();
		}
	}

	public void animateToCurrentLocation() {
		if (currentPoint != null) {
			mapController.animateTo(currentPoint);
		}
	}

	public String getBestProvider() {
		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String bestProvider = locationManager.getBestProvider(criteria, true);
//		bestProvider = locationManager.NETWORK_PROVIDER;
		return bestProvider;
	}

	class ListClickListener implements OnClickListener
	{
		public void onClick(View v) {
			Intent intent = new Intent();
        	intent.setClass(MainMapActivity.this, ContactListActivity.class);
        	MainMapActivity.this.startActivity(intent);
		}
	}
}