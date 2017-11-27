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
import com.snb.hbm.orm.Nongshi_tixing;
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Zhanxiao_xinxi;
import com.snb.hbm.orm.Zhengce_fagui;
import com.snb.services.impl.SnbService;

public class ZCFG extends ActionSupport{
	private  SnbService snbService;
	private String guanjianzi;
	private String zhengce_neirong;//政策内容
	private String zhengce_timu;
	private int xinxing;
	private String zhengce_guanjiazi;
	private int zhengce_type;
	private int nowpageA;
	public String query() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		//Map paraMapA=new HashMap();
		Map paraMap=new HashMap();
		PageList<Zhengce_fagui> pl=new PageList<Zhengce_fagui>();
		pl.setNowpage(1);
		paraMap.put("guanjianzi", guanjianzi);
		paraMap.put("xinxing", xinxing);
		List c=new ArrayList();
		List<Zhengce_fagui> zcfg=new ArrayList<Zhengce_fagui>();
		Map mdp=snbService.queryZc(paraMap);
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
				Zhengce_fagui zc=new Zhengce_fagui();
				zc=(Zhengce_fagui)c.get(i+(pl.getNowpage()-1)*15);
				zcfg.add(zc);
				}else{
					break;
				}
		}
		pl.setPlist(zcfg);
		request.setAttribute("para", paraMap);
		session.setAttribute("zcfg", pl);//放入session中供前台获取
		return "success_query";
	}
	public String save() throws Exception {
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		Zhengce_fagui zc=new Zhengce_fagui();
		zc.setZhengce_timu(zhengce_timu);
		zc.setZhengce_neirong(zhengce_neirong);
		zc.setZhengce_guanjiazi(zhengce_guanjiazi);
		zc.setZhengce_biaozhiwei(1);
		zc.setZhengce_type(zhengce_type);
		zc.setFk_zhengce_user_id(user_id);
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		zc.setZhengce_shangchuan_time(d);
		zc.setZhengce_gengxin_shijian(d);
		paraMap.put("zc", zc);
		snbService.saveZc(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Zhengce_fagui");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_save";
	}
	public String update() throws Exception {
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		int user_id=(Integer)session.getAttribute("user_id");
		PageList<Zhengce_fagui> zcfg=(PageList)session.getAttribute("zcfg");
		int a=(Integer)session.getAttribute("whe");
		Zhengce_fagui zc=zcfg.getPlist().get(a-1);
		zc.setZhengce_timu(zhengce_timu);
		zc.setZhengce_neirong(zhengce_neirong);
		zc.setZhengce_guanjiazi(zhengce_guanjiazi);
		zc.setZhengce_type(zhengce_type);
		zc.setZhengce_gengxin_shijian(d);
		paraMap.put("zc", zc);
		snbService.updatezc(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Zhengce_fagui");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_update";
	}
	public String zhiding() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		Timestamp d = new Timestamp(System.currentTimeMillis());
		//Map paraMapA=new HashMap();
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		PageList<Zhengce_fagui> zcfg=(PageList)session.getAttribute("zcfg");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Zhengce_fagui zc=zcfg.getPlist().get(whe-1);
		
		zc.setZhengce_zhidingwei(1);
		zc.setZhengce_gengxin_shijian(d);
		paraMap.put("zc", zc);
		snbService.updatezc(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Zhengce_fagui");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_update";
		}
	public String cancelzhiding() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		Timestamp d = new Timestamp(System.currentTimeMillis());
		//Map paraMapA=new HashMap();
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		PageList<Zhengce_fagui> zcfg=(PageList)session.getAttribute("zcfg");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Zhengce_fagui zc=zcfg.getPlist().get(whe-1);
		
		zc.setZhengce_zhidingwei(0);
		zc.setZhengce_gengxin_shijian(d);
		paraMap.put("zc", zc);
		snbService.updatezc(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(0);
		rz.setRizhi_duix_mingcheng("Zhengce_fagui");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_update";
		}
	public String delete() throws Exception {
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		PageList<Zhengce_fagui> zcfg=(PageList)session.getAttribute("zcfg");
		Zhengce_fagui zc=zcfg.getPlist().get(whe-1);
		zc.setZhengce_biaozhiwei(0);
		paraMap.put("zc", zc);
		snbService.updatezc(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Zhengce_fagui");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_delete";
	}
	
	public String addNew() throws Exception {
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		PageList<Zhengce_fagui> zcfg=(PageList)session.getAttribute("zcfg");
		Zhengce_fagui zc=zcfg.getPlist().get(whe-1);
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		if(zc.getZhengce_biaozhiwei()==2){//已是新型，取消
			zc.setZhengce_biaozhiwei(1);
		}
		else{//不是新型，设置新型
			zc.setZhengce_biaozhiwei(2);
		}
		zc.setZhengce_gengxin_shijian(d );
		paraMap.put("zc", zc);
		snbService.updatezc(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Zhengce_fagui");
		
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_delete";
	}
	
	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}
	public String getGuanjianzi() {
		return guanjianzi;
	}
	public void setGuanjianzi(String guanjianzi) {
		this.guanjianzi = guanjianzi;
	}
	public String getZhengce_neirong() {
		return zhengce_neirong;
	}
	public void setZhengce_neirong(String zhengce_neirong) {
		this.zhengce_neirong = zhengce_neirong;
	}
	public String getZhengce_timu() {
		return zhengce_timu;
	}
	public void setZhengce_timu(String zhengce_timu) {
		this.zhengce_timu = zhengce_timu;
	}
	public String getZhengce_guanjiazi() {
		return zhengce_guanjiazi;
	}
	public void setZhengce_guanjiazi(String zhengce_guanjiazi) {
		this.zhengce_guanjiazi = zhengce_guanjiazi;
	}
	public int getZhengce_type() {
		return zhengce_type;
	}
	public void setZhengce_type(int zhengce_type) {
		this.zhengce_type = zhengce_type;
	}
	public int getXinxing() {
		return xinxing;
	}
	public void setXinxing(int xinxing) {
		this.xinxing = xinxing;
	}
	public int getNowpageA() {
		return nowpageA;
	}
	public void setNowpageA(int nowpageA) {
		this.nowpageA = nowpageA;
	}
	
}
