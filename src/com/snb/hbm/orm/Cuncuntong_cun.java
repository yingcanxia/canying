package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cuncuntong_cun {

	private int cun_id;//村id
	private int fk_zhen_id;//
	private String cun_mingcheng;//村的名称
	private String cun_miaoshu;//村的描述
	private int fk_user_id;//信息维护员
	private Timestamp cun_chuanjian_shijian;//村创建时间
	private int cun_biaozhiwei;//村标志位
	private Timestamp cun_gengxin_shijian;//村更新时间
	private int browse_count;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCun_id() {
		return cun_id;
	}
	public void setCun_id(int cun_id) {
		this.cun_id = cun_id;
	}
	public int getFk_zhen_id() {
		return fk_zhen_id;
	}
	public void setFk_zhen_id(int fk_zhen_id) {
		this.fk_zhen_id = fk_zhen_id;
	}
	public String getCun_mingcheng() {
		return cun_mingcheng;
	}
	public void setCun_mingcheng(String cun_mingcheng) {
		this.cun_mingcheng = cun_mingcheng;
	}
	public String getCun_miaoshu() {
		return cun_miaoshu;
	}
	public void setCun_miaoshu(String cun_miaoshu) {
		this.cun_miaoshu = cun_miaoshu;
	}
	public int getFk_user_id() {
		return fk_user_id;
	}
	public void setFk_user_id(int fk_user_id) {
		this.fk_user_id = fk_user_id;
	}
	public Timestamp getCun_chuanjian_shijian() {
		return cun_chuanjian_shijian;
	}
	public void setCun_chuanjian_shijian(Timestamp cun_chuanjian_shijian) {
		this.cun_chuanjian_shijian = cun_chuanjian_shijian;
	}
	public int getCun_biaozhiwei() {
		return cun_biaozhiwei;
	}
	public void setCun_biaozhiwei(int cun_biaozhiwei) {
		this.cun_biaozhiwei = cun_biaozhiwei;
	}
	public Timestamp getCun_gengxin_shijian() {
		return cun_gengxin_shijian;
	}
	public void setCun_gengxin_shijian(Timestamp cun_gengxin_shijian) {
		this.cun_gengxin_shijian = cun_gengxin_shijian;
	}
	public int getBrowse_count() {
		return browse_count;
	}
	public void setBrowse_count(int browse_count) {
		this.browse_count = browse_count;
	}
	
	
}
