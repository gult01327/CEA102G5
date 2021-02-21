package com.recipe_board.model;

import java.io.Serializable;
import java.sql.Date;

public class RecbVO implements Serializable {
	
	private Integer recbID;
	private Integer recID;
	private Integer memID;
	private String recbContent;
	private Date recbTime;
	private Integer recbStatus;
	
	
	public RecbVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getRecbID() {
		return recbID;
	}


	public void setRecbID(Integer recbID) {
		this.recbID = recbID;
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


	public String getRecbContent() {
		return recbContent;
	}


	public void setRecbContent(String recbContent) {
		this.recbContent = recbContent;
	}


	public Date getRecbTime() {
		return recbTime;
	}


	public void setRecbTime(Date recbTime) {
		this.recbTime = recbTime;
	}


	public Integer getRecbStatus() {
		return recbStatus;
	}


	public void setRecbStatus(Integer recbStatus) {
		this.recbStatus = recbStatus;
	}
	
	
	
	
}
