package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.GuestDao;
import com.camper.www.dto.GuestDto;

public class GModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_gid = request.getParameter("s_gid");
		GuestDao gDao = GuestDao.getInstance();
		GuestDto gDto = gDao.getGuest(s_gid);
		request.setAttribute("guest", s_gid);
	}
}
