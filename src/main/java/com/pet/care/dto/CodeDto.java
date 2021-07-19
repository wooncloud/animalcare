package com.pet.care.dto;

import java.io.Serializable;

public class CodeDto implements Serializable {
	
	private static final long serialVersionUID = -1595455828380400623L;
	private String codeid;
	private String codetype;
	private String codename;
	private String delflag;

	public CodeDto() {
	}

	public CodeDto(String codeid, String codetype, String codename, String delflag) {
		this.codeid = codeid;
		this.codetype = codetype;
		this.codename = codename;
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "CodeDto [codeid=" + codeid + ", codetype=" + codetype + ", codename=" + codename + ", delflag="
				+ delflag + "]";
	}

	public String getCodeid() {
		return codeid;
	}

	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}

	public String getCodetype() {
		return codetype;
	}

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

}
