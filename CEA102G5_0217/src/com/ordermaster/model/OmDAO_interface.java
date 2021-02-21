package com.ordermaster.model;

import java.util.List;

import com.cart.model.CartVO;

public interface OmDAO_interface {
	public void insert(OmVO omVO);
    public void update(OmVO omVO);
    public void cancel(OmVO omVO);
    public OmVO findByPrimaryKey(Integer omID);
    public List<OmVO> getAll();
    public List<OmVO> getAllByMemID(Integer memID);
}
