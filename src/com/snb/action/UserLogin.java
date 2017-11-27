package com.snb.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.snb.bean.MD5;
import com.snb.hbm.orm.Users;
import com.snb.services.impl.SnbService;

public class UserLogin extends ActionSupport{
	private String username;
	private String password;
	private  SnbService snbService;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map paraMapb=new HashMap();
		//OldPeople a=new OldPeople();
		Users us=new Users();
		//a.setUsername(Username);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		paraMap.put("username",username);
		paraMap.put("_user_biaozhiwei",0);
		Map map=snbService.query(paraMap);
		List list=(List)map.get("list");
		if(list.size()==0){
			return "false";
		}
		else{
			us=(Users)list.get(0);
			us.setUser_lastlogin(d);
			session.setAttribute("user", us);
			paraMapb.put("us", us);
			String pwd=MD5.MD5(password);
			snbService.updateus(paraMapb);
			if(us.getUser_pwd().equals(pwd)){
				session.setAttribute("user_id", us.getUser_id());
				session.setAttribute("user_pwd", us.getUser_pwd());
				session.setAttribute("user_yonghuming_id", us.getUser_yonghuming_id());
				session.setAttribute("user_xingming", us.getUser_xingming());
				session.setAttribute("user_dianhua", us.getUser_dianhua());
				session.setAttribute("user_dizhi", us.getUser_dizhi());
				session.setAttribute("user_shenfenzheng_haoma", us.getUser_shenfenzheng_haoma());
				session.setAttribute("user_leixing", us.getUser_leixing());
				return "success";
			}
		}
		return "false";
	}

	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
