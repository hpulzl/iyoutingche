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

public class SaoYiSaoGetDepotInfo {
	public static  pcars getDate(String href,String depot_code){
		try {
			href = href+"depot_code="+depot_code;
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
		return new pcars();
	}

	private static pcars pathJSONxml(InputStream input) {
		pcars pcar =null;
		/**
		 * js.put("avialable", avialable);
				js.put("Pcar_count", Pcar_count);
				js.put("depot_name", depot.getDepot_name()); 
				js.put("depot_place", depot.getDepot_place());  //位置
				js.put("depot_recommend", depot.getDepot_recommend());  //描述
		 */
		try {
			byte [] imgall = StreamTool.read(input);
			String json = new String(imgall);
			JSONObject js = new JSONObject(json);
			pcar = new pcars(js.getString("depot_name"), js.getString("depot_place"), js.getString("depot_recommend"),Integer.parseInt(js.getString("avialable")),Integer.parseInt( js.getString("Pcar_count")));
			Log.i("SyiS",js.getString("depot_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.i("SaoYiSao", pcar.getFrom());
		return pcar;
	}
}
