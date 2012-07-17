package com.shinetech.taxi.overlay;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class ItemizedOverlayMyLocation extends ItemizedOverlay<OverlayItem> {
	
	private ArrayList<OverlayItem> mOverlayItems = new ArrayList<OverlayItem>();

	public ItemizedOverlayMyLocation(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return mOverlayItems.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlayItems.size();
	}
	
	public void addOverlay(OverlayItem item)
	{
		mOverlayItems.add(item);
		populate();
	}
	
	public void initiateOverlays(GeoPoint point)
	{
		OverlayItem item = new OverlayItem(point, "", "");
		addOverlay(item);
	}
	
	@Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow)
    {
        if(!shadow)
        {
            super.draw(canvas, mapView, false);
        }
    }

}
