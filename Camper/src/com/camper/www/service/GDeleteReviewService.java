package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.ReviewDao;

public class GDeleteReviewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int i_rev_no = Integer.parseInt(request.getParameter("i_rev_no"));
		ReviewDao rvDao = ReviewDao.getInstance();
		int result = rvDao.deleteReview(i_rev_no);
		if(result == ReviewDao.SUCCESS){
			request.setAttribute("deleteRVResult", "리뷰가 삭제되었습니다");
		}else {
			request.setAttribute("deleteRVResult", "리뷰를 삭제하지 못했습니다");
		}
	}

}
