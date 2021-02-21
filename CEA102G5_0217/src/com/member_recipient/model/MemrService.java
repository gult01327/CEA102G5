package com.member_recipient.model;

import java.util.List;

import com.member.model.MemDAO;

public class MemrService {
	private MemrDAO_interface dao;
	
	public MemrService() {
		dao = new MemrDAO();
	}
	
	public MemrVO addMemr(Integer memID, String memrName, String memrPhone, String memrAddress) {
		
		MemrVO memrVO = new MemrVO();
		memrVO.setMemID(memID);
		memrVO.setMemrName(memrName);
		memrVO.setMemrPhone(memrPhone);
		memrVO.setMemrAddress(memrAddress);
		
		dao.insert(memrVO);
		
		return memrVO;
	}
	
	public MemrVO updateMemr(Integer memrID, String memrName, String memrPhone, String memrAddress) {
		MemrVO memrVO = new MemrVO();
		memrVO.setMemrID(memrID);
		memrVO.setMemrName(memrName);
		memrVO.setMemrPhone(memrPhone);
		memrVO.setMemrAddress(memrAddress);
		
		dao.update(memrVO);
		return memrVO;
	}
	
	public List<MemrVO> getAllByMemID(Integer memID){
		
		return dao.getAllByMemID(memID);
	}
	
	public MemrVO getByMemrID(Integer memrID) {

		return dao.findByMemrID(memrID);
	}
	
	public void deleteMemrByMemrID(Integer memrID) {
		dao.delete(memrID);
	}

}
