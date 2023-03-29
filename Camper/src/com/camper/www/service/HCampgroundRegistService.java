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

import com.camper.www.dao.CampgroundDao;
import com.camper.www.dto.CampgroundDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class HCampgroundRegistService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		String path = request.getRealPath("campgroundUpload");
		int maxSize = 1024*1024*10;
		String[] cgpics = new String[7];
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
					os = new FileOutputStream("C:\\JU\\source\\08_1stProject\\Project1_Camper\\Camper\\WebContent\\campgroundUpload"+imgfile);
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
		String s_camp_name = mRequest.getParameter("cgname");
		String s_camp_addr = mRequest.getParameter("cgaddr");
		String s_camp_desc = mRequest.getParameter("cgdesc");
		String s_camp_tel = mRequest.getParameter("cgtel");
		String s_hid = mRequest.getParameter("s_hid");
		String s_camp_mainpic = cgpics[2]!=null ? cgpics[2]:"noimg.jpg";
		String s_camp_mappic = cgpics[5]!=null ? cgpics[5]:"noimg.jpg";
		String s_camp_pic1 = cgpics[6]!=null ? cgpics[6]:"noimg.jpg";
		String s_camp_pic2 = cgpics[4]!=null ? cgpics[4]:"noimg.jpg";
		String s_camp_pic3 = cgpics[3]!=null ? cgpics[3]:"noimg.jpg";
		String s_camp_pic4 = cgpics[1]!=null ? cgpics[1]:"noimg.jpg";
		String s_camp_pic5 = cgpics[0]!=null ? cgpics[0]:"noimg.jpg";
		String s_bathroom = mRequest.getParameter("bathroomChk")!=null ? "Y" : "N";
		String s_showerbooth = mRequest.getParameter("showerboothChk")!=null ? "Y" : "N";
		String s_store = mRequest.getParameter("storeChk")!=null ? "Y" : "N";
		String s_sink = mRequest.getParameter("sinkChk")!=null ? "Y" : "N";
		String s_wifi = mRequest.getParameter("wifiChk")!=null ? "Y" : "N";
		String s_playground = mRequest.getParameter("playgroundChk")!=null ? "Y" : "N";
		String s_with_pet = mRequest.getParameter("with_petChk")!=null ? "Y" : "N";
		String s_swim_pool = mRequest.getParameter("swim_poolChk")!=null ? "Y" : "N";
		CampgroundDao cDao = CampgroundDao.getInstance();
		CampgroundDto cg = new CampgroundDto(null, s_camp_name, s_camp_desc, s_camp_addr, s_camp_tel, s_hid, s_camp_mainpic, s_camp_mappic, s_camp_pic1, s_camp_pic2, s_camp_pic3, s_camp_pic4, s_camp_pic5, s_bathroom, s_showerbooth, s_store, s_sink, s_wifi, s_playground, s_with_pet, s_swim_pool, null, null);
		result = cDao.registerCampground(cg);
		if(result == CampgroundDao.SUCCESS) {
			request.setAttribute("cg", cg);
		}else {
			request.setAttribute("registErrorMsg", "캠핑장 등록이 실패하였습니다. 입력한 정보를 다시 확인해 주세요");
		}
	}
}
