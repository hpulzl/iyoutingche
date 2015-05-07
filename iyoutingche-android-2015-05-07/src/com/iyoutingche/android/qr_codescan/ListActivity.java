package com.iyoutingche.android.qr_codescan;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.iyoutingche.android.bean.pcars;
import com.iyoutingche.android.http.Ip;
import com.iyoutingche.android.http.SaoYiSaoGetDepotInfo;
import com.zhy.weixin6.ui.R;

public class ListActivity extends Activity{
	private String park;
	public pcars pcar;
	public static final String href = "http://"+Ip.getIp()+"/iyoutingche_android/servlet/GetDepotSer?";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_erweima_list);
		init();
	}
	public void init(){
		Intent intent = getIntent();
		park = intent.getStringExtra("result");
		Log.i("ListActivity",park);
			new Thread(new Runnable() {
				@Override
				public void run() {
					pcar = SaoYiSaoGetDepotInfo.getDate(href, park);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if(pcar  != null){
								List<pcars> list = new ArrayList<pcars>();
								list.add(pcar);
								Log.i("List", pcar.getFrom());
								pcarsAdapter adapter = new pcarsAdapter(ListActivity.this, R.layout.erweima_item, list);
								ListView listView = (ListView) findViewById(R.id.list);
								listView.setAdapter(adapter);
							}
						} 
					});
				}
			}).start();
	}	
}
