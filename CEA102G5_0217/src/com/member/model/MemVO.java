package com.member.model;

import java.io.Serializable;
import java.sql.Date;

public class MemVO implements Serializable {
	private Integer memID;
	private String memName;
	private String memAccount;
	private String memPassword;
	private String memPhone;
	private String memEmail;
	private Integer memBonus;
	private Integer memStatus;
	private Date memTime;
	private byte[] memPicture;
	
	public MemVO() {
		super();
	}
	
	public String getMemPicSrc() {
		String src = "?id="+ this.memID +"&table=MEMBER_INFO&column=MEM_PICTURE&idname=MEM_ID";
		return src;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemAccount() {
		return memAccount;
	}

	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}

	public String getMemPassword() {
		return memPassword;
	}

	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public Integer getMemBonus() {
		return memBonus;
	}

	public void setMemBonus(Integer memBonus) {
		this.memBonus = memBonus;
	}

	public Integer getMemStatus() {
		return memStatus;
	}

	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}

	public Date getMemTime() {
		return memTime;
	}

	public void setMemTime(Date memTime) {
		this.memTime = memTime;
	}

	public byte[] getMemPicture() {
		return memPicture;
	}

	public void setMemPicture(byte[] memPicture) {
		this.memPicture = memPicture;
	}
	
	
	
	

}
