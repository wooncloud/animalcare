package com.pet.care.dto;

import java.io.Serializable;

public class AdminDto implements Serializable {

	private static final long serialVersionUID = 1788344602505135092L;

	private String email;
	private String name;
	private String password;
	private String delflag;

	public AdminDto() {
	}

	public AdminDto(String email, String name, String password, String delflag) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "AdminDto [email=" + email + ", name=" + name + ", password=" + password + ", delflag=" + delflag + "]";
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

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

}
