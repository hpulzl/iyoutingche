package com.iyoutingche.android.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.iyoutingche.android.http.Ip;
import com.iyoutingche.android.http.Login;
import com.iyoutingche.android.qq.login;
import com.iyoutingche.android.ui.MainActivity;
import com.zhy.weixin6.ui.R;

public class LoginActivity extends Activity implements OnClickListener{

	private TextView user_name;
	private TextView user_password;
	private Button submit;
	private Button add;
	private ImageButton qq;
	
	public String email;
	public String password;
	private static String id = Ip.getIp();
	public static final String href="http://"+id+"/iyoutingche_android//servlet/UserLogin?";
	public boolean flag = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);//去除自带标题
		setContentView(R.layout.activity_login);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_login_title);//设置自定义标题
		init();
		user_name.requestFocus();

	}
	
	private void init() {
		user_name = (TextView) findViewById(R.id.user_email);
		user_password = (TextView) findViewById(R.id.user_password);
		submit = (Button)findViewById(R.id.submit);
		add = (Button)findViewById(R.id.add);
		qq = (ImageButton)findViewById(R.id.qq);
		
		submit.setOnClickListener(this);
		add.setOnClickListener(this);
		qq.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit:
			email = user_name.getText().toString();
			password = user_password.getText().toString();
			if(email.equals("null")||email.length()==0){
				Toast.makeText(LoginActivity.this, "您还没有输入邮箱",Toast.LENGTH_SHORT).show();
				user_name.setText("");
				user_name.requestFocus();//获得输入焦点
			}else{
				if(password.equals("null")||password.length()==0){
					Toast.makeText(LoginActivity.this, "密码不能为空",Toast.LENGTH_SHORT).show();
					user_password.setText("");
					user_password.requestFocus();//获得输入焦点
				}else{
					String em = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
					Pattern p = Pattern.compile(em);
					Matcher m = p.matcher(email);
					if (m.matches()) {
						
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								flag = Login.getSave(email, password, href);
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										if(flag){
											Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
											SharedPreferences preferences = getSharedPreferences("config",
													MODE_PRIVATE);
											Editor editor = preferences.edit();
											editor.putString("username", email);
											editor.commit();
											LoginActivity.this.startActivity(new Intent().setClass(LoginActivity.this, MainActivity.class));
											LoginActivity.this.finish();
										}else{
											user_password.setText("");
											user_password.requestFocus();
											Toast.makeText(LoginActivity.this, "邮箱或密码输入有误", Toast.LENGTH_SHORT).show();
										}
									}
								});
							}
						}).start();
					}else{
						Toast.makeText(LoginActivity.this, "请正确输入邮箱",Toast.LENGTH_SHORT).show();
						user_name.setText("");
						user_name.requestFocus();
					}
				}
			}
			break;
		case R.id.add:
			Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();
			LoginActivity.this.startActivity(new Intent().setClass(LoginActivity.this, AddUserActivity.class));
			break;
		case R.id.qq:
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, login.class);
			LoginActivity.this.startActivity(intent);
			finish();
			break;	
		default:
			break;
		}
	}
	
}
