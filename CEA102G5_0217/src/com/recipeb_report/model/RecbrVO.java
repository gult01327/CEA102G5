package com.recipeb_report.model;

import java.io.Serializable;
import java.sql.Date;

public class RecbrVO implements Serializable {

	private Integer recbID;
	private Integer memID;
	private String recbrContent;
	private Integer recbrReply;
	private Date recbrTime;
	private Integer recbrStatus;
	
	public RecbrVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRecbID() {
		return recbID;
	}

	public void setRecbID(Integer recbID) {
		this.recbID = recbID;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public String getRecbrContent() {
		return recbrContent;
	}

	public void setRecbrContent(String recbrContent) {
		this.recbrContent = recbrContent;
	}

	public Integer getRecbrReply() {
		return recbrReply;
	}

	public void setRecbrReply(Integer recbrReply) {
		this.recbrReply = recbrReply;
	}

	public Date getRecbrTime() {
		return recbrTime;
	}

	public void setRecbrTime(Date recbrTime) {
		this.recbrTime = recbrTime;
	}

	public Integer getRecbrStatus() {
		return recbrStatus;
	}

	public void setRecbrStatus(Integer recbrStatus) {
		this.recbrStatus = recbrStatus;
	}
	
	
	
	
}
