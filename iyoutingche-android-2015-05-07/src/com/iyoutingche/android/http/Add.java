package com.iyoutingche.android.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Add {
	/**emailView, passwordView, href_Add**/
	/*public boolean addUser(String email, String password, String name, String sex, String phone, String href){
		return false;
	}*/
	public boolean addUser(String email,String password,String href){
		boolean flag = false;
//		System.out.println(sex);
		HttpURLConnection conn = null;
		try {
			URL url = new URL(href);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			
			//post请求参数
//			String data = "email="+URLEncoder.encode(email)+"&password="+URLEncoder.encode(password)+"&name="+URLEncoder.encode(name)+"&sex="+URLEncoder.encode(sex)+"&phone="+URLEncoder.encode(phone);
			String data = "email="+URLEncoder.encode(email)+"&password="+URLEncoder.encode(password)+"&name="+URLEncoder.encode("")+"&sex="+URLEncoder.encode("")+"&phone="+URLEncoder.encode("");
			OutputStream out = conn.getOutputStream();
			out.write(data.getBytes());
			out.flush();
			out.close();
			
			int respon = conn.getResponseCode();
			if(respon == 200){
				InputStream is = conn.getInputStream();
				String stu = getStringFromInputStream(is);
				if(stu.equals("AddYes")){
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
