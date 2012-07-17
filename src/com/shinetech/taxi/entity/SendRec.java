package com.shinetech.taxi.entity;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.map.test.R;

public class SendRec extends SendBase {
	private int length;
	private ImageView record;
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public ImageView getRecord() {
		return record;
	}
	public void setRecord(ImageView record) {
		this.record = record;
	}

	public SendRec(int type, int length, Context context) {
		super(type, context);
		int showLength = 8;
		if(length>28)
		{
			showLength = 28;
		} else if (length < 8) {
			showLength =8;			
		} else {
			showLength = length;
		}
		LayoutParams imgParams = new LayoutParams(showLength*10, 60);
		record = new ImageView(context);
		record.setLayoutParams(imgParams);
		record.setBackgroundResource(SendBase.bg[type]);
//		record.setMinimumWidth(60);
//		record.setMaxWidth(280);
		record.setImageResource(R.drawable.record);
		switch (type) {
		case SendBase.TYPE_OTHER:
			super.getTxtLayout().addView(super.getImage());
			super.getTxtLayout().addView(record);
			break;
		case SendBase.TYPE_SELF:
			super.getTxtLayout().addView(record);
			super.getTxtLayout().addView(super.getImage());
			break;
		default:
			break;
		}
	}
	
	
}
