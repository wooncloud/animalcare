package com.pet.care.dto;

import org.springframework.security.core.GrantedAuthority;

public class AuthDto implements GrantedAuthority {

	private static final long serialVersionUID = 649071729749774580L;
	
	private String auth_no;
	private String auth_name;
	private String auth_exp;

	public AuthDto() {
	}

	public AuthDto(String auth_no, String auth_name, String auth_exp) {
		super();
		this.auth_no = auth_no;
		this.auth_name = auth_name;
		this.auth_exp = auth_exp;
	}

	@Override
	public String toString() {
		return "AuthDto [auth_no=" + auth_no + ", auth_name=" + auth_name + ", auth_exp=" + auth_exp + "]";
	}

	public String getAuth_no() {
		return auth_no;
	}

	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
	}

	public String getAuth_name() {
		return auth_name;
	}

	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}

	public String getAuth_exp() {
		return auth_exp;
	}

	public void setAuth_exp(String auth_exp) {
		this.auth_exp = auth_exp;
	}

	@Override
	public String getAuthority() {
		return auth_name;
	}

}
