package com.iyoutingche.android.activity;

import com.iyoutingche.android.ui.MainActivity;
import com.iyoutingche.android.viewpage.ViewAll;
import com.zhy.weixin6.ui.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class WellComeActivity extends Activity {

	private String View_content = "No.1";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_come);
        
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				//判断用户是否为第一次打开该应用
		        SharedPreferences sp = getSharedPreferences(View_content, Context.MODE_PRIVATE);
		        //判断用户是否是第一次登录
		        SharedPreferences sp1 = getSharedPreferences("config", Context.MODE_PRIVATE);
		        //从文件中获取保存的数据
		        String No = sp.getString("No", "yes");
		        try {
					Thread.sleep(1000*3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    	Intent intent = new Intent();
		        if(("yes").equals(No)){
		        	Editor editor = sp.edit();
		        	editor.clear();
		        	editor.putString("No", "No");
		        	editor.commit();
					intent.setClass(WellComeActivity.this, ViewAll.class);
				}else{
					String name=sp1.getString("username", null);
					/**
					 * 是否已经登录过
					 */
					if(name==null){
						intent.setClass(WellComeActivity.this, LoginActivity.class);
					}else{
						intent.setClass(WellComeActivity.this, MainActivity.class);
					}
						/*Intent intent = new Intent();
						intent.setClass(WellComeActivity.this, LoginActivity.class);
						WellComeActivity.this.startActivity(intent);
						WellComeActivity.this.finish();*/
					}
		        WellComeActivity.this.startActivity(intent);
				WellComeActivity.this.finish();
		        }
		}).start();
    }
}

