package com.orderdetail.model;

import java.util.List;

import com.cart.model.CartService;
import com.cart.model.CartVO;
import com.ordermaster.model.OmVO;

public class OdService {
	private OdDAO_interface dao;

	public OdService() {
		dao = new OdDAO();
	}
	
	public OmVO addOd (Integer memID, Integer mrID, Integer omPrice, String omrName, String omrPhone, String omrAdd) {
		OmVO omVO = new OmVO();
		
		omVO.setMemID(memID);
		omVO.setMrID(mrID);
		omVO.setOmPrice(omPrice);
		omVO.setOmrName(omrName);
		omVO.setOmrPhone(omrPhone);
		omVO.setOmrAddress(omrAdd);
		
		CartService cartSvc = new CartService();
		List<CartVO> list = cartSvc.getAllByMemID(memID);
		
		dao.insert(list, omVO);
		
		
		return omVO;
	}
	
	public List<OdVO> getAllByOmID(Integer omID){
		
		List<OdVO> list = dao.getAllByOmID(omID);
		
		
		return list;
	}
	
	

}
