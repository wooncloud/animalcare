package com.pet.care.dto;

import java.io.Serializable;
import java.util.List;

public class HospitalJoinDto implements Serializable{

	private static final long serialVersionUID = -4550825082994053361L;
	
	private int seq;
	private String operator_email;
	private String name;
	private String address1;
	private String address2;
	private String tel;
	private String emergency;
	private String opentime;
	private String delflag;
	private String content;

	private List<UserDto> userdto;
	private List<PetTypeDto> pettypedto;
	private List<OperatorDto> operatordto;
	
	public HospitalJoinDto() {
	}

	public HospitalJoinDto(int seq, String operator_email, String name, String address1, String address2, String tel,
			String emergency, String opentime, String delflag, String content, List<UserDto> userdto,
			List<PetTypeDto> pettypedto, List<OperatorDto> operatordto) {
		super();
		this.seq = seq;
		this.operator_email = operator_email;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.tel = tel;
		this.emergency = emergency;
		this.opentime = opentime;
		this.delflag = delflag;
		this.content = content;
		this.userdto = userdto;
		this.pettypedto = pettypedto;
		this.operatordto = operatordto;
	}

	@Override
	public String toString() {
		return "HospitalJoinDto [seq=" + seq + ", operator_email=" + operator_email + ", name=" + name + ", address1="
				+ address1 + ", address2=" + address2 + ", tel=" + tel + ", emergency=" + emergency + ", opentime="
				+ opentime + ", delflag=" + delflag + ", content=" + content + ", userdto=" + userdto + ", pettypedto="
				+ pettypedto + ", operatordto=" + operatordto + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getOperator_email() {
		return operator_email;
	}

	public void setOperator_email(String operator_email) {
		this.operator_email = operator_email;
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

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
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

	public List<UserDto> getUserdto() {
		return userdto;
	}

	public void setUserdto(List<UserDto> userdto) {
		this.userdto = userdto;
	}

	public List<PetTypeDto> getPettypedto() {
		return pettypedto;
	}

	public void setPettypedto(List<PetTypeDto> pettypedto) {
		this.pettypedto = pettypedto;
	}

	public List<OperatorDto> getOperatordto() {
		return operatordto;
	}

	public void setOperatordto(List<OperatorDto> operatordto) {
		this.operatordto = operatordto;
	}
	
	
	
}
