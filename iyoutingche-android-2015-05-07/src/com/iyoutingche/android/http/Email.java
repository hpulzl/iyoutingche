package com.iyoutingche.android.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Email {
	public boolean findEmail(String email, String href){
		boolean flag = false;
		
		HttpURLConnection conn = null;
		try {
			URL url = new URL(href);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			
			//post请求参数
			String data = "email="+email;
			OutputStream out = conn.getOutputStream();
			out.write(data.getBytes());
			out.flush();
			out.close();
			
			int respon = conn.getResponseCode();
			if(respon == 200){
				InputStream is = conn.getInputStream();
				String stu = getStringFromInputStream(is);
				if(stu.equals("YesLive")){
					flag = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.disconnect();
			}
		}
		
		return flag;
	}
	
	private static String getStringFromInputStream(InputStream is) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while((len = is.read(buffer)) != -1){
			baos.write(buffer, 0, len);
		}
		is.close();
		
		String html = new String(baos.toByteArray(), "GBK");//接收服务器端的数据时，防止出现中文乱码。
		
		baos.close();
		return html;
		
	}
}
