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

import com.camper.www.dao.HostDao;
import com.camper.www.dto.HostDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class HModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("mPicUpload");
		int maxSize = 1024*1024;
		String[] spics = new String[3];
		int result = HostDao.FAIL;
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			int idx = 0;
			while(paramNames.hasMoreElements()) {
				String param = paramNames.nextElement();
				spics[idx] = mRequest.getFilesystemName(param);
				idx++;
			}
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		for(String imgfile : spics) {
			InputStream is = null;
			OutputStream os = null;
			File serverFile = new File(path + "/" +imgfile);
			if(serverFile.exists()) {
				try {
					is = new FileInputStream(serverFile);
					os = new FileOutputStream("C:\\JU\\source\\08_1stProject\\Camper\\WebContent\\mPicUpload\\"+imgfile);
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
		String dbPw = mRequest.getParameter("dbPw");
		String dbPhoto = mRequest.getParameter("dbPic");
		String dbBisPic = mRequest.getParameter("dbBisPic");
		String dbAccPic = mRequest.getParameter("dbAccPic");
		String dbBank = mRequest.getParameter("bankname");
		String s_hpic = spics[2]!=null ? spics[2] : dbPhoto;
		String s_hid = mRequest.getParameter("mid");
		String s_hpw = mRequest.getParameter("mpwNew");
		if(s_hpw.equals("")) {
			s_hpw = dbPw;
		}
		String s_hbis_name = mRequest.getParameter("bisname");
		String s_hbis_num = mRequest.getParameter("bisnum");
		String s_hbis_pic = spics[1]!=null ? spics[1] : dbBisPic;
		String s_haddr = mRequest.getParameter("bisaddr");
		String s_hacc_bankname = mRequest.getParameter("bankname");
		if(s_hacc_bankname.equals("")) {
			s_hacc_bankname = dbBank;
		}
		String s_haccount = mRequest.getParameter("haccount");
		String s_hacc_pic = spics[0]!=null ? spics[0] : dbAccPic;
		HostDao hDao = HostDao.getInstance();
		HostDto host = new HostDto(s_hid, s_hpw, s_hbis_name, s_hbis_num, s_hbis_pic, s_haddr, s_hacc_bankname, s_haccount, s_hacc_pic, s_hpic);
		result = hDao.modifyHost(host);
		if(result == HostDao.SUCCESS) {
			HttpSession session = request.getSession();
			session.setAttribute("host", host);
			request.setAttribute("modifyResult", "정보가 수정되었습니다");
		}else {
			request.setAttribute("modifyErrorMsg", "정보 수정이 실패하였습니다 입력한 정보를 확인해 주세요");
		}	
	}
}


