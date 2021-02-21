package com.commodity_category.model;

import java.util.List;
import java.util.Set;

import com.commodity.model.ComVO;



public interface ComcDAO_Interface {
	 public void insert(ComcVO comcVO);
     public void update(ComcVO comcVO);
     public void delete(int comcID);
     public ComcVO findByPrimaryKey(int comcID);
     public List<ComcVO> getAll();
     //查詢某部門的員工(一對多)(回傳 Set)
     public Set<ComVO> getComsByComcID(int comcID);

}
