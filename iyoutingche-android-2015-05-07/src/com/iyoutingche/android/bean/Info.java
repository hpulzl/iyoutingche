package com.iyoutingche.android.bean;

import java.io.Serializable;

public class Info implements Serializable{
	private static final long serialVersionUID = 1865909840855955119L;
	private double latitud;
	private double longitude;
	private String imgMap;
	private String name;
	private String distance;
	private int number;
	
//	static {
//		infos.add(new Info(113.271771, 35.194112, R.drawable.map_a1, "河南理工大學明德樓",
//				"距离209米", 1456));
//		infos.add(new Info(113.273729, 35.192379, R.drawable.map_a2, "河南理工大學老體育場",
//				"距离897米", 456));
//		infos.add(new Info(113.281275, 35.196759, R.drawable.map_a3, "河南理工大學新體育場",
//				"距离249米", 1456));
//		infos.add(new Info(113.27952, 35.19371, R.drawable.map_a4, "河南理工大學計算機學院",
//				"距离679米", 1456));
//		
//	}
	
	public Info(double latitud, double longitude, String imgMap, String name,
			String distance, int number) {
		super();
		this.latitud = latitud;
		this.longitude = longitude;
		this.imgMap = imgMap;
		this.name = name;
		this.distance = distance;
		this.number = number;
	}
	
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getimgMap() {
		return imgMap;
	}
	public void setImgId(String imgMap) {
		this.imgMap = imgMap;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public int getNumber() {
		return number;
	}
	public void setZan(int number) {
		this.number = number;
	}
	
}
