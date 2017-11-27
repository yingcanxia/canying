package com.snb.hbm.orm;

import java.sql.Timestamp;

public class Liulan_rizhi {

	private int id;
	private int duixiang_id;
	private String duixiang_mingcheng;
	private Timestamp liulan_shijian;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDuixiang_id() {
		return duixiang_id;
	}
	public void setDuixiang_id(int duixiang_id) {
		this.duixiang_id = duixiang_id;
	}
	public String getDuixiang_mingcheng() {
		return duixiang_mingcheng;
	}
	public void setDuixiang_mingcheng(String duixiang_mingcheng) {
		this.duixiang_mingcheng = duixiang_mingcheng;
	}
	public Timestamp getLiulan_shijian() {
		return liulan_shijian;
	}
	public void setLiulan_shijian(Timestamp liulan_shijian) {
		this.liulan_shijian = liulan_shijian;
	}
	
}
