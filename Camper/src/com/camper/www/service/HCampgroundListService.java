package com.camper.www.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camper.www.dao.CampgroundDao;
import com.camper.www.dto.CampgroundDto;

public class HCampgroundListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			if(request.getAttribute("pageNum")!=null) {
				pageNum = (String)request.getAttribute("pageNum");
			}else {
				pageNum = "1";
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 10, BLOCKSIZE = 5;
		int startRow = (currentPage - 1) * PAGESIZE + 1;
		int endRow   = startRow + PAGESIZE - 1;
		CampgroundDao cDao = CampgroundDao.getInstance();
		HttpSession session = request.getSession();
		String s_hid = (String)session.getAttribute("s_hid");
		ArrayList<CampgroundDto> cgList = cDao.myCgList(s_hid, startRow, endRow);
		request.setAttribute("cgList", cgList);
		int totCnt = cDao.myCgTotCnt(s_hid);
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageNum", currentPage);
	}
}
