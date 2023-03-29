package com.camper.www.dto;

import java.sql.Timestamp;

public class HostDto {
	private String s_hid;
	private String s_hemail;
	private String s_hpw;
	private String s_hname;
	private String s_htel;
	private String s_hbis_name;
	private String s_hbis_num;
	private String s_hbis_pic;
	private String s_haddr;
	private String s_hacc_bankname;
	private String s_haccount;
	private String s_hacc_pic;
	private String s_hpic;
	private String h_del_yn;
	private String h_ok_yn;
	private Timestamp h_rdate;
	public HostDto() {}
	public HostDto(String s_hid, String s_hemail, String s_hpw, String s_hname, String s_htel, String s_hbis_name, String s_hbis_num,
			String s_hbis_pic, String s_haddr, String s_hacc_bankname, String s_haccount, String s_hacc_pic, String s_hpic, String h_del_yn,
			String h_ok_yn, Timestamp h_rdate) {
		this.s_hid = s_hid;
		this.s_hemail = s_hemail;
		this.s_hpw = s_hpw;
		this.s_hname = s_hname;
		this.s_htel = s_htel;
		this.s_hbis_name = s_hbis_name;
		this.s_hbis_num = s_hbis_num;
		this.s_hbis_pic = s_hbis_pic;
		this.s_haddr = s_haddr;
		this.s_hacc_bankname = s_hacc_bankname;
		this.s_haccount = s_haccount;
		this.s_hacc_pic = s_hacc_pic;
		this.s_hpic = s_hpic;
		this.h_del_yn = h_del_yn;
		this.h_ok_yn = h_ok_yn;
		this.h_rdate = h_rdate;
	}
	public HostDto(String s_hid, String s_hpw, String s_hbis_name, String s_hbis_num, String s_hbis_pic, String s_haddr,
			String s_hacc_bankname, String s_haccount, String s_hacc_pic, String s_hpic) {
		this.s_hid = s_hid;
		this.s_hpw = s_hpw;
		this.s_hbis_name = s_hbis_name;
		this.s_hbis_num = s_hbis_num;
		this.s_hbis_pic = s_hbis_pic;
		this.s_haddr = s_haddr;
		this.s_hacc_bankname = s_hacc_bankname;
		this.s_haccount = s_haccount;
		this.s_hacc_pic = s_hacc_pic;
		this.s_hpic = s_hpic;
	}
	public String getS_hid() {
		return s_hid;
	}
	public void setS_hid(String s_hid) {
		this.s_hid = s_hid;
	}
	public String getS_hemail() {
		return s_hemail;
	}
	public void setS_hemail(String s_hemail) {
		this.s_hemail = s_hemail;
	}
	public String getS_hpw() {
		return s_hpw;
	}
	public void setS_hpw(String s_hpw) {
		this.s_hpw = s_hpw;
	}
	public String getS_hname() {
		return s_hname;
	}
	public void setS_hname(String s_hname) {
		this.s_hname = s_hname;
	}
	public String getS_htel() {
		return s_htel;
	}
	public void setS_htel(String s_htel) {
		this.s_htel = s_htel;
	}
	public String getS_hbis_name() {
		return s_hbis_name;
	}
	public void setS_hbis_name(String s_hbis_name) {
		this.s_hbis_name = s_hbis_name;
	}
	public String getS_hbis_num() {
		return s_hbis_num;
	}
	public void setS_hbis_num(String s_hbis_num) {
		this.s_hbis_num = s_hbis_num;
	}
	public String getS_hbis_pic() {
		return s_hbis_pic;
	}
	public void setS_hbis_pic(String s_hbis_pic) {
		this.s_hbis_pic = s_hbis_pic;
	}
	public String getS_haddr() {
		return s_haddr;
	}
	public void setS_haddr(String s_haddr) {
		this.s_haddr = s_haddr;
	}
	public String getS_hacc_bankname() {
		return s_hacc_bankname;
	}
	public void setS_hacc_bankname(String s_hacc_bankname) {
		this.s_hacc_bankname = s_hacc_bankname;
	}
	public String getS_haccount() {
		return s_haccount;
	}
	public void setS_haccount(String s_haccount) {
		this.s_haccount = s_haccount;
	}
	public String getS_hacc_pic() {
		return s_hacc_pic;
	}
	public void setS_hacc_pic(String s_hacc_pic) {
		this.s_hacc_pic = s_hacc_pic;
	}
	public String getS_hpic() {
		return s_hpic;
	}
	public void setS_hpic(String s_hpic) {
		this.s_hpic = s_hpic;
	}
	public String getH_del_yn() {
		return h_del_yn;
	}
	public void setH_del_yn(String h_del_yn) {
		this.h_del_yn = h_del_yn;
	}
	public String getH_ok_yn() {
		return h_ok_yn;
	}
	public void setH_ok_yn(String h_ok_yn) {
		this.h_ok_yn = h_ok_yn;
	}
	public Timestamp getH_rdate() {
		return h_rdate;
	}
	public void setH_rdate(Timestamp h_rdate) {
		this.h_rdate = h_rdate;
	}
	@Override
	public String toString() {
		return "HostDto [s_hid=" + s_hid + ", s_hemail=" + s_hemail + ", s_hpw=" + s_hpw + ", s_hname=" + s_hname
				+ ", s_htel=" + s_htel + ", s_hbis_name=" + s_hbis_name + ", s_hbis_num=" + s_hbis_num + ", s_hbis_pic="
				+ s_hbis_pic + ", s_haddr=" + s_haddr + ", s_hacc_bankname=" + s_hacc_bankname + ", s_haccount="
				+ s_haccount + ", s_hacc_pic=" + s_hacc_pic + ", s_hpic=" + s_hpic + ", h_del_yn=" + h_del_yn
				+ ", h_ok_yn=" + h_ok_yn + ", h_rdate=" + h_rdate + "]";
	}
}
