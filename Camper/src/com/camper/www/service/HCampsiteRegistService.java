package com.camper.www.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.CampsiteDao;
import com.camper.www.dto.CampsiteDto;


public class HCampsiteRegistService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		int siteCnt = Integer.parseInt(request.getParameter("siteCnt"));
		String[] s_sitenames = new String[siteCnt];
		String[] s_siteprices = new String[siteCnt];
		System.out.println(siteCnt);
		int s=1;
		for(int i=0; i<siteCnt; i++) {
			s_sitenames[i] = request.getParameter("sitename_"+s);
			s_siteprices[i] = request.getParameter("siteprice_"+s);
			CampsiteDao csDao = CampsiteDao.getInstance();
			CampsiteDto cs = new CampsiteDto(null, null, s_sitenames[i], s_siteprices[i], null);		
			result = csDao.registerCampsite(cs);
			s++;
			if(result == CampsiteDao.SUCCESS) {
				request.setAttribute("cs", cs);
				request.setAttribute("registCgResult", "캠핑장 등록이 완료되었습니다");
			}else {
				request.setAttribute("registErrorMsg", "캠핑 사이트 등록이 실패하였습니다. 입력한 사이트 정보를 다시 확인해 주세요.");
			}
		}
	}
}
