package com.commodity_category.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.commodity.model.ComVO;

public class ComcDAO implements ComcDAO_Interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myproject");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO COMMODITY_CATEGORY (COMC_NAME) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT COMC_ID, COMC_NAME FROM COMMODITY_CATEGORY";
	private static final String GET_ONE_STMT = "SELECT COMC_ID, COMC_NAME FROM COMMODITY_CATEGORY WHERE COMC_ID = ?";
	private static final String GET_COM_ByCOMCID_STMT = "SELECT * FROM COMMODITY WHERE COMC_ID = ? ORDER BY COM_ID";
	
	private static final String DELETE_COM = "DELETE FROM COMMODITY WHERE COMC_ID = ?";
	private static final String DELETE_COMMC = "DELETE FROM COMMODITY_CATEGORY WHERE COMC_ID = ?";	
	
	private static final String UPDATE = "UPDATE COMMODITY_CATEGORY SET COMC_NAME=? WHERE COMC_ID = ?";
	
	@Override
	public void insert(ComcVO comcVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, comcVO.getComcName());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(ComcVO comcVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, comcVO.getComcName());
			pstmt.setInt(2, comcVO.getComcID());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}  finally {
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
	public void delete(int comcID) {
		int updateCount_COM = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_COM);
			pstmt.setInt(1, comcID);
			updateCount_COM = pstmt.executeUpdate();
			
			//刪商品分類
			pstmt = con.prepareStatement(DELETE_COMMC);
			pstmt.setInt(1, comcID);
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除分類編號" + comcID + "時，共有商品" + updateCount_COM + "件商品同時被刪除");
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}  finally {
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
	public ComcVO findByPrimaryKey(int comcID) {
		ComcVO comcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1,comcID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				comcVO = new ComcVO();
				comcVO.setComcID(rs.getInt("COMC_ID"));
				comcVO.setComcName(rs.getString("COMC_NAME"));
				
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return comcVO;
	}

	@Override
	public List<ComcVO> getAll() {
		List<ComcVO> list = new ArrayList<ComcVO>();
		ComcVO comcVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				comcVO = new ComcVO();
				comcVO.setComcID(rs.getInt("COMC_ID"));
				comcVO.setComcName(rs.getString("COMC_NAME"));
				
				list.add(comcVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}  finally {
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
	public Set<ComVO> getComsByComcID(int comcID) {
		Set<ComVO> set = new LinkedHashSet<ComVO>();
		ComVO comVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COM_ByCOMCID_STMT);
			pstmt.setInt(1, comcID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				comVO = new ComVO();
				comVO.setComID(rs.getInt("COM_ID"));
				comVO.setComcID(rs.getInt("COMC_ID"));
				comVO.setComName(rs.getString("COM_NAME"));
				comVO.setComPrice(rs.getInt("COM_PRICE"));
				comVO.setComPicture(rs.getBytes("COM_PICTURE"));
				comVO.setComContent(rs.getString("COM_CONTENT"));
				comVO.setComStatus(rs.getInt("COM_STATUS"));
				comVO.setComWeight(rs.getInt("COM_WEIGHT"));
				comVO.setComUnit(rs.getString("COM_UNIT"));
				comVO.setComCal(rs.getFloat("COM_CAL"));
				comVO.setComCarb(rs.getFloat("COM_CARB"));
				comVO.setComPro(rs.getFloat("COM_PRO"));
				comVO.setComFat(rs.getFloat("COM_FAT"));
				comVO.setComProp(rs.getString("COM_PROP"));
				comVO.setComSales(rs.getInt("COM_SALES"));
				comVO.setComTime(rs.getDate("COM_TIME"));
				
				set.add(comVO);
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		return set;
	}

}
