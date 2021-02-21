package com.commodity_category.model;

import java.util.List;
import java.util.Set;

import com.commodity.model.ComVO;

public class ComcService {
	
	private ComcDAO_Interface dao;
	
	public ComcService() {
		dao = new ComcDAO();
	}
	
	public List<ComcVO> getAll() {
		return dao.getAll();
	}

	public ComcVO getOneComc(int comcID) {
		return dao.findByPrimaryKey(comcID);
	}

	public Set<ComVO> getComsByComcID(int comcID) {
		return dao.getComsByComcID(comcID);
	}

	public void deleteComc(int comcID) {
		dao.delete(comcID);
	}
	
	public ComcVO addComc(String comcName) {
		ComcVO comcVO = new ComcVO();
		comcVO.setComcName(comcName);
		dao.insert(comcVO);
		
		return comcVO;
	}

}
