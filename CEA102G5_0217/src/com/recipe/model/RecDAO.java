package com.recipe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RecDAO implements RecDAO_interface {
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
	
	//SQL«ü¥O
	private static final String INSERT_STMT = "INSERT INTO RECIPE (MEM_ID,REC_NAME,REC_PICTURE_1,REC_PICTURE_2,REC_PICTURE_3\r\n" + 
			",REC_VIDEO,REC_FUNCTION,REC_CAL,REC_CARB,REC_PROT,REC_FAT,REC_STATUS,\r\n" + 
			"REC_BONUS,REC_CONTENT,REC_SIZE,REC_COOKTIME)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	@Override
	public void insert(RecVO recVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, recVO.getMemID());
			pstmt.setString(2, recVO.getRecName());
			pstmt.setBytes(3, recVO.getRecPicture1());
			pstmt.setBytes(4, recVO.getRecPicture2());
			pstmt.setBytes(5, recVO.getRecPicture3());
			pstmt.setBytes(6, recVO.getRecVideo());
			pstmt.setString(7, recVO.getRecFunction());
			pstmt.setFloat(8, recVO.getRecCal());
			pstmt.setFloat(9, recVO.getRecCarb());
			pstmt.setFloat(10, recVO.getRecProtein());
			pstmt.setFloat(11, recVO.getRecFat());
			pstmt.setInt(12, recVO.getRecStatus());
			pstmt.setInt(13, recVO.getRecBonus());
			pstmt.setString(14, recVO.getRecContent());
			pstmt.setInt(15, recVO.getRecSize());
			pstmt.setInt(16, recVO.getRecCooktime());
			
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
	public void update(RecVO recVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer recID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecVO findByPrimaryKey(Integer recID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecVO> getAllByMemID(Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
