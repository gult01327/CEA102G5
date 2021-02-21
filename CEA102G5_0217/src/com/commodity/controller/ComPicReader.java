package com.commodity.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ComPicReader
 */
@WebServlet("/ComPicReader")
public class ComPicReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComPicReader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    Connection con;
    private static final String GET_PIC = "SELECT COM_PICTURE FROM COMMODITY WHERE COM_ID = ?";
    private static final String GET_PIC2 = "SELECT COM_PICTURE2 FROM COMMODITY WHERE COM_ID = ?";
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/gif");
		ServletOutputStream sos = response.getOutputStream();
	
		try {
			String pic = request.getParameter("pic").trim();
			
			if("1".equals(pic)) {
			int ID = Integer.parseInt(request.getParameter("id").trim());
			String table = request.getParameter("table");
			String column = request.getParameter("column");
			String idName = request.getParameter("idname");
			String sql = "SELECT " + column + " FROM " + table +" WHERE "+ idName+"=?";
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, ID);
			ResultSet rs = pstmt.executeQuery();
			
				if(rs.next()) {
					BufferedInputStream bis = new BufferedInputStream(rs.getBinaryStream(column));
					byte[] buff = new byte[4*1024];
					int len;
					while((len = bis.read(buff))!=-1) {
						sos.write(buff, 0, len);
					}
					bis.close();
					rs.close();
					pstmt.close();
				}
			
			}else if("2".equals(pic)) {
				int ID = Integer.parseInt(request.getParameter("id").trim());
				String table = request.getParameter("table");
				String column = request.getParameter("column");
				String idName = request.getParameter("idname");
				String sql = "SELECT " + column + pic +" FROM " + table +" WHERE "+ idName+"=?";
				
				PreparedStatement pstmt =con.prepareStatement(sql);
				pstmt.setInt(1, ID);
				ResultSet rs = pstmt.executeQuery();
				
					if(rs.next()) {
						BufferedInputStream bis = new BufferedInputStream(rs.getBinaryStream(column+pic));
						if(bis.available()==0) {
							InputStream is = getServletContext().getResourceAsStream("/NoData/none.jpg");
							byte buff[] = new byte[is.available()];
							is.read(buff);
							sos.write(buff);
							is.close();
							return;
						}
						
						byte[] buff = new byte[4*1024];
						int len;
						while((len = bis.read(buff))!=-1) {
							sos.write(buff, 0, len);
						}
						bis.close();
						rs.close();
						pstmt.close();
					}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			InputStream is = getServletContext().getResourceAsStream("/NoData/null2.jpg");
			byte buff[] = new byte[is.available()];
			is.read(buff);
			sos.write(buff);
			is.close();
		}
		
		
		
		
		
		
	}
	public void init() throws ServletException{
    	try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myproject");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
    }
	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
