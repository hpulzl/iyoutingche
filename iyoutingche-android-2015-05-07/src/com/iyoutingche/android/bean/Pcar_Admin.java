package com.iyoutingche.android.bean;

public class Pcar_Admin {
	private String admin_id;
	private String admin_name;
	private String admin_sex;
	private String admin_email;
	private String admin_phone;
	private String admin_password;
	
	public void Pcar_Admin(){
	}
	public Pcar_Admin(String admin_id, String admin_name, String admin_sex,
			String admin_email, String admin_phone, String admin_password) {
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.admin_sex = admin_sex;
		this.admin_email = admin_email;
		this.admin_phone = admin_phone;
		this.admin_password = admin_password;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_sex() {
		return admin_sex;
	}

	public void setAdmin_sex(String admin_sex) {
		this.admin_sex = admin_sex;
	}

	public String getAdmin_email() {
		return admin_email;
	}

	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}

	public String getAdmin_phone() {
		return admin_phone;
	}

	public void setAdmin_phone(String admin_phone) {
		this.admin_phone = admin_phone;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	
}
