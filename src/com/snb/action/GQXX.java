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
import com.snb.bean.PageList;
import com.snb.hbm.orm.Gongqiu_xinxi;
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Zhanxiao_xinxi;
import com.snb.services.impl.SnbService;

public class GQXX extends ActionSupport{
	private  SnbService snbService;
	private String shangpin;
	private int shenhe;
	private int gongqiu_xinxi_type;
	private int nowpageA=0;
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		String user_leixing=(String)session.getAttribute("user_leixing");
		String user_dianhua=(String)session.getAttribute("user_dianhua");
		Map paraMap=new HashMap();
		Map paraMapB=new HashMap();
		PageList<Gongqiu_xinxi> pl=new PageList<Gongqiu_xinxi>();
		pl.setNowpage(1);
		paraMap.put("shangpin", shangpin);
		paraMapB.put("user_dianhua", user_dianhua);
		List c=new ArrayList();
		List<Gongqiu_xinxi> gqxx=new ArrayList<Gongqiu_xinxi>();
		if(user_leixing.equals("2")||user_leixing.equals("3")){
		Map mdp=snbService.queryGq1(paraMap);
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
		}else if(nowpageA>0){
			pl.setNowpage(nowpageA);
		}
		
		for(int i=0;i<15;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*15<c.size()){
				Gongqiu_xinxi zx=new Gongqiu_xinxi();
				zx=(Gongqiu_xinxi)c.get(i+(pl.getNowpage()-1)*15);
				gqxx.add(zx);
				}else{
					break;
				}
		}
		}
		else if(user_leixing.equals("0")){
			Map mdp=snbService.queryGq0(paraMap,paraMapB);
			c=(List)mdp.get("list");
			pl.setNumber(c.size());
			pl.setSumPage((c.size()+15-1)/15);
			for(int i=0;i<15;i++){//将获取道德list重新压入有范型的list里
				if(i+(pl.getNowpage()-1)*15<c.size()){
					Gongqiu_xinxi zx=new Gongqiu_xinxi();
					zx=(Gongqiu_xinxi)c.get(i+(pl.getNowpage()-1)*15);
					gqxx.add(zx);
					}else{
						break;
					}
		}
		}
		pl.setPlist(gqxx);
		request.setAttribute("para", paraMap);
		session.setAttribute("gqxx", pl);//放入session中供前台获取
		return "success_query";
	}
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	public String update() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		int user_id=(Integer)session.getAttribute("user_id");
		PageList<Gongqiu_xinxi> gqxx=(PageList)session.getAttribute("gqxx");
		int a=(Integer)session.getAttribute("whe");
		Gongqiu_xinxi gq=gqxx.getPlist().get(a-1);
		gq.setGongqiu_xinxi_type(gongqiu_xinxi_type);
		gq.setGongqiu_xinxi_shenhe_zhuangtai(shenhe);
		gq.setGongqiu_xinxi_gengxin_shijian(d);
		paraMap.put("gq", gq);
		snbService.updategq(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Gongqiu_xinxi");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_update";
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
		PageList<Gongqiu_xinxi> gqxx=(PageList)session.getAttribute("gqxx");
		Gongqiu_xinxi gq=gqxx.getPlist().get(whe-1);
		gq.setGongqiu_xinxi_biaozhiwei(0);
		paraMap.put("gq", gq);
		snbService.updategq(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Gongqiu_xinxi");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_delete";
	}

	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}
	public String getShangpin() {
		return shangpin;
	}
	public void setShangpin(String shangpin) {
		this.shangpin = shangpin;
	}
	public int getShenhe() {
		return shenhe;
	}
	public void setShenhe(int shenhe) {
		this.shenhe = shenhe;
	}
	public int getGongqiu_xinxi_type() {
		return gongqiu_xinxi_type;
	}
	public void setGongqiu_xinxi_type(int gongqiu_xinxi_type) {
		this.gongqiu_xinxi_type = gongqiu_xinxi_type;
	}
	public int getNowpageA() {
		return nowpageA;
	}
	public void setNowpageA(int nowpageA) {
		this.nowpageA = nowpageA;
	}
	
}
