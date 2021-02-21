package com.member_recipient.model;

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

public class MemrDAO implements MemrDAO_interface {
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
	
	private static final String INSERT_STMT = "INSERT INTO member_recipient (MEM_ID,MEMR_NAME,MEMR_PHONE,MEMR_ADDRESS) VALUES (?,?,?,?)";
	
	private static final String GET_ALL_BY_MEMID_STMT = "SELECT MEMR_ID,MEM_ID,MEMR_NAME, MEMR_PHONE, MEMR_ADDRESS FROM MEMBER_RECIPIENT WHERE MEM_ID=?";
	
	private static final String GET_ONE_BY_MEMRID_STMT = "SELECT * FROM MEMBER_RECIPIENT WHERE MEMR_ID=?";
	
	private static final String UPDATE_STMT = "UPDATE MEMBER_RECIPIENT SET MEMR_NAME=?,MEMR_PHONE=?,MEMR_ADDRESS=? WHERE MEMR_ID=?";
	
	private static final String DELETE_STMT = "DELETE FROM MEMBER_RECIPIENT WHERE MEMR_ID=?";
	@Override
	public void insert(MemrVO memrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, memrVO.getMemID());
			pstmt.setString(2, memrVO.getMemrName());
			pstmt.setString(3, memrVO.getMemrPhone());
			pstmt.setString(4, memrVO.getMemrAddress());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(MemrVO memrVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, memrVO.getMemrName());
			pstmt.setString(2, memrVO.getMemrPhone());
			pstmt.setString(3, memrVO.getMemrAddress());
			System.out.println(memrVO.getMemrAddress());
			pstmt.setInt(4, memrVO.getMemrID());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void delete(Integer memrID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, memrID);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

	@Override
	public MemrVO findByMemrID(Integer memrID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemrVO memrVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_MEMRID_STMT);
			pstmt.setInt(1, memrID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memrVO = new MemrVO();
				memrVO.setMemrID(rs.getInt("MEMR_ID"));
				memrVO.setMemID(rs.getInt("MEM_ID"));
				memrVO.setMemrName(rs.getString("MEMR_NAME"));
				memrVO.setMemrPhone(rs.getString("MEMR_PHONE"));
				memrVO.setMemrAddress(rs.getString("MEMR_ADDRESS"));
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return memrVO;
	}

	@Override
	public List<MemrVO> getAllByMemID(Integer memID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemrVO memrVO = null;
		List<MemrVO> list = new ArrayList<MemrVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMID_STMT);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memrVO = new MemrVO();
				memrVO.setMemrID(rs.getInt("MEMR_ID"));
				memrVO.setMemID(rs.getInt("MEM_ID"));
				memrVO.setMemrName(rs.getString("MEMR_NAME"));
				memrVO.setMemrPhone(rs.getString("MEMR_PHONE"));
				memrVO.setMemrAddress(rs.getString("MEMR_ADDRESS"));
				
				list.add(memrVO);
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}

}
