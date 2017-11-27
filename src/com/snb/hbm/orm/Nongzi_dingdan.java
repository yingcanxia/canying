package com.snb.hbm.orm;

public class Nongzi_dingdan {

	private int dingdan_id;//订单id
	private int dingdan_shuliang;//订单数量
	private String dingdan_name;//订单名称
	private String dingdan_dizhi;//订单地址
	private String dingdan_dianhua;//订单电话
	private int fk_dingdan_user_id;//用户id
	
	
	
	public int getDingdan_id() {
		return dingdan_id;
	}
	public void setDingdan_id(int dingdan_id) {
		this.dingdan_id = dingdan_id;
	}
	public int getDingdan_shuliang() {
		return dingdan_shuliang;
	}
	public void setDingdan_shuliang(int dingdan_shuliang) {
		this.dingdan_shuliang = dingdan_shuliang;
	}
	public String getDingdan_name() {
		return dingdan_name;
	}
	public void setDingdan_name(String dingdan_name) {
		this.dingdan_name = dingdan_name;
	}
	public String getDingdan_dizhi() {
		return dingdan_dizhi;
	}
	public void setDingdan_dizhi(String dingdan_dizhi) {
		this.dingdan_dizhi = dingdan_dizhi;
	}
	public String getDingdan_dianhua() {
		return dingdan_dianhua;
	}
	public void setDingdan_dianhua(String dingdan_dianhua) {
		this.dingdan_dianhua = dingdan_dianhua;
	}
	public int getFk_dingdan_user_id() {
		return fk_dingdan_user_id;
	}
	public void setFk_dingdan_user_id(int fk_dingdan_user_id) {
		this.fk_dingdan_user_id = fk_dingdan_user_id;
	}
	
	
}
