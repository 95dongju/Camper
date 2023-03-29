package com.camper.www.dto;

import java.sql.Timestamp;

public class CampgroundDto {
	private String s_camp_no;
	private String s_camp_name;
	private String s_camp_desc;
	private String s_camp_addr;
	private String s_camp_tel;
	private String s_hid;
	private String s_camp_mainpic;
	private String s_camp_mappic;
	private String s_camp_pic1;
	private String s_camp_pic2;
	private String s_camp_pic3;
	private String s_camp_pic4;
	private String s_camp_pic5;
	private String s_bathroom;
	private String s_showerbooth;
	private String  s_store;
	private String s_sink;
	private String s_wifi;
	private String s_playground;
	private String s_with_pet;
	private String s_swim_pool;
	private String cg_del_yn;
	private Timestamp cg_rdate;
	public CampgroundDto() {}
	public CampgroundDto(String s_camp_no, String s_camp_name, String s_camp_desc, String s_camp_addr, String s_camp_tel, String s_hid,
			String s_camp_mainpic, String s_camp_mappic, String s_camp_pic1, String s_camp_pic2, String s_camp_pic3,
			String s_camp_pic4, String s_camp_pic5, String s_bathroom, String s_showerbooth, String s_store,
			String s_sink, String s_wifi, String s_playground, String s_with_pet, String s_swim_pool, String cg_del_yn,
			Timestamp cg_rdate) {
		this.s_camp_no = s_camp_no;
		this.s_camp_name = s_camp_name;
		this.s_camp_desc = s_camp_desc;
		this.s_camp_addr = s_camp_addr;
		this.s_camp_tel = s_camp_tel;
		this.s_hid = s_hid;
		this.s_camp_mainpic = s_camp_mainpic;
		this.s_camp_mappic = s_camp_mappic;
		this.s_camp_pic1 = s_camp_pic1;
		this.s_camp_pic2 = s_camp_pic2;
		this.s_camp_pic3 = s_camp_pic3;
		this.s_camp_pic4 = s_camp_pic4;
		this.s_camp_pic5 = s_camp_pic5;
		this.s_bathroom = s_bathroom;
		this.s_showerbooth = s_showerbooth;
		this.s_store = s_store;
		this.s_sink = s_sink;
		this.s_wifi = s_wifi;
		this.s_playground = s_playground;
		this.s_with_pet = s_with_pet;
		this.s_swim_pool = s_swim_pool;
		this.cg_del_yn = cg_del_yn;
		this.cg_rdate = cg_rdate;
	}
	public CampgroundDto(String s_camp_no, String s_camp_name, String s_camp_desc, String s_camp_addr, String s_camp_tel, String s_camp_mainpic,
			String s_camp_mappic, String s_camp_pic1, String s_camp_pic2, String s_camp_pic3, String s_camp_pic4,
			String s_camp_pic5, String s_bathroom, String s_showerbooth, String s_store, String s_sink, String s_wifi,
			String s_playground, String s_with_pet, String s_swim_pool) {
		this.s_camp_no = s_camp_no;
		this.s_camp_name = s_camp_name;
		this.s_camp_desc = s_camp_desc;
		this.s_camp_addr = s_camp_addr;
		this.s_camp_tel = s_camp_tel;
		this.s_camp_mainpic = s_camp_mainpic;
		this.s_camp_mappic = s_camp_mappic;
		this.s_camp_pic1 = s_camp_pic1;
		this.s_camp_pic2 = s_camp_pic2;
		this.s_camp_pic3 = s_camp_pic3;
		this.s_camp_pic4 = s_camp_pic4;
		this.s_camp_pic5 = s_camp_pic5;
		this.s_bathroom = s_bathroom;
		this.s_showerbooth = s_showerbooth;
		this.s_store = s_store;
		this.s_sink = s_sink;
		this.s_wifi = s_wifi;
		this.s_playground = s_playground;
		this.s_with_pet = s_with_pet;
		this.s_swim_pool = s_swim_pool;
	}
	public CampgroundDto(String s_camp_no, String s_camp_name) {
		this.s_camp_no = s_camp_no;
		this.s_camp_name = s_camp_name;
	}
	
	public CampgroundDto(String s_camp_name, String s_camp_addr, String s_camp_mainpic) {
		super();
		this.s_camp_name = s_camp_name;
		this.s_camp_addr = s_camp_addr;
		this.s_camp_mainpic = s_camp_mainpic;
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
	public String getS_camp_desc() {
		return s_camp_desc;
	}
	public void setS_camp_desc(String s_camp_desc) {
		this.s_camp_desc = s_camp_desc;
	}
	public String getS_camp_addr() {
		return s_camp_addr;
	}
	public void setS_camp_addr(String s_camp_addr) {
		this.s_camp_addr = s_camp_addr;
	}
	public String getS_camp_tel() {
		return s_camp_tel;
	}
	public void setS_camp_tel(String s_camp_tel) {
		this.s_camp_tel = s_camp_tel;
	}
	public String getS_hid() {
		return s_hid;
	}
	public void setS_hid(String s_hid) {
		this.s_hid = s_hid;
	}
	public String getS_camp_mainpic() {
		return s_camp_mainpic;
	}
	public void setS_camp_mainpic(String s_camp_mainpic) {
		this.s_camp_mainpic = s_camp_mainpic;
	}
	public String getS_camp_mappic() {
		return s_camp_mappic;
	}
	public void setS_camp_mappic(String s_camp_mappic) {
		this.s_camp_mappic = s_camp_mappic;
	}
	public String getS_camp_pic1() {
		return s_camp_pic1;
	}
	public void setS_camp_pic1(String s_camp_pic1) {
		this.s_camp_pic1 = s_camp_pic1;
	}
	public String getS_camp_pic2() {
		return s_camp_pic2;
	}
	public void setS_camp_pic2(String s_camp_pic2) {
		this.s_camp_pic2 = s_camp_pic2;
	}
	public String getS_camp_pic3() {
		return s_camp_pic3;
	}
	public void setS_camp_pic3(String s_camp_pic3) {
		this.s_camp_pic3 = s_camp_pic3;
	}
	public String getS_camp_pic4() {
		return s_camp_pic4;
	}
	public void setS_camp_pic4(String s_camp_pic4) {
		this.s_camp_pic4 = s_camp_pic4;
	}
	public String getS_camp_pic5() {
		return s_camp_pic5;
	}
	public void setS_camp_pic5(String s_camp_pic5) {
		this.s_camp_pic5 = s_camp_pic5;
	}
	public String getS_bathroom() {
		return s_bathroom;
	}
	public void setS_bathroom(String s_bathroom) {
		this.s_bathroom = s_bathroom;
	}
	public String getS_showerbooth() {
		return s_showerbooth;
	}
	public void setS_showerbooth(String s_showerbooth) {
		this.s_showerbooth = s_showerbooth;
	}
	public String getS_store() {
		return s_store;
	}
	public void setS_store(String s_store) {
		this.s_store = s_store;
	}
	public String getS_sink() {
		return s_sink;
	}
	public void setS_sink(String s_sink) {
		this.s_sink = s_sink;
	}
	public String getS_wifi() {
		return s_wifi;
	}
	public void setS_wifi(String s_wifi) {
		this.s_wifi = s_wifi;
	}
	public String getS_playground() {
		return s_playground;
	}
	public void setS_playground(String s_playground) {
		this.s_playground = s_playground;
	}
	public String getS_with_pet() {
		return s_with_pet;
	}
	public void setS_with_pet(String s_with_pet) {
		this.s_with_pet = s_with_pet;
	}
	public String getS_swim_pool() {
		return s_swim_pool;
	}
	public void setS_swim_pool(String s_swim_pool) {
		this.s_swim_pool = s_swim_pool;
	}
	public String getCg_del_yn() {
		return cg_del_yn;
	}
	public void setCg_del_yn(String cg_del_yn) {
		this.cg_del_yn = cg_del_yn;
	}
	public Timestamp getCg_rdate() {
		return cg_rdate;
	}
	public void setCg_rdate(Timestamp cg_rdate) {
		this.cg_rdate = cg_rdate;
	}
	@Override
	public String toString() {
		return "CampgroundDto [s_camp_no=" + s_camp_no + ", s_camp_name=" + s_camp_name + ", s_camp_desc=" + s_camp_desc
				+ ", s_camp_addr=" + s_camp_addr + ", s_camp_tel=" + s_camp_tel + ", s_hid=" + s_hid
				+ ", s_camp_mainpic=" + s_camp_mainpic + ", s_camp_mappic=" + s_camp_mappic + ", s_camp_pic1="
				+ s_camp_pic1 + ", s_camp_pic2=" + s_camp_pic2 + ", s_camp_pic3=" + s_camp_pic3 + ", s_camp_pic4="
				+ s_camp_pic4 + ", s_camp_pic5=" + s_camp_pic5 + ", s_bathroom=" + s_bathroom + ", s_showerbooth="
				+ s_showerbooth + ", s_store=" + s_store + ", s_sink=" + s_sink + ", s_wifi=" + s_wifi
				+ ", s_playground=" + s_playground + ", s_with_pet=" + s_with_pet + ", s_swim_pool=" + s_swim_pool
				+ ", cg_del_yn=" + cg_del_yn + ", cg_rdate=" + cg_rdate + "]";
	}
}
