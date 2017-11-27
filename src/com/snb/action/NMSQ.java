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
import com.snb.hbm.orm.Fabu_nongmin_suqiu;
import com.snb.hbm.orm.Huifu_nongmin_suqiu;
import com.snb.hbm.orm.Nongmin_suqiu;
import com.snb.hbm.orm.Rizhi;
import com.snb.services.impl.SnbService;

public class NMSQ extends ActionSupport{
	private  SnbService snbService;
	private String timu;
	private String name;
	private String tel;
	private int state=-1; 
	private String nongmin_suqiu_neirong;//诉求内容
	private String nongmin_suqiu_tupian_lianjie;//诉求图片链接
	private String nongmin_suqiu_renyuan_xingming;//发出诉求人的姓名（冗余处理）
	private String nongmin_suqiu_renyuan_dianhua;//发出诉求人的电话（冗余处理）
	private int fk_nongmin_suqiu_huifu_jiegou_id;//回复id
	private String huifu_nongmin_suqiu_timu;
	private String huifu_suqiu_neirong;
	private String huifu_nongmin_suqiu_neirong;
	private int fk_neirong_suqiu_yunashi_id;
	private int suqiu_user_id;
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();
		Map paraMapA=new HashMap();
		Map paraMapB=new HashMap();
		Map paraMapC=new HashMap();
		String user_dianhua=(String)session.getAttribute("user_dianhua");
		PageList<Object[]> pl=new PageList<Object[]>();
		Nongmin_suqiu quer=new Nongmin_suqiu();
		quer.setNongmin_suqiu_timu(timu);
		quer.setState(state);
		quer.setNongmin_suqiu_renyuan_xingming(name);
		quer.setNongmin_suqiu_renyuan_dianhua(tel);
		
		
		String user_leixing=(String)session.getAttribute("user_leixing");
		paraMapA.put("user_id", user_dianhua);
		paraMapB.put("quer", quer);
		List c=new ArrayList();
		List<Object[]> b=new ArrayList<Object[]>();
		
		if(user_leixing.equals("2")||user_leixing.equals("3")){
			
			Map mdp=snbService.query1(paraMapB);
			c=(List)mdp.get("list");
			pl.setNumber(c.size());
			pl.setSumPage((c.size()-1)/15+1);
			pl.setNowpage(1);
			if(nowpage!=null&&!nowpage.equals(" ")){
				int now=Integer.parseInt(nowpage);
				if(now<1){
					now=1;
				}else if(now>pl.getSumPage()){
					now=pl.getSumPage();
				}
				pl.setNowpage(now);
			}
			for(int i=0;i<15;i++){
				if(i+(pl.getNowpage()-1)*15<c.size()){
				
					Object[] sq=new Object[13];
					sq=(Object[])c.get(i+(pl.getNowpage()-1)*15);
					b.add(sq);
			
				}else{
					break;
				}
			}
		}
		else{
			Map mdp=snbService.query0(paraMapA,paraMapB);
			c=(List)mdp.get("list");
			pl.setNumber(c.size());
			pl.setSumPage((c.size()+15-1)/15);
			pl.setNumber(c.size());
			pl.setSumPage((c.size()-1)/15+1);
			pl.setNowpage(1);
			if(nowpage!=null&&!nowpage.equals(" ")){
				int now=Integer.parseInt(nowpage);
				if(now<1){
					now=1;
				}else if(now>pl.getSumPage()){
					now=pl.getSumPage();
				}
				pl.setNowpage(now);
			}
			for(int i=0;i<10;i++){
				if(i+(pl.getNowpage()-1)*15<c.size()){
//					Nongmin_suqiu sq=new Nongmin_suqiu();
//					sq=(Nongmin_suqiu)c.get(i+(pl.getNowpage()-1)*10);
//					b.add(sq);
					Object[] sq=new Object[13];
					sq=(Object[])c.get(i+(pl.getNowpage()-1)*15);
					b.add(sq);
					}else{
						break;
					}
			}
		}
		pl.setPlist(b);

		request.setAttribute("paraA", paraMapA);
		request.setAttribute("paraB", paraMapB);
		request.setAttribute("paraC", paraMapC);
		session.setAttribute("list", pl);
		return "success_query";
	}
	public String update() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		Map paraMapA=new HashMap();
		Map paraMapB=new HashMap();
		Map paraMapC=new HashMap();
		Map riz=new HashMap();
		
		PageList<Object[]> b=(PageList)session.getAttribute("list");
		int a=(Integer)session.getAttribute("whe");
		Timestamp d = new Timestamp(System.currentTimeMillis());
		Object[] obj = b.getPlist().get(a-1);
		Nongmin_suqiu sq= new Nongmin_suqiu();
		//change by Li Zhiying
		sq.setNongmin_suqiu_id((Integer)obj[0]);
		sq.setNongmin_suqiu_timu((String)obj[1]);
		sq.setNongmin_suqiu_neirong((String)obj[2]);
		sq.setNongmin_suqiu_tupian_lianjie((String)obj[3]);
		sq.setFk_nongmin_suqiu_user_id((Integer)obj[4]);
		sq.setNongmin_suqiu_renyuan_xingming((String)obj[5]);
		sq.setNongmin_suqiu_renyuan_dianhua((String)obj[6]);
		
		sq.setState(1);
		
		sq.setNongmin_suqiu_biaozhiwei((Integer)obj[8]);
		sq.setNongmin_suqiu_gengxin_shijian((Timestamp)obj[9]);
		sq.setNongmin_suqiu_zhidingwei((Integer)obj[10]);
		sq.setState_tz((Integer)obj[11]);
		//change by Li Zhiying
		paraMapC.put("nongmin_suqiu_id", sq.getNongmin_suqiu_id());
		Map mdp=snbService.queryhf(paraMapC);
		List c=new ArrayList();
		c=(List)mdp.get("list");
		Huifu_nongmin_suqiu hf=new Huifu_nongmin_suqiu();
		if(c.size()!=0){
		hf=(Huifu_nongmin_suqiu)c.get(0);
		hf.setHuifu_nongmin_suqiu_timu(huifu_nongmin_suqiu_timu);
		hf.setHuifu_nongmin_suqiu_neirong(huifu_suqiu_neirong);
		hf.setFk_neirong_suqiu_yunashi_id(fk_neirong_suqiu_yunashi_id);
		//hf.setHuifu_nongmin_suqiu_chuanjian_shijian(d);
		hf.setHuifu_nongmin_suqiu_gengxin_shijian(d);
		hf.setFk_huifu_nongmin_suqiu_user_id(user_id);
		}else {
			hf.setHuifu_nongmin_suqiu_timu(huifu_nongmin_suqiu_timu);
			hf.setHuifu_nongmin_suqiu_neirong(huifu_suqiu_neirong);
			hf.setFk_neirong_suqiu_yunashi_id(fk_neirong_suqiu_yunashi_id);
			hf.setHuifu_nongmin_suqiu_chuanjian_shijian(d);
			hf.setHuifu_nongmin_suqiu_gengxin_shijian(d);
			hf.setFk_huifu_nongmin_suqiu_user_id(user_id);
		}
		sq.setNongmin_suqiu_gengxin_shijian(d);
		paraMapB.put("sq", sq);
		snbService.updatesq(paraMapB);
		paraMapA.put("hf", hf);
		snbService.saveHuifu(paraMapA);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Huifu_nongmin_suqiu");
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
		Map paraMapB=new HashMap();
		Map riz=new HashMap();
		PageList<Object[]> b=(PageList)session.getAttribute("list");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Nongmin_suqiu sq=new Nongmin_suqiu();//
		Object[] a=b.getPlist().get(whe-1);
		sq.setNongmin_suqiu_id((Integer)a[0]);
		sq.setNongmin_suqiu_timu((String)a[1]);
		sq.setNongmin_suqiu_neirong((String)a[2]);
		sq.setNongmin_suqiu_tupian_lianjie((String)a[3]);
		sq.setFk_nongmin_suqiu_user_id((Integer)a[4]);
		sq.setNongmin_suqiu_renyuan_xingming((String)a[5]);
		sq.setNongmin_suqiu_renyuan_dianhua((String)a[6]);
		sq.setState((Integer)a[7]);
		sq.setNongmin_suqiu_biaozhiwei((Integer)a[8]);
		sq.setNongmin_suqiu_gengxin_shijian((Timestamp)a[9]);
		sq.setNongmin_suqiu_zhidingwei(1);
		sq.setState_tz((Integer)a[11]);
		sq.setNongmin_suqiu_gengxin_shijian(d);
		paraMapB.put("sq", sq);
		snbService.updatesq(paraMapB);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongmin_suqiu");
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
		Map paraMapB=new HashMap();
		Map riz=new HashMap();
		PageList<Object[]> b=(PageList)session.getAttribute("list");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Nongmin_suqiu sq=new Nongmin_suqiu();//
		Object[] a=b.getPlist().get(whe-1);
		sq.setNongmin_suqiu_id((Integer)a[0]);
		sq.setNongmin_suqiu_timu((String)a[1]);
		sq.setNongmin_suqiu_neirong((String)a[2]);
		sq.setNongmin_suqiu_tupian_lianjie((String)a[3]);
		sq.setFk_nongmin_suqiu_user_id((Integer)a[4]);
		sq.setNongmin_suqiu_renyuan_xingming((String)a[5]);
		sq.setNongmin_suqiu_renyuan_dianhua((String)a[6]);
		sq.setState((Integer)a[7]);
		sq.setNongmin_suqiu_biaozhiwei((Integer)a[8]);
		sq.setNongmin_suqiu_gengxin_shijian((Timestamp)a[9]);
		sq.setNongmin_suqiu_zhidingwei(0);
		sq.setState_tz((Integer)a[11]);
		sq.setNongmin_suqiu_gengxin_shijian(d);
		paraMapB.put("sq", sq);
		snbService.updatesq(paraMapB);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongmin_suqiu");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_update";
		}
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
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
		PageList<Object[]> b=(PageList)session.getAttribute("list");
		Nongmin_suqiu sq=new Nongmin_suqiu();//
		Object[] a=b.getPlist().get(whe-1);
		sq.setNongmin_suqiu_biaozhiwei(0);
		//Object[] a=b.getPlist().get(whe-1);
		sq.setNongmin_suqiu_id((Integer)a[0]);
		sq.setNongmin_suqiu_timu((String)a[1]);
		sq.setNongmin_suqiu_neirong((String)a[2]);
		sq.setNongmin_suqiu_tupian_lianjie((String)a[3]);
		sq.setFk_nongmin_suqiu_user_id((Integer)a[4]);
		sq.setNongmin_suqiu_renyuan_xingming((String)a[5]);
		sq.setNongmin_suqiu_renyuan_dianhua((String)a[6]);
		sq.setState((Integer)a[7]);
		sq.setNongmin_suqiu_biaozhiwei(0);
		sq.setNongmin_suqiu_gengxin_shijian((Timestamp)a[9]);
		sq.setNongmin_suqiu_zhidingwei((Integer)a[10]);
		sq.setState_tz((Integer)a[11]);
		paraMap.put("sq", sq);
		snbService.updatesq(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongmin_suqiu");
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
	public String getNongmin_suqiu_neirong() {
		return nongmin_suqiu_neirong;
	}
	public void setNongmin_suqiu_neirong(String nongmin_suqiu_neirong) {
		this.nongmin_suqiu_neirong = nongmin_suqiu_neirong;
	}
	public String getNongmin_suqiu_tupian_lianjie() {
		return nongmin_suqiu_tupian_lianjie;
	}
	public void setNongmin_suqiu_tupian_lianjie(String nongmin_suqiu_tupian_lianjie) {
		this.nongmin_suqiu_tupian_lianjie = nongmin_suqiu_tupian_lianjie;
	}
	public String getNongmin_suqiu_renyuan_xingming() {
		return nongmin_suqiu_renyuan_xingming;
	}
	public void setNongmin_suqiu_renyuan_xingming(String nongmin_suqiu_renyuan_xingming) {
		this.nongmin_suqiu_renyuan_xingming = nongmin_suqiu_renyuan_xingming;
	}
	public String getNongmin_suqiu_renyuan_dianhua() {
		return nongmin_suqiu_renyuan_dianhua;
	}
	public void setNongmin_suqiu_renyuan_dianhua(String nongmin_suqiu_renyuan_dianhua) {
		this.nongmin_suqiu_renyuan_dianhua = nongmin_suqiu_renyuan_dianhua;
	}
	public int getFk_nongmin_suqiu_huifu_jiegou_id() {
		return fk_nongmin_suqiu_huifu_jiegou_id;
	}
	public void setFk_nongmin_suqiu_huifu_jiegou_id(int fk_nongmin_suqiu_huifu_jiegou_id) {
		this.fk_nongmin_suqiu_huifu_jiegou_id = fk_nongmin_suqiu_huifu_jiegou_id;
	}
	public String getHuifu_nongmin_suqiu_timu() {
		return huifu_nongmin_suqiu_timu;
	}
	public void setHuifu_nongmin_suqiu_timu(String huifu_nongmin_suqiu_timu) {
		this.huifu_nongmin_suqiu_timu = huifu_nongmin_suqiu_timu;
	}
	public String getHuifu_suqiu_neirong() {
		return huifu_suqiu_neirong;
	}
	public void setHuifu_suqiu_neirong(String huifu_suqiu_neirong) {
		this.huifu_suqiu_neirong = huifu_suqiu_neirong;
	}
	public String getHuifu_nongmin_suqiu_neirong() {
		return huifu_nongmin_suqiu_neirong;
	}
	public void setHuifu_nongmin_suqiu_neirong(String huifu_nongmin_suqiu_neirong) {
		this.huifu_nongmin_suqiu_neirong = huifu_nongmin_suqiu_neirong;
	}
	public int getFk_neirong_suqiu_yunashi_id() {
		return fk_neirong_suqiu_yunashi_id;
	}
	public void setFk_neirong_suqiu_yunashi_id(int fk_neirong_suqiu_yunashi_id) {
		this.fk_neirong_suqiu_yunashi_id = fk_neirong_suqiu_yunashi_id;
	}
	public int getSuqiu_user_id() {
		return suqiu_user_id;
	}
	public void setSuqiu_user_id(int suqiu_user_id) {
		this.suqiu_user_id = suqiu_user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
