package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camper.www.dao.CampsiteDao;
import com.camper.www.dto.CampsiteDto;

public class HCampsiteRegistService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		int siteCnt = Integer.parseInt(request.getParameter("siteCnt"));
		HttpSession session = request.getSession();
		System.out.println(siteCnt);
		String s_camp_no = request.getParameter("s_camp_no");
		System.out.println(s_camp_no);
		String s_siteprice = request.getParameter("siteprice_1");
		System.out.println(s_siteprice);
		String s_sitename = request.getParameter("sitename_1");
		System.out.println(s_sitename);
		CampsiteDao csDao = CampsiteDao.getInstance();
		CampsiteDto cs = new CampsiteDto(null, s_camp_no, s_sitename, s_siteprice, null);
		result = csDao.registerCampsite(cs);
		System.out.println(result);
		if(result == CampsiteDao.SUCCESS) {
			session.setAttribute("cs", cs);
			request.setAttribute("registCgResult", "캠핑장 등록이 완료되었습니다");
		}else {
			request.setAttribute("registErrorMsg", "캠핑 사이트 등록이 실패하였습니다. 입력한 사이트 정보를 다시 확인해 주세요.");
		}
	}
}
