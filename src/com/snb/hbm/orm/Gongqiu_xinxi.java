package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Gongqiu_xinxi {

	private int gongqiu_xinxi_id;//供求信息的id
	private int gongqiu_xinxi_leixing;//供求信息的类型
	private String gongqiu_xinxi_shangpin;//供求商品
	private int fk_gongqiu_xinxi_user_id;//维护信息人员
	private int gongqiu_xinxi_shenhe_zhuangtai;//信息审核状态
	private String gongqiu_xinxi_miaoshu;//供求信息描述
	private Timestamp gongqiu_xinxi_youxiaoqi;//供求信息的有效期
	private String gongqiu_xinxi_tupian_lianjie;//供求信息的图片链接
	private String gongqiu_xinxi_renyuan_dianhua;//人员联系电话
	private int gongqiu_xinxi_biaozhiwei;
	private Timestamp gongqiu_xinxi_gengxin_shijian;
	private int gongqiu_xinxi_type;//供求信息类型
	private String gongqiu_xinxi_name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getGongqiu_xinxi_id() {
		return gongqiu_xinxi_id;
	}
	public void setGongqiu_xinxi_id(int gongqiu_xinxi_id) {
		this.gongqiu_xinxi_id = gongqiu_xinxi_id;
	}
	public int getGongqiu_xinxi_leixing() {
		return gongqiu_xinxi_leixing;
	}
	public void setGongqiu_xinxi_leixing(int gongqiu_xinxi_leixing) {
		this.gongqiu_xinxi_leixing = gongqiu_xinxi_leixing;
	}
	public String getGongqiu_xinxi_shangpin() {
		return gongqiu_xinxi_shangpin;
	}
	public void setGongqiu_xinxi_shangpin(String gongqiu_xinxi_shangpin) {
		this.gongqiu_xinxi_shangpin = gongqiu_xinxi_shangpin;
	}
	public int getFk_gongqiu_xinxi_user_id() {
		return fk_gongqiu_xinxi_user_id;
	}
	public void setFk_gongqiu_xinxi_user_id(int fk_gongqiu_xinxi_user_id) {
		this.fk_gongqiu_xinxi_user_id = fk_gongqiu_xinxi_user_id;
	}
	public int getGongqiu_xinxi_shenhe_zhuangtai() {
		return gongqiu_xinxi_shenhe_zhuangtai;
	}
	public void setGongqiu_xinxi_shenhe_zhuangtai(int gongqiu_xinxi_shenhe_zhuangtai) {
		this.gongqiu_xinxi_shenhe_zhuangtai = gongqiu_xinxi_shenhe_zhuangtai;
	}
	public String getGongqiu_xinxi_miaoshu() {
		return gongqiu_xinxi_miaoshu;
	}
	public void setGongqiu_xinxi_miaoshu(String gongqiu_xinxi_miaoshu) {
		this.gongqiu_xinxi_miaoshu = gongqiu_xinxi_miaoshu;
	}
	public Timestamp getGongqiu_xinxi_youxiaoqi() {
		return gongqiu_xinxi_youxiaoqi;
	}
	public void setGongqiu_xinxi_youxiaoqi(Timestamp gongqiu_xinxi_youxiaoqi) {
		this.gongqiu_xinxi_youxiaoqi = gongqiu_xinxi_youxiaoqi;
	}
	public String getGongqiu_xinxi_tupian_lianjie() {
		return gongqiu_xinxi_tupian_lianjie;
	}
	public void setGongqiu_xinxi_tupian_lianjie(String gongqiu_xinxi_tupian_lianjie) {
		this.gongqiu_xinxi_tupian_lianjie = gongqiu_xinxi_tupian_lianjie;
	}
	public String getGongqiu_xinxi_renyuan_dianhua() {
		return gongqiu_xinxi_renyuan_dianhua;
	}
	public void setGongqiu_xinxi_renyuan_dianhua(String gongqiu_xinxi_renyuan_dianhua) {
		this.gongqiu_xinxi_renyuan_dianhua = gongqiu_xinxi_renyuan_dianhua;
	}
	public int getGongqiu_xinxi_biaozhiwei() {
		return gongqiu_xinxi_biaozhiwei;
	}
	public void setGongqiu_xinxi_biaozhiwei(int gongqiu_xinxi_biaozhiwei) {
		this.gongqiu_xinxi_biaozhiwei = gongqiu_xinxi_biaozhiwei;
	}
	public Timestamp getGongqiu_xinxi_gengxin_shijian() {
		return gongqiu_xinxi_gengxin_shijian;
	}
	public void setGongqiu_xinxi_gengxin_shijian(Timestamp gongqiu_xinxi_gengxin_shijian) {
		this.gongqiu_xinxi_gengxin_shijian = gongqiu_xinxi_gengxin_shijian;
	}
	public int getGongqiu_xinxi_type() {
		return gongqiu_xinxi_type;
	}
	public void setGongqiu_xinxi_type(int gongqiu_xinxi_type) {
		this.gongqiu_xinxi_type = gongqiu_xinxi_type;
	}
	public String getGongqiu_xinxi_name() {
		return gongqiu_xinxi_name;
	}
	public void setGongqiu_xinxi_name(String gongqiu_xinxi_name) {
		this.gongqiu_xinxi_name = gongqiu_xinxi_name;
	}
	
	
}
