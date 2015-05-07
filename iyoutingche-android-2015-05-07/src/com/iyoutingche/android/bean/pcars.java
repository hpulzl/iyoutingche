package com.iyoutingche.android.bean;

public class pcars {
	private String name;// 停车场名字
	private String number;// 空闲数量
	private String from;// 停车场位置
	private String Image;// 停车场图片
	private String details;// 停车场详情
	private int pcar_count; // 得到停车位的总数
	private int avialable; // 得到剩余的停车位数

	
	/**
	 * ---{"depot_recommend":"有30个停车位，欢迎预约停车。","depot_name":"青龙峡停车场","depot_place":"焦作市博爱县","Pcar_count":0,"avialable":30}
	 */
	public pcars(String name, String from, String details, int pcar_count,
			int avialable) {
		super();
		this.name = name;
		this.from = from;
		this.details = details;
		this.pcar_count = pcar_count;
		this.avialable = avialable;
	}
	public int getPcar_count() {
		return pcar_count;
	}

	public void setPcar_count(int pcar_count) {
		this.pcar_count = pcar_count;
	}

	public int getAvialable() {
		return avialable;
	}

	public void setAvialable(int avialable) {
		this.avialable = avialable;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		details = details;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public pcars(String name, String number, String from, String details,
			String img) {
		this.name = name;
		this.from = from;
		this.Image = img;
		this.details = details;
		this.number = number;
	}

	public pcars() {
		// TODO Auto-generated constructor stub
	}
	
}
