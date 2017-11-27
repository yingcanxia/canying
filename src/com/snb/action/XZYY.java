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
import com.snb.hbm.orm.Nongmin_suqiu;
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Xingzheng_yuyue;
import com.snb.services.impl.SnbService;

public class XZYY extends ActionSupport{
	private  SnbService snbService;
	private String timu;
	private String name;
	private String tel;
	private int zhuangtai=-1;
	private int shenhe;
	private String xingzheng_yuyue_fankui_neirong;
	private int nowpageA=0;
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		Map paraMapA=new HashMap();
		Map paraMapB=new HashMap();
		Xingzheng_yuyue query=new Xingzheng_yuyue();
		query.setXingzheng_yuyue_timu(timu);
		query.setXingzheng_yuyue_renyuan_xingming(name);
		query.setXingzheng_yuyue_renyuan_dianhua(tel);
		query.setXingzheng_yuyue_zhuangtai(zhuangtai);
		PageList<Xingzheng_yuyue> pl=new PageList<Xingzheng_yuyue>();
		
		String user_dianhua=(String)session.getAttribute("user_dianhua");//获取登录时user的信息
		String user_leixing=(String)session.getAttribute("user_leixing");
		paraMapA.put("user_id", user_dianhua);
		paraMapB.put("query", query);
		List c=new ArrayList();
		List<Xingzheng_yuyue> xzyy=new ArrayList<Xingzheng_yuyue>();
		if(user_leixing.equals("2")||user_leixing.equals("3")){//类型为超级用户的时候进与service的函数+1
			
			Map mdp=snbService.queryYy1(paraMapB);
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
			}else if(nowpageA>0){
				pl.setNowpage(nowpageA);
			}
			for(int i=0;i<15;i++){
				if(i+(pl.getNowpage()-1)*15<c.size()){
				Xingzheng_yuyue sq=new Xingzheng_yuyue();
				sq=(Xingzheng_yuyue)c.get(i+(pl.getNowpage()-1)*15);
				xzyy.add(sq);
				pl.setPlist(xzyy);
				}
				else{
					break;
				}
			}
		}
		else{//类型为普通用户的时候进与service的函数+0
			Map mdp=snbService.queryYy0(paraMapA,paraMapB);
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
			}else if(nowpageA>0){
				pl.setNowpage(nowpageA);
			}
			for(int i=0;i<15;i++){
				if(i+(pl.getNowpage()-1)*15<c.size()){
					Xingzheng_yuyue sq=new Xingzheng_yuyue();
					sq=(Xingzheng_yuyue)c.get(i+(pl.getNowpage()-1)*15);
					xzyy.add(sq);
					pl.setPlist(xzyy);
					}else{
						break;
					}
		}
		}
		request.setAttribute("paraA", paraMapA);
		request.setAttribute("paraB", paraMapB);
		session.setAttribute("xzyy", pl);//放入session中供前台获取
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
		int a=(Integer)session.getAttribute("whe");
		PageList<Xingzheng_yuyue> b=(PageList)session.getAttribute("xzyy");
		Xingzheng_yuyue yy=b.getPlist().get(a-1);
		yy.setXingzheng_yuyue_zhuangtai(shenhe);
		yy.setXingzheng_yuyue_fankui_neirong(xingzheng_yuyue_fankui_neirong);
		yy.setXingzheng_yuyue_gengxin_shijian(d);
		paraMap.put("yy", yy);
		snbService.updateyy(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Xingzheng_yuyue");
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
		PageList<Xingzheng_yuyue> b=(PageList)session.getAttribute("xzyy");
		Xingzheng_yuyue yy=b.getPlist().get(whe-1);
		yy.setXingzheng_yuyue_biaozhiwei(0);
		paraMap.put("yy", yy);
		snbService.updateyy(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Xingzheng_yuyue");
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
	public int getShenhe() {
		return shenhe;
	}
	public void setShenhe(int shenhe) {
		this.shenhe = shenhe;
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
	public int getZhuangtai() {
		return zhuangtai;
	}
	public void setZhuangtai(int zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	public String getXingzheng_yuyue_fankui_neirong() {
		return xingzheng_yuyue_fankui_neirong;
	}
	public void setXingzheng_yuyue_fankui_neirong(String xingzheng_yuyue_fankui_neirong) {
		this.xingzheng_yuyue_fankui_neirong = xingzheng_yuyue_fankui_neirong;
	}
	public int getNowpageA() {
		return nowpageA;
	}
	public void setNowpageA(int nowpageA) {
		this.nowpageA = nowpageA;
	}
	
}
