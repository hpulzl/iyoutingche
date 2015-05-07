package com.iyoutingche.android.bean;

public class Pcar_Space {
	private String space_code;
	private String depot_code;
	private String space_place;
	private String space_coord;
	private String space_guard;
	
	
	public Pcar_Space() {
	}
	public Pcar_Space(String spaceCode, String depotCode, String spacePlace,
			String spaceCoord, String spaceGuard) {
		super();
		space_code = spaceCode;
		depot_code = depotCode;
		space_place = spacePlace;
		space_coord = spaceCoord;
		space_guard = spaceGuard;
	}
	public String getSpace_code() {
		return space_code;
	}
	public void setSpace_code(String spaceCode) {
		space_code = spaceCode;
	}
	public String getDepot_code() {
		return depot_code;
	}
	public void setDepot_code(String depotCode) {
		depot_code = depotCode;
	}
	public String getSpace_place() {
		return space_place;
	}
	public void setSpace_place(String spacePlace) {
		space_place = spacePlace;
	}
	public String getSpace_coord() {
		return space_coord;
	}
	public void setSpace_coord(String spaceCoord) {
		space_coord = spaceCoord;
	}
	public String getSpace_guard() {
		return space_guard;
	}
	public void setSpace_guard(String spaceGuard) {
		space_guard = spaceGuard;
	}
}
