package com.snb.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.snb.bean.PageList;
import com.snb.hbm.orm.Cuncuntong_cun;
import com.snb.hbm.orm.Cuncuntong_zhen;
import com.snb.hbm.orm.Jiage_tongbao;
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Zhanxiao_xinxi;
import com.snb.services.impl.SnbService;

public class CCTC extends ActionSupport{
	private SnbService snbService;
	private String timu;
	private String cun_mingcheng;
	private String cun_miaoshu;
	private int fk_zhen_id;
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String id=(String)request.getParameter("zhenid");
		HttpSession session=request.getSession();
		int zhenid=-1;
		if(id!=null&&!id.equals(" ")){
			zhenid=Integer.parseInt(id);
			session.setAttribute("zhenid", zhenid);
		}else{
			zhenid=(Integer)session.getAttribute("zhenid");
		}
		String nowpage=request.getParameter("nowpage");
		PageList<Cuncuntong_cun> pl=new PageList<Cuncuntong_cun>();
		pl.setNowpage(1);
		
		Map paraMap=new HashMap();
		paraMap.put("zhenid", zhenid);
		List c=new ArrayList();
		List<Cuncuntong_cun> cctc=new ArrayList<Cuncuntong_cun>();
		Map mdp=snbService.querycun(paraMap);
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
		
		
		for(int i=0;i<c.size();i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*10<c.size()){
				Cuncuntong_cun cun=new Cuncuntong_cun();
				cun=(Cuncuntong_cun)c.get(i+(pl.getNowpage()-1)*10);
				cctc.add(cun);
				pl.setPlist(cctc);
				}else{
					break;
				}
		}
		request.setAttribute("condition", paraMap);
		session.setAttribute("cctc", pl);//放入session中供前台获取
		return "success_query";

	}
	public String save() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		int zhenid=(Integer)session.getAttribute("zhenid");
		int user_id=(Integer)session.getAttribute("user_id");
		
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		Cuncuntong_cun cun=new Cuncuntong_cun();
		cun.setCun_mingcheng(cun_mingcheng);
		cun.setCun_miaoshu(cun_miaoshu);
		cun.setFk_zhen_id(zhenid);
		cun.setFk_user_id(user_id);
		cun.setCun_biaozhiwei(1);
		cun.setCun_chuanjian_shijian(d);
		cun.setCun_gengxin_shijian(d);
		paraMap.put("cun", cun);
		snbService.savecun(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Cuncuntong_cun");
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
		
		PageList<Cuncuntong_cun> b=(PageList)session.getAttribute("cctc");
		int user_id=(Integer)session.getAttribute("user_id");
		int zhenid=(Integer)session.getAttribute("zhenid");
		int a=(Integer)session.getAttribute("whe");
		Cuncuntong_cun cun=b.getPlist().get(a-1);
		
		cun.setCun_mingcheng(cun_mingcheng);
		cun.setCun_miaoshu(cun_miaoshu);
		cun.setFk_zhen_id(zhenid);
		cun.setCun_gengxin_shijian(d);
		
		paraMap.put("cun", cun);
		snbService.updatecun(paraMap);
		
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Cuncuntong_cun");
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
		PageList<Cuncuntong_cun> b=(PageList)session.getAttribute("cctc");
		Cuncuntong_cun cun=b.getPlist().get(whe-1);
		
		cun.setCun_biaozhiwei(0);
		
		paraMap.put("cun", cun);
		snbService.updatecun(paraMap);
		
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Cuncuntong_cun");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		
		return "success_delete";
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
	public String getCun_mingcheng() {
		return cun_mingcheng;
	}
	public void setCun_mingcheng(String cun_mingcheng) {
		this.cun_mingcheng = cun_mingcheng;
	}
	public String getCun_miaoshu() {
		return cun_miaoshu;
	}
	public void setCun_miaoshu(String cun_miaoshu) {
		this.cun_miaoshu = cun_miaoshu;
	}
	public int getFk_zhen_id() {
		return fk_zhen_id;
	}
	public void setFk_zhen_id(int fk_zhen_id) {
		this.fk_zhen_id = fk_zhen_id;
	}

	
}
