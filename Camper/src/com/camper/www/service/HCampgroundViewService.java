package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.CampgroundDao;
import com.camper.www.dto.CampgroundDto;

public class HCampgroundViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_camp_no = request.getParameter("s_camp_no");
		CampgroundDao cDao = CampgroundDao.getInstance();
		CampgroundDto cgView = cDao.cgView(s_camp_no);
		request.setAttribute("cgView", cgView);
		request.setAttribute("s_camp_no", s_camp_no);
	}
}
