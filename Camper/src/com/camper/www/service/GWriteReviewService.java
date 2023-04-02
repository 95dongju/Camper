package com.camper.www.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camper.www.dao.ReviewDao;
import com.camper.www.dto.GuestDto;
import com.camper.www.dto.ReviewDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GWriteReviewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		String path = request.getRealPath("reviewUpload");
		int maxSize = 1024*1024*10;
		String[] cgpics = new String[4];
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			int idx = 0;
			while(paramNames.hasMoreElements()) {
				String param = paramNames.nextElement();
				cgpics[idx] = mRequest.getFilesystemName(param);
				idx++;
			}
			HttpSession session = request.getSession();
			GuestDto guest = (GuestDto)session.getAttribute("guest");
			if(guest!=null) {
				String s_camp_no = mRequest.getParameter("s_camp_no");
				String s_gid = mRequest.getParameter("s_gid");
				String s_rev_title = mRequest.getParameter("review_title");
				String s_rev_content = mRequest.getParameter("review_content");
				String s_rev_ip = request.getRemoteAddr();
				String s_rev_mainpic = cgpics[3]!=null? cgpics[3] : "noimg.jpg";
				String s_rev_pic1 = cgpics[2]!=null? cgpics[2] : "noimg.jpg";
				String s_rev_pic2 = cgpics[1]!=null? cgpics[1] : "noimg.jpg";
				String s_rev_pic3 = cgpics[0]!=null? cgpics[0] : "noimg.jpg";
				ReviewDao rvDao = ReviewDao.getInstance();
				ReviewDto rv = new ReviewDto(null, s_camp_no, s_gid, s_rev_title, s_rev_content, null, s_rev_ip, s_rev_mainpic, s_rev_pic1, s_rev_pic2, s_rev_pic3);
				result = rvDao.writeReview(rv);
				if(result == ReviewDao.SUCCESS) {
					request.setAttribute("reviewResult", "리뷰가 성공적으로 등록되었습니다");
				}else {
					request.setAttribute("reviewResult", "리뷰가 등록되지 않았습니다");
				}
			}else {
				request.setAttribute("reviewResult", "로그인 후 리뷰를 작성해 주세요");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		for(String imgfile : cgpics) {
			InputStream is = null;
			OutputStream os = null;
			File serverFile = new File(path+"/"+imgfile);
			if(serverFile.exists()) {
				try {
					is = new FileInputStream(serverFile);
					os = new FileOutputStream("C:\\JU\\source\\08_1stProject\\Project1_Camper\\Camper\\WebContent\\reviewUpload"+imgfile);
					byte[] bs = new byte[(int)serverFile.length()];
					while(true) {
						int readByteCnt = is.read(bs);
						if(readByteCnt == -1) break;
						os.write(bs, 0, readByteCnt);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					try {
						if(os!=null) os.close();
						if(is!=null) is.close();
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
	}

}
