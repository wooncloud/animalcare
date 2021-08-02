package com.pet.care.dto;

public class SurveyDto {

	private int seq;
	private String question;
	private String regdate;
	private String startdate;
	private String enddate;
	private String delflag;
	private String title;
	private int hospital_seq;
	private String surveyflag;
	
	public SurveyDto() {
		super();
	}

	public SurveyDto(int seq, String question, String regdate, String startdate, String enddate, String delflag,
			String title, int hospital_seq, String surveyflag) {
		super();
		this.seq = seq;
		this.question = question;
		this.regdate = regdate;
		this.startdate = startdate;
		this.enddate = enddate;
		this.delflag = delflag;
		this.title = title;
		this.hospital_seq = hospital_seq;
		this.surveyflag = surveyflag;
	}

	@Override
	public String toString() {
		return "SurveyDto [seq=" + seq + ", question=" + question + ", regdate=" + regdate + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", delflag=" + delflag + ", title=" + title + ", hospital_seq="
				+ hospital_seq + ", surveyflag=" + surveyflag + "]";
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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
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

	public String getSurveyflag() {
		return surveyflag;
	}

	public void setSurveyflag(String surveyflag) {
		this.surveyflag = surveyflag;
	}
	
}
