package com.commodity.model;

import java.io.Serializable;
import java.sql.Date;

import com.commodity_category.model.ComcVO;

public class ComVO implements Serializable {
	private int comID;
	private int comcID;
	private String comName;
	private String comcName;
	private int comPrice;
	private byte[] comPicture;
	private byte[] comPicture2;
	private String comContent;
	private int comStatus;
	private int comWeight;
	private String comUnit;
	private float comCal;
	private float comCarb;
	private float ComPro;
	private float ComFat;
	private String ComProp;
	private int comSales;
	private Date comTime;
	
	
	
	
	public ComVO() {
		super();
	}
	
	
	
	public String getComPicSrc() {
		String src = "?id="+this.comID+"&table=COMMODITY&column=COM_PICTURE&idname=COM_ID";
		return src;
	}

	public int getComID() {
		return comID;
	}


	public void setComID(int comID) {
		this.comID = comID;
	}


	public int getComcID() {
		return comcID;
	}


	public void setComcID(int comcID) {
		this.comcID = comcID;
	}


	public String getComName() {
		return comName;
	}


	public void setComName(String comName) {
		this.comName = comName;
	}


	public int getComPrice() {
		return comPrice;
	}


	public void setComPrice(int comPrice) {
		this.comPrice = comPrice;
	}


	public byte[] getComPicture() {
		return comPicture;
	}


	public void setComPicture(byte[] comPicture) {
		this.comPicture = comPicture;
	}


	public String getComContent() {
		return comContent;
	}


	public void setComContent(String comContent) {
		this.comContent = comContent;
	}


	public int getComStatus() {
		return comStatus;
	}


	public void setComStatus(int comStatus) {
		this.comStatus = comStatus;
	}


	public int getComWeight() {
		return comWeight;
	}


	public void setComWeight(int comWeight) {
		this.comWeight = comWeight;
	}


	public String getComUnit() {
		return comUnit;
	}


	public void setComUnit(String comUnit) {
		this.comUnit = comUnit;
	}


	public float getComCal() {
		return comCal;
	}


	public void setComCal(float comCal) {
		this.comCal = comCal;
	}


	public float getComCarb() {
		return comCarb;
	}


	public void setComCarb(float comCarb) {
		this.comCarb = comCarb;
	}


	public float getComPro() {
		return ComPro;
	}


	public void setComPro(float comPro) {
		ComPro = comPro;
	}


	public float getComFat() {
		return ComFat;
	}


	public void setComFat(float comFat) {
		ComFat = comFat;
	}


	public String getComProp() {
		return ComProp;
	}


	public void setComProp(String comProp) {
		ComProp = comProp;
	}


	public int getComSales() {
		return comSales;
	}


	public void setComSales(int comSales) {
		this.comSales = comSales;
	}


	public Date getComTime() {
		return comTime;
	}


	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}


	public String getComcName() {
		return comcName;
	}


	public void setComcName(String comcName) {
		this.comcName = comcName;
	}


	public byte[] getComPicture2() {
		return comPicture2;
	}


	public void setComPicture2(byte[] comPicture2) {
		this.comPicture2 = comPicture2;
	}
	
	
	
	

}