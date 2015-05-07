package com.iyoutingche.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.iyoutingche.android.bean.pcars;
import com.iyoutingche.android.utils.StreamTool;
/*
 *加载数据
 */
public class DataScroll {
	
	public List<pcars> getDate(int offset, int maxResult, String href, String province){
		try {
			href = href+"offset="+offset+"&maxResult="+maxResult+"&province="+URLEncoder.encode(province);
			URL url = new URL(href);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5000);
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() == 200){
				InputStream input = conn.getInputStream();
				return pathJSONxml(input);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<pcars>();
	}

	private List<pcars> pathJSONxml(InputStream input) {
		List<pcars> list = new ArrayList<pcars>();
		try {
			byte [] imgall = StreamTool.read(input);
			String json = new String(imgall);
			JSONArray array = new JSONArray(json);
			for(int i = 0; i<array.length(); i++){
				JSONObject object = array.getJSONObject(i);
				pcars pcar = new pcars(object.getString("name"), object.getString("number"), object.getString("from"), object.getString("details"), object.getString("img"));
				list.add(pcar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
