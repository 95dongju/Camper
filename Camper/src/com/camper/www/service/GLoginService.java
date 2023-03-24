package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camper.www.dao.GuestDao;
import com.camper.www.dto.GuestDto;

public class GLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_gid = request.getParameter("mid");
		String s_gpw = request.getParameter("mpw");
		GuestDao gDao = GuestDao.getInstance();
		int result = gDao.guestLogin(s_gid, s_gpw);
		if(result == GuestDao.LOGIN_SUCCESS) {
			HttpSession session = request.getSession();
			GuestDto guest = gDao.getGuest(s_gid);
			session.setAttribute("guest", guest);
		}else {
			request.setAttribute("loginErrorMsg", "아이디와 비밀번호를 확인하세요");
		}
	}
}
