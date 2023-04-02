package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camper.www.dao.CampgroundDao;
import com.camper.www.dao.ReservationDao;
import com.camper.www.dto.HostDto;

public class HCampgroundDelService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_camp_no = request.getParameter("s_camp_no");
		CampgroundDao gDao = CampgroundDao.getInstance();
		ReservationDao rDao = ReservationDao.getInstance();
		int possible = rDao.getTotRezCGforHost(s_camp_no);
		if(possible == 0) {
			int result = gDao.withdrawalCg(s_camp_no);
			if(result == CampgroundDao.SUCCESS) {
				request.setAttribute("deleteCGResult", "캠핑장 삭제가 완료되었습니다");
			}else {
				request.setAttribute("deleteCGResult", "캠핑장을 삭제하지 못했습니다");
			}
		}else {
			request.setAttribute("deleteFailCuzRez", "캠핑장 예약 내역이 있어 삭제가 불가능합니다");
		}
		
	}
}
