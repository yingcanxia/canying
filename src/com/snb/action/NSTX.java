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
import com.snb.hbm.orm.Huangye_dianjia;
import com.snb.hbm.orm.Jiage_tongbao;
import com.snb.hbm.orm.Nongshi_tixing;
import com.snb.hbm.orm.Nongzi_caigou;
import com.snb.hbm.orm.Rizhi;
import com.snb.services.impl.SnbService;

public class NSTX extends ActionSupport{
	private SnbService snbService;
	private String timu;
	private String nongshi_tixing_timu;
	private String nongshi_tixing_neirong;
	private int nowpageA=0;
	
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		Map paraMap=new HashMap();
		paraMap.put("timu", timu);
		PageList<Nongshi_tixing> pl=new PageList<Nongshi_tixing>();
		pl.setNowpage(1);
		List c=new ArrayList();
		List<Nongshi_tixing> nstx=new ArrayList<Nongshi_tixing>();
		Map mdp=snbService.queryTx(paraMap);
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
				Nongshi_tixing tx=new Nongshi_tixing();
				tx=(Nongshi_tixing)c.get(i+(pl.getNowpage()-1)*15);
				nstx.add(tx);
				}else{
					break;
				}
		}
		pl.setPlist(nstx);
		request.setAttribute("para", paraMap);
		session.setAttribute("nstx", pl);//放入session中供前台获取
		return "success_query";
	}
	public String save() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();//通过request获取session对象
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		int user_id=(Integer)session.getAttribute("user_id");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		Nongshi_tixing tx=new Nongshi_tixing();
		tx.setNongshi_tixing_timu(nongshi_tixing_timu);
		tx.setNongshi_tixing_neirong(nongshi_tixing_neirong);

		tx.setNongshi_tixing_chuanjian_shijian(d);
		tx.setNongshi_tixing_gengxin_shijian(d);
		tx.setFk_nongshi_tixing_user_id(user_id);
		tx.setNongshi_tixing_biaozhiwei(1);
		paraMap.put("tx", tx);
		snbService.saveTx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongshi_tixing");
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
		int user_id=(Integer)session.getAttribute("user_id");
		PageList<Nongshi_tixing> nstx=(PageList)session.getAttribute("nstx");
		int a=(Integer)session.getAttribute("whe");
		Nongshi_tixing tx=nstx.getPlist().get(a-1);
		tx.setNongshi_tixing_timu(nongshi_tixing_timu);

		tx.setNongshi_tixing_neirong(nongshi_tixing_neirong);
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		tx.setNongshi_tixing_gengxin_shijian(d);
		paraMap.put("tx", tx);
		snbService.updatetx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongshi_tixing");
		
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
		PageList<Nongshi_tixing> nstx=(PageList)session.getAttribute("nstx");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Nongshi_tixing tx=nstx.getPlist().get(whe-1);
		tx.setNongshi_tixing_zhidingwei(1);
		tx.setNongshi_tixing_gengxin_shijian(d);
		paraMap.put("tx", tx);
		snbService.updatetx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongshi_tixing");
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
		PageList<Nongshi_tixing> nstx=(PageList)session.getAttribute("nstx");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Nongshi_tixing tx=nstx.getPlist().get(whe-1);
		tx.setNongshi_tixing_zhidingwei(0);
		tx.setNongshi_tixing_gengxin_shijian(d);
		paraMap.put("tx", tx);
		snbService.updatetx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongshi_tixing");
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
		PageList<Nongshi_tixing> nstx=(PageList)session.getAttribute("nstx");
		Nongshi_tixing tx=nstx.getPlist().get(whe-1);
		tx.setNongshi_tixing_biaozhiwei(0);
		paraMap.put("tx", tx);
		snbService.updatetx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongshi_tixing");
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
	public String getNongshi_tixing_timu() {
		return nongshi_tixing_timu;
	}
	public void setNongshi_tixing_timu(String nongshi_tixing_timu) {
		this.nongshi_tixing_timu = nongshi_tixing_timu;
	}
	public String getNongshi_tixing_neirong() {
		return nongshi_tixing_neirong;
	}
	public void setNongshi_tixing_neirong(String nongshi_tixing_neirong) {
		this.nongshi_tixing_neirong = nongshi_tixing_neirong;
	}
	public int getNowpageA() {
		return nowpageA;
	}
	public void setNowpageA(int nowpageA) {
		this.nowpageA = nowpageA;
	}
	
}
