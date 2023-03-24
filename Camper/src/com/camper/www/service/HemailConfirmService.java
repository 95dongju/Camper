package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.HostDao;

public class HemailConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_hemail = request.getParameter("memail");
		HostDao hDao = HostDao.getInstance();
		int result = hDao.hemailConfirm(s_hemail);
		if(result == HostDao.EXISTENT) {
			request.setAttribute("hemailConfirmResult", "<b>사용 중인 이메일입니다</b>");
		}else {
			request.setAttribute("hemailConfirmResult", "사용 가능한 이메일입니다");
		}
	}
}
