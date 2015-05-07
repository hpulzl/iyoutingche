package com.iyoutingche.android.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.iyoutingche.android.utils.StreamTool;

import android.util.Log;

public class Login {
	
	public static boolean getSave(String userEmail, String password, String href){
		boolean flag = false;
		
		HttpURLConnection conn ;
		
		try{
			String Email = URLEncoder.encode(userEmail, "utf-8"); 
			String pwd = URLEncoder.encode(password, "utf-8");
			URL url = new URL(href+"email="+Email+"&password="+pwd);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			if(conn.getResponseCode()==200){
				InputStream in = conn.getInputStream();
				String Login_news = new String(StreamTool.read(in), "GBK");//接收服务器端的数据时，防止出现中文乱码。
				if(Login_news.equals("yes")){
					flag = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return flag;
	}
}
