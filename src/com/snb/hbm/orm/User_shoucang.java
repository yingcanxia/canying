package com.snb.hbm.orm;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User_shoucang {

	private int shoucang_id;//收藏id
	private String tablename_id;//表名加id
	private String fk_shoucang_user_id;//收藏人id
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getShoucang_id() {
		return shoucang_id;
	}
	public void setShoucang_id(int shoucang_id) {
		this.shoucang_id = shoucang_id;
	}
	public String getTablename_id() {
		return tablename_id;
	}
	public void setTablename_id(String tablename_id) {
		this.tablename_id = tablename_id;
	}
	public String getFk_shoucang_user_id() {
		return fk_shoucang_user_id;
	}
	public void setFk_shoucang_user_id(String fk_shoucang_user_id) {
		this.fk_shoucang_user_id = fk_shoucang_user_id;
	}
	
	
}
