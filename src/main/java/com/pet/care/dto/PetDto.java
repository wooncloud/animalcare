package com.pet.care.dto;

import java.sql.Date;

public class PetDto {

	private String id;
	private String user_email;
	private String name;
	private Date birth;
	private float weight;
	private String gender;
	private String type;
	private String typeinfo;
	private String aniregistnum;
	private String notification;
	private String profile;
	private String delflag;

	public PetDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PetDto(String id, String user_email, String name, Date birth, float weight, String gender, String type,
			String typeinfo, String aniregistnum, String notification, String profile, String delflag) {
		super();
		this.id = id;
		this.user_email = user_email;
		this.name = name;
		this.birth = birth;
		this.weight = weight;
		this.gender = gender;
		this.type = type;
		this.typeinfo = typeinfo;
		this.aniregistnum = aniregistnum;
		this.notification = notification;
		this.profile = profile;
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "PetDto [id=" + id + ", user_email=" + user_email + ", name=" + name + ", birth=" + birth + ", weight="
				+ weight + ", gender=" + gender + ", type=" + type + ", typeinfo=" + typeinfo + ", aniregistnum="
				+ aniregistnum + ", notification=" + notification + ", profile=" + profile + ", delflag=" + delflag
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = Date.valueOf(birth);
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeinfo() {
		return typeinfo;
	}

	public void setTypeinfo(String typeinfo) {
		this.typeinfo = typeinfo;
	}

	public String getAniregistnum() {
		return aniregistnum;
	}

	public void setAniregistnum(String aniregistnum) {
		this.aniregistnum = aniregistnum;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

}
