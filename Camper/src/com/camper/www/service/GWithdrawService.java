package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camper.www.dao.GuestDao;
import com.camper.www.dao.HostDao;
import com.camper.www.dao.ReservationDao;
import com.camper.www.dto.GuestDto;
import com.camper.www.dto.HostDto;

public class GWithdrawService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String s_gid = null;
		String s_gpw = request.getParameter("mpw");
		String gr_status = "N";
		GuestDto sessionGuest = (GuestDto)session.getAttribute("guest");
		if(sessionGuest!=null) {
			s_gid = sessionGuest.getS_gid();
		}
		GuestDao gDao = GuestDao.getInstance();
		ReservationDao rDao = ReservationDao.getInstance();
		int possible = rDao.getTotRezforGuest(s_gid, gr_status);
		if(possible == 0) {
			int result = gDao.withdrawGuest(s_gid, s_gpw);
			if(result == GuestDao.SUCCESS) {
				session.invalidate();
				request.setAttribute("withdrawalResult", "회원 탈퇴가 완료되었습니다.");
			}else {
				request.setAttribute("withdrawalResult", "로그인 상태가 아닙니다");
			}
		}else {
			request.setAttribute("withdrawFailCuzRez", "캠핑장 예약 내역이 있어 탈퇴가 불가능합니다.");
		}
	}
}
