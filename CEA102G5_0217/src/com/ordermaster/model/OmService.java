package com.ordermaster.model;

import java.util.List;

import com.commodity_category.model.ComcVO;

public class OmService {
	private OmDAO_interface dao;
	
	public OmService() {
		dao = new OmDAO();
	}
	
	public OmVO updateOmShip(Integer omID,Integer omShip) {
		OmVO omVO = new OmVO();
		omVO.setOmID(omID);
		omVO.setOmShip(omShip);
		dao.update(omVO);
		
		return omVO;
	}
	
	public OmVO updateOmPay(Integer omID, Integer omPay) {
		OmVO omVO = new OmVO();
		omVO.setOmID(omID);
		omVO.setOmPay(omPay);
		dao.cancel(omVO);
		
		return omVO;
	}

	
	public List<OmVO> getAll() {
		return dao.getAll();
	}
	
	public List<OmVO> getAllbyMemID(Integer memID){
		return dao.getAllByMemID(memID);
	}

}
