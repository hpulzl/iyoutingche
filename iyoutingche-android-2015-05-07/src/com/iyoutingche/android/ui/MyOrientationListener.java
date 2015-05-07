package com.iyoutingche.android.ui;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

/**
 * 方向传感器监听
 * @author Administrator
 *
 */

public class MyOrientationListener implements SensorEventListener{

	private SensorManager mSensorManager;//方向传感器管理者
	private Context mContext;//获得一个上下文对象
	private Sensor mSensor;//获得一个传感器对象
	
	private float mLastx = 0;//方向传感器x轴坐标
	
	public MyOrientationListener(Context context){
		this.mContext = context;
	}
	
	@SuppressWarnings("deprecation")
	public void start(){
		//开始监听
		mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);//获得传感器管理权
		if(mSensorManager != null){
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);//获得一个方向传感器对象
		}else{
			Toast.makeText(mContext, "您的设备没有安装传感器", Toast.LENGTH_LONG).show();
		}
		if(mSensor != null){
			mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
		}else{
			Toast.makeText(mContext, "您的设备不支持方向传感器", Toast.LENGTH_LONG).show();
		}
	}
	
	public void stop(){
		//结束监听
		mSensorManager.unregisterListener(this);
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		//精度放生改变监听
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		//方向发生改变监听
		if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
			float x = event.values[SensorManager.DATA_X];
			if(Math.abs(x - mLastx)>1.0){
				if(mOrientationListener != null){
					mOrientationListener.OnOrientationChanged(x);
				}
			}
			mLastx = x;
		}
	}
	
	private OnOrientationListener mOrientationListener;
	
	public void setOnOrientationListener(OnOrientationListener mOrientationListener) {
		this.mOrientationListener = mOrientationListener;
	}

	public interface OnOrientationListener{
		void OnOrientationChanged(float x);
	}

}
