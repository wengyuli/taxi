package com.shinetech.taxi.entity;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.map.test.R;

public class SendBase {

//	private static final int SELF = 0;
//	private static final int OTHER = 1;
	public static final int TYPE_OTHER = 0;
	public static final int TYPE_SELF = 1;
	private int type;
	private LinearLayout txtLayout;
	private ImageView image;
	protected static final int[] bg = {R.drawable.txt_back, R.drawable.txt_self};
	
	public SendBase(int type, Context context)
	{
		this.type = type;
		this.txtLayout = new LinearLayout(context);
		this.image = new ImageView(context);
		txtLayout.setOrientation(LinearLayout.HORIZONTAL);
		txtLayout.setPadding(10, 10, 10, 0);
		LayoutParams imgParams = new LayoutParams(60, 60);
		image.setLayoutParams(imgParams);
		switch (type) {
		case SendBase.TYPE_OTHER:
			break;
		case SendBase.TYPE_SELF:
			txtLayout.setGravity(Gravity.RIGHT);
			break;
		default:
			break;
		}
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public LinearLayout getTxtLayout() {
		return txtLayout;
	}
	public void setTxtLayout(LinearLayout txtLayout) {
		this.txtLayout = txtLayout;
	}
	public ImageView getImage() {
		return image;
	}
	public void setImage(ImageView image) {
		this.image = image;
	}
	public static void scrollToBottom(final View scroll, final View inner) {

		Handler mHandler = new Handler();

		mHandler.post(new Runnable() {
			public void run() {
				if (scroll == null || inner == null) {
					return;
				}
				int offset = inner.getMeasuredHeight() - scroll.getHeight();
				if (offset < 0) {
					offset = 0;
				}
				scroll.scrollTo(0, offset);
			}
		});
	}
}
