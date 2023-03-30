package com.camper.www.dto;

import java.sql.Timestamp;

public class GuestDto {
	private String s_gid;
	private String s_gpw;
	private String s_gemail;
	private String s_gname;
	private String s_gnick;
	private String s_gtel;
	private String s_gphoto;
	private String g_admin_flag;
	private String g_del_yn;
	private Timestamp g_rdate;
	public GuestDto() {	}
	public GuestDto(String s_gid, String s_gpw, String s_gemail, String s_gname, String s_gnick, String s_gtel,
			String s_gphoto, String g_admin_flag, String g_del_yn, Timestamp g_rdate) {
		this.s_gid = s_gid;
		this.s_gpw = s_gpw;
		this.s_gemail = s_gemail;
		this.s_gname = s_gname;
		this.s_gnick = s_gnick;
		this.s_gtel = s_gtel;
		this.s_gphoto = s_gphoto;
		this.g_admin_flag = g_admin_flag;
		this.g_del_yn = g_del_yn;
		this.g_rdate = g_rdate;
	}
	public GuestDto(String s_gid, String s_gpw, String s_gemail, String s_gname, String s_gnick, String s_gtel,
			String s_gphoto) {
		this.s_gid = s_gid;
		this.s_gpw = s_gpw;
		this.s_gemail = s_gemail;
		this.s_gname = s_gname;
		this.s_gnick = s_gnick;
		this.s_gtel = s_gtel;
		this.s_gphoto = s_gphoto;
	}
	public String getS_gid() {
		return s_gid;
	}
	public void setS_gid(String s_gid) {
		this.s_gid = s_gid;
	}
	public String getS_gpw() {
		return s_gpw;
	}
	public void setS_gpw(String s_gpw) {
		this.s_gpw = s_gpw;
	}
	public String getS_gemail() {
		return s_gemail;
	}
	public void setS_gemail(String s_gemail) {
		this.s_gemail = s_gemail;
	}
	public String getS_gname() {
		return s_gname;
	}
	public void setS_gname(String s_gname) {
		this.s_gname = s_gname;
	}
	public String getS_gnick() {
		return s_gnick;
	}
	public void setS_gnick(String s_gnick) {
		this.s_gnick = s_gnick;
	}
	public String getS_gtel() {
		return s_gtel;
	}
	public void setS_gtel(String s_gtel) {
		this.s_gtel = s_gtel;
	}
	public String getS_gphoto() {
		return s_gphoto;
	}
	public void setS_gphoto(String s_gphoto) {
		this.s_gphoto = s_gphoto;
	}
	public String getG_admin_flag() {
		return g_admin_flag;
	}
	public void setG_admin_flag(String g_admin_flag) {
		this.g_admin_flag = g_admin_flag;
	}
	public String getG_del_yn() {
		return g_del_yn;
	}
	public void setG_del_yn(String g_del_yn) {
		this.g_del_yn = g_del_yn;
	}
	public Timestamp getG_rdate() {
		return g_rdate;
	}
	public void setG_rdate(Timestamp g_rdate) {
		this.g_rdate = g_rdate;
	}
	@Override
	public String toString() {
		return "GuestDto [s_gid=" + s_gid + ", s_gpw=" + s_gpw + ", s_gemail=" + s_gemail + ", s_gname=" + s_gname
				+ ", s_gnick=" + s_gnick + ", s_gtel=" + s_gtel + ", s_gphoto=" + s_gphoto + ", g_admin_flag="
				+ g_admin_flag + ", g_del_yn=" + g_del_yn + ", g_rdate=" + g_rdate + "]";
	}
}