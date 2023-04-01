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

import com.camper.www.dao.GuestDao;
import com.camper.www.dto.GuestDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("mPicUpload");
		int maxSize = 1024*1024;
		String s_gphoto = "";
		int result = GuestDao.FAIL;
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				s_gphoto = mRequest.getFilesystemName(param);
			}
			String dbPw = mRequest.getParameter("dbPw");
			String dbPhoto = mRequest.getParameter("dbPic");
			if(s_gphoto == null) {
				s_gphoto = "noprofile.jpg";
			}
			String s_gid = mRequest.getParameter("mid");
			String s_gpw = mRequest.getParameter("mpwNew");
			if(s_gpw.equals("")) {
				s_gpw = dbPw;
			}
			String s_gname = mRequest.getParameter("mname");
			String s_gnick = mRequest.getParameter("mnick");
			String s_gtel = mRequest.getParameter("mtel");
			String s_gemail = mRequest.getParameter("memail");
			GuestDao gDao = GuestDao.getInstance();
			GuestDto guest = new GuestDto(s_gid, s_gpw,s_gemail, s_gname, s_gnick, s_gtel, s_gphoto);
			result = gDao.modifyGuest(guest);
			if(result == GuestDao.SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("guest", guest);
				request.setAttribute("modifyResult", "정보가 수정되었습니다");
			}else {
				request.setAttribute("modifyErrorMsg", "정보 수정이 실패하였습니다 입력한 정보를 확인해 주세요");
			}	
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		File serverFile = new File(path+"/"+s_gphoto);
		if(!s_gphoto.equals("noprofile.jpg") && result==GuestDao.SUCCESS) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:\\JU\\source\\08_1stProject\\Project1_Camper\\Camper\\WebContent\\mPicUpload"+s_gphoto);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int readByteCnt = is.read(bs);
					if(readByteCnt==-1) break;
					os.write(bs, 0, readByteCnt);
				}
				System.out.println("첨부된 파일("+s_gphoto+") 복사 완료");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				}catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

}
