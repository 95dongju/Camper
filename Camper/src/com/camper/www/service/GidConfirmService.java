package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.GuestDao;

public class GidConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_gid = request.getParameter("mid");
		GuestDao gDao = GuestDao.getInstance();
		int result = gDao.gidConfirm(s_gid);
		if(result == GuestDao.EXISTENT) {
			request.setAttribute("gidConfirmResult", "<b>사용 중인 아이디입니다</b>");
		}else {
			request.setAttribute("gidConfirmResult", "사용 가능한 아이디입니다");
		}
	}
}
