package com.snb.hbm.orm;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User_gerenshiwu_leibaio {

	private int gerenshiwu_id;//事物的id
	private int fk_user_id_gerenshiwu;//用户的id
	private int gerenshiwu_leixing;//事物的类型
	private int fk_shiwu_id;//事物的id
	private String fk_shiwu_timu;//事物的题目
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getGerenshiwu_id() {
		return gerenshiwu_id;
	}
	public void setGerenshiwu_id(int gerenshiwu_id) {
		this.gerenshiwu_id = gerenshiwu_id;
	}
	public int getFk_user_id_gerenshiwu() {
		return fk_user_id_gerenshiwu;
	}
	public void setFk_user_id_gerenshiwu(int fk_user_id_gerenshiwu) {
		this.fk_user_id_gerenshiwu = fk_user_id_gerenshiwu;
	}
	public int getGerenshiwu_leixing() {
		return gerenshiwu_leixing;
	}
	public void setGerenshiwu_leixing(int gerenshiwu_leixing) {
		this.gerenshiwu_leixing = gerenshiwu_leixing;
	}
	public int getFk_shiwu_id() {
		return fk_shiwu_id;
	}
	public void setFk_shiwu_id(int fk_shiwu_id) {
		this.fk_shiwu_id = fk_shiwu_id;
	}
	public String getFk_shiwu_timu() {
		return fk_shiwu_timu;
	}
	public void setFk_shiwu_timu(String fk_shiwu_timu) {
		this.fk_shiwu_timu = fk_shiwu_timu;
	}
	
}
