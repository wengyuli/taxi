package com.shinetech.taxi.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.map.test.R;

public class MyLocationOverlay extends Overlay {
	private GeoPoint currentPoint;
	private Context context;
	
	public MyLocationOverlay(Context con) {
		this.context = con;
	}

	public GeoPoint getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(GeoPoint currentPoint) {
		this.currentPoint = currentPoint;
	}
	
	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
			long when) {
		// TODO Auto-generated method stub
		super.draw(canvas, mapView, shadow, when);
		Paint paint = new Paint();
		Point screenPoint = new Point();
		mapView.getProjection().toPixels(currentPoint, screenPoint);
		paint.setStrokeWidth(1);
		paint.setARGB(255, 255, 0, 0);
		paint.setTextSize(30);
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.pin);
		canvas.drawBitmap(bitmap, screenPoint.x, screenPoint.y, paint);
		return false;
	}
}
