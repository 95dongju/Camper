package com.camper.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camper.www.dao.ReviewDao;
import com.camper.www.dto.ReviewDto;

public class GReviewViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int i_rev_no = Integer.parseInt(request.getParameter("i_rev_no"));
		ReviewDao rDao = ReviewDao.getInstance();
		ReviewDto rvView = rDao.viewReview(i_rev_no);
		request.setAttribute("rvView", rvView);
	}
}
