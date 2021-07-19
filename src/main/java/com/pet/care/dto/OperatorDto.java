package com.pet.care.dto;

import java.io.Serializable;
import java.util.Date;

public class OperatorDto implements Serializable{
	
	private static final long serialVersionUID = -2022022354487422017L;
	
	private String email;
	private String name;
	private String password;
	private String phone;
	private String corpregnum;
	private int licensenum;
	private Date regdate;
	private String approvalflag;
	private String delflag;
	
	public OperatorDto() {
	}

	public OperatorDto(String email, String name, String password, String phone, String corpregnum, int licensenum,
			Date regdate, String approvalflag, String delflag) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.corpregnum = corpregnum;
		this.licensenum = licensenum;
		this.regdate = regdate;
		this.approvalflag = approvalflag;
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "OperatorDto [email=" + email + ", name=" + name + ", password=" + password + ", phone=" + phone
				+ ", corpregnum=" + corpregnum + ", licensenum=" + licensenum + ", regdate=" + regdate
				+ ", approvalflag=" + approvalflag + ", delflag=" + delflag + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCorpregnum() {
		return corpregnum;
	}

	public void setCorpregnum(String corpregnum) {
		this.corpregnum = corpregnum;
	}

	public int getLicensenum() {
		return licensenum;
	}

	public void setLicensenum(int licensenum) {
		this.licensenum = licensenum;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getApprovalflag() {
		return approvalflag;
	}

	public void setApprovalflag(String approvalflag) {
		this.approvalflag = approvalflag;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
}
