package com.pet.care.dto;

import java.util.Date;

public class SurveyResultDto {

	private int seq;
	private int survey_seq;
	private int hospital_seq;
	private String title;
	private String answer;
	private Date regdate;
	
	public SurveyResultDto() {
		super();
	}

	public SurveyResultDto(int seq, int survey_seq, int hospital_seq, String title, String answer, Date regdate) {
		super();
		this.seq = seq;
		this.survey_seq = survey_seq;
		this.hospital_seq = hospital_seq;
		this.title = title;
		this.answer = answer;
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "SurveyResultDto [seq=" + seq + ", survey_seq=" + survey_seq + ", hospital_seq=" + hospital_seq
				+ ", title=" + title + ", answer=" + answer + ", regdate=" + regdate + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getSurvey_seq() {
		return survey_seq;
	}

	public void setSurvey_seq(int survey_seq) {
		this.survey_seq = survey_seq;
	}

	public int getHospital_seq() {
		return hospital_seq;
	}

	public void setHospital_seq(int hospital_seq) {
		this.hospital_seq = hospital_seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
