package com.camper.www.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.CampgroundDao;
import com.camper.www.dao.CampsiteDao;
import com.camper.www.dto.CampgroundDto;
import com.camper.www.dto.CampsiteDto;

public class HCampgroundViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_camp_no = request.getParameter("s_camp_no");
		CampgroundDao cDao = CampgroundDao.getInstance();
		CampgroundDto cgView = cDao.cgView(s_camp_no);
		request.setAttribute("cgView", cgView);
		request.setAttribute("s_camp_no", s_camp_no);
		CampsiteDao csDao = CampsiteDao.getInstance();
		ArrayList<CampsiteDto> csList = csDao.listCampsite(s_camp_no);
		request.setAttribute("csList", csList);
	}
}
