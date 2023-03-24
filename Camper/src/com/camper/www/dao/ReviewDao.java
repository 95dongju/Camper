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
	// 1. 전체 리뷰 목록
	public ArrayList<ReviewDto> reviewAllList() {
		ArrayList<ReviewDto> rvList = new ArrayList<ReviewDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT S_GNAME, S_REV_TITLE, S_REV_CONTENT, D_RDATE, S_REV_MAINPIC FROM MEMBER_GUEST G, GUEST_REVIEW R WHERE G.S_GID = R.S_GID AND G.G_DEL_YN = 'N' ORDER BY D_RDATE DESC";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_gname = rs.getString("s_gname");
				String s_rev_title = rs.getString("s_rev_title");
				String s_rev_content = rs.getString("s_rev_content");
				Timestamp d_rdate = rs.getTimestamp("d_rdate");
				String s_rev_mainpic =  rs.getString("s_rev_mainpic");
				rvList.add(new ReviewDto(s_gname, s_rev_title, s_rev_content, d_rdate, s_rev_mainpic));
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
	// 2. 특정 캠핑장 리뷰 목록
	public ArrayList<ReviewDto> reviewCgList(String s_camp_no) {
		ArrayList<ReviewDto> rvList = new ArrayList<ReviewDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT S_GNAME, S_REV_TITLE, D_RDATE, S_REV_MAINPIC FROM MEMBER_GUEST G, GUEST_REVIEW R WHERE G.S_GID = R.S_GID AND G.G_DEL_YN = 'N' AND R.S_CAMP_NO = ? ORDER BY D_RDATE DESC";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_camp_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_gname = rs.getString("s_gname");
				String s_rev_title = rs.getString("s_rev_title");
				String s_rev_content = rs.getString("s_rev_content");
				Timestamp d_rdate = rs.getTimestamp("d_rdate");
				String s_rev_mainpic =  rs.getString("s_rev_mainpic");
				rvList.add(new ReviewDto(s_gname, s_rev_title, s_rev_content, d_rdate, s_rev_mainpic));
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
	// 3. 리뷰 상세보기
	public ReviewDto viewReview(int i_rev_no) {
		ReviewDto rvView = null;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT S_GNAME, S_REV_TITLE, S_REV_CONTENT, D_RDATE, S_REV_MAINPIC, S_REV_PIC1, S_REV_PIC2, S_REV_PIC3 FROM MEMBER_GUEST G, GUEST_REVIEW R WHERE G.S_GID = R.S_GID AND G.G_DEL_YN = 'N' AND R.I_REV_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_rev_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String s_gname = rs.getString("s_gname");
				String s_rev_title = rs.getString("s_rev_title");
				String s_rev_content = rs.getString("s_rev_content");
				Timestamp d_rdate = rs.getTimestamp("d_rdate");
				String s_rev_mainpic =  rs.getString("s_rev_mainpic");
				String s_rev_pic1 =  rs.getString("s_rev_pic1");
				String s_rev_pic2 =  rs.getString("s_rev_pic2");
				String s_rev_pic3 =  rs.getString("s_rev_pic3");
				rvView = new ReviewDto(s_gname, s_rev_title, s_rev_content, d_rdate, s_rev_mainpic, s_rev_pic1, s_rev_pic2, s_rev_pic3);
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
				"    VALUES (GUEST_REVIEW_NO_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, SYSDATE)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rv.getS_camp_no());
			pstmt.setString(2, rv.getS_gid());
			pstmt.setString(3, rv.getS_rev_title());
			pstmt.setString(4, rv.getS_rev_content());
			pstmt.setTimestamp(5, rv.getD_rdate());
			pstmt.setString(6, rv.getS_rev_ip());
			pstmt.setString(7, rv.getS_rev_mainpic());
			pstmt.setString(8, rv.getS_rev_pic1());
			pstmt.setString(9, rv.getS_rev_pic2());
			pstmt.setString(10, rv.getS_rev_pic3());
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
	// 5. 리뷰 수정
	public int modifyReview(ReviewDto rv) {
		int result = FAIL;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GUEST_REVIEW SET S_REV_TITLE = ?, " + 
				"                        S_REV_CONTENT = ?, " + 
				"                        S_REV_MAINPIC = ?, " + 
				"                        S_REV_PIC1 = ?, " + 
				"                        S_REV_PIC2 = ?, " + 
				"                        S_REV_PIC3 = ? " + 
				"                    WHERE I_REV_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rv.getS_rev_title());
			pstmt.setString(2, rv.getS_rev_content());
			pstmt.setString(3, rv.getS_rev_mainpic());
			pstmt.setString(4, rv.getS_rev_pic1());
			pstmt.setString(5, rv.getS_rev_pic2());
			pstmt.setString(6, rv.getS_rev_pic3());
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
	// 6. 리뷰 삭제
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
	// 7. 전체 리뷰 수
	public int getReviewTotCnt() {
		int totCnt = 0;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT COUNT(*) CNT FROM GUEST_REVIEW";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
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
	// 8. 특정 캠핑장 리뷰 수
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
}
