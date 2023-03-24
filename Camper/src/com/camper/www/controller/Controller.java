package com.camper.www.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.service.GJoinService;
import com.camper.www.service.GLoginService;
import com.camper.www.service.GLogoutService;
import com.camper.www.service.GemailConfirmService;
import com.camper.www.service.GidConfirmService;
import com.camper.www.service.HJoinService;
import com.camper.www.service.HJoinViewService;
import com.camper.www.service.HLoginService;
import com.camper.www.service.HLogoutService;
import com.camper.www.service.HbisnumConfirmService;
import com.camper.www.service.HemailConfirmService;
import com.camper.www.service.HidConfirmService;
import com.camper.www.service.Service;


@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		// ***************** 공통 요청 *****************
		if(command.equals("/main.do")) {
			viewPage = "main/main.jsp";
		}else if(command.equals("/joinView.do")) {
			viewPage = "member/join.jsp";
		}else if(command.equals("/loginView.do")) {
			viewPage = "member/login.jsp";
		// ***************** 게스트/호스트 로그인 *****************
		}else if(command.equals("/gidConfirm.do")) {
			service = new GidConfirmService();
			service.execute(request, response);
			viewPage = "guest/gidConfirm.jsp";
		}else if(command.equals("/gemailConfirm.do")) {
			service = new GemailConfirmService();
			service.execute(request, response);
			viewPage = "guest/gemailConfirm.jsp";
		}else if(command.equals("/guestJoin.do")) {
			service = new GJoinService();
			service.execute(request, response);
			viewPage = "guest/loginGuest.jsp";
		}else if(command.equals("/guestLogin.do")) {
			service = new GLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/hostJoinView.do")) {
			service = new HJoinViewService();
			service.execute(request, response);
			viewPage = "host/joinHost.jsp";
		}else if(command.equals("/hostJoin.do")) {
			service = new HJoinService();
			service.execute(request, response);
			viewPage = "host/loginHost.jsp";
		}else if(command.equals("/hostLoginView.do")) {
			viewPage = "host/joinHost.jsp";
		}else if(command.equals("/hostLogin.do")) {
			service = new HLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/hidConfirm.do")) {
			service = new HidConfirmService();
			service.execute(request, response);
			viewPage = "host/hidConfirm.jsp";
		}else if(command.equals("/hemailConfirm.do")) {
			service = new HemailConfirmService();
			service.execute(request, response);
			viewPage = "host/hemailConfirm.jsp";
		}else if(command.equals("/bisnumConfirm.do")) {
			service = new HbisnumConfirmService();
			service.execute(request, response);
			viewPage = "host/bisnumConfirm.jsp";
		// ***************** 게스트/호스트 로그아웃 *****************
		}else if(command.equals("/logoutGuest.do")) {
			service = new GLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/logoutHost.do")) {
			service = new HLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/logoutAll.do")) {
			service = new GLogoutService();
			service = new HLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
