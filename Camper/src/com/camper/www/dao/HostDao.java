package com.camper.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.camper.www.dto.HostDto;

public class HostDao {
	public static final int EXISTENT = 0;
	public static final int NONEXISTENT = 1;
	public static final int DELETE = 0;
	public static final int ACTIVE = 1;
	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private HostDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	private static HostDao instance = new HostDao();
	public static HostDao getInstance() {
		return instance;
	}
	// 1. 호스트 로그인
	public int hostLogin(String s_hid, String s_hpw) {
		int result = LOGIN_FAIL;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT * FROM MEMBER_HOST WHERE S_HID=? AND S_HPW = ? AND H_DEL_YN = 'N'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hid);
			pstmt.setString(2, s_hpw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = LOGIN_SUCCESS;
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
	// 2. 세션에 로그인 정보 저장하기 위해 s_hid로 hostDto 가져오기
	public HostDto getHost(String s_hid) {
		HostDto host = null;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT * FROM MEMBER_HOST WHERE S_HID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				host = new HostDto();
				String s_hemail = rs.getString("s_hemail");
				String s_hpw = rs.getString("s_hpw");
				String s_hname = rs.getString("s_hname");
				String s_htel = rs.getString("s_htel");
				String s_hbis_name = rs.getString("s_hbis_name");
				String s_hbis_num = rs.getString("s_hbis_num");
				String s_hbis_pic = rs.getString("s_hbis_pic");
				String s_haddr = rs.getString("s_haddr");
				String s_hacc_bankname = rs.getString("s_hacc_bankname");
				String s_haccount = rs.getString("s_haccount");
				String s_hacc_pic = rs.getString("s_hacc_pic");
				String s_hpic = rs.getString("s_hpic");
				String h_del_yn = rs.getString("h_del_yn");
				String h_ok_yn = rs.getString("h_ok_yn");
				Timestamp h_rdate = rs.getTimestamp("h_rdate");
				host = new HostDto(s_hid, s_hemail, s_hpw, s_hname, s_htel, s_hbis_name, s_hbis_num, s_hbis_pic, s_haddr, s_hacc_bankname, s_haccount, s_hacc_pic, s_hpic, h_del_yn, h_ok_yn, h_rdate);
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
		return host;
	}
	// 3. 호스트 아이디 중복체크
	public int hidConfirm(String s_hid) {
		int result = EXISTENT;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT * FROM MEMBER_HOST WHERE S_HID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = EXISTENT;
			}else {
				result = NONEXISTENT;
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
	// 4. 호스트 이메일 중복체크
	public int hemailConfirm(String s_hemail) {
		int result = EXISTENT;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT * FROM MEMBER_HOST WHERE S_HEMAIL=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hemail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = EXISTENT;
			}else {
				result = NONEXISTENT;
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
	// 5. 호스트 사업자 중복체크
	public int hBisnumConfirm(String s_hbis_num) {
		int result = EXISTENT;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT * FROM MEMBER_HOST WHERE S_HBIS_NUM = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hbis_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = EXISTENT;
			}else {
				result = NONEXISTENT;
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
	// 6. 호스트 회원가입
	public int joinHost(HostDto host) {
		int result = FAIL;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER_HOST VALUES " + 
				"    (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'noprofile.jpg', 'N', 'N', SYSDATE)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, host.getS_hid());
			pstmt.setString(2, host.getS_hemail());
			pstmt.setString(3, host.getS_hpw());
			pstmt.setString(4, host.getS_hname());
			pstmt.setString(5, host.getS_htel());
			pstmt.setString(6, host.getS_hbis_name());
			pstmt.setString(7, host.getS_hbis_num());
			pstmt.setString(8, host.getS_hbis_pic());
			pstmt.setString(9, host.getS_haddr());
			pstmt.setString(10, host.getS_hacc_bankname());
			pstmt.setString(11, host.getS_haccount());
			pstmt.setString(12, host.getS_hacc_pic());
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
	// 7. 호스트 정보 수정
	public int modifyHost(HostDto host) {
		int result = FAIL;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER_HOST SET S_HPW = ?, " + 
				"                        S_HBIS_NAME = ?, " + 
				"                        S_HBIS_NUM = ?, " + 
				"                        S_HBIS_PIC = ?, " + 
				"                        S_HADDR = ?, " + 
				"                        S_HACC_BANKNAME = ?," + 
				"                        S_HACCOUNT = ?," + 
				"                        S_HACC_PIC = ?, " + 
				"                        S_HPIC = ? " + 
				"                    WHERE S_HID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, host.getS_hpw());
			pstmt.setString(2, host.getS_hbis_name());
			pstmt.setString(3, host.getS_hbis_num());
			pstmt.setString(4, host.getS_hbis_pic());
			pstmt.setString(5, host.getS_haddr());
			pstmt.setString(6, host.getS_hacc_bankname());
			pstmt.setString(7, host.getS_haccount());
			pstmt.setString(8, host.getS_hacc_pic());
			pstmt.setString(9, host.getS_hpic());
			pstmt.setString(10, host.getS_hid());
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
	// 8. 호스트 탈퇴
	public int withdrawalHost(String s_hid, String s_hpw) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER_HOST SET H_DEL_YN = 'Y' WHERE S_HID = ? AND S_HPW = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_hid);
			pstmt.setString(2, s_hpw);
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
}
