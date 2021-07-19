package com.pet.care.dto;

import java.util.Date;

public class PaymentDto {

	private int seq;
	private String user_email;
	private String user_phone;
	private String paynum;
	private String hospital_name;
	private String paidamount;
	private Date paydate;
	private String applynum;
	private Date canceldate;
	private String cancelflag;
	private Date refunddate;
	private String refundflag;
	private int reservation_seq;
	
	public PaymentDto() {
		super();
	}

	public PaymentDto(int seq, String user_email, String user_phone, String paynum, String hospital_name,
			String paidamount, Date paydate, String applynum, Date canceldate, String cancelflag, Date refunddate,
			String refundflag, int reservation_seq) {
		super();
		this.seq = seq;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.paynum = paynum;
		this.hospital_name = hospital_name;
		this.paidamount = paidamount;
		this.paydate = paydate;
		this.applynum = applynum;
		this.canceldate = canceldate;
		this.cancelflag = cancelflag;
		this.refunddate = refunddate;
		this.refundflag = refundflag;
		this.reservation_seq = reservation_seq;
	}

	@Override
	public String toString() {
		return "PaymentDto [seq=" + seq + ", user_email=" + user_email + ", user_phone=" + user_phone + ", paynum="
				+ paynum + ", hospital_name=" + hospital_name + ", paidamount=" + paidamount + ", paydate=" + paydate
				+ ", applynum=" + applynum + ", canceldate=" + canceldate + ", cancelflag=" + cancelflag
				+ ", refunddate=" + refunddate + ", refundflag=" + refundflag + ", reservation_seq=" + reservation_seq
				+ "]";
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

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getPaynum() {
		return paynum;
	}

	public void setPaynum(String paynum) {
		this.paynum = paynum;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public String getPaidamount() {
		return paidamount;
	}

	public void setPaidamount(String paidamount) {
		this.paidamount = paidamount;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public String getApplynum() {
		return applynum;
	}

	public void setApplynum(String applynum) {
		this.applynum = applynum;
	}

	public Date getCanceldate() {
		return canceldate;
	}

	public void setCanceldate(Date canceldate) {
		this.canceldate = canceldate;
	}

	public String getCancelflag() {
		return cancelflag;
	}

	public void setCancelflag(String cancelflag) {
		this.cancelflag = cancelflag;
	}

	public Date getRefunddate() {
		return refunddate;
	}

	public void setRefunddate(Date refunddate) {
		this.refunddate = refunddate;
	}

	public String getRefundflag() {
		return refundflag;
	}

	public void setRefundflag(String refundflag) {
		this.refundflag = refundflag;
	}

	public int getReservation_seq() {
		return reservation_seq;
	}

	public void setReservation_seq(int reservation_seq) {
		this.reservation_seq = reservation_seq;
	}
	
	
	
}
