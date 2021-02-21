package com.member_recipient.model;

import java.io.Serializable;

public class MemrVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3492286423778344263L;
	
	private Integer memrID;
	private Integer memID;
	private String memrName;
	private String memrPhone;
	private String memrAddress;
	
	public MemrVO() {
		super();
	}

	public Integer getMemrID() {
		return memrID;
	}

	public void setMemrID(Integer memrID) {
		this.memrID = memrID;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public String getMemrName() {
		return memrName;
	}

	public void setMemrName(String memrName) {
		this.memrName = memrName;
	}

	public String getMemrPhone() {
		return memrPhone;
	}

	public void setMemrPhone(String memrPhone) {
		this.memrPhone = memrPhone;
	}

	public String getMemrAddress() {
		return memrAddress;
	}

	public void setMemrAddress(String memrAddress) {
		this.memrAddress = memrAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
