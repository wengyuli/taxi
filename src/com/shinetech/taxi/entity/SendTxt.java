package com.shinetech.taxi.entity;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

public class SendTxt extends SendBase {
	//the content to send;
	private String txt;
	private TextView textView;
	
	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public TextView getTextView() {
		return textView;
	}

	public void setTextView(TextView textView) {
		this.textView = textView;
	}

	public SendTxt(int type, String txt, Context context)
	{
		super(type, context);
		this.txt = txt;
		this.textView = new TextView(context);
//		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		textView.setText(txt);
		textView.setTextColor(Color.BLACK);
		textView.setBackgroundResource(SendBase.bg[type]);
		textView.setMaxWidth(300);
//		LayoutParams imgParams = new LayoutParams(60, 60);
//		imgParams.height = 50;
//		imgParams.width = 50;
		
		switch (type) {
		case SendBase.TYPE_OTHER:
			super.getTxtLayout().addView(super.getImage());
			super.getTxtLayout().addView(textView);
			break;
		case SendBase.TYPE_SELF:
			super.getTxtLayout().addView(textView);
			super.getTxtLayout().addView(super.getImage());
			break;
		default:
			break;
		}
	}
}
