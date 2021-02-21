package com.orderdetail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cart.model.CartVO;
import com.commodity.model.ComService;
import com.commodity.model.ComVO;
import com.ordermaster.model.OmVO;

public class OdDAO implements OdDAO_interface {
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
	
	//SQL指令
	private static final String OMINSERT_STMT = 
			"INSERT INTO ORDER_MASTER(MEM_ID,MEMR_ID,ORDM_PRICE,OMR_NAME,OMR_PHONE,OMR_ADDRESS) VALUES(?,?,?,?,?,?)";
	private static final String ODINSERT_STMT = 
			"INSERT INTO ORDER_DETAIL(ORDM_ID,COM_ID,ORDD_COUNT,ORDD_PRICE) VALUES(?,?,?,?)";
	private static final String GETALLBYOMID_STMT = 
			"SELECT * FROM ORDER_DETAIL WHERE ORDM_ID = ?";
	private static final String DELETEALLCART_STMT = 
			"DELETE FROM CART_DETAIL WHERE MEM_ID=?";
	private static final String GETALLBYOMIDJOIN_STMT = 
			"SELECT ORDM_ID,D.COM_ID,COM_NAME,COM_PICTURE,ORDD_COUNT,ORDD_PRICE,ORDD_RETURN,ORDD_REASON,ORDD_RTIME\r\n" + 
			"FROM ORDER_DETAIL D LEFT JOIN COMMODITY C ON D.COM_ID = C.COM_ID\r\n" + 
			"WHERE ORDM_ID = ?";
	@Override
	public void insert(List<CartVO> list, OmVO omVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			//1.新增主檔
			int[] cols = {1};
			pstmt = con.prepareStatement(OMINSERT_STMT,cols);
			
			pstmt.setInt(1, omVO.getMemID());
			pstmt.setInt(2, omVO.getMrID());
			pstmt.setInt(3, omVO.getOmPrice());
			pstmt.setString(4, omVO.getOmrName());
			pstmt.setString(5, omVO.getOmrPhone());
			pstmt.setString(6, omVO.getOmrAddress());
			
			pstmt.executeUpdate();
			
//			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//			rs = stmt.executeQuery("SELECT * FROM ORDER_MASTER");
//			rs.last();
//			Integer ordmID = rs.getInt("ORDM_ID");
			
			ResultSet rs2 = pstmt.getGeneratedKeys();
			Integer ordmID = null;
			if(rs2.next()) {
				ordmID = rs2.getInt(1);
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			ComService comSvc = new ComService();
			ComVO comVO = null;
			//2.新增明細
			for(CartVO cartVO : list) {
				
				pstmt = con.prepareStatement(ODINSERT_STMT);
				pstmt.setInt(1, ordmID);
				pstmt.setInt(2, cartVO.getComID());
				pstmt.setInt(3, cartVO.getCardCount());
				comVO = comSvc.getOneCom(cartVO.getComID());
				pstmt.setInt(4, comVO.getComPrice());
				
				pstmt.executeUpdate();
			}
			//3.清空購物車
			pstmt = con.prepareStatement(DELETEALLCART_STMT);
			pstmt.setInt(1, omVO.getMemID());
			pstmt.executeUpdate();
			
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("新增商品主檔ID:" + ordmID + "時，同時新增了" + list.size()+"筆訂單明細");			
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured."+se.getMessage());
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
	}
	@Override
	public void update(OdVO odVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer omID, Integer comID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CartVO findByPrimaryKey(Integer omID, Integer comID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OdVO> getAllByOmID(Integer omID) {
		List<OdVO> list = new ArrayList();
		OdVO odVO = null;
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALLBYOMIDJOIN_STMT);
			pstmt.setInt(1, omID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				odVO = new OdVO();
				odVO.setOmID(rs.getInt("ORDM_ID"));
				odVO.setComID(rs.getInt("COM_ID"));
				odVO.setComName(rs.getString("COM_NAME"));
				odVO.setOdCount(rs.getInt("ORDD_COUNT"));
				odVO.setOdPrice(rs.getInt("ORDD_PRICE"));
				odVO.setOdReturn(rs.getInt("ORDD_RETURN"));
				odVO.setOdReason(rs.getString("ORDD_REASON"));
				odVO.setOdRtime(rs.getDate("ORDD_RTIME"));
				
				list.add(odVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
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
