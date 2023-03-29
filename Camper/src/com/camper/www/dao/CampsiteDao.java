package com.camper.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.camper.www.dto.CampsiteDto;

public class CampsiteDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private CampsiteDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	private static CampsiteDao instance = new CampsiteDao();
	public static CampsiteDao getInstance() {
		return instance;
	}
	// 1. 캠핑장 내 캠핑 사이트 등록
	public int registerCampsite(CampsiteDto cs) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO HOST_CAMPSITE " + 
				"    VALUES ('CS'||TO_CHAR(HOST_CAMPSITE_NO_SEQ.NEXTVAL), 'CG'||TO_CHAR(HOST_CAMPGROUND_NO_SEQ.CURRVAL), ?, ?, 'N', SYSDATE)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cs.getS_sitename());
			pstmt.setString(2, cs.getS_siteprice());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 2. 캠핑 사이트 삭제
	public int withdrawalCampsite(String s_site_no) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE HOST_CAMPSITE SET CS_DEL_YN = 'Y' WHERE S_SITE_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_site_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 3. 캠핑 사이트 수정
	public int modifyCampsite(CampsiteDto cs) {
		int result = FAIL;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE HOST_CAMPSITE SET S_SITENAME = ?, " + 
				"                        S_SITEPRICE = ?" + 
				"                    WHERE S_SITE_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cs.getS_sitename());
			pstmt.setString(2, cs.getS_siteprice());
			pstmt.setString(3, cs.getS_site_no());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 4. 캠핑장 내 캠핑 사이트 목록
	public ArrayList<CampsiteDto> listCampsite(String s_camp_no) {
		ArrayList<CampsiteDto> siteList = new ArrayList<CampsiteDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT S_SITE_NO, S_SITENAME, S_SITEPRICE FROM HOST_CAMPSITE HCS, HOST_CAMPGROUND HC, MEMBER_HOST MH WHERE HCS.S_CAMP_NO = HC.S_CAMP_NO AND MH.H_DEL_YN = 'N' AND HC.S_HID = MH.S_HID AND HC.S_CAMP_NO = ? ORDER BY CS_RDATE";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_camp_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_site_no = rs.getString("s_site_no");
				String s_sitename = rs.getString("s_sitename");
				String s_siteprice = rs.getString("s_siteprice");
				siteList.add(new CampsiteDto(s_site_no, s_sitename, s_siteprice));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return siteList;
	}
}
