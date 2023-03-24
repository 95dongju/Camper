package com.camper.www.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class HJoinService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		String path = request.getRealPath("bisnisUpload");
		int maxSize = 1024*1024*5;
		String[] bpics = {"", ""};
		MultipartRequest mRequest = null;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			int idx = 0;
			while(paramNames.hasMoreElements()) {
				String param = paramNames.nextElement();
				bpics[idx] = mRequest.getFilesystemName(param);
				idx++;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		for(String imgfile : bpics) {
			InputStream is = null;
			OutputStream os = null;
			File serverFile = new File(path+"/"+imgfile);
			if(serverFile.exists()) {
				try {
					is = new FileInputStream(serverFile);
					os = new FileOutputStream("C:\\JU\\source\\08_1stProject\\PersonalProject\\WebContent\\bisnisUpload\\"+imgfile);
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
		String s_hid = mRequest.getParameter("mid");
		String s_hemail = mRequest.getParameter("memail");
		String s_hpw = mRequest.getParameter("mpw");
		String s_hname = mRequest.getParameter("mname");
		String s_htel = mRequest.getParameter("mtel");
		String s_hbis_name = mRequest.getParameter("bisname");
		String s_hbis_num = mRequest.getParameter("bisnum");
		String s_hbis_pic = bpics[1]!=null ? bpics[1]:"noImg.png";
		String s_haddr = mRequest.getParameter("haddr");
		String s_hacc_bankname = mRequest.getParameter("hacc_bankname");
		String s_haccount = mRequest.getParameter("haccount");
		String s_hacc_pic  = bpics[0]!=null ? bpics[0]:"noImg.png";
		String s_hpic = "noImg.png";
		HostDao hDao = HostDao.getInstance();
		result = hDao.hidConfirm(s_hid);
		if(result == HostDao.NONEXISTENT) {
			HostDto host = new HostDto(s_hid, s_hemail, s_hpw, s_hname, s_htel, s_hbis_name, s_hbis_num, s_hbis_pic, s_haddr, s_hacc_bankname, s_haccount, s_hacc_pic, s_hpic, null, null, null);
			result = hDao.joinHost(host);
			if(result == HostDao.SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("s_hid", s_hid);
				request.setAttribute("joinResult", "회원가입이 완료되었습니다");
			}else {
				request.setAttribute("joinErrorMsg", "회원가입이 실패되었습니다 입력한 정보를 다시 확인해 주세요");
			}
		}else {
			request.setAttribute("joinErrorMsg", "중복된 아이디는 회원가입이 불가합니다");
		}
	}
}
