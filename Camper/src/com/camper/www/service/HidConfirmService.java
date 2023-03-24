package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.HostDao;

public class HidConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String h_gid = request.getParameter("mid");
		HostDao hDao = HostDao.getInstance();
		int result = hDao.hidConfirm(h_gid);
		if(result == HostDao.EXISTENT) {
			request.setAttribute("hidConfirmResult", "<b>사용 중인 아이디입니다</b>");
		}else {
			request.setAttribute("hidConfirmResult", "사용 가능한 아이디입니다");
		}
	}
}
