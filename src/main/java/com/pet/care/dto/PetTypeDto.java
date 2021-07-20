package com.pet.care.dto;

import java.io.Serializable;

public class PetTypeDto implements Serializable{

	private static final long serialVersionUID = 5089144163491627308L;
	
	private int seq;
	private String pettype;
	private int hospital_seq;
	private String delflag;
	                  
	public PetTypeDto() {
	}

	public PetTypeDto(int seq, String pettype, int hospital_seq, String delflag) {
		super();
		this.seq = seq;
		this.pettype = pettype;
		this.hospital_seq = hospital_seq;
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "PetTypeDto [seq=" + seq + ", pettype=" + pettype + ", hospital_seq=" + hospital_seq + ", delflag="
				+ delflag + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getPettype() {
		return pettype;
	}

	public void setPettype(String pettype) {
		this.pettype = pettype;
	}

	public int getHospital_seq() {
		return hospital_seq;
	}

	public void setHospital_seq(int hospital_seq) {
		this.hospital_seq = hospital_seq;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
	
	
}
