package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camper.www.dao.HostDao;
import com.camper.www.dto.HostDto;

public class HLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_hid = request.getParameter("mid");
		String s_hpw = request.getParameter("mpw");
		HostDao hDao = HostDao.getInstance();
		int result = hDao.hostLogin(s_hid, s_hpw);
		if(result == HostDao.LOGIN_SUCCESS) {
			HttpSession session = request.getSession();
			HostDto host = hDao.getHost(s_hid);
			session.setAttribute("s_hid", s_hid);
			session.setAttribute("host", host);
		}else {
			request.setAttribute("loginErrorMsg", "아이디와 비밀번호를 확인하세요");
		}
	}
}
