package com.pet.care.dto;

import java.io.Serializable;
import java.util.Date;

public class ReservationDto implements Serializable {
	
	
	private static final long serialVersionUID = 6015754190827376850L;
	
	private int seq;
	private int hospital_seq;
	private String user_email;
	private String pet_name;
	private String reservetype;
	private Date regdate;
	private String reservedate;
	private String status;
	private String paynum;
	private String reservetime;
	private String commnet;
	
	
	
	public ReservationDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReservationDto(int seq, int hospital_seq, String user_email, String pet_name, String reservetype,
			Date regdate, String reservedate, String status, String paynum, String reservetime, String commnet) {
		super();
		this.seq = seq;
		this.hospital_seq = hospital_seq;
		this.user_email = user_email;
		this.pet_name = pet_name;
		this.reservetype = reservetype;
		this.regdate = regdate;
		this.reservedate = reservedate;
		this.status = status;
		this.paynum = paynum;
		this.reservetime = reservetime;
		this.commnet = commnet;
		
	}


	@Override
	public String toString() {
		return "ReservationDto [seq=" + seq + ", hospital_seq=" + hospital_seq + ", user_email=" + user_email
				+ ", pet_name=" + pet_name + ", reservetype=" + reservetype + ", regdate=" + regdate + ", reservedate="
				+ reservedate + ", status=" + status + ", paynum=" + paynum + ", reservetime=" + reservetime
				+ ", commnet=" + commnet + "]";
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


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getPet_name() {
		return pet_name;
	}


	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}


	public String getReservetype() {
		return reservetype;
	}


	public void setReservetype(String reservetype) {
		this.reservetype = reservetype;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}


	public String getReservedate() {
		return reservedate;
	}


	public void setReservedate(String reservedate) {
		this.reservedate = reservedate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPaynum() {
		return paynum;
	}


	public void setPaynum(String paynum) {
		this.paynum = paynum;
	}


	public String getReservetime() {
		return reservetime;
	}


	public void setReservetime(String reservetime) {
		this.reservetime = reservetime;
	}


	public String getCommnet() {
		return commnet;
	}


	public void setCommnet(String commnet) {
		this.commnet = commnet;
	}
	
	
	
	
	
}
