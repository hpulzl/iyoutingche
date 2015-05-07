package com.iyoutingche.android.ui;

import com.iyoutingche.android.image.SmartImageView;
import com.zhy.weixin6.ui.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PcarActivity extends Activity {
	private SmartImageView img;
	private TextView name;
	private TextView number;
	private TextView from;
	private TextView details;
	private Button yuyue;
	
	private String pcar_img;
	private String pcar_name;
	private String pcar_number;
	private String pcar_from;
	private String pcar_details;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pcar);
		init();
		getPcar();
		setContent();
		setPcar();
	}
	private void init() {
		img = (SmartImageView) findViewById(R.id.pcar_img);
		name = (TextView) findViewById(R.id.pcar_name);
		number = (TextView) findViewById(R.id.pcar_number);
		from = (TextView) findViewById(R.id.pcar_from);
		details = (TextView) findViewById(R.id.pcar_details);
		yuyue = (Button) findViewById(R.id.pcar_yuyue);
	}
	private void getPcar() {
		Bundle bundle = this.getIntent().getExtras();
		pcar_name = bundle.getString("name");
		pcar_number = bundle.getString("number");
		pcar_from = bundle.getString("from");
		pcar_img = bundle.getString("img");
		pcar_details = bundle.getString("details");
	}
	private void setContent() {
		name.setText("停车场："+pcar_name);
		number.setText("剩余空车位："+pcar_number);
		from.setText("停车场位置："+pcar_from);
		details.setText("停车详情："+pcar_details);
		img.setImageUrl(pcar_img);
	}
	private void setPcar() {
		yuyue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(PcarActivity.this, "预约成功", Toast.LENGTH_SHORT).show();
			}
		});	
	}
}
