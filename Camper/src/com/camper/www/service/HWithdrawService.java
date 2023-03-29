package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camper.www.dao.HostDao;
import com.camper.www.dto.HostDto;

public class HWithdrawService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String s_hid = null;
		String s_hpw = request.getParameter("s_hpw");
		HostDto sessionHost = (HostDto)session.getAttribute("host");
		if(sessionHost!=null) {
			s_hid = sessionHost.getS_hid();
		}
		HostDao hDao = HostDao.getInstance();
		int result = hDao.withdrawalHost(s_hid, s_hpw);
		if(result == HostDao.SUCCESS) {
			session.invalidate();
			request.setAttribute("withdrawalResult", "회원 탈퇴가 완료되었습니다.");
		}else {
			request.setAttribute("withdrawalResult", "로그인 상태가 아닙니다");
		}
	}
}
