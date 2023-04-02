package com.camper.www.dto;

import java.sql.Timestamp;

public class ReservationDto {
	private String s_rez_no;
	private String s_site_no;
	private String d_select;
	private String s_gid;
	private Timestamp d_rez_date;
	private String gr_status;
	private String s_gtel;
	private int day;
	private String s_camp_no;
	private String s_camp_name;
	private String s_gname;
	public ReservationDto() {}
	public ReservationDto(String s_rez_no, String s_site_no, String d_select, String s_gid,
			Timestamp d_rez_date, String s_gtel, String gr_status, int day) {
		this.s_rez_no = s_rez_no;
		this.s_site_no = s_site_no;
		this.d_select = d_select;
		this.s_gid = s_gid;
		this.d_rez_date = d_rez_date;
		this.s_gtel = s_gtel;
		this.gr_status = gr_status;
		this.day = day;
	}
	public ReservationDto(String s_rez_no, String s_site_no, String d_select, String s_gid, Timestamp d_rez_date,
			String gr_status, String s_gtel, String s_camp_no, String s_camp_name, String s_gname) {
		this.s_rez_no = s_rez_no;
		this.s_site_no = s_site_no;
		this.d_select = d_select;
		this.s_gid = s_gid;
		this.d_rez_date = d_rez_date;
		this.gr_status = gr_status;
		this.s_gtel = s_gtel;
		this.s_camp_no = s_camp_no;
		this.s_camp_name = s_camp_name;
		this.s_gname = s_gname;
	}
	public String getS_rez_no() {
		return s_rez_no;
	}
	public void setS_rez_no(String s_rez_no) {
		this.s_rez_no = s_rez_no;
	}
	public String getS_site_no() {
		return s_site_no;
	}
	public void setS_site_no(String s_site_no) {
		this.s_site_no = s_site_no;
	}
	public String getD_select() {
		return d_select;
	}
	public void setD_select(String d_select) {
		this.d_select = d_select;
	}
	public String getS_gid() {
		return s_gid;
	}
	public void setS_gid(String s_gid) {
		this.s_gid = s_gid;
	}
	public Timestamp getD_rez_date() {
		return d_rez_date;
	}
	public void setD_rez_date(Timestamp d_rez_date) {
		this.d_rez_date = d_rez_date;
	}
	public String getGr_status() {
		return gr_status;
	}
	public void setGr_status(String gr_status) {
		this.gr_status = gr_status;
	}
	
	public String getS_gtel() {
		return s_gtel;
	}
	public void setS_gtel(String s_gtel) {
		this.s_gtel = s_gtel;
	}
	
	public String getS_camp_no() {
		return s_camp_no;
	}
	public void setS_camp_no(String s_camp_no) {
		this.s_camp_no = s_camp_no;
	}
	public String getS_camp_name() {
		return s_camp_name;
	}
	public void setS_camp_name(String s_camp_name) {
		this.s_camp_name = s_camp_name;
	}	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getS_gname() {
		return s_gname;
	}
	public void setS_gname(String s_gname) {
		this.s_gname = s_gname;
	}
	@Override
	public String toString() {
		return "ReservationDto [s_rez_no=" + s_rez_no + ", s_site_no=" + s_site_no + ", d_select=" + d_select
				+ ", s_gid=" + s_gid + ", d_rez_date=" + d_rez_date + ", gr_status=" + gr_status + ", s_gtel=" + s_gtel
				+ ", day=" + day + ", s_camp_no=" + s_camp_no + ", s_camp_name=" + s_camp_name + "]";
	}
}
