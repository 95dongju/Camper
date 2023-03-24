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

import com.camper.www.dto.CampgroundDto;

public class CampgroundDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private CampgroundDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	private static CampgroundDao instance = new CampgroundDao();
	public static CampgroundDao getInstance() {
		return instance;
	}
	// 1. 캠핑장 등록
	public int registerCampground(CampgroundDto cg) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO HOST_CAMPGROUND " + 
				"    VALUES ('CG'||TO_CHAR(HOST_CAMPGROUND_NO_SEQ.NEXTVAL), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'N', SYSDATE)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cg.getS_camp_name());
			pstmt.setString(2, cg.getS_camp_desc());
			pstmt.setString(3, cg.getS_camp_addr());
			pstmt.setString(4, cg.getS_hid());
			pstmt.setString(5, cg.getS_camp_mainpic());
			pstmt.setString(6, cg.getS_camp_mappic());
			pstmt.setString(7, cg.getS_camp_pic1());
			pstmt.setString(8, cg.getS_camp_pic2());
			pstmt.setString(9, cg.getS_camp_pic3());
			pstmt.setString(10, cg.getS_camp_pic4());
			pstmt.setString(11, cg.getS_camp_pic5());
			pstmt.setString(12, cg.getS_bathroom());
			pstmt.setString(13, cg.getS_showerbooth());
			pstmt.setString(14, cg.getS_store());
			pstmt.setString(15, cg.getS_sink());
			pstmt.setString(16, cg.getS_wifi());
			pstmt.setString(17, cg.getS_playground());
			pstmt.setString(18, cg.getS_with_pet());
			pstmt.setString(19, cg.getS_swim_pool());
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
	// 2. 캠핑장 지우기 전 캠프사이트 삭제
	private int preWithdrawalCampsite(String s_camp_no) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE HOST_CAMPSITE SET CS_DEL_YN = 'Y' WHERE S_CAMP_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_camp_no);
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
	// 2. 캠프장 삭제
	public int withdrawalCg(String s_camp_no) {
		int result = FAIL;
		preWithdrawalCampsite(s_camp_no);
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE HOST_CAMPGROUND SET CG_DEL_YN = 'Y' WHERE S_CAMP_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_camp_no);
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
	// 3. 캠핑장 정보 수정
	public int modifyCg(CampgroundDto cg) {
		int result = FAIL;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE HOST_CAMPGROUND SET S_CAMP_NAME = ? " + 
				"                            S_CAMP_DESC = ? " + 
				"                            S_CAMP_ADDR = ? " + 
				"                            S_CAMP_MAINPIC = ? " + 
				"                            S_CAMP_MAPPIC = ? " + 
				"                            S_CAMP_PIC1 = ? " + 
				"                            S_CAMP_PIC2 = ? " + 
				"                            S_CAMP_PIC3 = ? " + 
				"                            S_CAMP_PIC4 = ? " + 
				"                            S_CAMP_PIC5 = ? " + 
				"                            S_BATHROOM = ? " + 
				"                            S_SHOWERBOOTH = ? " + 
				"                            S_STORE = ? " + 
				"                            S_SINK = ? " + 
				"                            S_WIFI = ? " + 
				"                            S_PLAYGROUND = ? " + 
				"                            S_WITH_PET = ? " + 
				"                            S_SWIM_POOL = ? " + 
				"                        WHERE S_CAMP_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cg.getS_camp_name());
			pstmt.setString(2, cg.getS_camp_desc());
			pstmt.setString(3, cg.getS_camp_addr());
			pstmt.setString(4, cg.getS_camp_mainpic());
			pstmt.setString(5, cg.getS_camp_mappic());
			pstmt.setString(6, cg.getS_camp_pic1());
			pstmt.setString(7, cg.getS_camp_pic2());
			pstmt.setString(8, cg.getS_camp_pic3());
			pstmt.setString(9, cg.getS_camp_pic4());
			pstmt.setString(10, cg.getS_camp_pic5());
			pstmt.setString(11, cg.getS_bathroom());
			pstmt.setString(12, cg.getS_showerbooth());
			pstmt.setString(13, cg.getS_store());
			pstmt.setString(14, cg.getS_sink());
			pstmt.setString(15, cg.getS_wifi());
			pstmt.setString(16, cg.getS_playground());
			pstmt.setString(17, cg.getS_with_pet());
			pstmt.setString(18, cg.getS_swim_pool());
			pstmt.setString(19, cg.getS_camp_no());
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
	// 4. 내 캠핑장 수
	public int myCgTotCnt(String s_hid) {
		int totCnt = 0;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT COUNT(*) CNT FROM HOST_CAMPGROUND WHERE S_HID = ? AND MH.H_DEL_YN= 'N'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hid);
			rs = pstmt.executeQuery();
			rs.next();
			totCnt = rs.getInt(1);
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
		return totCnt;
	}
	// 5. 등록된 내 캠프장 목록
	public ArrayList<CampgroundDto> myCgList(String s_hid, int startRow, int endRow) {
		ArrayList<CampgroundDto> groundList = new ArrayList<CampgroundDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT S_CAMP_NO, S_CAMP_NAME FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM HOST_CAMPGROUND) A, MEMBER_HOST MH WHERE A.S_HID = MH.S_HID AND A.S_HID = ? AND MH.H_DEL_YN = 'N') WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_camp_no = rs.getString("s_camp_no");
				String s_camp_name = rs.getString("s_camp_name");
				groundList.add(new CampgroundDto(s_camp_no, s_camp_name));
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
		return groundList;
	}
	// 6. 캠프장 상세보기
	public CampgroundDto cgView(String s_camp_no) {
		CampgroundDto cgView = null;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT S_CAMP_NAME, S_CAMP_DESC, S_CAMP_ADDR, S_CAMP_MAINPIC, S_CAMP_MAPPIC, S_CAMP_PIC1, S_CAMP_PIC2, S_CAMP_PIC3, S_CAMP_PIC4, S_CAMP_PIC5, S_BATHROOM, S_SHOWERBOOTH, S_STORE, S_SINK, S_WIFI, S_PLAYGROUND, S_WITH_PET, S_SWIM_POOL FROM HOST_CAMPGROUND HC, MEMBER_HOST MH WHERE HC.S_HID = MH.S_HID AND MH.H_DEL_YN= 'N' AND S_CAMP_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_camp_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String s_camp_name = rs.getString("s_camp_name");
				String s_camp_desc = rs.getString("s_camp_desc");
				String s_camp_addr = rs.getString("s_camp_addr");
				String s_camp_mainpic = rs.getString("s_camp_mainpic");
				String s_camp_mappic = rs.getString("s_camp_mappic");
				String s_camp_pic1 = rs.getString("s_camp_pic1");
				String s_camp_pic2 = rs.getString("s_camp_pic2");
				String s_camp_pic3 = rs.getString("s_camp_pic3");
				String s_camp_pic4 = rs.getString("s_camp_pic4");
				String s_camp_pic5 = rs.getString("s_camp_pic5");
				String s_bathroom = rs.getString("s_bathroom");
				String s_showerbooth = rs.getString("s_showerbooth");
				String s_store = rs.getString("s_store");
				String s_sink = rs.getString("s_sink");
				String s_wifi = rs.getString("s_wifi");
				String s_playground = rs.getString("s_playground");
				String s_with_pet = rs.getString("s_with_pet");
				String s_swim_pool = rs.getString("s_swim_pool");
				cgView = new CampgroundDto(s_camp_name, s_camp_desc, s_camp_addr, s_camp_mainpic, s_camp_mappic, s_camp_pic1, s_camp_pic2, s_camp_pic3, s_camp_pic4, s_camp_pic5, s_bathroom, s_showerbooth, s_store, s_sink, s_wifi, s_playground, s_with_pet, s_swim_pool);
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
		return cgView;
	}
	// 7. 캠핑장 검색 (위치)
	public ArrayList<CampgroundDto> cgLocList(String cgLoc) {
		ArrayList<CampgroundDto> cgList = new ArrayList<CampgroundDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT S_CAMP_NAME, S_CAMP_ADDR, S_CAMP_MAINPIC "
				+ "FROM HOST_CAMPGROUND HC, MEMBER_HOST MH "
				+ "WHERE HC.S_HID = MH.S_HID AND S_CAMP_ADDR LIKE '%'||?||'%' AND MH.H_DEL_YN = 'N' ORDER BY CG_RDATE DESC";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cgLoc);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_camp_name = rs.getString("s_camp_name");
				String s_camp_addr = rs.getString("s_camp_addr");
				String s_camp_mainpic =  rs.getString("s_camp_mainpic");
				cgList.add(new CampgroundDto(s_camp_name, s_camp_addr, s_camp_mainpic));
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
		return cgList;
	}
	// 8. 캠핑장 검색 (이름)
	public ArrayList<CampgroundDto> cgNameList(String cgName) {
		ArrayList<CampgroundDto> cgList = new ArrayList<CampgroundDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT S_CAMP_NAME, S_CAMP_ADDR, S_CAMP_MAINPIC "
				+ "FROM HOST_CAMPGROUND HC, MEMBER_HOST MH "
				+ "WHERE HC.S_HID = MH.S_HID AND S_CAMP_NAME LIKE '%'||?||'%' AND MH.H_DEL_YN = 'N' ORDER BY CG_RDATE DESC";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cgName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_camp_name = rs.getString("s_camp_name");
				String s_camp_addr = rs.getString("s_camp_addr");
				String s_camp_mainpic =  rs.getString("s_camp_mainpic");
				cgList.add(new CampgroundDto(s_camp_name, s_camp_addr, s_camp_mainpic));
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
		return cgList;
	}
	// 9. 전체 캠핑장 목록
	public ArrayList<CampgroundDto> allCgList() {
		ArrayList<CampgroundDto> cgList = new ArrayList<CampgroundDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT S_CAMP_NAME, S_CAMP_ADDR, S_CAMP_MAINPIC "
				+ "FROM HOST_CAMPGROUND HC, MEMBER_HOST MH "
				+ "WHERE HC.S_HID = MH.S_HID WHERE MH.H_DEL_YN = 'N' ORDER BY CG_RDATE DESC";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_camp_name = rs.getString("s_camp_name");
				String s_camp_addr = rs.getString("s_camp_addr");
				String s_camp_mainpic =  rs.getString("s_camp_mainpic");
				cgList.add(new CampgroundDto(s_camp_name, s_camp_addr, s_camp_mainpic));
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
		return cgList;
	}
}
