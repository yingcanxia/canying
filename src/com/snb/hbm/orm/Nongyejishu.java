package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Nongyejishu {

	private int jishu_id;//技术id
	private int jishu_type;
	private String shipin_timu;//视频标题
	private String wenzhang_timu;//文章题目
	private String yinpin_timu;
	private String shipin_miao;//视频描述
	private String wenzhang_neirong;//文章内容
	private String yinpin_miao;
	private String shipin_dizhi;//视频地址
	private String yinpin_dizhi;
	private Timestamp chuangjian_time;//文章创建时间
	private Timestamp gengxin_time;
	private int fk_user_id;//发布视频的人
	
	private int jishu_biaozhiwei;
	private int jishu_zhidingwei;
	private int browse_count;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getJishu_id() {
		return jishu_id;
	}
	public void setJishu_id(int jishu_id) {
		this.jishu_id = jishu_id;
	}
	public String getShipin_timu() {
		return shipin_timu;
	}
	public void setShipin_timu(String shipin_timu) {
		this.shipin_timu = shipin_timu;
	}
	public String getWenzhang_timu() {
		return wenzhang_timu;
	}
	public void setWenzhang_timu(String wenzhang_timu) {
		this.wenzhang_timu = wenzhang_timu;
	}
	public String getShipin_miao() {
		return shipin_miao;
	}
	public void setShipin_miao(String shipin_miao) {
		this.shipin_miao = shipin_miao;
	}
	public String getWenzhang_neirong() {
		return wenzhang_neirong;
	}
	public void setWenzhang_neirong(String wenzhang_neirong) {
		this.wenzhang_neirong = wenzhang_neirong;
	}
	public String getShipin_dizhi() {
		return shipin_dizhi;
	}
	public void setShipin_dizhi(String shipin_dizhi) {
		this.shipin_dizhi = shipin_dizhi;
	}
	
	public int getJishu_type() {
		return jishu_type;
	}
	public void setJishu_type(int jishu_type) {
		this.jishu_type = jishu_type;
	}
	public String getYinpin_timu() {
		return yinpin_timu;
	}
	public void setYinpin_timu(String yinpin_timu) {
		this.yinpin_timu = yinpin_timu;
	}
	public String getYinpin_miao() {
		return yinpin_miao;
	}
	public void setYinpin_miao(String yinpin_miao) {
		this.yinpin_miao = yinpin_miao;
	}
	public String getYinpin_dizhi() {
		return yinpin_dizhi;
	}
	public void setYinpin_dizhi(String yinpin_dizhi) {
		this.yinpin_dizhi = yinpin_dizhi;
	}
	public Timestamp getChuangjian_time() {
		return chuangjian_time;
	}
	public void setChuangjian_time(Timestamp chuangjian_time) {
		this.chuangjian_time = chuangjian_time;
	}
	public int getFk_user_id() {
		return fk_user_id;
	}
	public void setFk_user_id(int fk_user_id) {
		this.fk_user_id = fk_user_id;
	}
	public int getJishu_biaozhiwei() {
		return jishu_biaozhiwei;
	}
	public void setJishu_biaozhiwei(int jishu_biaozhiwei) {
		this.jishu_biaozhiwei = jishu_biaozhiwei;
	}
	public Timestamp getGengxin_time() {
		return gengxin_time;
	}
	public void setGengxin_time(Timestamp gengxin_time) {
		this.gengxin_time = gengxin_time;
	}
	public int getJishu_zhidingwei() {
		return jishu_zhidingwei;
	}
	public void setJishu_zhidingwei(int jishu_zhidingwei) {
		this.jishu_zhidingwei = jishu_zhidingwei;
	}
	public int getBrowse_count() {
		return browse_count;
	}
	public void setBrowse_count(int browse_count) {
		this.browse_count = browse_count;
	}
	
}
