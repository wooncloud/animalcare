package com.pet.care.dto;

import java.io.Serializable;
import java.util.Date;

public class HospitalScheduleDto implements Serializable {

	private static final long serialVersionUID = 5363680096195603401L;
	
	private int seq;
	private int hospital_seq;
	private String title;
	private String check;
	private String delflag;
	private String content;
	private Date schedule;
	
	public HospitalScheduleDto() {
	}

	public HospitalScheduleDto(int seq, int hospital_seq, String title, String check, String delflag, String content,
			Date schedule) {
		super();
		this.seq = seq;
		this.hospital_seq = hospital_seq;
		this.title = title;
		this.check = check;
		this.delflag = delflag;
		this.content = content;
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "HospitalScheduleDto [seq=" + seq + ", hospital_seq=" + hospital_seq + ", title=" + title + ", check="
				+ check + ", delflag=" + delflag + ", content=" + content + ", schedule=" + schedule + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}
	
	
	
}
