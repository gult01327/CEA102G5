package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.commodity.model.ComVO;

public class MemDAO implements MemDAO_Interface {
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
	
	private static final String GET_ONE_STMT = "SELECT * FROM MEMBER_INFO WHERE MEM_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM MEMBER_INFO";
	
	private static final String INSERT_STMT ="INSERT INTO MEMBER_INFO (MEM_NAME,MEM_ACCOUNT,MEM_PASSWORD,MEM_PHONE,MEM_EMAIL,MEM_PICTURE)"
			+ " VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE MEMBER_INFO SET MEM_NAME=?,MEM_ACCOUNT=?,MEM_PASSWORD=?,"
			+ "MEM_PHONE=?,MEM_EMAIL=?,MEM_PICTURE=? WHERE MEM_ID=?";
	private static final String UPDATE_STMT2 = "UPDATE MEMBER_INFO SET MEM_NAME=?,MEM_ACCOUNT=?,MEM_PASSWORD=?,"
			+ "MEM_PHONE=?,MEM_EMAIL=? WHERE MEM_ID=?";
	private static final String FIND_BY_ACCOUNT = "SELECT * FROM MEMBER_INFO WHERE MEM_ACCOUNT=?";
	
	
	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memVO.getMemName());
			pstmt.setString(2, memVO.getMemAccount());
			pstmt.setString(3, memVO.getMemPassword());
			pstmt.setString(4, memVO.getMemPhone());
			pstmt.setString(5, memVO.getMemEmail());
			pstmt.setBytes(6, memVO.getMemPicture());
			
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
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			if(memVO.getMemPicture().length!=0) {
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setString(1, memVO.getMemName());
				pstmt.setString(2, memVO.getMemAccount());
				pstmt.setString(3, memVO.getMemPassword());
				pstmt.setString(4, memVO.getMemPhone());
				pstmt.setString(5, memVO.getMemEmail());
				pstmt.setBytes(6, memVO.getMemPicture());
				pstmt.setInt(7, memVO.getMemID());
				
				pstmt.executeUpdate();
			}else {
				pstmt = con.prepareStatement(UPDATE_STMT2);
				
				pstmt.setString(1, memVO.getMemName());
				pstmt.setString(2, memVO.getMemAccount());
				pstmt.setString(3, memVO.getMemPassword());
				pstmt.setString(4, memVO.getMemPhone());
				pstmt.setString(5, memVO.getMemEmail());
				pstmt.setInt(6, memVO.getMemID());
				
				pstmt.executeUpdate();
			}
			
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
	public void delete(Integer memID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemVO findByPrimaryKey(Integer memID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemVO memVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, memID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemID(rs.getInt("MEM_ID"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemAccount(rs.getString("MEM_ACCOUNT"));
				memVO.setMemPassword(rs.getString("MEM_PASSWORD"));
				memVO.setMemPhone(rs.getString("MEM_PHONE"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemBonus(rs.getInt("MEM_BONUS"));
				memVO.setMemStatus(rs.getInt("MEM_STATUS"));
				memVO.setMemTime(rs.getDate("MEM_TIME"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(rs != null) {
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
		
		
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemID(rs.getInt("MEM_ID"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemAccount(rs.getString("MEM_ACCOUNT"));
				memVO.setMemPassword(rs.getString("MEM_PASSWORD"));
				memVO.setMemPhone(rs.getString("MEM_PHONE"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemBonus(rs.getInt("MEM_BONUS"));
				memVO.setMemStatus(rs.getInt("MEM_STATUS"));
				memVO.setMemTime(rs.getDate("MEM_TIME"));
				
				list.add(memVO);
			}
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		} finally {
			if(rs != null) {
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

	@Override
	public List<MemVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemVO findByMemAccount(String memAccount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemVO memVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_ACCOUNT);
			pstmt.setString(1, memAccount);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemID(rs.getInt("MEM_ID"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemAccount(rs.getString("MEM_ACCOUNT"));
				memVO.setMemPassword(rs.getString("MEM_PASSWORD"));
				memVO.setMemPhone(rs.getString("MEM_PHONE"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemBonus(rs.getInt("MEM_BONUS"));
				memVO.setMemStatus(rs.getInt("MEM_STATUS"));
				memVO.setMemTime(rs.getDate("MEM_TIME"));
			}
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. no this member :"+se.getMessage());
		} finally {
			if(rs != null) {
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
		
		return memVO;
	}

}
