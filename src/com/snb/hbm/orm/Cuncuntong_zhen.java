package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cuncuntong_zhen {

	private int zhen_id;//镇的id
	private String zhen_mingcheng;//城镇的名称
	private String zhen_miaoshu;//成真的描述
	private int fk_user_id;//城镇信息维护员
	private Timestamp zhen_chuanjian_shijian;//镇创建时间
	private int zhen_biaozhiwei;//镇标志位
	private Timestamp zhen_gengxin_shijian;//镇更新时间
	private int browse_count;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getZhen_id() {
		return zhen_id;
	}
	public void setZhen_id(int zhen_id) {
		this.zhen_id = zhen_id;
	}
	public String getZhen_mingcheng() {
		return zhen_mingcheng;
	}
	public void setZhen_mingcheng(String zhen_mingcheng) {
		this.zhen_mingcheng = zhen_mingcheng;
	}
	public String getZhen_miaoshu() {
		return zhen_miaoshu;
	}
	public void setZhen_miaoshu(String zhen_miaoshu) {
		this.zhen_miaoshu = zhen_miaoshu;
	}
	public int getFk_user_id() {
		return fk_user_id;
	}
	public void setFk_user_id(int fk_user_id) {
		this.fk_user_id = fk_user_id;
	}
	public Timestamp getZhen_chuanjian_shijian() {
		return zhen_chuanjian_shijian;
	}
	public void setZhen_chuanjian_shijian(Timestamp zhen_chuanjian_shijian) {
		this.zhen_chuanjian_shijian = zhen_chuanjian_shijian;
	}
	public int getZhen_biaozhiwei() {
		return zhen_biaozhiwei;
	}
	public void setZhen_biaozhiwei(int zhen_biaozhiwei) {
		this.zhen_biaozhiwei = zhen_biaozhiwei;
	}
	public Timestamp getZhen_gengxin_shijian() {
		return zhen_gengxin_shijian;
	}
	public void setZhen_gengxin_shijian(Timestamp zhen_gengxin_shijian) {
		this.zhen_gengxin_shijian = zhen_gengxin_shijian;
	}
	public int getBrowse_count() {
		return browse_count;
	}
	public void setBrowse_count(int browse_count) {
		this.browse_count = browse_count;
	}
	
	
}
