package com.shinetech.taxi.overlay;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.map.test.R;
import com.shinetech.taxi.CommunicateActivity;
import com.shinetech.taxi.util.TaxiDao;

public class TaxiItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private TaxiDao dao;
	private Context mContext;
	private Dialog dialog;
	
	public TaxiItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
//		super(defaultMarker);
		mContext = context;
		dao = new TaxiDao();
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	
	public void initiateOverlays(GeoPoint currPoint, int raidus)
	{
		List<GeoPoint> points;
		points = dao.getRequiredPoints(currPoint, raidus);
		for(GeoPoint point : points)
		{
			OverlayItem overlayitem = new OverlayItem(point, "ԥA6900E", point.toString());
			addOverlay(overlayitem);
		}
	}
	
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = mOverlays.get(index);
	  dialog = new Dialog(mContext, R.style.MyDialog);
	  dialog.setContentView(R.layout.dialog);
	  dialog.setTitle(item.getTitle());
	  TextView titleTextView = (TextView)dialog.findViewById(R.id.dialog_title_text);
	  titleTextView.setText(item.getTitle());
	  Button communicateBtn = (Button)dialog.findViewById(R.id.dialog_button_talk);
	  communicateBtn.setOnClickListener(new CommunicateListener());
	  Button callBtn = (Button)dialog.findViewById(R.id.dialog_button_call);
	  callBtn.setOnClickListener(new CallListener());
	  dialog.show();
	  return true;
	}
	
	@Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow)
    {
        if(!shadow)
        {
            super.draw(canvas, mapView, false);
        }
    }
	
	class CommunicateListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent = new Intent();
        	intent.setClass(mContext, CommunicateActivity.class);
        	mContext.startActivity(intent);
        	dialog.dismiss();
		}
	}
	
	class CallListener implements OnClickListener {
		public void onClick(View v) {
			Uri uri = Uri.parse("tel:3456543");
			Intent intentCall = new Intent(Intent.ACTION_CALL, uri);
			mContext.startActivity(intentCall);
			dialog.dismiss();
		}
	}
}
