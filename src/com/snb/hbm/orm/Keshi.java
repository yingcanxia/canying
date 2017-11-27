package com.snb.hbm.orm;

import java.sql.Timestamp;

public class Keshi {

	private int keshi_id;//科室id
	private int fk_yiyuan_id;//科室所属医院的id
	private int keshi_biaozhiwei;//科室所属医院的id
	private int keshi_zhidingwei;//
	private String keshi_mingcheng;//科室的名称
	private String keshi_xiangqing;
	private Timestamp keshi_gengxin_shijian;
	public String getKeshi_xiangqing() {
		return keshi_xiangqing;
	}
	public int getKeshi_biaozhiwei() {
		return keshi_biaozhiwei;
	}
	public void setKeshi_biaozhiwei(int keshi_biaozhiwei) {
		this.keshi_biaozhiwei = keshi_biaozhiwei;
	}
	public int getKeshi_zhidingwei() {
		return keshi_zhidingwei;
	}
	public void setKeshi_zhidingwei(int keshi_zhidingwei) {
		this.keshi_zhidingwei = keshi_zhidingwei;
	}
	public void setKeshi_xiangqing(String keshi_xiangqing) {
		this.keshi_xiangqing = keshi_xiangqing;
	}
	
	public int getKeshi_id() {
		return keshi_id;
	}
	public int getFk_yiyuan_id() {
		return fk_yiyuan_id;
	}
	public void setFk_yiyuan_id(int fk_yiyuan_id) {
		this.fk_yiyuan_id = fk_yiyuan_id;
	}
	public void setKeshi_id(int keshi_id) {
		this.keshi_id = keshi_id;
	}
	public String getKeshi_mingcheng() {
		return keshi_mingcheng;
	}
	public void setKeshi_mingcheng(String keshi_mingcheng) {
		this.keshi_mingcheng = keshi_mingcheng;
	}
	public Timestamp getKeshi_gengxin_shijian() {
		return keshi_gengxin_shijian;
	}
	public void setKeshi_gengxin_shijian(Timestamp keshi_gengxin_shijian) {
		this.keshi_gengxin_shijian = keshi_gengxin_shijian;
	}
	
}
