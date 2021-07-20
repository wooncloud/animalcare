package com.pet.care.dto;

public class FaqBoardDto {
	
	
	private int seq;
	private String title;
	private String content;
	private String delflag;
	private String admin_email;
	
	
	public FaqBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FaqBoardDto(int seq, String title, String content, String delflag, String admin_email) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.delflag = delflag;
		this.admin_email = admin_email;
	}


	@Override
	public String toString() {
		return "FaqDto [seq=" + seq + ", title=" + title + ", content=" + content + ", delflag=" + delflag
				+ ", admin_email=" + admin_email + "]";
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
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


	public String getDelflag() {
		return delflag;
	}


	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}


	public String getAdmin_email() {
		return admin_email;
	}


	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	
	
}
