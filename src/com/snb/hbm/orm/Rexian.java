package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Rexian {

	private int rexian_id;
	private String zhuanjia_name;//专家姓名
	private String banshi_name;//办事人员姓名
	private String zhuanjia_tupain;//专家的图片
	private String banshi_tupian;//办事人员的图片
	private String banshi_danwei;//办事人员单位
	private String banshi_dizhi;//办事人员所在地址
	private int rexian_leixing;//热线种类
	private String zhuanjia_dianhua;//专家电话
	private int rexian_biaozhiwei;
	private Timestamp rexian_gengxin_shijian;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getRexian_id() {
		return rexian_id;
	}
	public void setRexian_id(int rexian_id) {
		this.rexian_id = rexian_id;
	}
	public String getZhuanjia_name() {
		return zhuanjia_name;
	}
	public void setZhuanjia_name(String zhuanjia_name) {
		this.zhuanjia_name = zhuanjia_name;
	}
	public String getBanshi_name() {
		return banshi_name;
	}
	public void setBanshi_name(String banshi_name) {
		this.banshi_name = banshi_name;
	}
	public String getZhuanjia_tupain() {
		return zhuanjia_tupain;
	}
	public void setZhuanjia_tupain(String zhuanjia_tupain) {
		this.zhuanjia_tupain = zhuanjia_tupain;
	}
	public String getBanshi_tupian() {
		return banshi_tupian;
	}
	public void setBanshi_tupian(String banshi_tupian) {
		this.banshi_tupian = banshi_tupian;
	}
	public String getBanshi_danwei() {
		return banshi_danwei;
	}
	public void setBanshi_danwei(String banshi_danwei) {
		this.banshi_danwei = banshi_danwei;
	}
	public String getBanshi_dizhi() {
		return banshi_dizhi;
	}
	public void setBanshi_dizhi(String banshi_dizhi) {
		this.banshi_dizhi = banshi_dizhi;
	}
	public int getRexian_leixing() {
		return rexian_leixing;
	}
	public void setRexian_leixing(int rexian_leixing) {
		this.rexian_leixing = rexian_leixing;
	}
	public String getZhuanjia_dianhua() {
		return zhuanjia_dianhua;
	}
	public void setZhuanjia_dianhua(String zhuanjia_dianhua) {
		this.zhuanjia_dianhua = zhuanjia_dianhua;
	}
	public int getRexian_biaozhiwei() {
		return rexian_biaozhiwei;
	}
	public void setRexian_biaozhiwei(int rexian_biaozhiwei) {
		this.rexian_biaozhiwei = rexian_biaozhiwei;
	}
	public Timestamp getRexian_gengxin_shijian() {
		return rexian_gengxin_shijian;
	}
	public void setRexian_gengxin_shijian(Timestamp rexian_gengxin_shijian) {
		this.rexian_gengxin_shijian = rexian_gengxin_shijian;
	}

}
