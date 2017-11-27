package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Nongshi_tixing {

	private int nongshi_tixing_id;//农事提醒id
	private String nongshi_tixing_timu;//农事提醒的题目
	private String nongshi_tixing_neirong;//弄湿提醒的内容
	private int fk_nongshi_tixing_user_id;//发布者的id
	private Timestamp nongshi_tixing_chuanjian_shijian;//创建时间
	private Timestamp nongshi_tixing_gengxin_shijian;//更新时间
	private int nongshi_tixing_biaozhiwei;//标志位
	private int nongshi_tixing_zhidingwei;//置顶位
	private int browse_count;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getNongshi_tixing_id() {
		return nongshi_tixing_id;
	}
	public void setNongshi_tixing_id(int nongshi_tixing_id) {
		this.nongshi_tixing_id = nongshi_tixing_id;
	}
	public String getNongshi_tixing_timu() {
		return nongshi_tixing_timu;
	}
	public void setNongshi_tixing_timu(String nongshi_tixing_timu) {
		this.nongshi_tixing_timu = nongshi_tixing_timu;
	}
	public String getNongshi_tixing_neirong() {
		return nongshi_tixing_neirong;
	}
	public void setNongshi_tixing_neirong(String nongshi_tixing_neirong) {
		this.nongshi_tixing_neirong = nongshi_tixing_neirong;
	}
	public int getFk_nongshi_tixing_user_id() {
		return fk_nongshi_tixing_user_id;
	}
	public void setFk_nongshi_tixing_user_id(int fk_nongshi_tixing_user_id) {
		this.fk_nongshi_tixing_user_id = fk_nongshi_tixing_user_id;
	}
	public Timestamp getNongshi_tixing_chuanjian_shijian() {
		return nongshi_tixing_chuanjian_shijian;
	}
	public void setNongshi_tixing_chuanjian_shijian(
			Timestamp nongshi_tixing_chuanjian_shijian) {
		this.nongshi_tixing_chuanjian_shijian = nongshi_tixing_chuanjian_shijian;
	}
	public Timestamp getNongshi_tixing_gengxin_shijian() {
		return nongshi_tixing_gengxin_shijian;
	}
	public void setNongshi_tixing_gengxin_shijian(
			Timestamp nongshi_tixing_gengxin_shijian) {
		this.nongshi_tixing_gengxin_shijian = nongshi_tixing_gengxin_shijian;
	}
	public int getNongshi_tixing_biaozhiwei() {
		return nongshi_tixing_biaozhiwei;
	}
	public void setNongshi_tixing_biaozhiwei(int nongshi_tixing_biaozhiwei) {
		this.nongshi_tixing_biaozhiwei = nongshi_tixing_biaozhiwei;
	}
	public int getNongshi_tixing_zhidingwei() {
		return nongshi_tixing_zhidingwei;
	}
	public void setNongshi_tixing_zhidingwei(int nongshi_tixing_zhidingwei) {
		this.nongshi_tixing_zhidingwei = nongshi_tixing_zhidingwei;
	}
	public int getBrowse_count() {
		return browse_count;
	}
	public void setBrowse_count(int browse_count) {
		this.browse_count = browse_count;
	}
	
}
