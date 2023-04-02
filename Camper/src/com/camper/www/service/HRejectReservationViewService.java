package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.ReservationDao;
import com.camper.www.dto.ReservationDto;

public class HRejectReservationViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_rez_no = request.getParameter("s_rez_no");
		ReservationDao rDao = ReservationDao.getInstance();
		int result = rDao.rejectRez(s_rez_no);
		if(result == ReservationDao.SUCCESS) {
			request.setAttribute("rejectResult", "예약이 취소되었습니다");
		}else {
			request.setAttribute("rejectResult", "예약이 취소되었습니다");
		}
	}

}
