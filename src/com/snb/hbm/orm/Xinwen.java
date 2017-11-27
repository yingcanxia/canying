package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Xinwen {
	private int xinwen_id;
	private String xinwen_timu;
	private int xinwen_biaozhiwei;
	private String xinwen_neirong;
	private String xinwen_tupian;
	private Timestamp xinwen_shangchuang_riqi;
	private Timestamp xinwen_gengxin_riqi;
	private int fk_shangchuang_userid;
	private int xinwen_zhidingwei;
	private int browse_count;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getXinwen_id() {
		return xinwen_id;
	}
	public void setXinwen_id(int xinwen_id) {
		this.xinwen_id = xinwen_id;
	}
	public String getXinwen_timu() {
		return xinwen_timu;
	}
	public void setXinwen_timu(String xinwen_timu) {
		this.xinwen_timu = xinwen_timu;
	}
	public int getXinwen_biaozhiwei() {
		return xinwen_biaozhiwei;
	}
	public void setXinwen_biaozhiwei(int xinwen_biaozhiwei) {
		this.xinwen_biaozhiwei = xinwen_biaozhiwei;
	}
	public String getXinwen_neirong() {
		return xinwen_neirong;
	}
	public void setXinwen_neirong(String xinwen_neirong) {
		this.xinwen_neirong = xinwen_neirong;
	}
	public Timestamp getXinwen_shangchuang_riqi() {
		return xinwen_shangchuang_riqi;
	}
	public void setXinwen_shangchuang_riqi(Timestamp xinwen_shangchuang_riqi) {
		this.xinwen_shangchuang_riqi = xinwen_shangchuang_riqi;
	}
	public Timestamp getXinwen_gengxin_riqi() {
		return xinwen_gengxin_riqi;
	}
	public void setXinwen_gengxin_riqi(Timestamp xinwen_gengxin_riqi) {
		this.xinwen_gengxin_riqi = xinwen_gengxin_riqi;
	}
	public int getFk_shangchuang_userid() {
		return fk_shangchuang_userid;
	}
	public void setFk_shangchuang_userid(int fk_shangchuang_userid) {
		this.fk_shangchuang_userid = fk_shangchuang_userid;
	}
	public String getXinwen_tupian() {
		return xinwen_tupian;
	}
	public void setXinwen_tupian(String xinwen_tupian) {
		this.xinwen_tupian = xinwen_tupian;
	}
	public int getXinwen_zhidingwei() {
		return xinwen_zhidingwei;
	}
	public void setXinwen_zhidingwei(int xinwen_zhidingwei) {
		this.xinwen_zhidingwei = xinwen_zhidingwei;
	}
	public int getBrowse_count() {
		return browse_count;
	}
	public void setBrowse_count(int browse_count) {
		this.browse_count = browse_count;
	}
	
}
