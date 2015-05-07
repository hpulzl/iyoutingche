package com.iyoutingche.android.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.iyoutingche.android.http.Add;
import com.iyoutingche.android.http.Email;
import com.iyoutingche.android.http.Ip;
import com.iyoutingche.android.ui.MainActivity;
import com.iyoutingche.android.utils.ToastUtil;
import com.zhy.weixin6.ui.R;

public class AddUserActivity extends Activity{
	
	private static String id = Ip.getIp();
	public static final String href_Email = "http://"+id+"/iyoutingche_android/servlet/UserAddFindEmail?";
	public static final String href_Add = "http://"+id+"/iyoutingche_android/servlet/UserAdd?";
	public EditText add_Email;
	public ImageView backto;
	public EditText add_Password;
	public Button add_User;
	
	public String emailView="";
	public String passwordView="";
	
	
	boolean flag = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);//去除自带标题
		setContentView(R.layout.activity_add);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_add_title);//设置自定义标题
		
		init();//得到引用
		
		add_Email.requestFocus();
		Log.i("AddUser", backto.toString());
		/**
		 * 返回登录界面
		 */
		backto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AddUserActivity.this,LoginActivity.class);
				startActivity(intent);
			}
		});
		
		/**
		 * 添加用户。
		 */
		add_User.setOnClickListener(new MyOnClickListener());
		
	}
	
	public void init() {
		add_Email = (EditText) findViewById(R.id.add_email);
		add_Password = (EditText) findViewById(R.id.add_password);
		backto = (ImageView)findViewById(R.id.backto);
		add_User = (Button) findViewById(R.id.add_user);
	}
	
	public void getView(){
		emailView = add_Email.getText().toString();
		passwordView = add_Password.getText().toString();
	}
	
	class myradiobuttonlistener implements OnClickListener{

		@Override
		public void onClick(View v) {
		}
		
	}

	class MyOnClickListener implements OnClickListener{
		
		String pho = "1[3|5|7|8|][0-9]{9}";
		Pattern phon = Pattern.compile(pho);
		Matcher mPhone ;
	
		@Override
		public void onClick(View v) {
			
			String email = add_Email.getText().toString();
			String password = add_Password.getText().toString();
			/*String pwd = add_Pwd.getText().toString();
			String phone = add_Phone.getText().toString();*/
			
			if("null".equals(email)||email.length()==0){
				add_Email.requestFocus();
			}else{
				String em = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
				Pattern p = Pattern.compile(em);
				Matcher m = p.matcher(email);
				if (!m.matches()) {
					add_Email.setText("");
					add_Email.requestFocus();
					/*Toast.makeText(AddUserActivity.this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();*/
					ToastUtil.showToast(AddUserActivity.this, "邮箱格式不正确");
				}else{
					if("null".equals(password)||password.length()==0){
						add_Password.requestFocus();
					}else{
									//判断邮箱是否已被注册
									new Thread(new Runnable() {
										
										@Override
										public void run() {
											String email = add_Email.getText().toString();
											Email ems = new Email();
											flag = ems.findEmail(email, href_Email);
											runOnUiThread(new Runnable() {
											
												@Override
												public void run() {
													if(flag){
														add_Email.setText("");
														Toast.makeText(AddUserActivity.this, "此邮箱已注册", Toast.LENGTH_SHORT).show();
														add_Email.requestFocus();
													}else{
														getView();
														new Thread(new Runnable() {
															
															@Override
															public void run() {
//																flag = new Add().addUser(emailView, passwordView, nameView, sexView, phoneView, href_Add);
																flag = new Add().addUser(emailView, passwordView, href_Add);
																runOnUiThread(new Runnable() {
																	
																	@Override
																	public void run() {
																		if(flag){
																			Toast.makeText(AddUserActivity.this, "注册成功，欢迎您的使用。", Toast.LENGTH_SHORT).show();
																			AddUserActivity.this.startActivity(new Intent().setClass(AddUserActivity.this, MainActivity.class));
																			finish();
																		}else{
																			Toast.makeText(AddUserActivity.this, "程序猿都去约会，请稍后重试。", Toast.LENGTH_SHORT).show();
																		}
																	}
																});
															}
														}).start();
													}
												}
											});
										}
									}).start();
								}
							}
					}
				}
	}
	
}
