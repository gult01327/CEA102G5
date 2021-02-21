package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.commodity.model.ComVO;
import com.member.model.MemService;
import com.member.model.MemVO;

/**
 * Servlet implementation class MemServlet
 */
@WebServlet("/MemServlet")
@MultipartConfig
public class MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemServlet() {
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
		HttpSession session = request.getSession();
		if("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String memAccount = request.getParameter("memAccount").trim();
				
				if(memAccount==null || memAccount.trim().length()==0) {
					errorMsgs.add("會員帳號請勿空白");
				}
				String memPassword = request.getParameter("memPassword").trim();
				
				if(memPassword==null||memPassword.trim().length()==0) {
					errorMsgs.add("會員密碼請勿空白");
				}
				
				MemVO memVO = null;
				try {
					MemService memSvc = new MemService();
					memVO = memSvc.getOneMemByAccount(memAccount);
					
					if(memVO.getMemPassword().equals(memPassword)) {
						session.setAttribute("memVO", memVO);
						String location = (String)session.getAttribute("location");
						ComVO comVO = (ComVO)session.getAttribute("comVO");
						if(location!=null) {
							session.removeAttribute("location");
							session.removeAttribute("comVO");
							request.setAttribute("comVO", comVO);
							System.out.println(location);
							RequestDispatcher successView = request.getRequestDispatcher(location);
							successView.forward(request, response);
							
							return;
						}
						String url = "/front_end/commodity/comindex.jsp";
						RequestDispatcher successView = request.getRequestDispatcher(url);
						successView.forward(request, response);
					}else {
						errorMsgs.add("密碼有誤，請重新輸入");
						RequestDispatcher failView = request.getRequestDispatcher("/front_end/member/login.jsp");
						failView.forward(request, response);
					}
				} catch (Exception e) {
					errorMsgs.add("帳號有誤，查無此人");
					RequestDispatcher failView = request.getRequestDispatcher("/front_end/member/login.jsp");
					failView.forward(request, response);
				}
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/front_end/member/login.jsp");
				failView.forward(request, response);
			}
			
			
		}
		
		if("logout".equals(action)) {
			session.removeAttribute("memVO");
			String url = "/front_end/commodity/comindex.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer memID = new Integer(request.getParameter("memID"));
				
				MemService memSvc = new MemService();
				
				MemVO memVO = memSvc.getOneMem(memID);
				request.setAttribute("memVO", memVO);
				String url = "/back_end/member/listOneMem.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				String memAccountReg = "^[(a-zA-Z0-9_)]{2,10}$";
				
				String memName = request.getParameter("memName");
				if(memName == null || memName.trim().length()==0) {
					errorMsgs.add("會員名稱請勿空白");
				}else if(!memName.trim().matches(memNameReg)) {
					errorMsgs.add("會員名稱只能是中英文字母數字和_，且長度必須在2~10之間");
				}
				
				String memAccount = request.getParameter("memAccount");
				if(memAccount==null || memAccount.trim().length()==0) {
					errorMsgs.add("會員帳號請勿空白");
				}else if(!memAccount.trim().matches(memAccountReg)) {
					errorMsgs.add("會員帳號只能是英文字母數字和_，且長度必須在2~10之間");
				}
				
				
				String memPassword = request.getParameter("memPassword");
				if(memPassword==null||memPassword.trim().length()==0) {
					errorMsgs.add("會員密碼請勿空白");
				}else if(!memAccount.trim().matches(memAccountReg)) {
					errorMsgs.add("會員密碼只能是英文字母數字和_，且長度必須在2~10之間");
				}
				
				String memPhoneReg = "^[(0-9)]{2,11}$";
				String memPhone = request.getParameter("memPhone");
				if(memPhone==null||memPhone.trim().length()==0) {
					errorMsgs.add("會員電話請勿空白");
				}else if(!memPhone.trim().matches(memPhoneReg)) {
					errorMsgs.add("會員電話只能是數字和，且長度必須在2~11之間");
				}
				String memEmail = request.getParameter("memEmail");
				if(memEmail==null||memEmail.trim().length()==0) {
					errorMsgs.add("會員Email請勿空白");
				}
				
				Part part = request.getPart("memUpfile");
				byte[] memPicture = null;
				
				if(part.getSize()!=0) {
					
				InputStream is = part.getInputStream();
				memPicture = new byte[is.available()];
				is.read(memPicture);
				is.close();
				
				}else {
					errorMsgs.add("請上傳圖片");
				}
				
				MemVO memVO = new MemVO();
				memVO.setMemName(memName);
				memVO.setMemAccount(memAccount);
				memVO.setMemPassword(memPassword);
				memVO.setMemPhone(memPhone);
				memVO.setMemEmail(memEmail);
				
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("memVO", memVO);
					RequestDispatcher failView = request.getRequestDispatcher("/back_end/member/addMem.jsp");
					failView.forward(request, response);
				}
				
				MemService memSvc = new MemService();
				memSvc.addMem(memName, memAccount, memPassword, memPhone, memEmail, memPicture);
				
				String url = "/back_end/member/listAllMem.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/back_end/member/addMem.jsp");
				failView.forward(request, response);
			}
			
			
		}
		
		if("getOne_For_Update".equals(action)) {
			
			try {
				Integer memID = new Integer(request.getParameter("memID"));
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memID);
				
				request.setAttribute("memVO", memVO);
				String url = "/back_end/member/updateMem.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				String memAccountReg = "^[(a-zA-Z0-9_)]{2,10}$";
				String memName = request.getParameter("memName");
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("會員名稱請勿空白");
				} else if (!memName.trim().matches(memNameReg)) {
					errorMsgs.add("會員名稱只能是中英文字母數字和_，且長度必須在2~10之間");
				}
				String memAccount = request.getParameter("memAccount");
				if (memAccount == null || memAccount.trim().length() == 0) {
					errorMsgs.add("會員帳號請勿空白");
				} else if (!memAccount.trim().matches(memAccountReg)) {
					errorMsgs.add("會員帳號只能是英文字母數字和_，且長度必須在2~10之間");
				}
				String memPassword = request.getParameter("memPassword");
				if (memPassword == null || memPassword.trim().length() == 0) {
					errorMsgs.add("會員密碼請勿空白");
				} else if (!memAccount.trim().matches(memAccountReg)) {
					errorMsgs.add("會員密碼只能是英文字母數字和_，且長度必須在2~10之間");
				}
				String memPhoneReg = "^[(0-9)]{2,11}$";
				String memPhone = request.getParameter("memPhone");
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("會員電話請勿空白");
				} else if (!memPhone.trim().matches(memPhoneReg)) {
					errorMsgs.add("會員電話只能是數字和，且長度必須在2~11之間");
				}
				String memEmail = request.getParameter("memEmail");
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.add("會員Email請勿空白");
				}
				
				Part part = request.getPart("memUpfile");
				byte[] memPicture = null;
				InputStream is = part.getInputStream();
				memPicture = new byte[is.available()];
				is.read(memPicture);
				is.close();
				
				Integer memID = new Integer(request.getParameter("memID"));
					
					
				MemVO memVO = new MemVO();
				memVO.setMemName(memName);
				memVO.setMemAccount(memAccount);
				memVO.setMemPassword(memPassword);
				memVO.setMemPhone(memPhone);
				memVO.setMemEmail(memEmail);
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("memVO", memVO);
					RequestDispatcher failView = request.getRequestDispatcher("/back_end/member/updateMem.jsp");
					failView.forward(request, response);
				}
				
				MemService memSvc = new MemService();
				memSvc.updateMem(memID, memName, memAccount, memPassword, memPhone, memEmail, memPicture);
				String url = "/back_end/member/listAllMem.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = request.getRequestDispatcher("/back_end/member/updateMem.jsp");
				failView.forward(request, response);
			}
			
		}
		
		
	}

}
