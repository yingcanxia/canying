package com.snb.hbm.orm;

import java.sql.Timestamp;

public class Shuoming_wendang {

	private int id;
	private int type;
	private String neirong;
	private Timestamp shangchuan_shijian;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getNeirong() {
		return neirong;
	}
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}
	public Timestamp getShangchuan_shijian() {
		return shangchuan_shijian;
	}
	public void setShangchuan_shijian(Timestamp shangchuan_shijian) {
		this.shangchuan_shijian = shangchuan_shijian;
	}
	
}
