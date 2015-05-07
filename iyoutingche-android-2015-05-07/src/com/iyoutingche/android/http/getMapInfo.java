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

import com.iyoutingche.android.bean.Info;
import com.iyoutingche.android.bean.pcars;
import com.iyoutingche.android.utils.StreamTool;

public class getMapInfo {
	public static List<Info> infos = new ArrayList<Info>();
	
	public List<Info> getMap(String href, String province){
		
		try {
			href = href+"province="+URLEncoder.encode(province);
			URL url = new URL(href);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5000);
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			Log.i("conn--------", conn.getResponseCode()+"");
			if(conn.getResponseCode() == 200){
				InputStream input = conn.getInputStream();
				return pathJSONxml(input);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return infos;
	}
	
	private List<Info> pathJSONxml(InputStream input) {
		List<Info> list = new ArrayList<Info>();
		Log.i("Come", "Come Here");
		try {
			byte [] mapAll = StreamTool.read(input);
			String json = new String(mapAll);
			JSONArray array = new JSONArray(json);
			Log.i("Json", array.toString());
			for(int i = 0; i<array.length(); i++){
				JSONObject object = array.getJSONObject(i);
				String x[] = object.getString("Latitude_Longitude").split(",");
				Log.i("Split1", x[0]);
				Info info = new Info(Double.parseDouble(x[0]), Double.parseDouble(x[1]),
						object.getString("img"), object.getString("name"), object.getString("from"), Integer.parseInt(object.getString("number")));
				Log.i("Split2", x[1]);
				list.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DataScroll:"+list.size());
		return list;
	}
	
}
