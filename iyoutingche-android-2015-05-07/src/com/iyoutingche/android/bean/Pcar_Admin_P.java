package com.iyoutingche.android.bean;

public class Pcar_Admin_P extends Pcar_Admin{

	private String pcar_p_bank;
	
	public String getPcar_p_bank() {
		return pcar_p_bank;
	}

	public void setPcar_p_bank(String pcar_p_bank) {
		this.pcar_p_bank = pcar_p_bank;
	}
	public Pcar_Admin_P(String admin_id, String admin_name, String admin_sex,
			String admin_email, String admin_phone, String admin_password) {
		super(admin_id, admin_name, admin_sex, admin_email, admin_phone, admin_password);
		// TODO Auto-generated constructor stub
	}

	public Pcar_Admin_P(String admin_id, String admin_name, String admin_sex,
			String admin_email, String admin_phone, String admin_password,
			String pcar_p_bank) {
		super(admin_id, admin_name, admin_sex, admin_email, admin_phone,
				admin_password);
		this.pcar_p_bank = pcar_p_bank;
	}
	
}
