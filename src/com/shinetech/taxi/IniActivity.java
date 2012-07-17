package com.shinetech.taxi;

import com.map.test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IniActivity extends Activity {
	public static final boolean DEBUG = true;
	private Button registerBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.register);
		registerBtn = (Button)findViewById(R.id.register_btn);
		registerBtn.setOnClickListener(new RegisterClickListener());
		if(DEBUG) {
			Intent intent = new Intent();
        	intent.setClass(IniActivity.this, LoginActivity.class);
        	IniActivity.this.startActivity(intent);
		}
	}
	
	class RegisterClickListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent();
        	intent.setClass(IniActivity.this, LoginActivity.class);
        	IniActivity.this.startActivity(intent);
        	finish();
		}
		
	}
}
