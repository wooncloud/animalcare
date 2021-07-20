package com.pet.care.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MedicalRecodeJoinDto implements Serializable{
	
	private static final long serialVersionUID = 7809691289764124721L;
	
	private int seq;
	private String pet_id;
	private String symptom;
	private String treatment;
	private String prescription;
	private Date treatdate;
	private int hospital_seq;

	private List<PetDto> petdto;
	private List<UserDto> userdto;
	private List<HospitalInfoDto> hospitalinfodto;
	
	public MedicalRecodeJoinDto() {
	}

	public MedicalRecodeJoinDto(int seq, String pet_id, String symptom, String treatment, String prescription,
			Date treatdate, int hospital_seq, List<PetDto> petdto, List<UserDto> userdto,
			List<HospitalInfoDto> hospitalinfodto) {
		super();
		this.seq = seq;
		this.pet_id = pet_id;
		this.symptom = symptom;
		this.treatment = treatment;
		this.prescription = prescription;
		this.treatdate = treatdate;
		this.hospital_seq = hospital_seq;
		this.petdto = petdto;
		this.userdto = userdto;
		this.hospitalinfodto = hospitalinfodto;
	}

	@Override
	public String toString() {
		return "MedicalRecodeJoinDto [seq=" + seq + ", pet_id=" + pet_id + ", symptom=" + symptom + ", treatment="
				+ treatment + ", prescription=" + prescription + ", treatdate=" + treatdate + ", hospital_seq="
				+ hospital_seq + ", petdto=" + petdto + ", userdto=" + userdto + ", hospitalinfodto=" + hospitalinfodto
				+ "]";
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

	public List<PetDto> getPetdto() {
		return petdto;
	}

	public void setPetdto(List<PetDto> petdto) {
		this.petdto = petdto;
	}

	public List<UserDto> getUserdto() {
		return userdto;
	}

	public void setUserdto(List<UserDto> userdto) {
		this.userdto = userdto;
	}

	public List<HospitalInfoDto> getHospitalinfodto() {
		return hospitalinfodto;
	}

	public void setHospitalinfodto(List<HospitalInfoDto> hospitalinfodto) {
		this.hospitalinfodto = hospitalinfodto;
	}
	
	
	
}
