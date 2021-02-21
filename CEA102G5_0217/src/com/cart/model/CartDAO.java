package com.cart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.commodity.model.ComVO;

public class CartDAO implements CartDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myproject");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO CART_DETAIL(MEM_ID,COM_ID,CARD_COUNT) VALUES(?,?,?)";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM CART_DETAIL WHERE COM_ID=?";
	private static final String GET_ALL_STMT =
			"SELECT * FROM CART_DETAIL ORDER BY COM_ID";
	private static final String UPDATE =
			"UPDATE CART_DETAIL SET CARD_COUNT=? WHERE COM_ID=?";
	private static final String DELETE =
			"DELETE FROM CART_DETAIL WHERE COM_ID=?";
	private static final String GET_ALL_BY_MEMID_STMT =
			"SELECT * FROM CART_DETAIL  WHERE MEM_ID=? ORDER BY COM_ID";
	@Override
	public void insert(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, cartVO.getMemID());
			pstmt.setInt(2, cartVO.getComID());
			pstmt.setInt(3, cartVO.getCardCount());
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	@Override
	public void update(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, cartVO.getCardCount());
			pstmt.setInt(2, cartVO.getComID());
			
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}			
		}
		
	}


	@Override
	public void delete(Integer comID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, comID);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}			
		}
		
	}

	@Override
	public CartVO findByPrimaryKey(Integer comID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartVO cartVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, comID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cartVO.setComID(rs.getInt("COM_ID"));
				cartVO.setCardCount(rs.getInt("CARD_COUNT"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}			
		}
		
		
		return cartVO;
	}

	@Override
	public List<CartVO> getAll() {
		List<CartVO> list = new ArrayList<CartVO>();
		CartVO cartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				cartVO = new CartVO();
				cartVO.setMemID(rs.getInt("MEM_ID"));
				cartVO.setComID(rs.getInt("COM_ID"));
				cartVO.setCardCount(rs.getInt("CARD_COUNT"));
				cartVO.setCardTime(rs.getDate("CARD_TIME"));
				
				list.add(cartVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}			
		}
		
		return list;
	}

	@Override
	public List<CartVO> getAllByMemID(Integer memID) {
		List<CartVO> list = new ArrayList<CartVO>();
		CartVO cartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMID_STMT);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cartVO = new CartVO();
				cartVO.setMemID(rs.getInt("MEM_ID"));
				cartVO.setComID(rs.getInt("COM_ID"));
				cartVO.setCardCount(rs.getInt("CARD_COUNT"));
				cartVO.setCardTime(rs.getDate("CARD_TIME"));
				
				list.add(cartVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}			
		}
		return list;
	}

}
