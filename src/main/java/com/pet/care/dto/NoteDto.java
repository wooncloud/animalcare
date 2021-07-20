package com.pet.care.dto;

import java.util.Date;

public class NoteDto {
	private int seq      ;
	private String pet_id   ;
	private String title    ;
	private String content  ;
	private String photo    ;
	private String delflag  ;
	private Date regdate  ;
	
	private PetDto pdto;

	public NoteDto() {
	
	}

	@Override
	public String toString() {
		return "NoteDto [seq=" + seq + ", pet_id=" + pet_id + ", title=" + title + ", content=" + content + ", photo="
				+ photo + ", delflag=" + delflag + ", regdate=" + regdate + ", pdto=" + pdto + "]";
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public PetDto getPdto() {
		return pdto;
	}

	public void setPdto(PetDto pdto) {
		this.pdto = pdto;
	}
	
	
}
