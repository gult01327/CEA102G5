package com.member_recipient.model;

import java.util.List;



public interface MemrDAO_interface {
	
	public void insert(MemrVO memrVO);
    public void update(MemrVO memrVO);
    public void delete(Integer memrID);
    public MemrVO findByMemrID(Integer memrID);
    public List<MemrVO> getAllByMemID(Integer memID);
}
