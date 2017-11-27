package com.snb.hbm.orm;

import java.sql.Timestamp;

public class Scroll_img {

	private int id;
	private String timu;
	private Timestamp shangchuan_shijan;
	private String tupian_neirong;
	private String tupian_lianjie;
	private int biaozhiwei;
	private int fk_user_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTimu() {
		return timu;
	}
	public void setTimu(String timu) {
		this.timu = timu;
	}
	
	public String getTupian_neirong() {
		return tupian_neirong;
	}
	public void setTupian_neirong(String tupian_neirong) {
		this.tupian_neirong = tupian_neirong;
	}
	public Timestamp getShangchuan_shijan() {
		return shangchuan_shijan;
	}
	public void setShangchuan_shijan(Timestamp shangchuan_shijan) {
		this.shangchuan_shijan = shangchuan_shijan;
	}
	public String getTupian_lianjie() {
		return tupian_lianjie;
	}
	public void setTupian_lianjie(String tupian_lianjie) {
		this.tupian_lianjie = tupian_lianjie;
	}
	public int getBiaozhiwei() {
		return biaozhiwei;
	}
	public void setBiaozhiwei(int biaozhiwei) {
		this.biaozhiwei = biaozhiwei;
	}
	public int getFk_user_id() {
		return fk_user_id;
	}
	public void setFk_user_id(int fk_user_id) {
		this.fk_user_id = fk_user_id;
	}
	
}
