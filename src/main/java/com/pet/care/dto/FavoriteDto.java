package com.pet.care.dto;

import java.io.Serializable;

public class FavoriteDto implements Serializable {

	private static final long serialVersionUID = -4344191818342513297L;
	
	private int seq;
	private String user_email;
	private int hospital_seq;
	private String memo;

	public FavoriteDto() {
	}

	public FavoriteDto(int seq, String user_email, int hospital_seq, String memo) {
		this.seq = seq;
		this.user_email = user_email;
		this.hospital_seq = hospital_seq;
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "FavoriteDto [seq=" + seq + ", user_email=" + user_email + ", hospital_seq=" + hospital_seq + ", memo="
				+ memo + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getHospital_seq() {
		return hospital_seq;
	}

	public void setHospital_seq(int hospital_seq) {
		this.hospital_seq = hospital_seq;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
