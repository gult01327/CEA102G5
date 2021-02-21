package com.recipe_report.model;

import java.io.Serializable;
import java.sql.Date;

public class RecrVO implements Serializable {

	private Integer recrID;
	private Integer recID;
	private Integer memID;
	private String recReplyContent;
	private Date recrTime;
	private Integer recrStatus;
	
	public RecrVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRecrID() {
		return recrID;
	}

	public void setRecrID(Integer recrID) {
		this.recrID = recrID;
	}

	public Integer getRecID() {
		return recID;
	}

	public void setRecID(Integer recID) {
		this.recID = recID;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public String getRecReplyContent() {
		return recReplyContent;
	}

	public void setRecReplyContent(String recReplyContent) {
		this.recReplyContent = recReplyContent;
	}

	public Date getRecrTime() {
		return recrTime;
	}

	public void setRecrTime(Date recrTime) {
		this.recrTime = recrTime;
	}

	public Integer getRecrStatus() {
		return recrStatus;
	}

	public void setRecrStatus(Integer recrStatus) {
		this.recrStatus = recrStatus;
	}
	
	
}
