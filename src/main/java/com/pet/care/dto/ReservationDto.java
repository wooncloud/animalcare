package com.pet.care.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.catalina.User;

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
	private String name;
	private String address1;
	private String phone;
	private String symptom;

	
	public ReservationDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReservationDto(int seq, int hospital_seq, String user_email, String pet_name, String reservetype,
			Date regdate, String reservedate, String status, String paynum, String reservetime, String commnet,
			String name, String address1, String phone, String symptom) {
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
		this.name = name;
		this.address1 = address1;
		this.phone = phone;
		this.symptom = symptom;
		
	}




	@Override
	public String toString() {
		return "ReservationDto [seq=" + seq + ", hospital_seq=" + hospital_seq + ", user_email=" + user_email
				+ ", pet_name=" + pet_name + ", reservetype=" + reservetype + ", regdate=" + regdate + ", reservedate="
				+ reservedate + ", status=" + status + ", paynum=" + paynum + ", reservetime=" + reservetime
				+ ", commnet=" + commnet + ", name=" + name + ", address1=" + address1 + ", phone=" + phone 
				+ ", symptom=" + symptom + "]";
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
	
	public String getSymptom() {
		return symptom;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	
	
}
