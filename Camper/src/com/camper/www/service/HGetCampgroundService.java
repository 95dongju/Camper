package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camper.www.dao.CampgroundDao;
import com.camper.www.dto.CampgroundDto;

public class HGetCampgroundService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CampgroundDto dto = (CampgroundDto)session.getAttribute("camp");
		CampgroundDao cgDao = CampgroundDao.getInstance();
		System.out.println(dto.getS_camp_no());
		CampgroundDto cgDto = cgDao.cgView(dto.getS_camp_no());
		request.setAttribute("cgDto", cgDto);
	}
}
