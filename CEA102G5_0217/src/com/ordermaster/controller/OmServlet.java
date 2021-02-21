package com.ordermaster.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orderdetail.model.OdService;
import com.orderdetail.model.OdVO;
import com.ordermaster.model.OmService;
import com.ordermaster.model.OmVO;

/**
 * Servlet implementation class OmServlet
 */
@WebServlet("/OmServlet")
public class OmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		if("BUY".equals(action)) {

			try {
				String omrAdd = request.getParameter("omrAdd");
				System.out.println(omrAdd);
				String omrName = request.getParameter("omrName");
				System.out.println(omrName);
				String omrPhone = request.getParameter("omrPhone");
				System.out.println(omrPhone);
				Integer memID = new Integer(request.getParameter("memID"));
				Integer memrID = new Integer(request.getParameter("memrID"));
				String amount = (String)session.getAttribute("amount");
				Integer omPrice = new Integer(amount);
				session.removeAttribute("amount");
				
				OdService odSvc = new OdService();
				OmVO omVO = odSvc.addOd(memID, memrID, omPrice, omrName,omrPhone,omrAdd);
				
				request.setAttribute("omVO", omVO);//¨S¥Î¨ì
				String url = "/front_end/commodity/listOmbyMemID.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if("ListOd_ByOmID".equals(action)) {
			String requestURL = request.getParameter("requestURL");
			try {
				Integer omID = new Integer(request.getParameter("omID"));
				
				OdService odSvc = new OdService();
				List<OdVO> list = odSvc.getAllByOmID(omID);
				
				request.setAttribute("list", list);
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if("Update_omShip".equals(action)) {
			String requestURL = request.getParameter("requestURL");
				try {
					Integer omID = new Integer(request.getParameter("omID"));
					Integer omShip = new Integer(request.getParameter("check"));
					OmService omSvc = new OmService();
					omSvc.updateOmShip(omID,omShip);
					String url = requestURL;
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

		}
		
		if("Cancel_om".equals(action)) {
			String requestURL = request.getParameter("requestURL");
			
			try {
				Integer omID = new Integer(request.getParameter("omID"));
				Integer omPay = new Integer(request.getParameter("check"));
				
				OmService omSvc = new OmService();
				omSvc.updateOmPay(omID, omPay);
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);		
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
