package com.pet.care.dto;

import java.util.Date;
import java.util.List;

public class AnswerBoardDto {

	private int seq;
	private String email;
	private String password;
	private String title;
	private String content;
	private Date regdate;
	private int parent_seq;
	private String delflag;
	private String answerboard_name;
	private String name;
	private String phone;
	private String reply;
	private String replystatus;

	public AnswerBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public AnswerBoardDto(int seq, String email, String password, String title, String content, Date regdate,
			int parent_seq, String delflag, String answerboard_name, String name, String phone, String reply,
			String replystatus) {
		super();
		this.seq = seq;
		this.email = email;
		this.password = password;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.parent_seq = parent_seq;
		this.delflag = delflag;
		this.answerboard_name = answerboard_name;
		this.name = name;
		this.phone = phone;
		this.reply = reply;
		this.replystatus = replystatus;
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

	public String getAnswerboard_name() {
		return answerboard_name;
	}

	public void setAnswerboard_name(String answerboard_name) {
		this.answerboard_name = answerboard_name;
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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getReplystatus() {
		return replystatus;
	}

	public void setReplystatus(String replystatus) {
		this.replystatus = replystatus;
	}
	

	@Override
	public String toString() {
		return "AnswerBoardDto [seq=" + seq + ", email=" + email + ", password=" + password + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", parent_seq=" + parent_seq + ", delflag="
				+ delflag + ", answerboard_name=" + answerboard_name + ", name=" + name + ", phone=" + phone
				+ ", reply=" + reply + ", replystatus=" + replystatus + "]";
	}

	
	
	
}
