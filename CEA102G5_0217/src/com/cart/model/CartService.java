package com.cart.model;

import java.util.List;

import com.commodity.model.ComVO;

public class CartService {
	
	private CartDAO_interface dao;

	public CartService() {
		dao = new CartDAO();
	}
	
	public CartVO addCart(Integer memID, Integer comID, Integer cardCount) {
		CartVO cartVO = new CartVO();
		cartVO.setMemID(memID);
		cartVO.setComID(comID);
		cartVO.setCardCount(cardCount);
		
		dao.insert(cartVO);
		
		return cartVO; 
	}
	public List<CartVO> getAll(){
		return dao.getAll();
	}
	
	public CartVO updateCart(Integer comID , Integer cardCount) {
		CartVO cartVO = new CartVO();
		cartVO.setComID(comID);
		cartVO.setCardCount(cardCount);
		
		dao.update(cartVO);
		
		return cartVO;
	}
	
	public void deleteCart(Integer comID) {
		dao.delete(comID);
	}
	
	public List<CartVO> getAllByMemID(Integer memID){
		return dao.getAllByMemID(memID);
	}

}
