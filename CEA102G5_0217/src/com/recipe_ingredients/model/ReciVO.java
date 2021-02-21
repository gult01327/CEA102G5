package com.recipe_ingredients.model;

import java.io.Serializable;

public class ReciVO implements Serializable {

	private Integer recID;
	private Integer comID;
	private Integer reciNums;
	
	public ReciVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRecID() {
		return recID;
	}

	public void setRecID(Integer recID) {
		this.recID = recID;
	}

	public Integer getComID() {
		return comID;
	}

	public void setComID(Integer comID) {
		this.comID = comID;
	}

	public Integer getReciNums() {
		return reciNums;
	}

	public void setReciNums(Integer reciNums) {
		this.reciNums = reciNums;
	}
	
	
	
}
