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
import com.snb.hbm.orm.Cuncuntong_zhen;
import com.snb.hbm.orm.Rizhi;
import com.snb.services.impl.SnbService;

public class CCTZ extends ActionSupport{
	private SnbService snbService;
	private String timu;
	private String zhen_mingcheng;
	private String zhen_miaoshu;
	private int nowpageA=0;
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();//通过request获取session对象
		Map paraMap=new HashMap();
		paraMap.put("timu", timu);
		List c=new ArrayList();
		String nowpage=request.getParameter("nowpage");
		PageList<Cuncuntong_zhen> pl=new PageList<Cuncuntong_zhen>();
		pl.setNowpage(1);
		List<Cuncuntong_zhen> cctz=new ArrayList<Cuncuntong_zhen>();
		Map mdp=snbService.queryzhen(paraMap);
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
				Cuncuntong_zhen sq=new Cuncuntong_zhen();
				sq=(Cuncuntong_zhen)c.get(i+(pl.getNowpage()-1)*15);
				cctz.add(sq);
				pl.setPlist(cctz);
				}else{
					break;
				}
			
		}
		request.setAttribute("para", paraMap);
		session.setAttribute("cctz", pl);//放入session中供前台获取
		return "success_query";

	}
	public String save() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();//通过request获取session对象
		int user_id=(Integer)session.getAttribute("user_id");
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		Cuncuntong_zhen zhen=new Cuncuntong_zhen();
		zhen.setZhen_miaoshu(zhen_miaoshu);
		zhen.setZhen_mingcheng(zhen_mingcheng);
		zhen.setFk_user_id(user_id);
		zhen.setZhen_biaozhiwei(1);
		zhen.setZhen_chuanjian_shijian(d);
		zhen.setZhen_gengxin_shijian(d);
		paraMap.put("zhen", zhen);
		snbService.savezhen(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Cuncuntong_zhen");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_save";
	}
	public String update() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		PageList<Cuncuntong_zhen> b=(PageList)session.getAttribute("cctz");
		int user_id=(Integer)session.getAttribute("user_id");
		int a=(Integer)session.getAttribute("whe");
		Cuncuntong_zhen zhen=b.getPlist().get(a-1);
		zhen.setZhen_mingcheng(zhen_mingcheng);
		zhen.setZhen_miaoshu(zhen_miaoshu);
		zhen.setZhen_gengxin_shijian(d);
		paraMap.put("zhen", zhen);
		snbService.updatezhen(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Cuncuntong_zhen");
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
		
//		int user_id=(Integer)session.getAttribute("user_id");
//		String i=request.getParameter("i");
//		int whe=Integer.parseInt(i);
//		PageList<Jiage_tongbao> jgtb=(PageList)session.getAttribute("jgtb");
//		Jiage_tongbao jg=jgtb.getPlist().get(whe-1);
		
		int user_id=(Integer)session.getAttribute("user_id");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		PageList<Cuncuntong_zhen> b=(PageList)session.getAttribute("cctz");
		Cuncuntong_zhen zhen=b.getPlist().get(whe-1);
		
		zhen.setZhen_biaozhiwei(0);
		
		paraMap.put("zhen", zhen);
		snbService.updatezhen(paraMap);
		
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Cuncuntong_zhen");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		
		return "success_delete";
	}
	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}
	public String getZhen_mingcheng() {
		return zhen_mingcheng;
	}
	public void setZhen_mingcheng(String zhen_mingcheng) {
		this.zhen_mingcheng = zhen_mingcheng;
	}
	public String getZhen_miaoshu() {
		return zhen_miaoshu;
	}
	public void setZhen_miaoshu(String zhen_miaoshu) {
		this.zhen_miaoshu = zhen_miaoshu;
	}
	public String getTimu() {
		return timu;
	}
	public void setTimu(String timu) {
		this.timu = timu;
	}
	public int getNowpageA() {
		return nowpageA;
	}
	public void setNowpageA(int nowpageA) {
		this.nowpageA = nowpageA;
	}
	
}
