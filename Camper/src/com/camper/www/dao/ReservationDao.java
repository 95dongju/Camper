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
	public static final int NOTACCEPT = 0;
	public static final int ACCEPT = 1;
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
	// 1. 예약
	public int reservation(String s_site_no, String selectDate, String s_gid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO GUEST_RESERVATION " + 
				"    VALUES (TO_CHAR(SYSDATE, 'YYMMDD')||'-'||(TO_CHAR(GUEST_RESERVATION_NO_SEQ.NEXTVAL)), ?, TO_CHAR(TO_DATE(?, 'YYYY-MM-DD')), ?, SYSDATE, 'N')";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_site_no);
			pstmt.setString(2, selectDate);
			pstmt.setString(3, s_gid);
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
		String sql = "SELECT GR.* FROM GUEST_RESERVATION GR WHERE S_SITE_NO = ? AND D_SELECT LIKE  TO_CHAR(TO_DATE(?, 'YYYY-MM-DD'))";
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
	// 2. 호스트 예약 수락
	public int confirmRez(String s_rez_no) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GUEST_RESERVATION SET GR_STATUS = 'Y' " + 
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
	// 해당 년월에 예약된 내용 보기
	public ArrayList<ReservationDto> getReservation(String s_site_no, String yearStr, String monthStr){
		ArrayList<ReservationDto> dtos = new ArrayList<ReservationDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM GUEST_RESERVATION WHERE S_SITE_NO = ? AND TO_CHAR(D_SELECT, 'YYYY-MM') LIKE ? || '-' || ?";
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
				String s_gtel =  rs.getString("s_gtel");
				dtos.add(new ReservationDto(s_rez_no, d_select, s_gid, d_rez_date, gr_status, s_gtel));
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
	// 3. 게스트 예약 수락 여부 확인
	public int confirmOrNot(String s_rez_no) {
		int result = NOTACCEPT;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT * FROM GUEST_RESERVATION WHERE S_SITE_NO = ? AND GR_STATUS = 'Y'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_rez_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = ACCEPT;
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
		return result;
	}
	// 4. 예약 취소 (호스트)
	public int cancelRezHost(String s_rez_no) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM GUEST_RESERVATION WHERE S_REZ_NO = ?";
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
	// 5. 특정 캠핑장 수락한 예약 수
	public int getRezTotCntY(String s_camp_no) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT GR.* FROM GUEST_RESERVATION GR, HOST_CAMPGROUND HC, HOST_CAMPSITE HS WHERE HS.S_SITE_NO = GR.S_SITE_NO AND HC.S_CAMP_NO =  HS.S_CAMP_NO AND HC.S_CAMP_NO = ? AND GR.GR_STATUS = 'Y'";
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
	// 5. 특정 캠핑장 수락하지 않은 예약 수
		public int getRezTotCntN(String s_camp_no) {
			int totCnt = 0;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT GR.* FROM GUEST_RESERVATION GR, HOST_CAMPGROUND HC, HOST_CAMPSITE HS WHERE HS.S_SITE_NO = GR.S_SITE_NO AND HC.S_CAMP_NO =  HS.S_CAMP_NO AND HC.S_CAMP_NO = ? AND GR.GR_STATUS = 'Y'";
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
		// 6. 특정 캠핑장 수락하지 않은 예약 리스트
	public ArrayList<ReservationDto> reservationNList(String s_camp_no) {
		ArrayList<ReservationDto> rezList = new ArrayList<ReservationDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT GR.* FROM GUEST_RESERVATION GR, HOST_CAMPGROUND HC, HOST_CAMPSITE HS WHERE HS.S_SITE_NO = GR.S_SITE_NO AND HC.S_CAMP_NO =  HS.S_CAMP_NO AND HC.S_CAMP_NO = ? AND GR.GR_STATUS = 'N'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_camp_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_rez_no = rs.getString("s_rez_no");
				String d_select = rs.getString("d_select");
				String s_gid = rs.getString("s_gid");
				Timestamp d_rez_date = rs.getTimestamp("d_rez_date");
				String gr_status = rs.getString("gr_status");
				String s_gtel =  rs.getString("s_gtel");
				rezList.add(new ReservationDto(s_rez_no, d_select, s_gid, d_rez_date, gr_status, s_gtel));
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
	// 6. 특정 캠핑장 수락한 예약 리스트
	public ArrayList<ReservationDto> reservationYList(String s_camp_no) {
		ArrayList<ReservationDto> rezList = new ArrayList<ReservationDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT GR.* FROM GUEST_RESERVATION GR, HOST_CAMPGROUND HC, HOST_CAMPSITE HS WHERE HS.S_SITE_NO = GR.S_SITE_NO AND HC.S_CAMP_NO =  HS.S_CAMP_NO AND HC.S_CAMP_NO = ? AND GR.GR_STATUS = 'Y'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_camp_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_rez_no = rs.getString("s_rez_no");
				String d_select = rs.getString("d_select");
				String s_gid = rs.getString("s_gid");
				Timestamp d_rez_date = rs.getTimestamp("d_rez_date");
				String gr_status = rs.getString("gr_status");
				String s_gtel =  rs.getString("s_gtel");
				rezList.add(new ReservationDto(s_rez_no, d_select, s_gid, d_rez_date, gr_status, s_gtel));
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
	// 7. 예약 수정 (호스트)
	public int modifyRez(ReservationDto rez) {
		int result = FAIL;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GUEST_RESERVATION SET S_SITE_NO = ?, " + 
				"                            D_SELECT = TO_DATE(?, 'YYYY-MM-DD'), " + 
				"                        WHERE S_REZ_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rez.getS_site_no());
			pstmt.setString(2, rez.getD_select());
			pstmt.setString(3, rez.getS_rez_no());
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
}