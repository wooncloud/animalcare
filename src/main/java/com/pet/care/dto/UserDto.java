package com.pet.care.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -2178185780795676208L;

	private String email;
	private String name;
	private String password;
	private String address1;
	private String address2;
	private String phone;
	private String regdate;
	private String delflag;

	public UserDto() {
	}

	public UserDto(String email, String name, String password, String address1, String address2, String phone,
			String regdate, String delflag) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.address1 = address1;
		this.address2 = address2;
		this.phone = phone;
		this.regdate = regdate;
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "UserDto [email=" + email + ", name=" + name + ", password=" + password + ", address1=" + address1
				+ ", address2=" + address2 + ", phone=" + phone + ", regdate=" + regdate + ", delflag=" + delflag + "]";
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

}
