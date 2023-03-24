package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.GuestDao;

public class GemailConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_gemail = request.getParameter("memail");
		GuestDao gDao = GuestDao.getInstance();
		int result = gDao.gemailConfirm(s_gemail);
		if(result == GuestDao.EXISTENT) {
			request.setAttribute("gemailConfirmResult", "<b>사용 중인 이메일입니다</b>");
		}else {
			request.setAttribute("gemailConfirmResult", "사용 가능한 이메일입니다");
		}
	}
}
