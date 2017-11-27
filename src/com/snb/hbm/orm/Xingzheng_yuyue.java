package com.snb.hbm.orm;

import java.sql.Timestamp;

public class Xingzheng_yuyue {

	private int xingzheng_yuyue_id;//行政预约id
	private String xingzheng_yuyue_timu;//预约题目
	private Timestamp xingzheng_yuyue_riqi;//预约日期
	private String xingzheng_yuyue_shijianduan;//时间点
	private String xingzheng_yuyue_renyuan_xingming;//人员姓名
	private String xingzheng_yuyue_renyuan_dianhua;//人员电话
	private int fk_xingzheng_yuyue_mubiao_keshi_id;//科室id
	private int xingzheng_yuyue_zhuangtai;//预约状态
	private int fk_xingzheng_yuyue_user_id;//预约的人
	private int xingzheng_yuyue_biaozhiwei;
	private int xingzheng_yuyue_zhuangtai_tz;
	private Timestamp xingzheng_yuyue_gengxin_shijian;
	private String xingzheng_yuyue_fankui_neirong;
	
	
	public int getXingzheng_yuyue_id() {
		return xingzheng_yuyue_id;
	}
	public void setXingzheng_yuyue_id(int xingzheng_yuyue_id) {
		this.xingzheng_yuyue_id = xingzheng_yuyue_id;
	}
	public String getXingzheng_yuyue_timu() {
		return xingzheng_yuyue_timu;
	}
	public void setXingzheng_yuyue_timu(String xingzheng_yuyue_timu) {
		this.xingzheng_yuyue_timu = xingzheng_yuyue_timu;
	}
	public Timestamp getXingzheng_yuyue_riqi() {
		return xingzheng_yuyue_riqi;
	}
	public void setXingzheng_yuyue_riqi(Timestamp xingzheng_yuyue_riqi) {
		this.xingzheng_yuyue_riqi = xingzheng_yuyue_riqi;
	}
	public String getXingzheng_yuyue_shijianduan() {
		return xingzheng_yuyue_shijianduan;
	}
	public void setXingzheng_yuyue_shijianduan(String xingzheng_yuyue_shijianduan) {
		this.xingzheng_yuyue_shijianduan = xingzheng_yuyue_shijianduan;
	}
	public String getXingzheng_yuyue_renyuan_xingming() {
		return xingzheng_yuyue_renyuan_xingming;
	}
	public void setXingzheng_yuyue_renyuan_xingming(
			String xingzheng_yuyue_renyuan_xingming) {
		this.xingzheng_yuyue_renyuan_xingming = xingzheng_yuyue_renyuan_xingming;
	}
	public String getXingzheng_yuyue_renyuan_dianhua() {
		return xingzheng_yuyue_renyuan_dianhua;
	}
	public void setXingzheng_yuyue_renyuan_dianhua(
			String xingzheng_yuyue_renyuan_dianhua) {
		this.xingzheng_yuyue_renyuan_dianhua = xingzheng_yuyue_renyuan_dianhua;
	}
	public int getFk_xingzheng_yuyue_mubiao_keshi_id() {
		return fk_xingzheng_yuyue_mubiao_keshi_id;
	}
	public void setFk_xingzheng_yuyue_mubiao_keshi_id(
			int fk_xingzheng_yuyue_mubiao_keshi_id) {
		this.fk_xingzheng_yuyue_mubiao_keshi_id = fk_xingzheng_yuyue_mubiao_keshi_id;
	}
	public int getXingzheng_yuyue_zhuangtai() {
		return xingzheng_yuyue_zhuangtai;
	}
	public void setXingzheng_yuyue_zhuangtai(int xingzheng_yuyue_zhuangtai) {
		this.xingzheng_yuyue_zhuangtai = xingzheng_yuyue_zhuangtai;
	}
	public int getFk_xingzheng_yuyue_user_id() {
		return fk_xingzheng_yuyue_user_id;
	}
	public void setFk_xingzheng_yuyue_user_id(int fk_xingzheng_yuyue_user_id) {
		this.fk_xingzheng_yuyue_user_id = fk_xingzheng_yuyue_user_id;
	}
	public int getXingzheng_yuyue_biaozhiwei() {
		return xingzheng_yuyue_biaozhiwei;
	}
	public void setXingzheng_yuyue_biaozhiwei(int xingzheng_yuyue_biaozhiwei) {
		this.xingzheng_yuyue_biaozhiwei = xingzheng_yuyue_biaozhiwei;
	}
	public Timestamp getXingzheng_yuyue_gengxin_shijian() {
		return xingzheng_yuyue_gengxin_shijian;
	}
	public void setXingzheng_yuyue_gengxin_shijian(Timestamp xingzheng_yuyue_gengxin_shijian) {
		this.xingzheng_yuyue_gengxin_shijian = xingzheng_yuyue_gengxin_shijian;
	}
	public int getXingzheng_yuyue_zhuangtai_tz() {
		return xingzheng_yuyue_zhuangtai_tz;
	}
	public void setXingzheng_yuyue_zhuangtai_tz(int xingzheng_yuyue_zhuangtai_tz) {
		this.xingzheng_yuyue_zhuangtai_tz = xingzheng_yuyue_zhuangtai_tz;
	}
	public String getXingzheng_yuyue_fankui_neirong() {
		return xingzheng_yuyue_fankui_neirong;
	}
	public void setXingzheng_yuyue_fankui_neirong(String xingzheng_yuyue_fankui_neirong) {
		this.xingzheng_yuyue_fankui_neirong = xingzheng_yuyue_fankui_neirong;
	}
	
	
}
