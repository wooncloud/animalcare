package com.pet.care.dto;

public class SurveyResponserDto {
	
	private int seq;
	private int survey_seq;
	private String responser;
	
	public SurveyResponserDto() {
		super();
	}

	public SurveyResponserDto(int seq, int survey_seq, String responser) {
		super();
		this.seq = seq;
		this.survey_seq = survey_seq;
		this.responser = responser;
	}

	@Override
	public String toString() {
		return "SurveyResponserDto [seq=" + seq + ", survey_seq=" + survey_seq + ", responser=" + responser + "]";
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

	public String getResponser() {
		return responser;
	}

	public void setResponser(String responser) {
		this.responser = responser;
	}
	
	
}
