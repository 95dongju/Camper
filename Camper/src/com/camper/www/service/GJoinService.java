package com.camper.www.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.camper.www.dao.GuestDao;
import com.camper.www.dto.GuestDto;

public class GJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		String count = "1";
		String json = "";
		String nickname = null;
		try {
			String apiurl = "https://nickname.hwanmoo.kr/?format=json&count="+count;
			URL url = new URL(apiurl);
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			json = bf.readLine();
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(json);
			JSONObject jsonObj = (JSONObject)obj;
			JSONArray jsonArray = (JSONArray)jsonObj.get("words");
			nickname = jsonArray.get(0).toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String s_gid = request.getParameter("mid");
		String s_gemail = request.getParameter("memail");
		String s_gpw = request.getParameter("mpw");
		String s_gname = request.getParameter("mname");
		String s_gnick = nickname;
		String s_gtel = request.getParameter("mtel");
		String s_gphoto = "noImg.jpg";
		GuestDao gDao = GuestDao.getInstance();
		result = gDao.gidConfirm(s_gid);
		if(result == GuestDao.NONEXISTENT) {
			GuestDto guest = new GuestDto(s_gid, s_gpw, s_gemail, s_gname, s_gnick, s_gtel, s_gphoto, null, null, null);
			result = gDao.joinGuest(guest);
			if(result == GuestDao.SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("s_gid", s_gid);
				request.setAttribute("joinResult", "회원가입이 완료되었습니다");
			}else {
				request.setAttribute("joinErrorMsg", "회원가입이 실패되었습니다 입력한 정보를 다시 확인해 주세요");
			}
		}else {
			request.setAttribute("joinErrorMsg", "중복된 아이디는 회원가입이 불가합니다");
		}
	}
}
