package com.iyoutingche.android.qr_codescan;

import java.util.List;

import com.iyoutingche.android.bean.pcars;
import com.iyoutingche.android.image.SmartImageView;
import com.zhy.weixin6.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class pcarsAdapter extends ArrayAdapter<pcars> {
	
	private SmartImageView img;   //图片
	private TextView name;    //姓名
	private TextView number;  //
	private TextView from;  //
	private TextView details; //详情
	private Button yuyue; 
	
	private String pcar_img;
	private String pcar_name;
	private String pcar_number;
	private String pcar_from;
	private String pcar_details;
	private int resourceId;
	public pcarsAdapter(Context context, int textViewResourceId,List<pcars> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}
	/**
	 * ---{"depot_recommend":"有30个停车位，欢迎预约停车。","depot_name":"青龙峡停车场","depot_place":"焦作市博爱县","Pcar_count":0,"avialable":30}
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		pcars pcar = getItem(position); // 获取当前项的pcar实例
		View view;
		if (convertView == null) {
		view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		} else {
		view = convertView;
		}
		name = (TextView)view.findViewById(R.id.pcar_name);
		from = (TextView)view.findViewById(R.id.pcar_from);
		details = (TextView)view.findViewById(R.id.pcar_details);
		number = (TextView)view.findViewById(R.id.pcar_number);
		/**
		 * (String name, String from, String details, int pcar_count,
			int avialable)
		 */
		name.setText(pcar.getName());
		from.setText(pcar.getFrom());
		details.setText(pcar.getDetails());
		number.setText(pcar.getNumber());
	return view;
	}
	
}
