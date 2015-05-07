package com.iyoutingche.android.ui;

import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfigeration;
import com.baidu.mapapi.map.MyLocationConfigeration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.iyoutingche.android.bean.Info;
import com.iyoutingche.android.http.Ip;
import com.iyoutingche.android.http.getMapInfo;
import com.iyoutingche.android.image.SmartImageView;
import com.iyoutingche.android.ui.MyOrientationListener.OnOrientationListener;
import com.iyoutingche.android.utils.ToastUtil;
import com.zhy.weixin6.ui.R;

public class TwoFragment extends Fragment
{
	private View view;
	MapView mMapView = null;  
    BaiduMap mBaiduMap = null;
    private Context context;
    
    //-------定位操作---------
    private static String id = Ip.getIp();
    private static final String href = "http://"+id+"/iyoutingche_android/servlet/getPcarMap?";
    private Handler handler = new Handler(){
    	public void handleMessage(Message msg) {
			addOverLay((List<Info>) msg.obj);
    	}
    };
    private LocationClient mLocationClient;
    private MyLocationListion myLocationListener;
    public String mProvince = "河南省"; // 获取省份信息
	public String mCity = "焦作市"; // 获取城市信息
	public String mDistrict = "山阳区"; // 获取区县信息
    private boolean flag = true;//判断是否为一次定位
    private BitmapDescriptor  bitmapdes;//定位点图标
    
    //-------方向传感器----------
    private MyOrientationListener mMyOrientationListener;
    private float mCurrentX;
    private LocationMode mLocationMode;//动态设置地图的模式
    
    //-------添加覆盖物操作--------
	private BitmapDescriptor mMarker;
	private RelativeLayout mMarkerly;//详细信息的布局
    
	public TwoFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
		SDKInitializer.initialize(this.getActivity().getApplicationContext());
		this.context = this.getActivity().getApplicationContext();
		view  = (View)inflater.inflate(R.layout.fragmenttwo, container, false);
		
		//添加覆盖物
		Button mapButton = (Button)view.findViewById(R.id.mapButton);
		Log.i("mapbutton", mapButton.toString());
		mapButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						List<Info> infos = new getMapInfo().getMap(href, mProvince);
						Message message = new Message();
						message.obj = infos;
						handler.sendMessage(message);
					}
				}).start();
			}
		});
		
		//获取地图控件引用 
        mMapView = (MapView)view.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        
      	//普通地图  
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);  
      	//卫星地图  
     // 	mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
      	//实时交通图
      	mBaiduMap.setTrafficEnabled(true);
      	
      	this.init();//设置地图的放大比例
      	this.initLocation();//初始化定位
      	this.initOrientationListener();//传感器初始化  
      	
      	/**
      	 * 地图的点击事件监听
      	 */
      	//监听点击覆盖物
      	mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				
				Bundle extraInfo = marker.getExtraInfo();
				Info info = (Info) extraInfo.getSerializable("info");
				SmartImageView img = (SmartImageView) mMarkerly.findViewById(R.id.map_info_img);
				TextView name = (TextView) mMarkerly.findViewById(R.id.map_info_name);
				TextView distance = (TextView) mMarkerly.findViewById(R.id.map_info_distance);
				TextView zan = (TextView) mMarkerly.findViewById(R.id.map_info_zan);
				img.setImageUrl(info.getimgMap());
				name.setText(info.getName());
				distance.setText(info.getDistance());
				zan.setText(info.getNumber()+"");
				
				InfoWindow infoWindow;
				TextView mTextView = new TextView(context);
				mTextView.setBackgroundResource(R.drawable.map_tips);
				mTextView.setPadding(30, 20, 30, 20);
				mTextView.setText(info.getName());
				
				final LatLng l = marker.getPosition();
				Point p = mBaiduMap.getProjection().toScreenLocation(l);
				p.y -= 47;//覆盖物详情显示的位置
				LatLng ll = mBaiduMap.getProjection().fromScreenLocation(p);
				infoWindow = new InfoWindow(mTextView, ll,
						new OnInfoWindowClickListener()
						{
							@Override
							public void onInfoWindowClick()
							{
								mBaiduMap.hideInfoWindow();
							}
						});
				mBaiduMap.showInfoWindow(infoWindow);
				
				mMarkerly.setVisibility(View.VISIBLE);//显示覆盖物详情
				
				return true;
			}
		});
      	
      	//监听点击其他位置
      	mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				return false;
			}
			
			@Override
			public void onMapClick(LatLng arg0) {
				mMarkerly.setVisibility(View.GONE);//隐藏覆盖物详情
				mBaiduMap.hideInfoWindow();//隐藏覆盖物详情
			}
		});
      	
        return view;
	}

	/**
	 * 设置地图的放大比例
	 */
    private void init() {
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
		mBaiduMap.setMapStatus(msu);
	}
	
    //地图初始化操作
	private void initLocation() {
		mLocationClient = new LocationClient(context);
    	myLocationListener = new MyLocationListion();
    	mLocationClient.registerLocationListener(myLocationListener);
    	LocationClientOption Option = new LocationClientOption();
    	Option.setCoorType("bd09ll");//设置坐标类型
    	Option.setIsNeedAddress(true);//需要显示位置信息必须设置为true
    	Option.setOpenGps(true);//打开手机上的GPS
    	Option.setScanSpan(1000);//设置请求的时间间隔
    	mLocationClient.setLocOption(Option);
    	bitmapdes=BitmapDescriptorFactory.fromResource(R.drawable.navi_map_gps_locked);//定位点图标
    	//罗盘模式
    	mLocationMode = LocationMode.COMPASS;
    	//跟随模式
    	mLocationMode = LocationMode.FOLLOWING;
    	this.initMarker();//添加覆盖物初始化
    	
	}
	
	//定位操作
    private class MyLocationListion implements BDLocationListener{

    	@Override
		public void onReceiveLocation(BDLocation location) {
			//转换数据
			MyLocationData data = new MyLocationData.Builder()
			.direction(mCurrentX)//设置方向传感器的方向信息
			.accuracy(location.getRadius())//半径
			.latitude(location.getLatitude())//经度
			.longitude(location.getLongitude())//纬度
			.build();
			mBaiduMap.setMyLocationData(data);
			
			//自定义的定位图标
		    MyLocationConfigeration config = new MyLocationConfigeration(LocationMode.COMPASS, true, bitmapdes);
			mBaiduMap.setMyLocationConfigeration(config);
			
			//定位成功后更新位置信息
			mProvince = location.getProvince();//更新省份信息
			mCity = location.getCity();//更新城市信息
			mDistrict = location.getDistrict();//获得街道信息
			
			if(flag){
				LatLng lat = new LatLng(location.getRadius(), location.getLatitude());
				MapStatusUpdate map = MapStatusUpdateFactory.newLatLng(lat);
				mBaiduMap.animateMapStatus(map);
				flag = false;
				Toast.makeText(context, location.getAddrStr(), Toast.LENGTH_LONG).show();
			}
		}
    }
    
    //传感器操作
  	private void initOrientationListener() {
  		//传感器事件初始化
      	mMyOrientationListener = new MyOrientationListener(context);
      	mMyOrientationListener.setOnOrientationListener(new OnOrientationListener() {
  		
      	//更新传感器参数
  		@Override
  		public void OnOrientationChanged(float x) {
  				mCurrentX = x;
  			}
  		});
  	}
    
    /**
     * 添加覆盖物操作
     */
  	//设置覆盖物图标
	@SuppressWarnings("static-access")
	private void initMarker() {
		mMarker = new BitmapDescriptorFactory().fromResource(R.drawable.map_maker);
		mMarkerly = (RelativeLayout) view.findViewById(R.id.map_maker_ly);
	}
	
	//添加覆盖物
	protected void addOverLay(List<Info> infos) {
		//mBaiduMap.clear();//清空地图覆盖物
		LatLng mLatLng = null;
		Marker marker = null;
		OverlayOptions option;
		for(Info info : infos){
			//设置经纬度
			mLatLng = new LatLng(info.getLongitude(), info.getLatitud());
			//设置图标
			option = new MarkerOptions().position(mLatLng).icon(mMarker).zIndex(5);
			marker = (Marker) mBaiduMap.addOverlay(option);
			Bundle ago = new Bundle();
			ago.putSerializable("info", info);
			marker.setExtraInfo(ago);
		}
		
		//将定位点移到该区域
		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(mLatLng);
		mBaiduMap.setMapStatus(msu);
		
	}
	
    @Override
    public void onStart() {
    	super.onStart();
    	//开启定位
    	mBaiduMap.setMyLocationEnabled(true);
    	if(!mLocationClient.isStarted()){
    		mLocationClient.start();
    		//开启方向传感器
    		mMyOrientationListener.start();
    	}
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	//停止定位
    	mBaiduMap.setMyLocationEnabled(false);
    	if(mLocationClient.isStarted()){
    		mLocationClient.stop();
    		//关闭方向传感器
    		mMyOrientationListener.stop();
    	}
    }
    
    @Override
	public void onDestroy() {  
        super.onDestroy();  
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
        mMapView.onDestroy();  
        Log.i("MapDestory", "--------------");
    }  
    
    @Override
	public void onResume() {  
        super.onResume();  
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume();  
        }
    
    @Override
    public void onPause() {
    	super.onPause();
    	mMapView.onPause();  
    }
    
}