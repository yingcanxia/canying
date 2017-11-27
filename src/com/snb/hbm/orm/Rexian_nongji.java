package com.snb.hbm.orm;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Rexian_nongji implements Serializable{
	private static final long serialVersionUID = 1L;
	private int fk_rexian_id;
	private int fk_nongji_id;
	private int biaozhiwei;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getFk_rexian_id() {
		return fk_rexian_id;
	}
	public void setFk_rexian_id(int fk_rexian_id) {
		this.fk_rexian_id = fk_rexian_id;
	}
	public int getFk_nongji_id() {
		return fk_nongji_id;
	}
	public void setFk_nongji_id(int fk_nongji_id) {
		this.fk_nongji_id = fk_nongji_id;
	}
	public int getBiaozhiwei() {
		return biaozhiwei;
	}
	public void setBiaozhiwei(int biaozhiwei) {
		this.biaozhiwei = biaozhiwei;
	}
	
}
