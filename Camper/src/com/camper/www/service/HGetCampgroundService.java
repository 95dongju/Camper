package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.CampgroundDao;
import com.camper.www.dto.CampgroundDto;

public class HGetCampgroundService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_camp_no = request.getParameter("s_camp_no");
		CampgroundDao cgDao = CampgroundDao.getInstance();
		CampgroundDto cgDto = cgDao.cgView(s_camp_no);
		System.out.println(cgDto);
		request.setAttribute("cgDto", cgDto);
	}
}
