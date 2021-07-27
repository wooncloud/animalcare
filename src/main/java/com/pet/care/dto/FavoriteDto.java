package com.pet.care.dto;

import java.io.Serializable;

public class FavoriteDto implements Serializable {

	private static final long serialVersionUID = -8289248511557020482L;

	private String seq;
	private String name;
	private String address1;
	private String address2;
	private String tel;
	private String opentime;
	private String pettype;

	public FavoriteDto() {
	}

	public FavoriteDto(String seq, String name, String address1, String address2, String tel, String opentime,
			String pettype) {
		this.seq = seq;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.tel = tel;
		this.opentime = opentime;
		this.pettype = pettype;
	}

	@Override
	public String toString() {
		return "FavoriteDto [seq=" + seq + ", name=" + name + ", address1=" + address1 + ", address2=" + address2
				+ ", tel=" + tel + ", opentime=" + opentime + ", pettype=" + pettype + "]";
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getPettype() {
		return pettype;
	}

	public void setPettype(String pettype) {
		this.pettype = pettype;
	}

}
