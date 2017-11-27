package com.snb.hbm.orm;

import java.sql.Timestamp;

public class ZhuanJia {
	private int zhuanjia_id;
	private String zhuanjia_mingcheng;
	private String zhuanjia_zhicheng;
	private String zhuanjia_lingyu;
	private String zhuanjia_zhiban_shijian;
	private int fk_keshi_id;
	private int zhuanjia_biaozhiwei;
	private int zhuanjia_zhidingwei;
	private Timestamp zhuanjia_gengxin_shijian;
	public int getZhuanjia_id() {
		return zhuanjia_id;
	}
	public void setZhuanjia_id(int zhuanjia_id) {
		this.zhuanjia_id = zhuanjia_id;
	}
	public String getZhuanjia_mingcheng() {
		return zhuanjia_mingcheng;
	}
	public void setZhuanjia_mingcheng(String zhuanjia_mingcheng) {
		this.zhuanjia_mingcheng = zhuanjia_mingcheng;
	}
	public String getZhuanjia_zhicheng() {
		return zhuanjia_zhicheng;
	}
	public void setZhuanjia_zhicheng(String zhuanjia_zhicheng) {
		this.zhuanjia_zhicheng = zhuanjia_zhicheng;
	}
	public String getZhuanjia_lingyu() {
		return zhuanjia_lingyu;
	}
	public void setZhuanjia_lingyu(String zhuanjia_lingyu) {
		this.zhuanjia_lingyu = zhuanjia_lingyu;
	}
	public String getZhuanjia_zhiban_shijian() {
		return zhuanjia_zhiban_shijian;
	}
	public void setZhuanjia_zhiban_shijian(String zhuanjia_zhiban_shijian) {
		this.zhuanjia_zhiban_shijian = zhuanjia_zhiban_shijian;
	}
	public int getFk_keshi_id() {
		return fk_keshi_id;
	}
	public void setFk_keshi_id(int fk_keshi_id) {
		this.fk_keshi_id = fk_keshi_id;
	}
	public int getZhuanjia_biaozhiwei() {
		return zhuanjia_biaozhiwei;
	}
	public void setZhuanjia_biaozhiwei(int zhuanjia_biaozhiwei) {
		this.zhuanjia_biaozhiwei = zhuanjia_biaozhiwei;
	}
	public int getZhuanjia_zhidingwei() {
		return zhuanjia_zhidingwei;
	}
	public void setZhuanjia_zhidingwei(int zhuanjia_zhidingwei) {
		this.zhuanjia_zhidingwei = zhuanjia_zhidingwei;
	}
	public Timestamp getZhuanjia_gengxin_shijian() {
		return zhuanjia_gengxin_shijian;
	}
	public void setZhuanjia_gengxin_shijian(Timestamp zhuanjia_gengxin_shijian) {
		this.zhuanjia_gengxin_shijian = zhuanjia_gengxin_shijian;
	}
	
	
}
