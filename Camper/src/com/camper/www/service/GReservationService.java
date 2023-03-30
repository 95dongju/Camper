package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.ReservationDao;

public class GReservationService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String selectDate = request.getParameter("selectDate");
		String s_gid =  request.getParameter("s_gid");
		String s_site_no = request.getParameter("s_site_no");
		ReservationDao rezDao = ReservationDao.getInstance();
		int reserved = rezDao.getReservation(s_site_no, selectDate);
		if(reserved==0) {
			int reservationResult = rezDao.reservation(s_site_no, selectDate, s_gid);
			request.setAttribute("reservationResult", reservationResult);
		}
	}
}
