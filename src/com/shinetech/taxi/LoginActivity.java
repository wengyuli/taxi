package com.shinetech.taxi;

import com.map.test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Button loginBtn;
	private EditText nameEditText, pwdEditText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.login);
		loginBtn = (Button)findViewById(R.id.login_btn);
		loginBtn.setOnClickListener(new LoginClickListener());
		nameEditText = (EditText)findViewById(R.id.name_edit);
		pwdEditText = (EditText)findViewById(R.id.pwd_edit);
		if(IniActivity.DEBUG) {
			Intent intent = new Intent();
        	intent.setClass(LoginActivity.this, LoginActivity.class);
        	LoginActivity.this.startActivity(intent);
		}
	}
	
	class LoginClickListener implements OnClickListener {
		public void onClick(View v) {
			String name = nameEditText.getText().toString().trim();
			String pwd = pwdEditText.getText().toString().trim();
			if(name.equals("admin") && pwd.equals("123"))
			{
				Intent intent = new Intent();
	        	intent.setClass(LoginActivity.this, MainMapActivity.class);
	        	LoginActivity.this.startActivity(intent);
	        	finish();
			} else {
				Toast.makeText(LoginActivity.this, "用户名或密码错误,请重新输入", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
