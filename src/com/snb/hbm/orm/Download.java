package com.snb.hbm.orm;

import java.sql.Timestamp;

public class Download {

	private String id;
	private Timestamp time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
