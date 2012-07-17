package com.shinetech.taxi;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.map.test.R;
import com.shinetech.taxi.util.GetLatestContacts;
import com.shinetech.taxi.util.GetNearbyContacts;

public class ContactListActivity extends Activity {

	private ListView latestListView, nearbyListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.contact_list);
		latestListView = (ListView)findViewById(R.id.latest_list);
		nearbyListView = (ListView)findViewById(R.id.nearby_list);
		RelativeLayout rLayout = (RelativeLayout)nearbyListView.findViewById(R.id.item_relativeLayout);
		rLayout.setBackgroundColor(Color.argb(0, 172, 172, 172));
		showList();
	}

	private void showList() {
		ArrayList<HashMap<String, Object>> latestList = new ArrayList<HashMap<String,Object>>();
	    HashMap<String, Object> map ;
	    GetLatestContacts getLatestContacts = new GetLatestContacts();
	    int[] resId = getLatestContacts.simulatePhoto();
	    String[] names = getLatestContacts.simulateName();
	    String[] msgs = getLatestContacts.simulateMsg();
	    for(int i = 0; i < getLatestContacts.simulateName().length; i++)
	    {
	    	String name = names[i];
	    	String msg = msgs[i];
	    	int photoRes = resId[i];
	    	map = new HashMap<String, Object>();
	    	map.put("name", name);
	    	map.put("msg", msg);
	    	map.put("photo", photoRes);
	    	latestList.add(map);
	    }
		SimpleAdapter listAdapter = new SimpleAdapter(this, latestList, R.layout.list_item, 
        		new String[] {"name","msg","photo"}, 
        		new int[] {R.id.user_name,R.id.last_message,R.id.user_head});        
		
        latestListView.setAdapter(listAdapter);
        
        ArrayList<HashMap<String, Object>> nearbyList = new ArrayList<HashMap<String,Object>>();
        GetNearbyContacts getNearbyContacts = new GetNearbyContacts();
	    names = getNearbyContacts.simulateName();
	    msgs = getNearbyContacts.simulateMsg();
	    resId = getNearbyContacts.simulatePhoto();
	    for(int i = 0; i < getNearbyContacts.simulateName().length; i++)
	    {
	    	String name = names[i];
	    	String msg = msgs[i];
	    	int photoRes = resId[i];
	    	map = new HashMap<String, Object>();
	    	map.put("name", name);
	    	map.put("msg", msg);
	    	map.put("photo", photoRes);
	    	nearbyList.add(map);
	    }
		listAdapter = new SimpleAdapter(this, nearbyList, R.layout.list_item, 
        		new String[] {"name","msg","photo"}, 
        		new int[] {R.id.user_name,R.id.last_message,R.id.user_head});        
		
        nearbyListView.setAdapter(listAdapter);
	}
}
