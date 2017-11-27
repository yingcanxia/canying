package com.snb.action;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.snb.bean.PageList;

import com.snb.hbm.orm.Cuncuntong_zhen;
import com.snb.services.impl.SnbService;

public class RZTJ extends ActionSupport{
	private  SnbService snbService;

	
	public String gztj() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();//通过request获取session对象
		String x=(String)request.getParameter("i");
		int user_id;
		if(x!=null&&!x.equals(" ")){
			user_id=Integer.parseInt(x);
		}else{
			user_id=(Integer)session.getAttribute("user_id");
		}
		Map paraMap=new HashMap();
		paraMap.put("user_id", user_id);
		List c=new ArrayList();
		String nowpage=request.getParameter("nowpage");
		PageList<Object[]> pl=new PageList<Object[]>();
		pl.setNowpage(1);
		List<Object[]> rzt=new ArrayList<Object[]>();
		Map mdp=snbService.queryTongji(paraMap);
		c=(List)mdp.get("list");
		pl.setNumber(c.size());
		pl.setSumPage((c.size()+15-1)/15);
		if(nowpage!=null&&!nowpage.equals(" ")){
			int now=Integer.parseInt(nowpage);
			if(now<1){
				now=1;
			}else if(now>pl.getSumPage()){
				now=pl.getSumPage();
			}
			pl.setNowpage(now);
		}
		
		//c.get(0).getClass();
		
		for(int i=0;i<15;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*15<c.size()){
				Object[] rz= new Object[6];
				rz=(Object[])c.get(i+(pl.getNowpage()-1)*15);
				rzt.add(rz);
				pl.setPlist(rzt);
				}else{
					break;
				}
		}
		request.setAttribute("para", paraMap);
		session.setAttribute("rzt", pl);//放入session中供前台获取
		return "success_gztj";
		
	}
	public String lltj() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();//通过request获取session对象
		String x=(String)request.getParameter("i");
		/*int user_id;
		if(x!=null&&!x.equals(" ")){
			user_id=Integer.parseInt(x);
		}else{
			user_id=(Integer)session.getAttribute("user_id");
		}*/
		Map paraMap=new HashMap();
		//paraMap.put("user_id", user_id);
		List a=new ArrayList();
		List b=new ArrayList();
		List c=new ArrayList();
		List d=new ArrayList();
		List e=new ArrayList();
		List f=new ArrayList();
		List g=new ArrayList();
		List h=new ArrayList();
		List<Object[]> rzt=new ArrayList<Object[]>();
		Map mdp=snbService.queryllTongji(paraMap);
		a=(List)mdp.get("listnstx");
		b=(List)mdp.get("listzcfg");
		c=(List)mdp.get("listjgtb");
		d=(List)mdp.get("listxw");
		e=(List)mdp.get("listzhen");
		f=(List)mdp.get("listcun");
		g=(List)mdp.get("listzxxx");
		h=(List)mdp.get("listnyjs");
		BigDecimal listnstx=(BigDecimal)a.get(0);
		BigDecimal listzcfg=(BigDecimal)b.get(0);
		BigDecimal listjgtb=(BigDecimal)c.get(0);
		BigDecimal listxw=(BigDecimal)d.get(0);
		BigDecimal listzhen=(BigDecimal)e.get(0);
		BigDecimal listcun=(BigDecimal)f.get(0);
		BigDecimal listzxxx=(BigDecimal)g.get(0);
		BigDecimal listnyjs=(BigDecimal)h.get(0);;
		List<BigDecimal> llrz=new ArrayList<BigDecimal>();
		llrz.add(listnstx);
		llrz.add(listzcfg);
		llrz.add(listjgtb);
		llrz.add(listxw);
		llrz.add(listzhen);
		llrz.add(listcun);
		llrz.add(listzxxx);
		llrz.add(listnyjs);
		session.setAttribute("llrz", llrz);//放入session中供前台获取
		return "success_lltj";
		
	}

	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}
	
}
