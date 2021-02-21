package com.recipe.model;

import java.io.Serializable;
import java.sql.Date;

public class RecVO implements Serializable {
	private Integer recID;
	private Integer memID;
	private String recName;
	private byte[] recPicture1;
	private byte[] recPicture2;
	private byte[] recPicture3;
	private byte[] recVideo;
	private String recFunction;
	private Float recCal;
	private Float recCarb;
	private Float recProtein;
	private Float recFat;
	private Integer recStatus;
	private Date recTime;
	private Date recADate;
	private Integer recBonus;
	private String recContent;
	private Integer recSize;
	private Integer recCooktime;
	
	
	public RecVO() {
		super();
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


	public String getRecName() {
		return recName;
	}


	public void setRecName(String recName) {
		this.recName = recName;
	}


	public byte[] getRecPicture1() {
		return recPicture1;
	}


	public void setRecPicture1(byte[] recPicture1) {
		this.recPicture1 = recPicture1;
	}


	public byte[] getRecPicture2() {
		return recPicture2;
	}


	public void setRecPicture2(byte[] recPicture2) {
		this.recPicture2 = recPicture2;
	}


	public byte[] getRecPicture3() {
		return recPicture3;
	}


	public void setRecPicture3(byte[] recPicture3) {
		this.recPicture3 = recPicture3;
	}


	public byte[] getRecVideo() {
		return recVideo;
	}


	public void setRecVideo(byte[] recVideo) {
		this.recVideo = recVideo;
	}


	public String getRecFunction() {
		return recFunction;
	}


	public void setRecFunction(String recFunction) {
		this.recFunction = recFunction;
	}


	public Float getRecCal() {
		return recCal;
	}


	public void setRecCal(Float recCal) {
		this.recCal = recCal;
	}


	public Float getRecCarb() {
		return recCarb;
	}


	public void setRecCarb(Float recCarb) {
		this.recCarb = recCarb;
	}


	public Float getRecProtein() {
		return recProtein;
	}


	public void setRecProtein(Float recProtein) {
		this.recProtein = recProtein;
	}


	public Float getRecFat() {
		return recFat;
	}


	public void setRecFat(Float recFat) {
		this.recFat = recFat;
	}


	public Integer getRecStatus() {
		return recStatus;
	}


	public void setRecStatus(Integer recStatus) {
		this.recStatus = recStatus;
	}


	public Date getRecTime() {
		return recTime;
	}


	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}


	public Date getRecADate() {
		return recADate;
	}


	public void setRecADate(Date recADate) {
		this.recADate = recADate;
	}


	public Integer getRecBonus() {
		return recBonus;
	}


	public void setRecBonus(Integer recBonus) {
		this.recBonus = recBonus;
	}


	public String getRecContent() {
		return recContent;
	}


	public void setRecContent(String recContent) {
		this.recContent = recContent;
	}


	public Integer getRecSize() {
		return recSize;
	}


	public void setRecSize(Integer recSize) {
		this.recSize = recSize;
	}


	public Integer getRecCooktime() {
		return recCooktime;
	}


	public void setRecCooktime(Integer recCooktime) {
		this.recCooktime = recCooktime;
	}
	
	
	
	
	
}
