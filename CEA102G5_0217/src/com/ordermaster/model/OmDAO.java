package com.ordermaster.model;

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

import com.cart.model.CartVO;

public class OmDAO implements OmDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myproject");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL_STMT = "SELECT * FROM ORDER_MASTER ORDER BY ORDM_ID";
	private static final String UPDATE_STMT = "UPDATE ORDER_MASTER SET ORDM_SHIP = 1 WHERE ORDM_ID = ?";
	private static final String UPDATE_STMT2 = "UPDATE ORDER_MASTER SET ORDM_SHIP = 0 WHERE ORDM_ID = ?";
	private static final String CANCEL_STMT = "UPDATE ORDER_MASTER SET ORDM_PAY = 0 WHERE ORDM_ID = ?";
	private static final String CANCEL_STMT2 = "UPDATE ORDER_MASTER SET ORDM_PAY = 1 WHERE ORDM_ID = ?";
	private static final String GET_ALL_BY_MEMID_STMT = "SELECT * FROM ORDER_MASTER WHERE MEM_ID=? ORDER BY ORDM_ID";
	@Override
	public void insert(OmVO omVO) {
		
		
	}

	@Override
	public void update(OmVO omVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			if(omVO.getOmShip() == 0) {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setInt(1, omVO.getOmID());
				
				pstmt.executeUpdate();
			}
			
			if(omVO.getOmShip() == 1) {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT2);
				
				pstmt.setInt(1, omVO.getOmID());
				
				pstmt.executeUpdate();
			}
			
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void cancel(OmVO omVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			if(omVO.getOmPay()==1) {
				con = ds.getConnection();
				pstmt = con.prepareStatement(CANCEL_STMT);
				pstmt.setInt(1, omVO.getOmID());
				
				pstmt.executeUpdate();
			}
			
			if(omVO.getOmPay()==0) {
				con = ds.getConnection();
				pstmt = con.prepareStatement(CANCEL_STMT2);
				pstmt.setInt(1, omVO.getOmID());
				
				pstmt.executeUpdate();
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		}
		
	}

	@Override
	public OmVO findByPrimaryKey(Integer omID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OmVO> getAll() {
		List<OmVO> list = new ArrayList<OmVO>();
		OmVO omVO = null;
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				omVO = new OmVO();
				omVO.setOmID(rs.getInt("ORDM_ID"));
				omVO.setMemID(rs.getInt("MEM_ID"));
				omVO.setMrID(rs.getInt("MEMR_ID"));
				omVO.setOmPrice(rs.getInt("ORDM_PRICE"));
				omVO.setOmPay(rs.getInt("ORDM_PAY"));
				omVO.setOmShip(rs.getInt("ORDM_SHIP"));
				omVO.setOmDiscount(rs.getInt("ORDM_DISCOUNT"));
				omVO.setOmTime(rs.getDate("ORDM_TIME"));
				omVO.setOmrName(rs.getString("OMR_NAME"));
				omVO.setOmrPhone(rs.getString("OMR_PHONE"));
				omVO.setOmrAddress(rs.getString("OMR_ADDRESS"));
				
				list.add(omVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

	@Override
	public List<OmVO> getAllByMemID(Integer memID) {
		List<OmVO> list = new ArrayList<OmVO>();
		OmVO omVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMID_STMT);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				omVO = new OmVO();
				omVO.setOmID(rs.getInt("ORDM_ID"));
				omVO.setMemID(rs.getInt("MEM_ID"));
				omVO.setMrID(rs.getInt("MEMR_ID"));
				omVO.setOmPrice(rs.getInt("ORDM_PRICE"));
				omVO.setOmPay(rs.getInt("ORDM_PAY"));
				omVO.setOmShip(rs.getInt("ORDM_SHIP"));
				omVO.setOmDiscount(rs.getInt("ORDM_DISCOUNT"));
				omVO.setOmTime(rs.getDate("ORDM_TIME"));
				omVO.setOmrName(rs.getString("OMR_NAME"));
				omVO.setOmrPhone(rs.getString("OMR_PHONE"));
				omVO.setOmrAddress(rs.getString("OMR_ADDRESS"));
				
				list.add(omVO);
				
			}
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
