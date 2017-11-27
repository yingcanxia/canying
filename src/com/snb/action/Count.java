package com.snb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.snb.services.impl.SnbService;

public class Count extends ActionSupport{

	private  SnbService snbService;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String i=request.getParameter("i");
		String ty=request.getParameter("type");
		String yyid_use=request.getParameter("yyid");
		int whe=Integer.parseInt(i);
		session.setAttribute("whe", whe);
		session.setAttribute("yyid", yyid_use);
		return ty;
	}

	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}
	
}
