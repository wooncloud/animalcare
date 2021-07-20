package com.pet.care.dto;

import java.io.Serializable;

public class HospitalInfoDto implements Serializable{

	private static final long serialVersionUID = 8190068621641468328L;

	private int seq;
	private String operator_email;
	private String name;
	private String address1;
	private String address2;
	private String tel;
	private String emergency;
	private String opentime;
	private String delflag;
	private String content;
	
	public HospitalInfoDto() {
	}

	public HospitalInfoDto(int seq, String operator_email, String name, String address1, String address2, String tel,
			String emergency, String opentime, String delflag, String content) {
		super();
		this.seq = seq;
		this.operator_email = operator_email;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.tel = tel;
		this.emergency = emergency;
		this.opentime = opentime;
		this.delflag = delflag;
		this.content = content;
	}

	@Override
	public String toString() {
		return "HospitalInfoDto [seq=" + seq + ", operator_email=" + operator_email + ", name=" + name + ", address1="
				+ address1 + ", address2=" + address2 + ", tel=" + tel + ", emergency=" + emergency + ", opentime="
				+ opentime + ", delflag=" + delflag + ", content=" + content + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getOperator_email() {
		return operator_email;
	}

	public void setOperator_email(String operator_email) {
		this.operator_email = operator_email;
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

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
