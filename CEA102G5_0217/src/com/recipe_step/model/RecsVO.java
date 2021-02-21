package com.recipe_step.model;

import java.io.Serializable;

public class RecsVO implements Serializable {

	private Integer recsID;
	private Integer recID;
	private String recsContent;
	private byte[] recsPicture;
	
	
	public RecsVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getRecsID() {
		return recsID;
	}


	public void setRecsID(Integer recsID) {
		this.recsID = recsID;
	}


	public Integer getRecID() {
		return recID;
	}


	public void setRecID(Integer recID) {
		this.recID = recID;
	}


	public String getRecsContent() {
		return recsContent;
	}


	public void setRecsContent(String recsContent) {
		this.recsContent = recsContent;
	}


	public byte[] getRecsPicture() {
		return recsPicture;
	}


	public void setRecsPicture(byte[] recsPicture) {
		this.recsPicture = recsPicture;
	}
	
	
	
	
}
