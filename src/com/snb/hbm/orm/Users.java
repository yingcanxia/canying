package com.snb.hbm.orm;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Users {

	private int user_id;//用户id
	private String user_pwd;//用户密码
	private String user_yonghuming_id;//用户登录名
	private String user_xingming;//用户真实姓名
	private String user_dianhua;//用户电话
	private String user_dizhi;//用户地址
	private String user_shenfenzheng_haoma;//用户身份证号码
	private String user_leixing;//用户类型
	private Timestamp user_zhuce_shijian;
	private Timestamp user_lastlogin;
	private int user_biaozhiwei;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_yonghuming_id() {
		return user_yonghuming_id;
	}
	public void setUser_yonghuming_id(String user_yonghuming_id) {
		this.user_yonghuming_id = user_yonghuming_id;
	}
	public String getUser_xingming() {
		return user_xingming;
	}
	public void setUser_xingming(String user_xingming) {
		this.user_xingming = user_xingming;
	}
	public String getUser_dianhua() {
		return user_dianhua;
	}
	public void setUser_dianhua(String user_dianhua) {
		this.user_dianhua = user_dianhua;
	}
	public String getUser_dizhi() {
		return user_dizhi;
	}
	public void setUser_dizhi(String user_dizhi) {
		this.user_dizhi = user_dizhi;
	}
	public String getUser_shenfenzheng_haoma() {
		return user_shenfenzheng_haoma;
	}
	public void setUser_shenfenzheng_haoma(String user_shenfenzheng_haoma) {
		this.user_shenfenzheng_haoma = user_shenfenzheng_haoma;
	}
	public String getUser_leixing() {
		return user_leixing;
	}
	public void setUser_leixing(String user_leixing) {
		this.user_leixing = user_leixing;
	}
	public Timestamp getUser_zhuce_shijian() {
		return user_zhuce_shijian;
	}
	public void setUser_zhuce_shijian(Timestamp user_zhuce_shijian) {
		this.user_zhuce_shijian = user_zhuce_shijian;
	}
	public Timestamp getUser_lastlogin() {
		return user_lastlogin;
	}
	public void setUser_lastlogin(Timestamp user_lastlogin) {
		this.user_lastlogin = user_lastlogin;
	}
	public int getUser_biaozhiwei() {
		return user_biaozhiwei;
	}
	public void setUser_biaozhiwei(int user_biaozhiwei) {
		this.user_biaozhiwei = user_biaozhiwei;
	}
	
}
