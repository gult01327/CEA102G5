package com.member_recipient.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member_recipient.model.MemrService;
import com.member_recipient.model.MemrVO;

/**
 * Servlet implementation class MemrServlet
 */
@WebServlet("/MemrServlet")
public class MemrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemrServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("insert".equals(action)) {
			try {
				Integer memID = new Integer(request.getParameter("memID"));
				String memrName = request.getParameter("memrName");
				String memrPhone = request.getParameter("memrPhone");
				String memrAddress = request.getParameter("memrAdd");
				MemrService memrSvc = new MemrService();
				MemrVO memrVO = memrSvc.addMemr(memID, memrName, memrPhone, memrAddress);
				String url = "/front_end/member/listAllMemRec.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if("chooseAdd".equals(action)) {
			try {
				boolean openModal = true;
				request.setAttribute("openModal", openModal);
				String url = "/front_end/commodity/Checkout.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				return;
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if("confirmADD".equals(action)) {
			try {
				Integer memrID = new Integer(request.getParameter("checkedAdd"));
				
				MemrService memrSvc = new MemrService();
				MemrVO memrVO = memrSvc.getByMemrID(memrID);
				
				request.setAttribute("memrVO", memrVO);
				String url = "/front_end/commodity/Checkout.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if("getOne_For_Update".equals(action)) {
			try {
				Integer memrID = new Integer(request.getParameter("memrID"));
				MemrService memrSvc = new MemrService();
				MemrVO memrVO = memrSvc.getByMemrID(memrID);
				request.setAttribute("memrVO", memrVO);
				String url = "/front_end/member/updateMemRec.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		if("update".equals(action)) {
			Integer memrID = new Integer(request.getParameter("memrID"));
			String memrName = request.getParameter("memrName");
			String memrPhone = request.getParameter("memrPhone");
			String memrAddress = request.getParameter("memrAdd");
			
			MemrService memrSvc = new MemrService();
			MemrVO memrVO = memrSvc.updateMemr(memrID, memrName, memrPhone, memrAddress);
			
			String url = "/front_end/member/listAllMemRec.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		
		if("delete".equals(action)) {
			Integer memrID = new Integer(request.getParameter("memrID"));
			MemrService memrSvc = new MemrService();
			memrSvc.deleteMemrByMemrID(memrID);
			String url = "/front_end/member/listAllMemRec.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		
	}

}
