package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Nongmin_suqiu {

	private int nongmin_suqiu_id;//诉求id
	private String nongmin_suqiu_timu;//诉求题目
	private String nongmin_suqiu_neirong;//诉求内容
	private String nongmin_suqiu_tupian_lianjie;//诉求图片链接
	private int fk_nongmin_suqiu_user_id;//发出诉求人的id
	private String nongmin_suqiu_renyuan_xingming;//发出诉求人的姓名（冗余处理）
	private String nongmin_suqiu_renyuan_dianhua;//发出诉求人的电话（冗余处理）
	/*private int fk_nongmin_suqiu_huifu_jiegou_id;//回复id
*/	private int state;//状态（审核使用）
	private int nongmin_suqiu_biaozhiwei;//假删除
	private Timestamp nongmin_suqiu_gengxin_shijian;//按时间排序
	private int nongmin_suqiu_zhidingwei;
	private int state_tz;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getNongmin_suqiu_id() {
		return nongmin_suqiu_id;
	}
	public void setNongmin_suqiu_id(int nongmin_suqiu_id) {
		this.nongmin_suqiu_id = nongmin_suqiu_id;
	}
	public String getNongmin_suqiu_timu() {
		return nongmin_suqiu_timu;
	}
	public void setNongmin_suqiu_timu(String nongmin_suqiu_timu) {
		this.nongmin_suqiu_timu = nongmin_suqiu_timu;
	}
	public String getNongmin_suqiu_neirong() {
		return nongmin_suqiu_neirong;
	}
	public void setNongmin_suqiu_neirong(String nongmin_suqiu_neirong) {
		this.nongmin_suqiu_neirong = nongmin_suqiu_neirong;
	}
	public String getNongmin_suqiu_tupian_lianjie() {
		return nongmin_suqiu_tupian_lianjie;
	}
	public void setNongmin_suqiu_tupian_lianjie(String nongmin_suqiu_tupian_lianjie) {
		this.nongmin_suqiu_tupian_lianjie = nongmin_suqiu_tupian_lianjie;
	}
	
	public int getFk_nongmin_suqiu_user_id() {
		return fk_nongmin_suqiu_user_id;
	}
	public void setFk_nongmin_suqiu_user_id(int fk_nongmin_suqiu_user_id) {
		this.fk_nongmin_suqiu_user_id = fk_nongmin_suqiu_user_id;
	}
	public String getNongmin_suqiu_renyuan_xingming() {
		return nongmin_suqiu_renyuan_xingming;
	}
	public void setNongmin_suqiu_renyuan_xingming(
			String nongmin_suqiu_renyuan_xingming) {
		this.nongmin_suqiu_renyuan_xingming = nongmin_suqiu_renyuan_xingming;
	}
	public String getNongmin_suqiu_renyuan_dianhua() {
		return nongmin_suqiu_renyuan_dianhua;
	}
	public void setNongmin_suqiu_renyuan_dianhua(
			String nongmin_suqiu_renyuan_dianhua) {
		this.nongmin_suqiu_renyuan_dianhua = nongmin_suqiu_renyuan_dianhua;
	}
	/*public int getFk_nongmin_suqiu_huifu_jiegou_id() {
		return fk_nongmin_suqiu_huifu_jiegou_id;
	}
	public void setFk_nongmin_suqiu_huifu_jiegou_id(
			int fk_nongmin_suqiu_huifu_jiegou_id) {
		this.fk_nongmin_suqiu_huifu_jiegou_id = fk_nongmin_suqiu_huifu_jiegou_id;
	}*/
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getNongmin_suqiu_biaozhiwei() {
		return nongmin_suqiu_biaozhiwei;
	}
	public void setNongmin_suqiu_biaozhiwei(int nongmin_suqiu_biaozhiwei) {
		this.nongmin_suqiu_biaozhiwei = nongmin_suqiu_biaozhiwei;
	}
	public Timestamp getNongmin_suqiu_gengxin_shijian() {
		return nongmin_suqiu_gengxin_shijian;
	}
	public void setNongmin_suqiu_gengxin_shijian(Timestamp nongmin_suqiu_gengxin_shijian) {
		this.nongmin_suqiu_gengxin_shijian = nongmin_suqiu_gengxin_shijian;
	}
	public int getNongmin_suqiu_zhidingwei() {
		return nongmin_suqiu_zhidingwei;
	}
	public void setNongmin_suqiu_zhidingwei(int nongmin_suqiu_zhidingwei) {
		this.nongmin_suqiu_zhidingwei = nongmin_suqiu_zhidingwei;
	}
	public int getState_tz() {
		return state_tz;
	}
	public void setState_tz(int state_tz) {
		this.state_tz = state_tz;
	}
	
	
}
