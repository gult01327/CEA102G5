package com.cart.model;

import java.io.Serializable;
import java.sql.Date;

public class CartVO implements Serializable {
	private Integer memID;
	private Integer comID;
	private Integer cardCount;
	private Date cardTime;
	
	
	public CartVO() {
		super();
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getComID() {
		return comID;
	}
	public void setComID(Integer comID) {
		this.comID = comID;
	}
	public Integer getCardCount() {
		return cardCount;
	}
	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}
	public Date getCardTime() {
		return cardTime;
	}
	public void setCardTime(Date cardTime) {
		this.cardTime = cardTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comID == null) ? 0 : comID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartVO other = (CartVO) obj;
		if (comID == null) {
			if (other.comID != null)
				return false;
		} else if (!comID.equals(other.comID))
			return false;
		return true;
	}
	
	
	
}
