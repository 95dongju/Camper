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

import com.camper.www.dto.ReplyDto;
import com.camper.www.dto.ReviewDto;

public class ReplyDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private ReplyDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	private static ReplyDao instance = new ReplyDao();
	public static ReplyDao getInstance() {
		return instance;
	}
	// 1. 댓글 확인
	public ArrayList<ReplyDto> replyList(int i_rev_no) {
		ArrayList<ReplyDto> reply = new ArrayList<ReplyDto>();
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT S_REP_CONTENT, S_CAMP_NAME FROM HOST_REPLY HR, HOST_CAMPGROUND CG WHERE HR.S_HID = CG.S_HID AND I_REV_NO = ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_rev_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String s_rep_content = rs.getString("s_rep_content");
				String s_camp_name = rs.getString("s_camp_name");
				reply.add(new ReplyDto(s_rep_content, s_camp_name));
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
		return reply;
	}
	// 2. 댓글 작성
	public int writeReply(ReplyDto rp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO HOST_REPLY " + 
				"    VALUES (HOST_REPLY_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rp.getS_rep_content());
			pstmt.setString(2, rp.getS_camp_no());
			pstmt.setInt(3, rp.getI_rev_no());
			pstmt.setString(4, rp.getS_hid());
			pstmt.setString(5, rp.getS_rep_ip());
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
	// 3. 댓글 수정
	public int modifyReview(ReplyDto rp) {
		int result = FAIL;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE HOST_REPLY SET S_REP_CONTENT = ? WHERE I_REP_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rp.getS_rep_content());
			pstmt.setInt(2, rp.getI_rep_no());
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
	// 4. 댓글 삭제
	public int withdrawalReply(int i_rep_no) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM HOST_REPLY WHERE I_REP_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_rep_no);
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
	// 5. 특정 글에 달린 전체 댓글 수
	public int getReplyTotCnt(int i_rev_no) {
		int totCnt = 0;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = "SELECT COUNT(*) CNT FROM HOST_REPLY WHERE I_REV_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_rev_no);
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
