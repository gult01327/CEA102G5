package com.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartService;
import com.cart.model.CartVO;
import com.commodity.model.ComService;
import com.commodity.model.ComVO;
import com.member.model.MemVO;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
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
		System.out.println(action);
		HttpSession session = request.getSession();
		
		if("getOne_For_Cart".equals(action)) {
			
			try {
				int comID = Integer.parseInt(request.getParameter("comID"));
				ComService comSvc = new ComService();
				ComVO comVO = comSvc.getOneCom(comID);
				request.setAttribute("comVO", comVO);
				String url = "/front_end/commodity/listOneCom_Cart.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if("ADD".equals(action)) {
			Object memVO = session.getAttribute("memVO");
			String location = request.getParameter("location");
			if(memVO==null) {
				session.setAttribute("location", location);
				response.sendRedirect(request.getContextPath()+"/front_end/member/login.jsp");
				return;
			}
			
			try {
				MemVO memVO2 = (MemVO)memVO;
				
				//抓出購物車table內所有VO檢查是否有comID重複的
				CartService cartSvc = new CartService();
				Integer memID = memVO2.getMemID();
				List list = cartSvc.getAllByMemID(memID);
				//宣告一個VO將值包裝進去判斷集合內是否有一樣的物件
				CartVO cartVO = new CartVO();
				Integer comID = new Integer(request.getParameter("comID"));
				Integer cardCount = new Integer(request.getParameter("cardCount"));
				cartVO.setComID(comID);
				cartVO.setMemID(memID);
				cartVO.setCardCount(cardCount);
				if (list.contains(cartVO)) {
					//如果有重複的呼叫service update更改count
					CartVO inCartVO = (CartVO) list.get(list.indexOf(cartVO));
					Integer addCount = inCartVO.getCardCount() + cartVO.getCardCount();
					CartVO newCartVO = cartSvc.updateCart(comID, addCount);
					ComService comSvc = new ComService();
					ComVO comVO = comSvc.getOneCom(comID);
					request.setAttribute("comVO", comVO);
					String url = "/front_end/commodity/listOneCom_Cart.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
				} else {
					//如果沒有重複的呼叫service insert插入一筆資料
					CartVO newCartVO = cartSvc.addCart(memID, comID, cardCount);
					ComService comSvc = new ComService();
					ComVO comVO = comSvc.getOneCom(comID);
					request.setAttribute("comVO", comVO);
					String url = "/front_end/commodity/listOneCom_Cart.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if("delete_Cart".equals(action)) {
			try {
				Integer comID = new Integer(request.getParameter("comID"));
				CartService cartSvc = new CartService();
				cartSvc.deleteCart(comID);
				String url = "/front_end/commodity/listAllCart.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("CHECKOUT".equals(action)) {
			try {
				MemVO memVO = (MemVO)session.getAttribute("memVO");

				CartService cartSvc = new CartService();
				List<CartVO> list = cartSvc.getAllByMemID(memVO.getMemID());
				int total = 0;
				ComService comSvc = new ComService();
				for (int i = 0; i < list.size(); i++) {
					CartVO cartVO = list.get(i);
					int counts = cartVO.getCardCount();
					ComVO comVO = comSvc.getOneCom(cartVO.getComID());
					int price = comVO.getComPrice();
					total += (counts * price);
				}
				String amount = String.valueOf(total);
				session.setAttribute("amount", amount);
				String url = "/front_end/commodity/Checkout.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("minusCount".equals(action)) {
			try {
				Integer comID = new Integer(request.getParameter("comID"));
				Integer cardCount = new Integer(request.getParameter("cardCount"));
				System.out.println(comID);
				System.out.println(cardCount);
				CartService cartSvc = new CartService();
				CartVO cartVO = cartSvc.updateCart(comID, cardCount - 1);
//				PrintWriter out = response.getWriter();
//				out.print(cartVO.getCardCount());
				String url = "/front_end/commodity/listAllCart.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		if("plusCount".equals(action)) {
			try {
				Integer comID = new Integer(request.getParameter("comID"));
				Integer cardCount = new Integer(request.getParameter("cardCount"));
				CartService cartSvc = new CartService();
				cartSvc.updateCart(comID, cardCount + 1);
				String url = "/front_end/commodity/listAllCart.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
