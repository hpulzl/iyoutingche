package com.iyoutingche.android.ui;

import com.iyoutingche.android.activity.LoginActivity;
import com.zhy.weixin6.ui.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class FourFragment extends Fragment implements OnClickListener
{
	private View view;
	private TextView change;
	private TableRow img;
	private TableRow username;
	private TableRow nicheng;
	private TableRow sex;
	private TableRow year;
	private TableRow from;
	private TableRow writing;
	
	public FourFragment()
	{
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		view  = (View)inflater.inflate(R.layout.fragmentfour, container, false);
		
		change = (TextView) view.findViewById(R.id.change);
	
		change.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences preferences = getActivity().getSharedPreferences("config", 0);
				Editor editor = preferences.edit();
				editor.putString("username", null);
				editor.commit();
				startActivity(new Intent().setClass(getActivity().getApplicationContext(), LoginActivity.class));
				getActivity().finish();
			}
		});
		
		init();
		
		return view;
		
	}

	private void init() {
		img = (TableRow) view.findViewById(R.id.img);
		username = (TableRow) view.findViewById(R.id.username);
		nicheng = (TableRow) view.findViewById(R.id.nincheng);
		sex = (TableRow) view.findViewById(R.id.sex);
		year = (TableRow) view.findViewById(R.id.year);
		from = (TableRow) view.findViewById(R.id.from);
		writing = (TableRow) view.findViewById(R.id.writing);
		
		img.setOnClickListener(this);
		username.setOnClickListener(this);
		nicheng.setOnClickListener(this);
		sex.setOnClickListener(this);
		year.setOnClickListener(this);
		from.setOnClickListener(this);
		writing.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img:
			Toast.makeText(getActivity(), "头像", Toast.LENGTH_SHORT).show();
			break;
		case R.id.username:
			Toast.makeText(getActivity(), "001", Toast.LENGTH_SHORT).show();
			break;
		case R.id.nincheng:
			Toast.makeText(getActivity(), "i优停车", Toast.LENGTH_SHORT).show();
			break;
		case R.id.sex:
			Toast.makeText(getActivity(), "男", Toast.LENGTH_SHORT).show();
			break;
		case R.id.year:
			Toast.makeText(getActivity(), "22", Toast.LENGTH_SHORT).show();
			break;
		case R.id.from:
			Toast.makeText(getActivity(), "河南省焦作市山阳区河南理工大学", Toast.LENGTH_SHORT).show();
			break;
		case R.id.writing:
			Toast.makeText(getActivity(), "90后", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
