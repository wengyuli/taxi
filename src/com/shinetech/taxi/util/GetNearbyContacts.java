package com.shinetech.taxi.util;

import com.map.test.R;

public class GetNearbyContacts {

	private String[] nameList;
	private String[] msgList;
	private int[] photoList;
	
	public String[] simulateName()
	{
		nameList = new String[]{"oawcdn", "空格+9", "巴拉拉", "onve", "sky", "34tf", "重复", "casgaer", "cszgeryg"};
		return nameList;
	}
	
	public String[] simulateMsg()
	{
		msgList = new String[]{"", "", "", "", "", "", "", "", ""};
		return msgList;
	}
	
	public int[] simulatePhoto()
	{
		photoList = new int[]{R.drawable.head1, R.drawable.head2, R.drawable.head3, R.drawable.head4, R.drawable.head5, R.drawable.head6, R.drawable.head7, R.drawable.head8, R.drawable.head9};
		return photoList;
	}
}
