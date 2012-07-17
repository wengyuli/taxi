package com.shinetech.taxi;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.map.test.R;
import com.shinetech.taxi.entity.SendBase;
import com.shinetech.taxi.entity.SendRec;
import com.shinetech.taxi.entity.SendTxt;

public class CommunicateActivity extends Activity {

//	private LinearLayout topLinearLayout = new LinearLayout(this);
	private LinearLayout conversation;
	private EditText messageText;
	private Button btnSend, btnRecord;
	private ScrollView scrollView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.communicate);
		btnSend = (Button)findViewById(R.id.send_message);
		btnSend.setOnClickListener(new SendMessageListener());
		btnRecord = (Button)findViewById(R.id.record);
//		btnRecord.setOnKeyListener(new RecordKeyListener());
		btnRecord.setOnTouchListener(new RecordTouchListener());
		scrollView = (ScrollView)findViewById(R.id.scroll);
		messageText = (EditText)findViewById(R.id.write_content);
		conversation = (LinearLayout)findViewById(R.id.conversation);
		loadConversation();
//		this.setContentView(layoutResID)
	}
	
	private void loadConversation()
	{
		ArrayList<SendTxt> list = new ArrayList<SendTxt>();
		SendTxt st = new SendTxt(SendBase.TYPE_OTHER, "你好！", this);
		st.getImage().setImageResource(R.drawable.hujintao);
		list.add(st);
		st = new SendTxt(SendBase.TYPE_SELF, "你好", this);
		st.getImage().setImageResource(R.drawable.self);
		list.add(st);
		st = new SendTxt(SendBase.TYPE_OTHER, "您需要打车么？", this);
		st.getImage().setImageResource(R.drawable.hujintao);
		list.add(st);
		st = new SendTxt(SendBase.TYPE_SELF, "是的，我现在在纬五路经四路路口。", this);
		st.getImage().setImageResource(R.drawable.self);
		list.add(st);
		for(SendTxt sendTxt: list) {
			conversation.addView(sendTxt.getTxtLayout());
		}
		SendBase.scrollToBottom(scrollView, conversation);
	}
	
	class SendMessageListener implements OnClickListener {

		public void onClick(View v) {
			if(!messageText.getText().toString().equals(""))
			{
				SendTxt st = new SendTxt(SendBase.TYPE_SELF,
						messageText.getText().toString(), CommunicateActivity.this);
				st.getImage().setImageResource(R.drawable.self);
				conversation.addView(st.getTxtLayout());
//				InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//				m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				SendBase.scrollToBottom(scrollView, conversation);
				messageText.setText("");
			} else {
				Toast.makeText(CommunicateActivity.this, "发送内容不能为空", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
	class RecordTouchListener implements OnTouchListener {

		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case KeyEvent.ACTION_DOWN:
				Toast.makeText(CommunicateActivity.this, "开始录音.松开按键发送录音", Toast.LENGTH_SHORT).show();				
				break;
			case KeyEvent.ACTION_UP:
//				Toast.makeText(CommunicateActivity.this, "结束录音", Toast.LENGTH_SHORT).show();	
				SendRec st = new SendRec(SendBase.TYPE_SELF, 20, CommunicateActivity.this);
				st.getImage().setImageResource(R.drawable.self);
				conversation.addView(st.getTxtLayout());
//				InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//				m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				SendBase.scrollToBottom(scrollView, conversation);
				break;
			default:
				break;
			}
			return false;
		}
		
	}
}
