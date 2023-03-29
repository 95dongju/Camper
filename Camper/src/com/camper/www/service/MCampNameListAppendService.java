package com.camper.www.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.CampgroundDao;
import com.camper.www.dto.CampgroundDto;

public class MCampNameListAppendService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		String cgName = request.getParameter("cgName");
		if(pageNum==null) {
			if(request.getAttribute("pageNum")!=null) {
				pageNum = (String)request.getAttribute("pageNum");
			}else {
				pageNum = "1";
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 5;
		int startRow = (currentPage-1) * PAGESIZE + 1;
		int endRow   = startRow + PAGESIZE - 1;
		CampgroundDao cDao = CampgroundDao.getInstance();
		int totCnt = cDao.searchNameTotCnt(cgName);
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		if(currentPage <= pageCnt) {
			ArrayList<CampgroundDto> searchNameList = cDao.cgNameList(cgName, startRow, endRow);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("searchNameList", searchNameList);
			request.setAttribute("pageNum", currentPage);
		}else {
			request.setAttribute("error", "마지막 페이지입니다");
		}
	}
}
