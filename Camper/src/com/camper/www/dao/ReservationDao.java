package com.camper.www.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.camper.www.dto.ReservationDto;

public class ReservationDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private ReservationDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	private static ReservationDao instance = new ReservationDao();
	public static ReservationDao getInstance() {
		return instance;
	}
	// 1. 게스트 신규 예약
	public int reservation(String s_site_no, String selectDate, String s_gid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO GUEST_RESERVATION " + 
				"    VALUES (TO_CHAR(SYSDATE, 'YYMMDD')||'-'||(TO_CHAR(GUEST_RESERVATION_NO_SEQ.NEXTVAL)), ?, TO_DATE(?, 'YYYY-MM-DD'), ?, SYSDATE, 'Y', TO_CHAR(TO_DATE(?, 'YYYY-MM-DD'), 'DD'))";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_site_no);
			pstmt.setString(2, selectDate);
			pstmt.setString(3, s_gid);
			pstmt.setString(4, selectDate);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 2. 해당 날짜 예약 여부 확인
	public int getReservation(String s_site_no, String selectDate){
		int reserved = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT GR.* FROM GUEST_RESERVATION GR WHERE S_SITE_NO = ? AND D_SELECT = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_site_no);
			pstmt.setString(2, selectDate);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reserved = 1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return reserved;
	}
	// 3. 해당 년월에 예약된 내용 보기
	public ArrayList<ReservationDto> getReservation(String s_site_no, String yearStr, String monthStr){
		ArrayList<ReservationDto> dtos = new ArrayList<ReservationDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM GUEST_RESERVATION WHERE S_SITE_NO = ? AND TO_CHAR(D_SELECT, 'YYYY-MM') LIKE ? || '-' || ? ORDER BY DAY";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_site_no);
			pstmt.setString(2, yearStr);
			pstmt.setString(3, monthStr);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_rez_no = rs.getString("s_rez_no");
				String d_select = rs.getString("d_select");
				String s_gid = rs.getString("s_gid");
				Timestamp d_rez_date = rs.getTimestamp("d_rez_date");
				String gr_status = rs.getString("gr_status");
				int day = rs.getInt("day");
				dtos.add(new ReservationDto(s_rez_no, s_site_no, d_select, s_gid, d_rez_date, gr_status, null, day));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dtos;
	}
	// 4. 호스트 예약 거절
	public int rejectRez(String s_rez_no) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GUEST_RESERVATION SET GR_STATUS = 'N' " + 
				"                        WHERE S_REZ_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_rez_no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 5. 게스트의 (체크아웃 되지 않은) 예약 수 / 거절 당한 예약 수
	public int getTotRezforGuest(String s_gid, String gr_status) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM GUEST_RESERVATION GR, HOST_CAMPGROUND HC, HOST_CAMPSITE HS WHERE HS.S_SITE_NO = GR.S_SITE_NO AND D_SELECT >= (TO_CHAR(SYSDATE, 'YYYY-MM-DD')) AND HC.S_CAMP_NO =  HS.S_CAMP_NO AND GR.S_GID = ? AND GR.GR_STATUS = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_gid);
			pstmt.setString(2, gr_status);
			rs = pstmt.executeQuery();
			rs.next();
			totCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return totCnt;
	}
	// 6. 게스트 (체크아웃 되지 않은) 예약 목록 확인 // gr_status Y면 예약 완료, X면 거절
	public ArrayList<ReservationDto> getRezListforGuest(String s_gid, String gr_status, int startRow, int endRow){
		ArrayList<ReservationDto> dtos = new ArrayList<ReservationDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, GR.*, HG.S_CAMP_NAME, HG.S_CAMP_NO " + 
				"FROM (SELECT * FROM GUEST_RESERVATION ORDER BY S_REZ_NO) GR, HOST_CAMPGROUND HG, HOST_CAMPSITE HS " + 
				"WHERE GR.S_SITE_NO = HS.S_SITE_NO " + 
				"AND HG.S_CAMP_NO = HS.S_CAMP_NO " + 
				"AND D_SELECT >= (TO_CHAR(SYSDATE, 'YYYY-MM-DD')) " + 
				"AND S_GID = ? AND GR_STATUS = ?) WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_gid);
			pstmt.setString(2, gr_status);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_rez_no = rs.getString("s_rez_no");
				String s_site_no = rs.getString("s_site_no");
				String d_select = rs.getString("d_select");
				Timestamp d_rez_date = rs.getTimestamp("d_rez_date");
				String s_camp_name = rs.getString("s_camp_name");
				String s_camp_no =  rs.getString("s_camp_no");
				dtos.add(new ReservationDto(s_rez_no, s_site_no, d_select.substring(0,10), s_gid, d_rez_date, null, null, s_camp_no, s_camp_name, null));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dtos;
	}	
	// 7. 게스트의 (체크아웃 한) 예약 수 / 거절 당한 예약 수
	public int getTotCntCOforGuest(String s_gid) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM GUEST_RESERVATION GR, HOST_CAMPGROUND HC, HOST_CAMPSITE HS WHERE HS.S_SITE_NO = GR.S_SITE_NO AND D_SELECT < (TO_CHAR(SYSDATE, 'YYYY-MM-DD')) AND HC.S_CAMP_NO =  HS.S_CAMP_NO AND GR.S_GID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_gid);
			rs = pstmt.executeQuery();
			rs.next();
			totCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return totCnt;
	}
	// 8. 게스트 (체크아웃 한) 예약 목록 확인 // gr_status Y면 예약 완료, X면 거절
	public ArrayList<ReservationDto> getCOListforGuest(String s_gid, int startRow, int endRow){
		ArrayList<ReservationDto> dtos = new ArrayList<ReservationDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, GR.*, HG.S_CAMP_NAME, HG.S_CAMP_NO " + 
				"FROM (SELECT * FROM GUEST_RESERVATION ORDER BY S_REZ_NO) GR, HOST_CAMPGROUND HG, HOST_CAMPSITE HS " + 
				"WHERE GR.S_SITE_NO = HS.S_SITE_NO " + 
				"AND HG.S_CAMP_NO = HS.S_CAMP_NO " + 
				"AND D_SELECT < (TO_CHAR(SYSDATE, 'YYYY-MM-DD')) " + 
				"AND S_GID = ?) WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_gid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_rez_no = rs.getString("s_rez_no");
				String s_site_no = rs.getString("s_site_no");
				String d_select = rs.getString("d_select");
				Timestamp d_rez_date = rs.getTimestamp("d_rez_date");
				String s_camp_name = rs.getString("s_camp_name");
				String s_camp_no =  rs.getString("s_camp_no");
				dtos.add(new ReservationDto(s_rez_no, s_site_no, d_select.substring(0,10), s_gid, d_rez_date, null, null, s_camp_no, s_camp_name, null));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dtos;
	}
	// 9. 특정 캠핑장 (체크아웃 되지 않은) 수락 / 거절한 예약 수
	public int getTotRezCGforHost(String s_camp_no) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM GUEST_RESERVATION GR, HOST_CAMPGROUND HC, HOST_CAMPSITE HS WHERE HS.S_SITE_NO = GR.S_SITE_NO AND D_SELECT >= (TO_CHAR(SYSDATE, 'YYYY-MM-DD')) AND HC.S_CAMP_NO = HS.S_CAMP_NO AND HC.S_CAMP_NO = ? AND GR.GR_STATUS = 'N'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_camp_no);
			rs = pstmt.executeQuery();
			rs.next();
			totCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return totCnt;
	}
	// 9. 호스트 (체크아웃 되지 않은) 수락 / 거절한 예약 수
	public int getTotRezforHost(String s_hid, String gr_status) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM GUEST_RESERVATION GR, HOST_CAMPGROUND HC, HOST_CAMPSITE HS WHERE HS.S_SITE_NO = GR.S_SITE_NO AND D_SELECT >= (TO_CHAR(SYSDATE, 'YYYY-MM-DD')) AND HC.S_CAMP_NO = HS.S_CAMP_NO AND HC.S_HID  = ? AND GR.GR_STATUS = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hid);
			pstmt.setString(2, gr_status);
			rs = pstmt.executeQuery();
			rs.next();
			totCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return totCnt;
	}
	// 10. 호스트 (체크아웃 되지 않은) 예약 / 거절한 예약 리스트
	public ArrayList<ReservationDto> getRezListforHost(String s_hid, String gr_status, int startRow, int endRow) {
		ArrayList<ReservationDto> rezList = new ArrayList<ReservationDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, GR.*, MG.S_GNAME, MG.S_GTEL, HG.S_CAMP_NO, HG.S_CAMP_NAME " + 
				"FROM (SELECT * FROM GUEST_RESERVATION ORDER BY S_REZ_NO DESC) GR, MEMBER_GUEST MG, HOST_CAMPGROUND HG, HOST_CAMPSITE HS " + 
				"WHERE HS.S_SITE_NO = GR.S_SITE_NO " + 
				"AND GR.S_GID = MG.S_GID " + 
				"AND HG.S_CAMP_NO = HS.S_CAMP_NO " + 
				"AND D_SELECT >= (TO_CHAR(SYSDATE, 'YYYY-MM-DD')) " + 
				"AND HG.S_HID = ? AND GR_STATUS = ?) WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hid);
			pstmt.setString(2, gr_status);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_rez_no = rs.getString("s_rez_no");
				String s_site_no = rs.getString("s_site_no");
				String d_select = rs.getString("d_select");
				String s_gid = rs.getString("s_gid");
				Timestamp d_rez_date = rs.getTimestamp("d_rez_date");
				String s_gtel =  rs.getString("s_gtel");
				String s_camp_no = rs.getString("s_camp_no");
				String s_gname = rs.getString("s_gname");
				String s_camp_name = rs.getString("s_camp_name");
				rezList.add(new ReservationDto(s_rez_no, s_site_no, d_select.substring(0,10), s_gid, d_rez_date, gr_status, s_gtel, s_camp_no, s_camp_name, s_gname));
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
		return rezList;
	}
	// 11. 호스트 (체크아웃 한) 수락 / 거절한 예약 수
	public int getTotCntCOforHost(String s_hid) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM GUEST_RESERVATION GR, HOST_CAMPGROUND HC, HOST_CAMPSITE HS WHERE HS.S_SITE_NO = GR.S_SITE_NO AND D_SELECT < (TO_CHAR(SYSDATE, 'YYYY-MM-DD')) AND HC.S_CAMP_NO = HS.S_CAMP_NO AND HC.S_HID = ?";
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
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return totCnt;
	}
	// 12. 호스트 (체크아웃 한) 예약 / 거절한 예약 리스트
	public ArrayList<ReservationDto> getCOListforHost(String s_hid, int startRow, int endRow) {
		ArrayList<ReservationDto> rezList = new ArrayList<ReservationDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, GR.*, MG.S_GNAME, MG.S_GTEL, HG.S_CAMP_NO, HG.S_CAMP_NAME " + 
				"FROM (SELECT * FROM GUEST_RESERVATION ORDER BY S_REZ_NO DESC) GR, MEMBER_GUEST MG, HOST_CAMPGROUND HG, HOST_CAMPSITE HS " + 
				"WHERE HS.S_SITE_NO = GR.S_SITE_NO " + 
				"AND GR.S_GID = MG.S_GID " + 
				"AND HG.S_CAMP_NO = HS.S_CAMP_NO " + 
				"AND D_SELECT < (TO_CHAR(SYSDATE, 'YYYY-MM-DD')) " + 
				"AND HG.S_HID = ? ) WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_rez_no = rs.getString("s_rez_no");
				String s_site_no = rs.getString("s_site_no");
				String d_select = rs.getString("d_select");
				String s_gid = rs.getString("s_gid");
				Timestamp d_rez_date = rs.getTimestamp("d_rez_date");
				String s_gtel =  rs.getString("s_gtel");
				String s_camp_no = rs.getString("s_camp_no");
				String s_gname = rs.getString("s_gname");
				String s_camp_name = rs.getString("s_camp_name");
				rezList.add(new ReservationDto(s_rez_no, s_site_no, d_select.substring(0,10), s_gid, d_rez_date, null, s_gtel, s_camp_no, s_camp_name, s_gname));
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
		return rezList;
	}
}