package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Huifu_nongmin_suqiu {

	private int huifu_nongmin_suqiu_id;//�ظ������id
	private String huifu_nongmin_suqiu_timu;//�ظ���Ŀ
	private String huifu_nongmin_suqiu_neirong;//�ظ�����
	private int fk_neirong_suqiu_yunashi_id;//ԭʼ����
	private Timestamp huifu_nongmin_suqiu_chuanjian_shijian;//����ʱ��
	private Timestamp huifu_nongmin_suqiu_gengxin_shijian;//����ʱ��
	private int fk_huifu_nongmin_suqiu_user_id;//�ظ�����Աid
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getHuifu_nongmin_suqiu_id() {
		return huifu_nongmin_suqiu_id;
	}
	public void setHuifu_nongmin_suqiu_id(int huifu_nongmin_suqiu_id) {
		this.huifu_nongmin_suqiu_id = huifu_nongmin_suqiu_id;
	}
	public String getHuifu_nongmin_suqiu_timu() {
		return huifu_nongmin_suqiu_timu;
	}
	public void setHuifu_nongmin_suqiu_timu(String huifu_nongmin_suqiu_timu) {
		this.huifu_nongmin_suqiu_timu = huifu_nongmin_suqiu_timu;
	}
	public String getHuifu_nongmin_suqiu_neirong() {
		return huifu_nongmin_suqiu_neirong;
	}
	public void setHuifu_nongmin_suqiu_neirong(String huifu_nongmin_suqiu_neirong) {
		this.huifu_nongmin_suqiu_neirong = huifu_nongmin_suqiu_neirong;
	}
	public int getFk_neirong_suqiu_yunashi_id() {
		return fk_neirong_suqiu_yunashi_id;
	}
	public void setFk_neirong_suqiu_yunashi_id(int fk_neirong_suqiu_yunashi_id) {
		this.fk_neirong_suqiu_yunashi_id = fk_neirong_suqiu_yunashi_id;
	}
	public Timestamp getHuifu_nongmin_suqiu_chuanjian_shijian() {
		return huifu_nongmin_suqiu_chuanjian_shijian;
	}
	public void setHuifu_nongmin_suqiu_chuanjian_shijian(
			Timestamp huifu_nongmin_suqiu_chuanjian_shijian) {
		this.huifu_nongmin_suqiu_chuanjian_shijian = huifu_nongmin_suqiu_chuanjian_shijian;
	}
	public Timestamp getHuifu_nongmin_suqiu_gengxin_shijian() {
		return huifu_nongmin_suqiu_gengxin_shijian;
	}
	public void setHuifu_nongmin_suqiu_gengxin_shijian(
			Timestamp huifu_nongmin_suqiu_gengxin_shijian) {
		this.huifu_nongmin_suqiu_gengxin_shijian = huifu_nongmin_suqiu_gengxin_shijian;
	}
	public int getFk_huifu_nongmin_suqiu_user_id() {
		return fk_huifu_nongmin_suqiu_user_id;
	}
	public void setFk_huifu_nongmin_suqiu_user_id(int fk_huifu_nongmin_suqiu_user_id) {
		this.fk_huifu_nongmin_suqiu_user_id = fk_huifu_nongmin_suqiu_user_id;
	}
	

}
