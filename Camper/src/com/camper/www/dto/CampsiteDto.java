package com.camper.www.dto;

import java.sql.Timestamp;

public class CampsiteDto {
	private String s_site_no;
	private String s_camp_no;
	private String s_sitename;
	private String s_siteprice;
	private Timestamp cs_rdate;
	private String cs_del_yn;
	public CampsiteDto() {}
	public CampsiteDto(String s_site_no, String s_camp_no, String s_sitename, String s_siteprice, Timestamp cs_rdate) {
		this.s_site_no = s_site_no;
		this.s_camp_no = s_camp_no;
		this.s_sitename = s_sitename;
		this.s_siteprice = s_siteprice;
		this.cs_rdate = cs_rdate;
	}
	
	public CampsiteDto(String s_site_no, String s_sitename, String s_siteprice) {
		this.s_site_no = s_site_no;
		this.s_sitename = s_sitename;
		this.s_siteprice = s_siteprice;
	}
	public String getS_site_no() {
		return s_site_no;
	}
	public void setS_site_no(String s_site_no) {
		this.s_site_no = s_site_no;
	}
	public String getS_camp_no() {
		return s_camp_no;
	}
	public void setS_camp_no(String s_camp_no) {
		this.s_camp_no = s_camp_no;
	}
	public String getS_sitename() {
		return s_sitename;
	}
	public void setS_sitename(String s_sitename) {
		this.s_sitename = s_sitename;
	}
	public String getS_siteprice() {
		return s_siteprice;
	}
	public void setS_siteprice(String s_siteprice) {
		this.s_siteprice = s_siteprice;
	}
	public Timestamp getCs_rdate() {
		return cs_rdate;
	}
	public void setCs_rdate(Timestamp cs_rdate) {
		this.cs_rdate = cs_rdate;
	}
	public String getCs_del_yn() {
		return cs_del_yn;
	}
	public void setCs_del_yn(String cs_del_yn) {
		this.cs_del_yn = cs_del_yn;
	}
	@Override
	public String toString() {
		return "CampsiteDto [s_site_no=" + s_site_no + ", s_camp_no=" + s_camp_no + ", s_sitename=" + s_sitename
				+ ", s_siteprice=" + s_siteprice + ", cs_rdate=" + cs_rdate + ", cs_del_yn=" + cs_del_yn + "]";
	}
}
