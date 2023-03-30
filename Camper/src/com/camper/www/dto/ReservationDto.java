package com.camper.www.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class ReservationDto {
	private String s_rez_no;
	private String s_site_no;
	private String d_select;
	private String s_gid;
	private Timestamp d_rez_date;
	private String gr_status;
	private String s_gtel;
	public ReservationDto() {}
	public ReservationDto(String s_rez_no, String s_site_no, String d_select, String s_gid,
			Timestamp d_rez_date, String gr_status) {
		this.s_rez_no = s_rez_no;
		this.s_site_no = s_site_no;
		this.d_select = d_select;
		this.s_gid = s_gid;
		this.d_rez_date = d_rez_date;
		this.gr_status = gr_status;
	}
	public ReservationDto(String s_rez_no, String d_select, String s_gid,
			Timestamp d_rez_date, String gr_status, String s_gtel) {
		super();
		this.s_rez_no = s_rez_no;
		this.d_select = d_select;
		this.s_gid = s_gid;
		this.d_rez_date = d_rez_date;
		this.gr_status = gr_status;
		this.s_gtel = s_gtel;
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
	@Override
	public String toString() {
		return "ReservationDto [s_rez_no=" + s_rez_no + ", s_site_no=" + s_site_no + ", d_select=" + d_select
				+ ", s_gid=" + s_gid + ", d_rez_date=" + d_rez_date + ", gr_status="
				+ gr_status + "]";
	}
}
