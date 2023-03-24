package com.camper.www.dto;

import java.sql.Timestamp;

public class ReplyDto {
	private int i_rep_no;
	private String s_rep_content;
	private String s_camp_no;
	private String s_camp_name;
	private int i_rev_no;
	private String s_hid;
	private String s_rep_ip;
	private Timestamp rp_rdate;
	public ReplyDto() {}
	public ReplyDto(int i_rep_no, String s_rep_content, String s_camp_no, String s_camp_name, int i_rev_no,
			String s_hid, String s_rep_ip, Timestamp rp_rdate) {
		this.i_rep_no = i_rep_no;
		this.s_rep_content = s_rep_content;
		this.s_camp_no = s_camp_no;
		this.s_camp_name = s_camp_name;
		this.i_rev_no = i_rev_no;
		this.s_hid = s_hid;
		this.s_rep_ip = s_rep_ip;
		this.rp_rdate = rp_rdate;
	}
	public ReplyDto(String s_rep_content, String s_camp_name) {
		super();
		this.s_rep_content = s_rep_content;
		this.s_camp_name = s_camp_name;
	}
	public int getI_rep_no() {
		return i_rep_no;
	}
	public void setI_rep_no(int i_rep_no) {
		this.i_rep_no = i_rep_no;
	}
	public String getS_rep_content() {
		return s_rep_content;
	}
	public void setS_rep_content(String s_rep_content) {
		this.s_rep_content = s_rep_content;
	}
	public String getS_camp_no() {
		return s_camp_no;
	}
	public void setS_camp_no(String s_camp_no) {
		this.s_camp_no = s_camp_no;
	}
	public int getI_rev_no() {
		return i_rev_no;
	}
	public void setI_rev_no(int i_rev_no) {
		this.i_rev_no = i_rev_no;
	}
	public String getS_hid() {
		return s_hid;
	}
	public void setS_hid(String s_hid) {
		this.s_hid = s_hid;
	}
	public String getS_rep_ip() {
		return s_rep_ip;
	}
	public void setS_rep_ip(String s_rep_ip) {
		this.s_rep_ip = s_rep_ip;
	}
	public Timestamp getRp_rdate() {
		return rp_rdate;
	}
	public void setRp_rdate(Timestamp rp_rdate) {
		this.rp_rdate = rp_rdate;
	}
	
	public String getS_camp_name() {
		return s_camp_name;
	}
	public void setS_camp_name(String s_camp_name) {
		this.s_camp_name = s_camp_name;
	}
	@Override
	public String toString() {
		return "ReplyDto [i_rep_no=" + i_rep_no + ", s_rep_content=" + s_rep_content + ", s_camp_no=" + s_camp_no
				+ ", s_camp_name=" + s_camp_name + ", i_rev_no=" + i_rev_no + ", s_hid=" + s_hid + ", s_rep_ip="
				+ s_rep_ip + ", rp_rdate=" + rp_rdate + "]";
	}
}
