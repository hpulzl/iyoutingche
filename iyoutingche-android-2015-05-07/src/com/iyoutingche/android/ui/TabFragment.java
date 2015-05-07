package com.iyoutingche.android.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.iyoutingche.android.bean.myBaseAdapter;
import com.iyoutingche.android.bean.pcars;
import com.iyoutingche.android.http.DataScroll;
import com.iyoutingche.android.http.Ip;
import com.zhy.weixin6.ui.R;

public class TabFragment extends Fragment implements IXListViewListener{
	
	private View view;
	private XListView mListView;
	public static List<pcars> data = new ArrayList<pcars>();
	private List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
	private myBaseAdapter madapter;
	private Handler handler;
	private static String id = Ip.getIp();
	public String href = "http://"+id+"/iyoutingche_android/servlet/getPcars?";
	private static int ItemCount=1;//当前第几页
	private static final int number=5;//每页显示数据量
	
	//获得位置信息
	public TwoFragment twoFragment = new TwoFragment();
	public String mProvince = "河南省"; // 获取省份信息
	public String mCity = "焦作市"; // 获取城市信息
	public String mDistrict = "山阳区"; // 获取区县信息
	public String getmProvince() {
		if(twoFragment.mProvince!=null){
			mProvince = twoFragment.mProvince;
		}
		return mProvince;
	}
	public String getmCity() {
		if(twoFragment.mCity!=null){
			mCity = twoFragment.mCity;
		}
		return mCity;
	}
	public String getmDistrict() {
		if(twoFragment.mDistrict!=null){
			mDistrict = twoFragment.mDistrict;
		}
		return mDistrict;
	}
	
	//创建视图
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view  = (View)inflater.inflate(R.layout.fragmentone, container, false);
		return view;
	}
	
	//对布局进行修改
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mListView = (XListView) view.findViewById(R.id.xListView);
		mListView.setOnItemClickListener(new onItemClickListener());
		mListView.setPullLoadEnable(true);
		data.clear();
		list.clear();
		Thread thread1 = new Thread(new MyRunable1(0, number));
		thread1.start();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");// 设置更新时间的格式
		mListView.setRefreshTime(format.format(date));// 设置更新时间
		mListView.setXListViewListener(this);
		
		handler = new Handler(){

			@SuppressWarnings("unchecked")
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(msg.what==1){
					list.clear();
					data.addAll((List<pcars>)msg.obj);
					list = getListMap(data);
					madapter = new myBaseAdapter(getActivity(), list);
					mListView.setAdapter(madapter);
					onLoad();//加载完成
				}else{
					if(msg.what==2){
						list.clear();
						data.addAll((List<pcars>)msg.obj);
						list = getListMap(data);
						madapter.notifyDataSetChanged();
						onLoad();//加载完成
					}
				}
			}
			
		};
		
	}
	
	private void onLoad() {
		
		mListView.stopRefresh();//刷新停止
		mListView.stopLoadMore();//加载停止
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");// 设置更新时间的格式
		mListView.setRefreshTime(format.format(date));// 设置更新时间
	}
		
	//下滑刷新
	@Override
	public void onRefresh() {
		data.clear();
		Thread thread1 = new Thread(new MyRunable1(0, number));
		thread1.start();
		ItemCount = 1;
	}

	//上滑加载
	@Override
	public void onLoadMore() {
		Thread thread2 = new Thread(new MyRunable2(ItemCount, number));
		thread2.start();
		ItemCount =ItemCount + 1;
	}
	
	//第一次加载数据时开启线程
	class MyRunable1 implements Runnable {
		private int Item;
		private int num;
		public MyRunable1(int Item, int num) {
			this.Item = Item;
			this.num = num;
		}
		public void run() {
			List<pcars> list = new ArrayList<pcars>();
			list = new DataScroll().getDate(Item, num, href, mProvince);
			Message msg = new Message();
			msg.what = 1;
			msg.obj = list;
			handler.sendMessage(msg);
			}
		}
		
	//第二次加载数据时开启线程
	class MyRunable2 implements Runnable {
		private int Item;
		private int num;
		public MyRunable2(int Item, int num) {
			this.Item = Item;
			this.num = num;
		}
		public void run() {
			List<pcars> result = new ArrayList<pcars>();
			result = new DataScroll().getDate(Item, num, href, mProvince);
			Message msg = new Message();
			msg.what = 2;
			msg.obj = result; 
			handler.sendMessage(msg);
		}
	}
	
	public List<HashMap<String, String>> getListMap(List<pcars> dataList){
		HashMap<String, String> map = null;
		for (pcars pcar : dataList) {
			map = new HashMap<String, String>();
			map.put("name", pcar.getName());
			map.put("number", pcar.getNumber());
			map.put("from", pcar.getFrom());
			map.put("img", pcar.getImage());
			list.add(map);
		}
		return list;
	} 
	
	//用户点击事件监听
	class onItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("name", data.get(position-1).getName());
			bundle.putString("number", data.get(position-1).getNumber());
			bundle.putString("from", data.get(position-1).getFrom());
			bundle.putString("details", data.get(position-1).getDetails());
			bundle.putString("img", data.get(position-1).getImage());
			intent.putExtras(bundle);
			intent.setClass(getActivity(), PcarActivity.class);
			getActivity().startActivity(intent);
		}
	}
}