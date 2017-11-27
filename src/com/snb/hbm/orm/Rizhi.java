package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Rizhi {
	private int rizhi_id;
	private int fk_user_id;
	private String rizhi_duix_mingcheng;
	private Timestamp rizhi_shij;
	private int rizhi_biaozhiwei;
	public int getRizhi_id() {
		return rizhi_id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public void setRizhi_id(int rizhi_id) {
		this.rizhi_id = rizhi_id;
	}
	public int getFk_user_id() {
		return fk_user_id;
	}
	public void setFk_user_id(int fk_user_id) {
		this.fk_user_id = fk_user_id;
	}
	public String getRizhi_duix_mingcheng() {
		return rizhi_duix_mingcheng;
	}
	public void setRizhi_duix_mingcheng(String rizhi_duix_mingcheng) {
		this.rizhi_duix_mingcheng = rizhi_duix_mingcheng;
	}
	public Timestamp getRizhi_shij() {
		return rizhi_shij;
	}
	public void setRizhi_shij(Timestamp rizhi_shij) {
		this.rizhi_shij = rizhi_shij;
	}
	public int getRizhi_biaozhiwei() {
		return rizhi_biaozhiwei;
	}
	public void setRizhi_biaozhiwei(int rizhi_biaozhiwei) {
		this.rizhi_biaozhiwei = rizhi_biaozhiwei;
	}
	
}
