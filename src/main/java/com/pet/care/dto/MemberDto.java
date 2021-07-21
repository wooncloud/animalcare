package com.pet.care.dto;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberDto implements UserDetails {

	private static final long serialVersionUID = 7876004929562622795L;

	private String email;
	private String name;
	private String password;
	private String usertype; // 권한
	private String delflag;

	private Collection<GrantedAuthority> authorities;
	private Date issueAt;

	public MemberDto() {
	}

	public MemberDto(String email, String name, String usertype, String delflag) {
		this.email = email;
		this.name = name;
		this.usertype = usertype;
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "MemberDto [email=" + email + ", name=" + name + ", usertype=" + usertype + ", delflag=" + delflag + "]";
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

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return delflag.equals("N") ? true : false;
	}

	public String getAuthority() {
		return usertype;
	}

	public void setAuthority(String authority) {
		this.usertype = authority;
	}

	public Date getIssueAt() {
		return issueAt;
	}

	public void setIssueAt(Date issueAt) {
		this.issueAt = issueAt;
	}

	@Override
	public String getPassword() {
		return password;
	}

}
