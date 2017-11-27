package com.snb.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.snb.bean.MD5;
import com.snb.bean.PageList;
import com.snb.hbm.orm.Gongqiu_xinxi;
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Users;
import com.snb.services.impl.SnbService;

public class USER extends ActionSupport{
	private  SnbService snbService;
	private String timu;
	private String passwordA;//过去的
	private String passwordB;//现在的
	private int nowpageA=0;

	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		
		Map paraMap=new HashMap();
		PageList<Users> pl=new PageList<Users>();
		pl.setNowpage(1);
		paraMap.put("timu", timu);
		List c=new ArrayList();
		List<Users> gqxx=new ArrayList<Users>();
		Map mdp=snbService.queryUs(paraMap);
		c=(List)mdp.get("list");
		pl.setNumber(c.size());
		pl.setSumPage((c.size()-1)/15+1);
		if(nowpage!=null&&!nowpage.equals(" ")){
			int now=Integer.parseInt(nowpage);
			if(now<1){
				now=1;
			}else if(now>pl.getSumPage()){
				now=pl.getSumPage();
			}
			pl.setNowpage(now);
		}else if(nowpageA>0){
			pl.setNowpage(nowpageA);
		}
		
		for(int i=0;i<15;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*15<c.size()){
				Users zx=new Users();
				zx=(Users)c.get(i+(pl.getNowpage()-1)*15);
				gqxx.add(zx);
				}else{
					break;
				}
		}
		pl.setPlist(gqxx);
		request.setAttribute("para", paraMap);
		session.setAttribute("users", pl);//放入session中供前台获取
		return "success_query";
	}
	public String querymanager() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		
		Map paraMap=new HashMap();
		PageList<Users> pl=new PageList<Users>();
		pl.setNowpage(1);
		paraMap.put("timu", timu);
		List c=new ArrayList();
		List<Users> gqxx=new ArrayList<Users>();
		Map mdp=snbService.querymanager(paraMap);
		c=(List)mdp.get("list");
		pl.setNumber(c.size());
		pl.setSumPage((c.size()-1)/15+1);
		if(nowpage!=null&&!nowpage.equals(" ")){
			int now=Integer.parseInt(nowpage);
			if(now<1){
				now=1;
			}else if(now>pl.getSumPage()){
				now=pl.getSumPage();
			}
			pl.setNowpage(now);
		}else if(nowpageA>0){
			pl.setNowpage(nowpageA);
		}
		for(int i=0;i<15;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*10<c.size()){
				Users zx=new Users();
				zx=(Users)c.get(i+(pl.getNowpage()-1)*15);
				gqxx.add(zx);
				}else{
					break;
				}
		}
		pl.setPlist(gqxx);
		session.setAttribute("manager", pl);//放入session中供前台获取
		return "query_manager";
	}
	public String queryuser0() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		
		Map paraMap=new HashMap();
		PageList<Users> pl=new PageList<Users>();
		pl.setNowpage(1);
		paraMap.put("timu", timu);
		List c=new ArrayList();
		List<Users> gqxx=new ArrayList<Users>();
		Map mdp=snbService.queryuser0(paraMap);
		c=(List)mdp.get("list");
		pl.setNumber(c.size());
		pl.setSumPage((c.size()-1)/15+1);
		if(nowpage!=null&&!nowpage.equals(" ")){
			int now=Integer.parseInt(nowpage);
			if(now<1){
				now=1;
			}else if(now>pl.getSumPage()){
				now=pl.getSumPage();
			}
			pl.setNowpage(now);
		}else if(nowpageA>0){
			pl.setNowpage(nowpageA);
		}
		
		for(int i=0;i<15;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*15<c.size()){
				Users zx=new Users();
				zx=(Users)c.get(i+(pl.getNowpage()-1)*15);
				gqxx.add(zx);
				}else{
					break;
				}
		}
		pl.setPlist(gqxx);
		request.setAttribute("para", paraMap);
		session.setAttribute("user0", pl);//放入session中供前台获取
		return "query_user0";
	}
	public String updateA() throws Exception {//授予权限
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		PageList<Users> gqxx=(PageList)session.getAttribute("users");
		Users gq=gqxx.getPlist().get(whe-1);
		gq.setUser_leixing("2");
		paraMap.put("us", gq);
		snbService.updateus(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("User");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);

		return "success_update";
	}
	public String updateB() throws Exception {//撤销权限
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		PageList<Users> gqxx=(PageList)session.getAttribute("users");
		Users gq=gqxx.getPlist().get(whe-1);
		gq.setUser_leixing("1");
		paraMap.put("us", gq);
		snbService.updateus(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("User");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_update";
	}
	public String updateC() throws Exception {//撤销权限
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		PageList<Users> gqxx=(PageList)session.getAttribute("user0");
		Users gq=gqxx.getPlist().get(whe-1);
		//gq.setUser_pwd("000000");
		String pwd=MD5.MD5("000000");
		gq.setUser_pwd(pwd);
		paraMap.put("us", gq);
		snbService.updateus(paraMap);
	/*	Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("User");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);*/
		return "success_updatec";
	}
	public String updatePassword() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Map paraMapA=new HashMap();
		Users us=(Users)session.getAttribute("user");
		String pwdA=MD5.MD5(passwordA);
		String pwdB=MD5.MD5(passwordB);
		if(pwdA.equals(us.getUser_pwd())){
			us.setUser_pwd(pwdB);
			paraMapA.put("us", us);
			snbService.updateus(paraMapA);
		}else{
			return "false";
		}
		return "update_password_success";
	}
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		PageList<Users> gqxx=(PageList)session.getAttribute("users");
		Users gq=gqxx.getPlist().get(whe-1);
		gq.setUser_biaozhiwei(0);
		paraMap.put("us", gq);
		snbService.updateus(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(0);
		rz.setRizhi_duix_mingcheng("User");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_delete";
	}
	public String realdelete() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		PageList<Users> gqxx=(PageList)session.getAttribute("user0");
		Users gq=gqxx.getPlist().get(whe-1);
		paraMap.put("us", gq);
		snbService.deleteus(paraMap);
		return "success_updatec";
	}
	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}
	public String getTimu() {
		return timu;
	}
	public void setTimu(String timu) {
		this.timu = timu;
	}
	public String getPasswordA() {
		return passwordA;
	}
	public void setPasswordA(String passwordA) {
		this.passwordA = passwordA;
	}
	public String getPasswordB() {
		return passwordB;
	}
	public void setPasswordB(String passwordB) {
		this.passwordB = passwordB;
	}
	public int getNowpageA() {
		return nowpageA;
	}
	public void setNowpageA(int nowpageA) {
		this.nowpageA = nowpageA;
	}
	
	
}
