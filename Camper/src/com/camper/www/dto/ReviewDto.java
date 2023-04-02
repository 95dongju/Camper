package com.camper.www.dto;

public class ReviewDto {
	private int i_rev_no;
	private String s_gnick;
	private String s_camp_name;
	private String s_camp_no;
	private String s_gid;
	private String s_rev_title;
	private String s_rev_content;
	private String d_rdate;
	private String s_rev_ip;
	private String s_rev_mainpic;
	private String s_rev_pic1;
	private String s_rev_pic2;
	private String s_rev_pic3;
	public ReviewDto() {}
	public ReviewDto(String s_gnick, int i_rev_no, String s_camp_name, String s_rev_title, String s_rev_content, String d_rdate,
			String s_rev_mainpic) {
		this.s_gnick = s_gnick;
		this.i_rev_no = i_rev_no;
		this.s_camp_name = s_camp_name;
		this.s_rev_title = s_rev_title;
		this.s_rev_content = s_rev_content;
		this.d_rdate = d_rdate;
		this.s_rev_mainpic = s_rev_mainpic;
	}
	public ReviewDto(String s_gnick, String s_camp_no, String s_gid, String s_rev_title,
			String s_rev_content, String d_rdate, String s_rev_ip, String s_rev_mainpic, String s_rev_pic1,
			String s_rev_pic2, String s_rev_pic3) {
		this.s_gnick = s_gnick;
		this.s_camp_no = s_camp_no;
		this.s_gid = s_gid;
		this.s_rev_title = s_rev_title;
		this.s_rev_content = s_rev_content;
		this.d_rdate = d_rdate;
		this.s_rev_ip = s_rev_ip;
		this.s_rev_mainpic = s_rev_mainpic;
		this.s_rev_pic1 = s_rev_pic1;
		this.s_rev_pic2 = s_rev_pic2;
		this.s_rev_pic3 = s_rev_pic3;
	}
	public int getI_rev_no() {
		return i_rev_no;
	}
	public void setI_rev_no(int i_rev_no) {
		this.i_rev_no = i_rev_no;
	}
	public String getS_gnick() {
		return s_gnick;
	}
	public void setS_gnick(String s_gnick) {
		this.s_gnick = s_gnick;
	}
	public String getS_camp_name() {
		return s_camp_name;
	}
	public void setS_camp_name(String s_camp_name) {
		this.s_camp_name = s_camp_name;
	}
	public String getS_camp_no() {
		return s_camp_no;
	}
	public void setS_camp_no(String s_camp_no) {
		this.s_camp_no = s_camp_no;
	}
	public String getS_gid() {
		return s_gid;
	}
	public void setS_gid(String s_gid) {
		this.s_gid = s_gid;
	}
	public String getS_rev_title() {
		return s_rev_title;
	}
	public void setS_rev_title(String s_rev_title) {
		this.s_rev_title = s_rev_title;
	}
	public String getS_rev_content() {
		return s_rev_content;
	}
	public void setS_rev_content(String s_rev_content) {
		this.s_rev_content = s_rev_content;
	}
	public String getD_rdate() {
		return d_rdate;
	}
	public void setD_rdate(String d_rdate) {
		this.d_rdate = d_rdate;
	}
	public String getS_rev_ip() {
		return s_rev_ip;
	}
	public void setS_rev_ip(String s_rev_ip) {
		this.s_rev_ip = s_rev_ip;
	}
	public String getS_rev_mainpic() {
		return s_rev_mainpic;
	}
	public void setS_rev_mainpic(String s_rev_mainpic) {
		this.s_rev_mainpic = s_rev_mainpic;
	}
	public String getS_rev_pic1() {
		return s_rev_pic1;
	}
	public void setS_rev_pic1(String s_rev_pic1) {
		this.s_rev_pic1 = s_rev_pic1;
	}
	public String getS_rev_pic2() {
		return s_rev_pic2;
	}
	public void setS_rev_pic2(String s_rev_pic2) {
		this.s_rev_pic2 = s_rev_pic2;
	}
	public String getS_rev_pic3() {
		return s_rev_pic3;
	}
	public void setS_rev_pic3(String s_rev_pic3) {
		this.s_rev_pic3 = s_rev_pic3;
	}
	@Override
	public String toString() {
		return "ReviewDto [i_rev_no=" + i_rev_no + ", s_gnick=" + s_gnick + ", s_camp_no=" + s_camp_no + ", s_gid="
				+ s_gid + ", s_rev_title=" + s_rev_title + ", s_rev_content=" + s_rev_content + ", d_rdate=" + d_rdate
				+ ", s_rev_ip=" + s_rev_ip + ", s_rev_mainpic=" + s_rev_mainpic + ", s_rev_pic1=" + s_rev_pic1
				+ ", s_rev_pic2=" + s_rev_pic2 + ", s_rev_pic3=" + s_rev_pic3 + "]";
	}
}
