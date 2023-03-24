package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.HostDao;

public class HbisnumConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_hbis_num = request.getParameter("bisnum");
		HostDao hDao = HostDao.getInstance();
		int result = hDao.hBisnumConfirm(s_hbis_num);
		if(result == HostDao.EXISTENT) {
			request.setAttribute("bisnumConfirmResult", "<b>사용 중인 사업자등록번호입니다</b>");
		}else {
			request.setAttribute("bisnumConfirmResult", "사용 가능한 사업자등록번호입니다");
		}
	}
}
