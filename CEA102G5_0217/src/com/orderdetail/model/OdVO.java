package com.orderdetail.model;

import java.io.Serializable;
import java.sql.Date;

public class OdVO implements Serializable {
	private Integer omID;
	private Integer comID;
	private String comName;
	private Integer odCount;
	private Integer odPrice;
	private Integer odReturn;
	private String odReason;
	private Date odRtime;
	
	public OdVO() {
		super();
	}
	public String getComPicSrc() {
		String src = "?id="+this.comID+"&table=COMMODITY&column=COM_PICTURE&idname=COM_ID";
		return src;
	}
	
	public Integer getOmID() {
		return omID;
	}

	public void setOmID(Integer omID) {
		this.omID = omID;
	}

	public Integer getComID() {
		return comID;
	}

	public void setComID(Integer comID) {
		this.comID = comID;
	}

	public Integer getOdCount() {
		return odCount;
	}

	public void setOdCount(Integer odCount) {
		this.odCount = odCount;
	}

	public Integer getOdPrice() {
		return odPrice;
	}

	public void setOdPrice(Integer odPrice) {
		this.odPrice = odPrice;
	}

	public Integer getOdReturn() {
		return odReturn;
	}

	public void setOdReturn(Integer odReturn) {
		this.odReturn = odReturn;
	}

	public String getOdReason() {
		return odReason;
	}

	public void setOdReason(String odReason) {
		this.odReason = odReason;
	}

	public Date getOdRtime() {
		return odRtime;
	}

	public void setOdRtime(Date odRtime) {
		this.odRtime = odRtime;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
	
	
	
}
