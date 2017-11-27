package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Zhanxiao_xinxi {

	private int zhanxiao_xinxi_id;//id
	private String zhanxiao_xinxi_timu;//展销题目
	private String zhanxiao_xinxi_shangpin_mingcheng;//商品名称
	private String zhanxiao_xinxi_shangpin_miaoshu;//商品描述
	private String zhanxiao_xinxi_tupian_lianjie;//图片链接
	private String zhanxiao_xinxi_dizhi;//地址
	private String zhanxiao_xinxi_lianxifangshi;//联系方式
	private int fk_zhanxiao_xinxi_user_id;//发布人id
	private int zhanxiao_xinxi_biaozhiwei;
	private Timestamp zhanxiao_xinxi_gengxin_shijian;
	private int zhanxiao_xinxi_zhidingwei;
	private int browse_count;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getZhanxiao_xinxi_id() {
		return zhanxiao_xinxi_id;
	}
	public void setZhanxiao_xinxi_id(int zhanxiao_xinxi_id) {
		this.zhanxiao_xinxi_id = zhanxiao_xinxi_id;
	}
	public String getZhanxiao_xinxi_timu() {
		return zhanxiao_xinxi_timu;
	}
	public void setZhanxiao_xinxi_timu(String zhanxiao_xinxi_timu) {
		this.zhanxiao_xinxi_timu = zhanxiao_xinxi_timu;
	}
	public String getZhanxiao_xinxi_shangpin_mingcheng() {
		return zhanxiao_xinxi_shangpin_mingcheng;
	}
	public void setZhanxiao_xinxi_shangpin_mingcheng(
			String zhanxiao_xinxi_shangpin_mingcheng) {
		this.zhanxiao_xinxi_shangpin_mingcheng = zhanxiao_xinxi_shangpin_mingcheng;
	}
	public String getZhanxiao_xinxi_shangpin_miaoshu() {
		return zhanxiao_xinxi_shangpin_miaoshu;
	}
	public void setZhanxiao_xinxi_shangpin_miaoshu(
			String zhanxiao_xinxi_shangpin_miaoshu) {
		this.zhanxiao_xinxi_shangpin_miaoshu = zhanxiao_xinxi_shangpin_miaoshu;
	}
	public String getZhanxiao_xinxi_tupian_lianjie() {
		return zhanxiao_xinxi_tupian_lianjie;
	}
	public void setZhanxiao_xinxi_tupian_lianjie(
			String zhanxiao_xinxi_tupian_lianjie) {
		this.zhanxiao_xinxi_tupian_lianjie = zhanxiao_xinxi_tupian_lianjie;
	}
	public String getZhanxiao_xinxi_dizhi() {
		return zhanxiao_xinxi_dizhi;
	}
	public void setZhanxiao_xinxi_dizhi(String zhanxiao_xinxi_dizhi) {
		this.zhanxiao_xinxi_dizhi = zhanxiao_xinxi_dizhi;
	}
	public String getZhanxiao_xinxi_lianxifangshi() {
		return zhanxiao_xinxi_lianxifangshi;
	}
	public void setZhanxiao_xinxi_lianxifangshi(String zhanxiao_xinxi_lianxifangshi) {
		this.zhanxiao_xinxi_lianxifangshi = zhanxiao_xinxi_lianxifangshi;
	}
	public int getFk_zhanxiao_xinxi_user_id() {
		return fk_zhanxiao_xinxi_user_id;
	}
	public void setFk_zhanxiao_xinxi_user_id(int fk_zhanxiao_xinxi_user_id) {
		this.fk_zhanxiao_xinxi_user_id = fk_zhanxiao_xinxi_user_id;
	}
	public int getZhanxiao_xinxi_biaozhiwei() {
		return zhanxiao_xinxi_biaozhiwei;
	}
	public void setZhanxiao_xinxi_biaozhiwei(int zhanxiao_xinxi_biaozhiwei) {
		this.zhanxiao_xinxi_biaozhiwei = zhanxiao_xinxi_biaozhiwei;
	}
	public Timestamp getZhanxiao_xinxi_gengxin_shijian() {
		return zhanxiao_xinxi_gengxin_shijian;
	}
	public void setZhanxiao_xinxi_gengxin_shijian(Timestamp zhanxiao_xinxi_gengxin_shijian) {
		this.zhanxiao_xinxi_gengxin_shijian = zhanxiao_xinxi_gengxin_shijian;
	}
	public int getZhanxiao_xinxi_zhidingwei() {
		return zhanxiao_xinxi_zhidingwei;
	}
	public void setZhanxiao_xinxi_zhidingwei(int zhanxiao_xinxi_zhidingwei) {
		this.zhanxiao_xinxi_zhidingwei = zhanxiao_xinxi_zhidingwei;
	}
	public int getBrowse_count() {
		return browse_count;
	}
	public void setBrowse_count(int browse_count) {
		this.browse_count = browse_count;
	}
	
}
