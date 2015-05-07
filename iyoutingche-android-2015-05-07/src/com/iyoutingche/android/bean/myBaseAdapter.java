package com.iyoutingche.android.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.iyoutingche.android.image.SmartImageView;
import com.zhy.weixin6.ui.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class myBaseAdapter extends BaseAdapter {

	private LayoutInflater mInflater;

	private List<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>();
	private Context mContext;
	private String url = null;// 存放图片的URL地址

	//data:从网络获的数据
	//resource:布局文件的id
	//from:map中的view值
	//to:布局文件中的id
	public myBaseAdapter(Context context, List<HashMap<String, String>> data) {
		this.mContext = context;
		this.data = data;
	}

	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("ViewHolder")
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHodler hodler = null;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.listview_item, parent, false);
			hodler = new ViewHodler();
			hodler.pcar_name = (TextView) convertView
					.findViewById(R.id.item_name);
			hodler.pcar_number = (TextView) convertView
					.findViewById(R.id.item_number);
			hodler.pcar_from = (TextView) convertView
					.findViewById(R.id.item_from);
			hodler.pcar_img = (SmartImageView) convertView
					.findViewById(R.id.item_img);
			hodler.pcar_name.setText("停车场："+data.get(position).get("name").toString());
			hodler.pcar_number.setText("剩余空闲车位："+data.get(position).get("number").toString());
			hodler.pcar_from.setText("地址："+data.get(position).get("from").toString());
			url = data.get(position).get("img").toString();
			if (url != null)
				hodler.pcar_img.setImageUrl(url);
				convertView.setTag(hodler);
		} else {
			hodler = (ViewHodler) convertView.getTag();
		}

		return convertView;
	}
	
	class ViewHodler {
		TextView pcar_name;// 停车场名称
		TextView pcar_number;// 剩余空车位
		TextView pcar_from;// 停车场位置
		SmartImageView pcar_img;// 停车场的图片
	}
	
}
