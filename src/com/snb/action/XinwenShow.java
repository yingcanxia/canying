package com.snb.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.snb.bean.FujianModel;
import com.snb.bean.PageList;
import com.snb.hbm.orm.Nongshi_tixing;
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Xinwen;
import com.snb.services.impl.SnbService;

public class XinwenShow extends ActionSupport{
	private SnbService snbService;
	private String timu;
	private int xinxing;
	private String xinwen_title;
	private String xinwen_neirong;
	private FujianModel fj=new FujianModel();
	private int nowpageA=0;
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		Map paraMap=new HashMap();
		paraMap.put("timu", timu);
		paraMap.put("xinxing", xinxing);
		PageList<Xinwen> pl=new PageList<Xinwen>();
		pl.setNowpage(1);
		List c=new ArrayList();
		List<Xinwen> xinwen=new ArrayList<Xinwen>();
		Map mdp=snbService.queryXw(paraMap);
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
				Xinwen tx=new Xinwen();
				tx=(Xinwen)c.get(i+(pl.getNowpage()-1)*15);
				xinwen.add(tx);
				}else{
					break;
				}
		}
		pl.setPlist(xinwen);
		request.setAttribute("para", paraMap);
		session.setAttribute("xinwen", pl);//放入session中供前台获取
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
		Xinwen xw=new Xinwen();
		if(fj.getUploadFileName()!=null&&!fj.getUploadFileName().equals("")){
			//上传部件开始
			System.out.println(fj.getUploadFileName());
			fj.getUpload().getName();
			//获取web根路径
			String webroot=ServletActionContext.getServletContext().getRealPath("/");
			//拼装保存路径
			String newPath=webroot+File.separator+"upload";
			System.out.println(newPath);
			String name=fj.getUploadFileName().substring(fj.getUploadFileName().lastIndexOf("."));
			String newFileName=UUID.randomUUID().toString().replace("-", "")+name;
			File newFile=new File(newPath,newFileName); 
			String imgroot="Upload/"+newFileName;
			System.out.println(imgroot);
			fj.getUpload().renameTo(newFile);
			//上传部件结束
			xw.setXinwen_tupian(imgroot);
			}
		
	
		xw.setXinwen_timu(xinwen_title);
		xw.setXinwen_neirong(xinwen_neirong);
		xw.setFk_shangchuang_userid(user_id);
		xw.setXinwen_shangchuang_riqi(d);
		xw.setXinwen_gengxin_riqi(d);
		xw.setXinwen_biaozhiwei(1);
		paraMap.put("xw", xw);
		snbService.saveXw(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Xinwen");
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
		PageList<Xinwen> xinwen=(PageList)session.getAttribute("xinwen");
		int a=(Integer)session.getAttribute("whe");
		Xinwen xw=xinwen.getPlist().get(a-1);
		if(fj.getUploadFileName()!=null&&!fj.getUploadFileName().equals("")){
			//上传部件开始
			System.out.println(fj.getUploadFileName());
			fj.getUpload().getName();
			//获取web根路径
			String webroot=ServletActionContext.getServletContext().getRealPath("/");
			//拼装保存路径
			String newPath=webroot+File.separator+"upload";
			System.out.println(newPath);
			String name=fj.getUploadFileName().substring(fj.getUploadFileName().lastIndexOf("."));
			String newFileName=UUID.randomUUID().toString().replace("-", "")+name;
			File newFile=new File(newPath,newFileName); 
			String imgroot="Upload/"+newFileName;
			System.out.println(imgroot);
			fj.getUpload().renameTo(newFile);
			//上传部件结束
			xw.setXinwen_tupian(imgroot);
			}
		
		xw.setXinwen_timu(xinwen_title);
		xw.setXinwen_neirong(xinwen_neirong);
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		xw.setXinwen_gengxin_riqi(d);
		paraMap.put("xw", xw);
		snbService.updatexw(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Xinwen");
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
		PageList<Xinwen> xinwen=(PageList)session.getAttribute("xinwen");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Xinwen xw=xinwen.getPlist().get(whe-1);
		xw.setXinwen_zhidingwei(1);
		xw.setXinwen_gengxin_riqi(d);
		paraMap.put("xw", xw);
		snbService.updatexw(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Xinwen");
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
		PageList<Xinwen> xinwen=(PageList)session.getAttribute("xinwen");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Xinwen xw=xinwen.getPlist().get(whe-1);
		xw.setXinwen_zhidingwei(0);
		xw.setXinwen_gengxin_riqi(d);
		paraMap.put("xw", xw);
		snbService.updatexw(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Xinwen");
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
		PageList<Xinwen> xinwen=(PageList)session.getAttribute("xinwen");
		Xinwen xw=xinwen.getPlist().get(whe-1);
		xw.setXinwen_biaozhiwei(0);
		paraMap.put("xw", xw);
		snbService.updatexw(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Xinwen");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_delete";
	}
	
	public String addNew() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		PageList<Xinwen> xinwen=(PageList)session.getAttribute("xinwen");
		Xinwen xw=xinwen.getPlist().get(whe-1);
		if(xw.getXinwen_biaozhiwei()==2){//已是新型，取消
			xw.setXinwen_biaozhiwei(1);
		}
		else{//不是新型，设置新型
			xw.setXinwen_biaozhiwei(2);
		}
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		xw.setXinwen_gengxin_riqi(d);
		paraMap.put("xw", xw);
		snbService.updatexw(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Xinwen");
		
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
	public String getXinwen_title() {
		return xinwen_title;
	}
	public void setXinwen_title(String xinwen_title) {
		this.xinwen_title = xinwen_title;
	}
	public String getXinwen_neirong() {
		return xinwen_neirong;
	}
	public void setXinwen_neirong(String xinwen_neirong) {
		this.xinwen_neirong = xinwen_neirong;
	}
	public FujianModel getFj() {
		return fj;
	}
	public void setFj(FujianModel fj) {
		this.fj = fj;
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
