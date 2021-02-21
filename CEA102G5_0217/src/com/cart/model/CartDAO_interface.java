package com.cart.model;

import java.util.List;

import com.commodity.model.ComVO;

public interface CartDAO_interface {
	public void insert(CartVO cartVO);
    public void update(CartVO cartVO);
    public void delete(Integer comID);
    public CartVO findByPrimaryKey(Integer comID);
    public List<CartVO> getAll();
    public List<CartVO> getAllByMemID(Integer memID);

}
