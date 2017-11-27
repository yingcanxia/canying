package com.snb.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.snb.services.impl.SnbService;

public class LLTJ extends ActionSupport{
	private SnbService snbService;

	private String qishi;
	private String jieshu;
	public String queryZongti() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//��ȡrequest����
		HttpSession session=request.getSession();//ͨ��request��ȡsession����
		String x=(String)request.getParameter("i");
		Map paraMap=new HashMap();
		qishi=qishi+" 00:00:00";
		jieshu=jieshu+" 23:59:59";
		paraMap.put("qishi", qishi);
		paraMap.put("jieshu", jieshu);
		List c=new ArrayList();
		//c=snbService.query(paraMap);
		Map mdp=snbService.queryzongti(paraMap);
		c=(List)mdp.get("list");
		List<Object[]> list=new ArrayList<Object[]>();
		Map mcp=snbService.querydownload();
		List d=(List)mcp.get("list");
		for(int j=0;j<c.size();j++){
			Object[] a=(Object[])c.get(j);
			list.add(a);
		}
		request.setAttribute("para", paraMap);
		session.setAttribute("llzt", list);
		session.setAttribute("downloadzt", d.size());
		return "success_queryzongti";
	}
	public String queryGebie() throws Exception {
		// TODO Auto-generated method stub
		return "success_queryGenie";
	}
	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}
	public String getQishi() {
		return qishi;
	}
	public void setQishi(String qishi) {
		this.qishi = qishi;
	}
	public String getJieshu() {
		return jieshu;
	}
	public void setJieshu(String jieshu) {
		this.jieshu = jieshu;
	}
	
}
