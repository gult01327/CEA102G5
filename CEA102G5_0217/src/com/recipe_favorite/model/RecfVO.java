package com.recipe_favorite.model;

import java.io.Serializable;

public class RecfVO implements Serializable {

	private Integer memID;
	private Integer recID;
	
	
	public RecfVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getMemID() {
		return memID;
	}


	public void setMemID(Integer memID) {
		this.memID = memID;
	}


	public Integer getRecID() {
		return recID;
	}


	public void setRecID(Integer recID) {
		this.recID = recID;
	}
	
	
}
