package com.snb.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.snb.bean.MD5;
import com.snb.hbm.orm.Users;
import com.snb.services.impl.SnbService;

public class AddUser extends ActionSupport{
	//private int user_id;//用户id
	private String user_pwd;//用户密码
	private String user_yonghuming_id;//用户登录名
	private String user_xingming;//用户真实姓名
	private String user_dianhua;//用户电话
	private String user_dizhi;//用户地址
	private String user_shenfenzheng_haoma;//用户身份证号码
	//private String user_leixing;//用户类型
	private  SnbService snbService;

	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Users us=new Users();
		String pwd=MD5.MD5(user_pwd);
		us.setUser_pwd(pwd);
		us.setUser_yonghuming_id(user_yonghuming_id);
		us.setUser_xingming(user_xingming);
		us.setUser_dianhua(user_dianhua);
		us.setUser_dizhi(user_dizhi);
		us.setUser_shenfenzheng_haoma(user_shenfenzheng_haoma);
		us.setUser_leixing("1");
		us.setUser_biaozhiwei(1);
		Timestamp d = new Timestamp(System.currentTimeMillis());
		us.setUser_zhuce_shijian(d);
		paraMap.put("Users", us);
		snbService.saveUsers(paraMap);
		return "success";
	}


	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
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
	
	
}
