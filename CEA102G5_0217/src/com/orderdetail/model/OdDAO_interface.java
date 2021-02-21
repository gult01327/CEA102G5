package com.orderdetail.model;

import java.util.List;

import com.cart.model.CartVO;
import com.ordermaster.model.OmVO;

public interface OdDAO_interface {
	public void insert(List<CartVO> list,OmVO omVO);
    public void update(OdVO odVO);
    public void delete(Integer omID,Integer comID );
    public CartVO findByPrimaryKey(Integer omID,Integer comID);
    public List<OdVO> getAllByOmID(Integer omID);
}
