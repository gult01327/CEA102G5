package com.ordermaster.model;

import java.io.Serializable;
import java.sql.Date;

public class OmVO implements Serializable {
	private Integer omID;
	private Integer memID;
	private Integer mrID;
	private Integer omPrice;
	private Integer omPay;
	private Integer omShip;
	private Integer omDiscount;
	private Date omTime;
	private String omrName;
	private String omrPhone;
	private String omrAddress;
	
	
	
	

	public OmVO() {
		super();
	}


	public Integer getOmID() {
		return omID;
	}


	public void setOmID(Integer omID) {
		this.omID = omID;
	}


	public Integer getMemID() {
		return memID;
	}


	public void setMemID(Integer memID) {
		this.memID = memID;
	}


	public Integer getMrID() {
		return mrID;
	}


	public void setMrID(Integer mrID) {
		this.mrID = mrID;
	}


	public Integer getOmPrice() {
		return omPrice;
	}


	public void setOmPrice(Integer omPrice) {
		this.omPrice = omPrice;
	}


	public Integer getOmPay() {
		return omPay;
	}


	public void setOmPay(Integer omPay) {
		this.omPay = omPay;
	}


	public Integer getOmShip() {
		return omShip;
	}


	public void setOmShip(Integer omShip) {
		this.omShip = omShip;
	}


	public Integer getOmDiscount() {
		return omDiscount;
	}


	public void setOmDiscount(Integer omDiscount) {
		this.omDiscount = omDiscount;
	}
	
	public Date getOmTime() {
		return omTime;
	}


	public void setOmTime(Date omTime) {
		this.omTime = omTime;
	}


	public String getOmrName() {
		return omrName;
	}


	public void setOmrName(String omrName) {
		this.omrName = omrName;
	}


	public String getOmrPhone() {
		return omrPhone;
	}


	public void setOmrPhone(String omrPhone) {
		this.omrPhone = omrPhone;
	}


	public String getOmrAddress() {
		return omrAddress;
	}


	public void setOmrAddress(String omrAddress) {
		this.omrAddress = omrAddress;
	}
	
	
	
}
