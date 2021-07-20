package com.pet.care.dto;

import java.io.Serializable;
import java.util.Date;

public class MedicalRecodeDto implements Serializable{
	
	private static final long serialVersionUID = -7726314107389112755L;
	
	private int seq;
	private String pet_id;
	private String symptom;
	private String treatment;
	private String prescription;
	private Date treatdate;
	private int hospital_seq;

	public MedicalRecodeDto() {
	}

	public MedicalRecodeDto(int seq, String pet_id, String symptom, String treatment, String prescription,
			Date treatdate, int hospital_seq) {
		super();
		this.seq = seq;
		this.pet_id = pet_id;
		this.symptom = symptom;
		this.treatment = treatment;
		this.prescription = prescription;
		this.treatdate = treatdate;
		this.hospital_seq = hospital_seq;
	}

	@Override
	public String toString() {
		return "MedicalRecodeDto [seq=" + seq + ", pet_id=" + pet_id + ", symptom=" + symptom + ", treatment="
				+ treatment + ", prescription=" + prescription + ", treatdate=" + treatdate + ", hospital_seq="
				+ hospital_seq + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getPet_id() {
		return pet_id;
	}

	public void setPet_id(String pet_id) {
		this.pet_id = pet_id;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public Date getTreatdate() {
		return treatdate;
	}

	public void setTreatdate(Date treatdate) {
		this.treatdate = treatdate;
	}

	public int getHospital_seq() {
		return hospital_seq;
	}

	public void setHospital_seq(int hospital_seq) {
		this.hospital_seq = hospital_seq;
	}

	
	
}
