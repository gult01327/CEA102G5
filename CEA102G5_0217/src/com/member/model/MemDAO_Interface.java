package com.member.model;

import java.util.List;
import java.util.Map;

import com.commodity.model.ComVO;

public interface MemDAO_Interface {
	
	public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public void delete(Integer memID);
    public MemVO findByPrimaryKey(Integer memID);
    public MemVO findByMemAccount(String memAccount);
    public List<MemVO> getAll();
    //萬用複合查詢
    public List<MemVO> getAll(Map<String,String[]> map);

}
