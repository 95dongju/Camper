package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.HostDao;
import com.camper.www.dto.HostDto;

public class HModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_hid = request.getParameter("s_hid");
		HostDao hDao = HostDao.getInstance();
		HostDto hDto = hDao.getHost(s_hid);
		request.setAttribute("host", hDto);
	}

}
