package com.pet.care.dto;

import java.util.Date;

public class SurveyDto {

	private int seq;
	private String question;
	private Date regdate;
	private Date startdate;
	private Date enddate;
	private String delflag;
	private String title;
	private int hospital_seq;
	
	public SurveyDto() {
		super();
	}

	public SurveyDto(int seq, String question, Date regdate, Date startdate, Date enddate, String delflag, String title,
			int hospital_seq) {
		super();
		this.seq = seq;
		this.question = question;
		this.regdate = regdate;
		this.startdate = startdate;
		this.enddate = enddate;
		this.delflag = delflag;
		this.title = title;
		this.hospital_seq = hospital_seq;
	}

	@Override
	public String toString() {
		return "SurveyDto [seq=" + seq + ", question=" + question + ", regdate=" + regdate + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", delflag=" + delflag + ", title=" + title + ", hospital_seq="
				+ hospital_seq + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getHospital_seq() {
		return hospital_seq;
	}

	public void setHospital_seq(int hospital_seq) {
		this.hospital_seq = hospital_seq;
	}
	
}
