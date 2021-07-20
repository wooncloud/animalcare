package com.pet.care.dto;

import java.io.Serializable;

public class ReportDto implements Serializable {

	private static final long serialVersionUID = 5185392449226965871L;

	private String seq;
	private String title;
	private String content;
	private String type;
	private String reporter;
	private String defendant;
	private String res_seq;
	private String medi_seq;
	private String status;
	private String regdate;
	private String recvdate;
	private String processdate;
	private String delflag;

	public ReportDto() {
	}

	public ReportDto(String seq, String title, String content, String type, String reporter, String defendant,
			String res_seq, String medi_seq, String status, String regdate, String recvdate, String processdate,
			String delflag) {
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.type = type;
		this.reporter = reporter;
		this.defendant = defendant;
		this.res_seq = res_seq;
		this.medi_seq = medi_seq;
		this.status = status;
		this.regdate = regdate;
		this.recvdate = recvdate;
		this.processdate = processdate;
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "ReportDto [seq=" + seq + ", title=" + title + ", content=" + content + ", type=" + type + ", reporter="
				+ reporter + ", defendant=" + defendant + ", res_seq=" + res_seq + ", medi_seq=" + medi_seq
				+ ", status=" + status + ", regdate=" + regdate + ", recvdate=" + recvdate + ", processdate="
				+ processdate + ", delflag=" + delflag + "]";
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getDefendant() {
		return defendant;
	}

	public void setDefendant(String defendant) {
		this.defendant = defendant;
	}

	public String getRes_seq() {
		return res_seq;
	}

	public void setRes_seq(String res_seq) {
		this.res_seq = res_seq;
	}

	public String getMedi_seq() {
		return medi_seq;
	}

	public void setMedi_seq(String medi_seq) {
		this.medi_seq = medi_seq;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getRecvdate() {
		return recvdate;
	}

	public void setRecvdate(String recvdate) {
		this.recvdate = recvdate;
	}

	public String getProcessdate() {
		return processdate;
	}

	public void setProcessdate(String processdate) {
		this.processdate = processdate;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

}
