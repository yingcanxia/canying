package com.snb.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.snb.bean.PageList;
import com.snb.hbm.orm.Nongyejishu;
import com.snb.hbm.orm.Rexian_nongji;
import com.snb.services.impl.SnbService;

public class RXJS extends ActionSupport{
	private  SnbService snbService;
	private int timu;
	public String queryYixuan() throws Exception {//已选
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		//String rxid=request.getParameter("rxid");
		//int rexianid=Integer.parseInt(rxid);
		HttpSession session=request.getSession();//通过request获取session对象
		String rxid=request.getParameter("rxid");
		int rexianid;
		if(rxid!=null&&!rxid.equals(" ")){
			
			rexianid=Integer.parseInt(rxid);
			session.setAttribute("rexianid",rexianid );
		}else{
			rexianid=(Integer)session.getAttribute("rexianid");
		}
		Map paraMap=new HashMap();
		paraMap.put("timu", rexianid);
		PageList<Object[]> pl=new PageList<Object[]>();
		pl.setNowpage(1);
		List c=new ArrayList();
		List<Object[]> nyjs=new ArrayList<Object[]>();
		Map mdp=snbService.queryYiyou(paraMap);
		c=(List)mdp.get("list");
		pl.setNumber(c.size());
		pl.setSumPage((c.size()+10-1)/10);
		if(nowpage!=null&&!nowpage.equals(" ")){
			int now=Integer.parseInt(nowpage);
			if(now<1){
				now=1;
			}else if(now>pl.getSumPage()){
				now=pl.getSumPage();
			}
			pl.setNowpage(now);
		}
		
		
		for(int i=0;i<10;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*10<c.size()){
				Object[] js=new Object[16];
				js=(Object[])c.get(i+(pl.getNowpage()-1)*10);
				nyjs.add(js);
				}else{
					break;
				}
		}
		pl.setPlist(nyjs);
		request.setAttribute("para", paraMap);
		session.setAttribute("rxnj", pl);//放入session中供前台获取
		return "success_queryyixuan";
	}
	public String queryweixuan() throws Exception {//未选
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		
		String rxid=request.getParameter("rxid");
		int rexianid;
		if(rxid!=null&&!rxid.equals(" ")){
			
			rexianid=Integer.parseInt(rxid);
			session.setAttribute("rexianid",rexianid );
		}else{
			rexianid=(Integer)session.getAttribute("rexianid");
		}
		Map paraMap=new HashMap();
		Map paraMapB=new HashMap();
		timu=-1;
		paraMap.put("timu", timu);
		PageList<Nongyejishu> pl=new PageList<Nongyejishu>();
		pl.setNowpage(1);
		if(nowpage!=null&&!nowpage.equals(" ")){
			int now=Integer.parseInt(nowpage);
			if(now<1){
				now=1;
			}
			pl.setNowpage(now);
		}
		
		List c=new ArrayList();
		List<Nongyejishu> nyjs=new ArrayList<Nongyejishu>();
		Map mdp=snbService.queryjs(paraMap);
		c=(List)mdp.get("list");
		pl.setNumber(c.size());
		pl.setSumPage((c.size()+10-1)/10);
		for(int i=0;i<10;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*10<c.size()){
				Nongyejishu js=new Nongyejishu();
				js=(Nongyejishu)c.get(i+(pl.getNowpage()-1)*10);
				nyjs.add(js);
				}else{
					break;
				}
		}
		pl.setPlist(nyjs);
		session.setAttribute("rxnj", pl);//放入session中供前台获取
		List a=new ArrayList();
		List<Rexian_nongji> rxnj=new ArrayList<Rexian_nongji>();
		paraMapB.put("rexianid", rexianid);
		Map map=snbService.queryrn(paraMapB);
		a=(List)map.get("list");
		for(int j=0;j<a.size();j++){
			Rexian_nongji rn=new Rexian_nongji();
			rn=(Rexian_nongji)a.get(j);
			rxnj.add(rn);
		}
		session.setAttribute("rxjs", rxnj);
		
		return "success_query";
	}
	public String save() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String i=request.getParameter("i");
		int jsid=Integer.parseInt(i);
		HttpSession session=request.getSession();//通过request获取session对象
		int rexianid=(Integer)session.getAttribute("rexianid");
		Rexian_nongji rn=new Rexian_nongji();
		rn.setFk_rexian_id(rexianid);
		rn.setFk_nongji_id(jsid);
		rn.setBiaozhiwei(1);
		paraMap.put("rn", rn);
		snbService.saveRn(paraMap);
		return "success_save";
	}
	public String update() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		//String nowpage=request.getParameter("nowpage");
		String js_id=request.getParameter("i");
		HttpSession session=request.getSession();//通过request获取session对象
		Map paraMap=new HashMap();
		int rexianid=(Integer)session.getAttribute("rexianid");
		int nyjs=Integer.parseInt(js_id);
		Rexian_nongji rxnj=new Rexian_nongji();
		rxnj.setFk_rexian_id(rexianid);
		rxnj.setFk_nongji_id(nyjs);
		rxnj.setBiaozhiwei(0);
		paraMap.put("rn", rxnj);
		snbService.updatern(paraMap);
		return "success_update";
	}
	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}
	public int getTimu() {
		return timu;
	}
	public void setTimu(int timu) {
		this.timu = timu;
	}
	
}
