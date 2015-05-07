package com.iyoutingche.android.ui;

import com.iyoutingche.android.qr_codescan.Activity_shayisha;
import com.zhy.weixin6.ui.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ThreeFragment extends Fragment implements OnClickListener
{
	private View view;
	private LinearLayout lukuang; 
	private LinearLayout shaoyishao; 
	private LinearLayout yijian; 
	private LinearLayout weizhang;
	private LinearLayout money; 
	private LinearLayout jilu; 
	
	public ThreeFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		view  = (View)inflater.inflate(R.layout.fragmentthree, container, false);
		init();
		return view;
	}

	private void init() {
		lukuang = (LinearLayout)view.findViewById(R.id.lukuang);
		shaoyishao = (LinearLayout)view.findViewById(R.id.shaoyishao);
		yijian = (LinearLayout)view.findViewById(R.id.yijian);
		weizhang = (LinearLayout)view.findViewById(R.id.weizhang);
		money = (LinearLayout)view.findViewById(R.id.money);
		jilu = (LinearLayout)view.findViewById(R.id.jilu);
		
		lukuang.setOnClickListener(this);
		shaoyishao.setOnClickListener(this);
		yijian.setOnClickListener(this);
		weizhang.setOnClickListener(this);
		money.setOnClickListener(this);
		jilu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lukuang:
			Toast.makeText(getActivity(), "路况信息", Toast.LENGTH_SHORT).show();
			break;
		case R.id.shaoyishao:
			Toast.makeText(getActivity(), "扫一扫", Toast.LENGTH_SHORT).show();
			this.startActivity(new Intent().setClass(getActivity(), Activity_shayisha.class));
			break;
		case R.id.yijian:
			Toast.makeText(getActivity(), "一键定位", Toast.LENGTH_SHORT).show();
			break;
		case R.id.weizhang:
			Toast.makeText(getActivity(), "违章查询", Toast.LENGTH_SHORT).show();
			break;
		case R.id.money:
			Toast.makeText(getActivity(), "钱包", Toast.LENGTH_SHORT).show();
			break;
		case R.id.jilu:
			Toast.makeText(getActivity(), "停车记录", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
