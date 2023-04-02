package com.camper.www.service;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.CampgroundDao;
import com.camper.www.dao.ReservationDao;
import com.camper.www.dto.CampgroundDto;
import com.camper.www.dto.ReservationDto;
import com.camper.www.util.CalendarPrinter;

public class GReservationViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String s_site_no = request.getParameter("s_site_no");
		String yearParam = request.getParameter("year");
		String monthParam = request.getParameter("month");
		int year, month;
		if(yearParam == null && monthParam == null) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
			yearParam = String.valueOf(year);
			monthParam = month<10 ? "0"+month : String.valueOf(month);
		}else {
			year = Integer.parseInt(yearParam);
			month = Integer.parseInt(monthParam);
			monthParam = month<10 ? "0"+month : String.valueOf(month);
		}
		CalendarPrinter calPrint = new CalendarPrinter(year, month);
		ReservationDao reservationDao = ReservationDao.getInstance();
		ArrayList<ReservationDto> reservations = reservationDao.getReservation(s_site_no, yearParam, monthParam);
		CampgroundDao campGroundDao = CampgroundDao.getInstance();
		CampgroundDto camp = campGroundDao.cgView(s_site_no);
		request.setAttribute("reservations", reservations);
		request.setAttribute("calPrint", calPrint);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("camp", camp);
	}
}
