package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HJoinViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_hid = request.getParameter("mid");
		String s_hemail = request.getParameter("memail");
		String s_hname = request.getParameter("mname");
		String s_htel = request.getParameter("mtel");
		HttpSession session = request.getSession();
		session.setAttribute("s_hid", s_hid);
		session.setAttribute("s_hemail", s_hemail);
		session.setAttribute("s_hname", s_hname);
		session.setAttribute("s_htel", s_htel);
		session.setMaxInactiveInterval(1);
	}
}
