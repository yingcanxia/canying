package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Zhengce_fagui {

	private int zhengce_id;//政策id
	private String zhengce_timu;//政策题目
	private String zhengce_neirong;//政策内容
	private String zhengce_guanjiazi;//政策关键字
	private int zhengce_type;//政策类型
	private Timestamp zhengce_shangchuan_time;//政策发布时间
	private int fk_zhengce_user_id;//发布人
	private int zhengce_biaozhiwei;
	private Timestamp zhengce_gengxin_shijian;
	private int zhengce_zhidingwei;
	private int browse_count;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getZhengce_id() {
		return zhengce_id;
	}
	public void setZhengce_id(int zhengce_id) {
		this.zhengce_id = zhengce_id;
	}
	public String getZhengce_timu() {
		return zhengce_timu;
	}
	public void setZhengce_timu(String zhengce_timu) {
		this.zhengce_timu = zhengce_timu;
	}
	public String getZhengce_neirong() {
		return zhengce_neirong;
	}
	public void setZhengce_neirong(String zhengce_neirong) {
		this.zhengce_neirong = zhengce_neirong;
	}
	public String getZhengce_guanjiazi() {
		return zhengce_guanjiazi;
	}
	public void setZhengce_guanjiazi(String zhengce_guanjiazi) {
		this.zhengce_guanjiazi = zhengce_guanjiazi;
	}
	public int getZhengce_type() {
		return zhengce_type;
	}
	public void setZhengce_type(int zhengce_type) {
		this.zhengce_type = zhengce_type;
	}
	public Timestamp getZhengce_shangchuan_time() {
		return zhengce_shangchuan_time;
	}
	public void setZhengce_shangchuan_time(Timestamp zhengce_shangchuan_time) {
		this.zhengce_shangchuan_time = zhengce_shangchuan_time;
	}
	
	public int getFk_zhengce_user_id() {
		return fk_zhengce_user_id;
	}
	public void setFk_zhengce_user_id(int fk_zhengce_user_id) {
		this.fk_zhengce_user_id = fk_zhengce_user_id;
	}
	public int getZhengce_biaozhiwei() {
		return zhengce_biaozhiwei;
	}
	public void setZhengce_biaozhiwei(int zhengce_biaozhiwei) {
		this.zhengce_biaozhiwei = zhengce_biaozhiwei;
	}
	public Timestamp getZhengce_gengxin_shijian() {
		return zhengce_gengxin_shijian;
	}
	public void setZhengce_gengxin_shijian(Timestamp zhengce_gengxin_shijian) {
		this.zhengce_gengxin_shijian = zhengce_gengxin_shijian;
	}
	public int getZhengce_zhidingwei() {
		return zhengce_zhidingwei;
	}
	public void setZhengce_zhidingwei(int zhengce_zhidingwei) {
		this.zhengce_zhidingwei = zhengce_zhidingwei;
	}
	public int getBrowse_count() {
		return browse_count;
	}
	public void setBrowse_count(int browse_count) {
		this.browse_count = browse_count;
	}
	
	
}
