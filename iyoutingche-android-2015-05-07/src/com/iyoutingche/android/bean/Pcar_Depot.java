package com.iyoutingche.android.bean;

public class Pcar_Depot {
	private String amin_p_id;     //这个是什么东西啊？
	private String depot_code;   //停车场二维码
	private String depot_province;	//省
	private String depot_city;	//市
	private String depot_region;	//区	
	private String depot_name;	//停车场名
	private String depot_place;	//位置描述
	private String depot_coord;	//坐标
	private String depot_recommend;	//停车场的描述
	private String depot_img;	//停车场图片
	
	public Pcar_Depot(){
		
	}
	
	public Pcar_Depot(String depot_code, String depot_province,
			String depot_city, String depot_region, String depot_name,
			String depot_place, String depot_coord, String depot_recommend) {
		super();
		this.depot_code = depot_code;
		this.depot_province = depot_province;
		this.depot_city = depot_city;
		this.depot_region = depot_region;
		this.depot_name = depot_name;
		this.depot_place = depot_place;
		this.depot_coord = depot_coord;
		this.depot_recommend = depot_recommend;
	}

	public Pcar_Depot(String aminPId, String depotCode, String depotProvince,
			String depotCity, String depotRegion, String depotName,
			String depotPlace, String depotCoord, String depotRecommend,
			String depotImg) {
		super();
		amin_p_id = aminPId;
		depot_code = depotCode;
		depot_province = depotProvince;
		depot_city = depotCity;
		depot_region = depotRegion;
		depot_name = depotName;
		depot_place = depotPlace;
		depot_coord = depotCoord;
		depot_recommend = depotRecommend;
		depot_img = depotImg;
	}
	public String getAmin_p_id() {
		return amin_p_id;
	}
	public void setAmin_p_id(String aminPId) {
		amin_p_id = aminPId;
	}
	public String getDepot_code() {
		return depot_code;
	}
	public void setDepot_code(String depotCode) {
		depot_code = depotCode;
	}
	public String getDepot_province() {
		return depot_province;
	}
	public void setDepot_province(String depotProvince) {
		depot_province = depotProvince;
	}
	public String getDepot_city() {
		return depot_city;
	}
	public void setDepot_city(String depotCity) {
		depot_city = depotCity;
	}
	public String getDepot_region() {
		return depot_region;
	}
	public void setDepot_region(String depotRegion) {
		depot_region = depotRegion;
	}
	public String getDepot_name() {
		return depot_name;
	}
	public void setDepot_name(String depotName) {
		depot_name = depotName;
	}
	public String getDepot_place() {
		return depot_place;
	}
	public void setDepot_place(String depotPlace) {
		depot_place = depotPlace;
	}
	public String getDepot_coord() {
		return depot_coord;
	}
	public void setDepot_coord(String depotCoord) {
		depot_coord = depotCoord;
	}
	public String getDepot_recommend() {
		return depot_recommend;
	}
	public void setDepot_recommend(String depotRecommend) {
		depot_recommend = depotRecommend;
	}
	public String getDepot_img() {
		return depot_img;
	}
	public void setDepot_img(String depotImg) {
		depot_img = depotImg;
	}
	
	
}
