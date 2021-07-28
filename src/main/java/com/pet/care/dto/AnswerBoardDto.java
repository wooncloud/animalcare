package com.pet.care.dto;

import java.util.Date;

public class AnswerBoardDto {

	private int seq;
	private String email;
	private String password;
	private String title;
	private String content;
	private Date regdate;
	private int parent_seq;
	private String delflag;
	private String name;
	private String phone;
	public AnswerBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AnswerBoardDto(int seq, String email, String password, String title, String content, Date regdate,
			int parent_seq, String delflag, String name, String phone) {
		super();
		this.seq = seq;
		this.email = email;
		this.password = password;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.parent_seq = parent_seq;
		this.delflag = delflag;
		this.name = name;
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "AnswerBoardDto [seq=" + seq + ", email=" + email + ", password=" + password + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", parent_seq=" + parent_seq + ", delflag="
				+ delflag + ", name=" + name + ", phone=" + phone + "]";
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getParent_seq() {
		return parent_seq;
	}
	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}