package com.commodity_category.model;

import java.io.Serializable;

public class ComcVO implements Serializable {
	
	private int comcID;
	private String comcName;
	
	
	public ComcVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getComcID() {
		return comcID;
	}
	public void setComcID(int comcID) {
		this.comcID = comcID;
	}
	public String getComcName() {
		return comcName;
	}
	public void setComcName(String comcName) {
		this.comcName = comcName;
	}
	
	
}
