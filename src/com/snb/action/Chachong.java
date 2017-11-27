package com.snb.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.opensymphony.xwork2.ActionSupport;
import com.snb.services.impl.SnbService;

public class Chachong extends ActionSupport{
	private String user_yonghuming_id;
	private  SnbService snbService;
	private Map data=new HashMap();
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		paraMap.put("user_yonghuming_id",user_yonghuming_id);
		Map mdp=snbService.query2(paraMap);
		List c=new ArrayList();
		c=(List)mdp.get("list");
		if(c.size()==0){
			data.put("result", "ok");
		}
		else{
			data.put("result", "no");
		}
		return "returnJson";
		
	}

	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}

	public String getUser_yonghuming_id() {
		return user_yonghuming_id;
	}

	public void setUser_yonghuming_id(String user_yonghuming_id) {
		this.user_yonghuming_id = user_yonghuming_id;
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}
}
