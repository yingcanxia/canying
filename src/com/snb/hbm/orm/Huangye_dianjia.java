package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Huangye_dianjia {

	private int huangye_id;
	private int huangye_dianjia_type;
	private String huangye_dianjia_mingcheng;
	private String huangye_dianjia_dianhua;
	private String huangye_dianjia_dizhi;
	private String huangye_dianjia_tupian;
	private String huangye_xiangxi;
	private int huangye_biaozhiwei;
	private Timestamp huangye_gengxin_shijian;
	private int huangye_zhidingwei;
	private String huangye_dianjia_suoshuzhen;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getHuangye_id() {
		return huangye_id;
	}
	public void setHuangye_id(int huangye_id) {
		this.huangye_id = huangye_id;
	}
	
	public int getHuangye_dianjia_type() {
		return huangye_dianjia_type;
	}
	public void setHuangye_dianjia_type(int huangye_dianjia_type) {
		this.huangye_dianjia_type = huangye_dianjia_type;
	}
	public String getHuangye_dianjia_mingcheng() {
		return huangye_dianjia_mingcheng;
	}
	public void setHuangye_dianjia_mingcheng(String huangye_dianjia_mingcheng) {
		this.huangye_dianjia_mingcheng = huangye_dianjia_mingcheng;
	}
	public String getHuangye_dianjia_dianhua() {
		return huangye_dianjia_dianhua;
	}
	public void setHuangye_dianjia_dianhua(String huangye_dianjia_dianhua) {
		this.huangye_dianjia_dianhua = huangye_dianjia_dianhua;
	}
	public String getHuangye_dianjia_dizhi() {
		return huangye_dianjia_dizhi;
	}
	public void setHuangye_dianjia_dizhi(String huangye_dianjia_dizhi) {
		this.huangye_dianjia_dizhi = huangye_dianjia_dizhi;
	}
	public String getHuangye_dianjia_tupian() {
		return huangye_dianjia_tupian;
	}
	public void setHuangye_dianjia_tupian(String huangye_dianjia_tupian) {
		this.huangye_dianjia_tupian = huangye_dianjia_tupian;
	}
	public String getHuangye_xiangxi() {
		return huangye_xiangxi;
	}
	public void setHuangye_xiangxi(String huangye_xiangxi) {
		this.huangye_xiangxi = huangye_xiangxi;
	}
	public int getHuangye_biaozhiwei() {
		return huangye_biaozhiwei;
	}
	public void setHuangye_biaozhiwei(int huangye_biaozhiwei) {
		this.huangye_biaozhiwei = huangye_biaozhiwei;
	}
	public Timestamp getHuangye_gengxin_shijian() {
		return huangye_gengxin_shijian;
	}
	public void setHuangye_gengxin_shijian(Timestamp huangye_gengxin_shijian) {
		this.huangye_gengxin_shijian = huangye_gengxin_shijian;
	}
	public int getHuangye_zhidingwei() {
		return huangye_zhidingwei;
	}
	public void setHuangye_zhidingwei(int huangye_zhidingwei) {
		this.huangye_zhidingwei = huangye_zhidingwei;
	}
	public String getHuangye_dianjia_suoshuzhen() {
		return huangye_dianjia_suoshuzhen;
	}
	public void setHuangye_dianjia_suoshuzhen(String huangye_dianjia_suoshuzhen) {
		this.huangye_dianjia_suoshuzhen = huangye_dianjia_suoshuzhen;
	}
	
}
