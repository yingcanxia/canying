package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Fabu_nongmin_suqiu {

	private int fabu_nongmin_suqiu_id;//发布的诉求id
	private String fabu_nongmin_suqiu_timu;//发布诉求的题目
	private String fabu_nongmin_suqiu_neirong;//发布诉求的内容
	private String fabu_nongmin_suqiu_tupian_lianjie;//发布诉求图片的链接
	private String fabu_huifu_nongmin_suqiu_neirong;//回复内容
	private int fabu_biaozhiwei;
	private int fk_fabu_nongmin_suqiu_user_id;
	private String fabu_suqiu_renyuan_xingming;
	private String fabu_suqiu_renyuan_dianhua;
	private Timestamp fabu_nongmin_suqiu_gengxin_shijian;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getFabu_nongmin_suqiu_id() {
		return fabu_nongmin_suqiu_id;
	}
	public void setFabu_nongmin_suqiu_id(int fabu_nongmin_suqiu_id) {
		this.fabu_nongmin_suqiu_id = fabu_nongmin_suqiu_id;
	}
	public String getFabu_nongmin_suqiu_timu() {
		return fabu_nongmin_suqiu_timu;
	}
	public void setFabu_nongmin_suqiu_timu(String fabu_nongmin_suqiu_timu) {
		this.fabu_nongmin_suqiu_timu = fabu_nongmin_suqiu_timu;
	}
	public String getFabu_nongmin_suqiu_neirong() {
		return fabu_nongmin_suqiu_neirong;
	}
	public void setFabu_nongmin_suqiu_neirong(String fabu_nongmin_suqiu_neirong) {
		this.fabu_nongmin_suqiu_neirong = fabu_nongmin_suqiu_neirong;
	}
	public String getFabu_nongmin_suqiu_tupian_lianjie() {
		return fabu_nongmin_suqiu_tupian_lianjie;
	}
	public void setFabu_nongmin_suqiu_tupian_lianjie(
			String fabu_nongmin_suqiu_tupian_lianjie) {
		this.fabu_nongmin_suqiu_tupian_lianjie = fabu_nongmin_suqiu_tupian_lianjie;
	}
	public String getFabu_huifu_nongmin_suqiu_neirong() {
		return fabu_huifu_nongmin_suqiu_neirong;
	}
	public void setFabu_huifu_nongmin_suqiu_neirong(
			String fabu_huifu_nongmin_suqiu_neirong) {
		this.fabu_huifu_nongmin_suqiu_neirong = fabu_huifu_nongmin_suqiu_neirong;
	}
	public int getFabu_biaozhiwei() {
		return fabu_biaozhiwei;
	}
	public void setFabu_biaozhiwei(int fabu_biaozhiwei) {
		this.fabu_biaozhiwei = fabu_biaozhiwei;
	}
	public int getFk_fabu_nongmin_suqiu_user_id() {
		return fk_fabu_nongmin_suqiu_user_id;
	}
	public void setFk_fabu_nongmin_suqiu_user_id(int fk_fabu_nongmin_suqiu_user_id) {
		this.fk_fabu_nongmin_suqiu_user_id = fk_fabu_nongmin_suqiu_user_id;
	}
	public String getFabu_suqiu_renyuan_xingming() {
		return fabu_suqiu_renyuan_xingming;
	}
	public void setFabu_suqiu_renyuan_xingming(String fabu_suqiu_renyuan_xingming) {
		this.fabu_suqiu_renyuan_xingming = fabu_suqiu_renyuan_xingming;
	}
	public String getFabu_suqiu_renyuan_dianhua() {
		return fabu_suqiu_renyuan_dianhua;
	}
	public void setFabu_suqiu_renyuan_dianhua(String fabu_suqiu_renyuan_dianhua) {
		this.fabu_suqiu_renyuan_dianhua = fabu_suqiu_renyuan_dianhua;
	}
	public Timestamp getFabu_nongmin_suqiu_gengxin_shijian() {
		return fabu_nongmin_suqiu_gengxin_shijian;
	}
	public void setFabu_nongmin_suqiu_gengxin_shijian(Timestamp fabu_nongmin_suqiu_gengxin_shijian) {
		this.fabu_nongmin_suqiu_gengxin_shijian = fabu_nongmin_suqiu_gengxin_shijian;
	}
	
	
}
