package com.camper.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.camper.www.dto.ReviewDto;

public class ReviewDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private ReviewDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	private static ReviewDao instance = new ReviewDao();
	public static ReviewDao getInstance() {
		return instance;
	}
	// 1. 특정 캠핑장 리뷰 목록
	public ArrayList<ReviewDto> reviewCgList(String s_camp_no, int startRow, int endRow) {
		ArrayList<ReviewDto> rvList = new ArrayList<ReviewDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.S_CAMP_NO, A.I_REV_NO, MG.S_GNICK, A.S_REV_TITLE, A.D_RDATE, A.S_REV_MAINPIC " + 
				"FROM (SELECT * FROM GUEST_REVIEW) A, MEMBER_GUEST MG " + 
				"WHERE A.S_GID = MG.S_GID AND A.S_CAMP_NO = ?) WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_camp_no);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_gnick = rs.getString("s_gnick");
				int i_rev_no = rs.getInt("i_rev_no");
				String s_rev_title = rs.getString("s_rev_title");
				String d_rdate = rs.getString("d_rdate");
				String s_rev_mainpic =  rs.getString("s_rev_mainpic");
				rvList.add(new ReviewDto(s_gnick, i_rev_no, null, s_rev_title, null, d_rdate.substring(0,10), s_rev_mainpic));
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
		return rvList;
	}
	// 2. 특정 캠핑장 리뷰 수
	public int getReviewCgCnt(String s_camp_no) {
		int totCnt = 0;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT COUNT(*) CNT FROM GUEST_REVIEW WHERE S_CAMP_NO = ?";
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
				if(rs	!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	// 1. 게스트 작성 리뷰 목록
	public ArrayList<ReviewDto> reviewGuestList(String s_gid, int startRow, int endRow) {
		ArrayList<ReviewDto> rvList = new ArrayList<ReviewDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, HG.S_CAMP_NAME, A.I_REV_NO, A.S_REV_TITLE, A.D_RDATE, A.S_REV_MAINPIC " + 
				"FROM (SELECT * FROM GUEST_REVIEW) A, HOST_CAMPGROUND HG " + 
				"WHERE A.S_CAMP_NO = HG.S_CAMP_NO AND A.S_GID = ?) WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_gid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_camp_name = rs.getString("s_camp_name");
				int i_rev_no = rs.getInt("i_rev_no");
				String s_rev_title = rs.getString("s_rev_title");
				String d_rdate = rs.getString("d_rdate");
				String s_rev_mainpic =  rs.getString("s_rev_mainpic");
				rvList.add(new ReviewDto(null, i_rev_no, s_camp_name, s_rev_title, null, d_rdate.substring(0,10), s_rev_mainpic));
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
		return rvList;
	}
	// 2. 게스트 작성 리뷰 수
	public int getReviewGuestCnt(String s_gid) {
		int totCnt = 0;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT COUNT(*) CNT FROM GUEST_REVIEW WHERE S_GID = ?";
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
				if(rs	!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
		}
	// 3. 리뷰 상세보기
	public ReviewDto viewReview(int i_rev_no) {
		ReviewDto rvView = null;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT MG.S_GNICK, GR.* " + 
				"FROM GUEST_REVIEW GR, MEMBER_GUEST MG " + 
				"WHERE GR.S_GID = MG.S_GID AND GR.I_REV_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_rev_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String s_gnick = rs.getString("s_gnick");
				String s_gid = rs.getString("s_gid");
				String s_rev_title = rs.getString("s_rev_title");
				String s_rev_content = rs.getString("s_rev_content");
				String d_rdate = rs.getString("d_rdate");;
				String s_rev_ip =  rs.getString("s_rev_ip");
				String s_rev_mainpic =  rs.getString("s_rev_mainpic");
				String s_rev_pic1 =  rs.getString("s_rev_pic1");
				String s_rev_pic2 =  rs.getString("s_rev_pic2");
				String s_rev_pic3 =  rs.getString("s_rev_pic3");
				rvView = new ReviewDto(s_gnick, null, s_gid, s_rev_title, s_rev_content, d_rdate.substring(0,10), s_rev_ip, s_rev_mainpic, s_rev_pic1, s_rev_pic2, s_rev_pic3);
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
		return rvView;
	}
	// 4. 리뷰 작성
	public int writeReview(ReviewDto rv) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO GUEST_REVIEW " + 
				"    VALUES (GUEST_REVIEW_NO_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rv.getS_camp_no());
			pstmt.setString(2, rv.getS_gid());
			pstmt.setString(3, rv.getS_rev_title());
			pstmt.setString(4, rv.getS_rev_content());
			pstmt.setString(5, rv.getS_rev_ip());
			pstmt.setString(6, rv.getS_rev_mainpic());
			pstmt.setString(7, rv.getS_rev_pic1());
			pstmt.setString(8, rv.getS_rev_pic2());
			pstmt.setString(9, rv.getS_rev_pic3());
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
	// 5. 리뷰 삭제
	public int deleteReview(int i_rev_no) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM GUEST_REVIEW WHERE I_REV_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_rev_no);
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
