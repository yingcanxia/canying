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
import com.snb.hbm.orm.Nongmin_suqiu;
import com.snb.hbm.orm.Nongshi_tixing;
import com.snb.hbm.orm.Nongyejishu;
import com.snb.hbm.orm.Nongzi_caigou;
import com.snb.hbm.orm.Rizhi;
import com.snb.services.impl.SnbService;

public class NYJS extends ActionSupport{
	private SnbService snbService;
	private int timu=-1;
	private String js_timu;
	private String dizhi;
	private String miao;
	private int type;
	private int xinxing;
	private String wenzhang_timu;
	private String wenzhang_neirong;
	private int nowpageA;
//	private FujianModel fj=new FujianModel();
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		Map paraMap=new HashMap();
		paraMap.put("timu", timu);
		paraMap.put("xinxing", xinxing);
		PageList<Nongyejishu> pl=new PageList<Nongyejishu>();
		pl.setNowpage(1);
		List c=new ArrayList();
		List<Nongyejishu> nyjs=new ArrayList<Nongyejishu>();
		Map mdp=snbService.queryjs(paraMap);
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
				Nongyejishu js=new Nongyejishu();
				js=(Nongyejishu)c.get(i+(pl.getNowpage()-1)*15);
				nyjs.add(js);
				}else{
					break;
				}
		}
		pl.setPlist(nyjs);
		request.setAttribute("para", paraMap);
		session.setAttribute("nyjs", pl);//放入session中供前台获取
		return "success_query";
	}
	public String save() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();//通过request获取session对象
		int user_id=(Integer)session.getAttribute("user_id");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		Nongyejishu js=new Nongyejishu();
		js.setJishu_type(type);
		if(type==0){//视频0
			
			js.setShipin_timu(js_timu);
			js.setShipin_miao(miao);
//			if(fj.getUploadFileName()!=null&&!fj.getUploadFileName().equals("")){
//				//上传部件开始
//				System.out.println(fj.getUploadFileName());
//				fj.getUpload().getName();
//				//获取web根路径
//				String webroot=ServletActionContext.getServletContext().getRealPath("/");
//				//拼装保存路径
//				String newPath=webroot+File.separator+"upload";
//				System.out.println(newPath);
//				String name=fj.getUploadFileName().substring(fj.getUploadFileName().lastIndexOf("."));
//				String newFileName=UUID.randomUUID().toString().replace("-", "")+name;
//				File newFile=new File(newPath,newFileName); 
//				String imgroot="http://123.56.199.85:8080/sannongbao2/Upload/"+newFileName;
//				System.out.println(imgroot);
//				fj.getUpload().renameTo(newFile);
//				//上传部件结束
//				js.setShipin_dizhi(imgroot);
//				}
			js.setShipin_dizhi(dizhi);
		}else if(type == 2){//音频1
			js.setYinpin_timu(js_timu);
			js.setYinpin_miao(miao);
			js.setYinpin_dizhi(dizhi);
		}
		js.setFk_user_id(user_id);
		js.setChuangjian_time(d);
		js.setGengxin_time(d);
		js.setJishu_biaozhiwei(1);
		
		paraMap.put("js", js);
		snbService.saveJs(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Cuncuntong_cun");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		
		snbService.saveRz(riz);
		return "success_save";
	}
	public String save2() throws Exception {//文章保存
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();//通过request获取session对象
		int user_id=(Integer)session.getAttribute("user_id");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		Nongyejishu js=new Nongyejishu();
		js.setWenzhang_timu(wenzhang_timu);
		js.setWenzhang_neirong(wenzhang_neirong);
		js.setJishu_type(1);//文章2
		js.setFk_user_id(user_id);
		js.setChuangjian_time(d);
		js.setJishu_biaozhiwei(1);
		js.setGengxin_time(d);
		paraMap.put("js", js);
		snbService.saveJs(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongyejishu");
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
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		PageList<Nongyejishu> nyjs=(PageList)session.getAttribute("nyjs");
		int a=(Integer)session.getAttribute("whe");
		Nongyejishu js=nyjs.getPlist().get(a-1);
		js.setJishu_type(type);
		
		if(type==0){//视频0
			
			js.setShipin_timu(js_timu);
			js.setShipin_miao(miao);
//			if(fj.getUploadFileName()!=null&&!fj.getUploadFileName().equals("")){
//				//上传部件开始
//				System.out.println(fj.getUploadFileName());
//				fj.getUpload().getName();
//				//获取web根路径
//				String webroot=ServletActionContext.getServletContext().getRealPath("/");
//				//拼装保存路径
//				String newPath=webroot+File.separator+"upload";
//				System.out.println(newPath);
//				String name=fj.getUploadFileName().substring(fj.getUploadFileName().lastIndexOf("."));
//				String newFileName=UUID.randomUUID().toString().replace("-", "")+name;
//				File newFile=new File(newPath,newFileName); 
//				String imgroot="http://123.56.199.85:8080/sannongbao2/Upload/"+newFileName;
//				System.out.println(imgroot);
//				fj.getUpload().renameTo(newFile);
//				//上传部件结束
//				js.setShipin_dizhi(imgroot);
//				}
			js.setShipin_dizhi(dizhi);
		}else if(type == 2){//音频1
			js.setYinpin_timu(js_timu);
			js.setYinpin_miao(miao);
			js.setYinpin_dizhi(dizhi);
		}
		js.setGengxin_time(d);
		paraMap.put("js", js);
		snbService.updatejs(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongyejishu");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_update";
	}
	
	public String update2() throws Exception { //文章更新
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		PageList<Nongyejishu> nyjs=(PageList)session.getAttribute("nyjs");
		int a=(Integer)session.getAttribute("whe");
		Nongyejishu js=nyjs.getPlist().get(a-1);
		js.setJishu_type(1);//文章2
		js.setWenzhang_timu(wenzhang_timu);
		js.setWenzhang_neirong(wenzhang_neirong);
		js.setGengxin_time(d);
		paraMap.put("js", js);
		snbService.updatejs(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongyejishu");
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
		PageList<Nongyejishu> nyjs=(PageList)session.getAttribute("nyjs");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Nongyejishu js=nyjs.getPlist().get(whe-1);
		js.setJishu_zhidingwei(1);
		js.setGengxin_time(d);
		paraMap.put("js", js);
		snbService.updatejs(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongyejishu");
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
		PageList<Nongyejishu> nyjs=(PageList)session.getAttribute("nyjs");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Nongyejishu js=nyjs.getPlist().get(whe-1);
		js.setJishu_zhidingwei(0);
		js.setGengxin_time(d);
		paraMap.put("js", js);
		snbService.updatejs(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(0);
		rz.setRizhi_duix_mingcheng("Nongyejishu");
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
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		PageList<Nongyejishu> nyjs=(PageList)session.getAttribute("nyjs");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Nongyejishu js=nyjs.getPlist().get(whe-1);
		js.setJishu_biaozhiwei(0);
		paraMap.put("js", js);
		snbService.updatejs(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongyejishu");
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
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		PageList<Nongyejishu> nyjs=(PageList)session.getAttribute("nyjs");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Nongyejishu js=nyjs.getPlist().get(whe-1);
		if(js.getJishu_biaozhiwei()==2){//已是新型，取消
			js.setJishu_biaozhiwei(1);
		}
		else{//不是新型，设置新型
			js.setJishu_biaozhiwei(2);
		}
		js.setGengxin_time(d);
		paraMap.put("js", js);
		snbService.updatejs(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongyejishu");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_delete";
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
	public String getJs_timu() {
		return js_timu;
	}
	public void setJs_timu(String js_timu) {
		this.js_timu = js_timu;
	}
	public String getDizhi() {
		return dizhi;
	}
	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}
	public String getMiao() {
		return miao;
	}
	public void setMiao(String miao) {
		this.miao = miao;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getWenzhang_timu() {
		return wenzhang_timu;
	}
	public void setWenzhang_timu(String wenzhang_timu) {
		this.wenzhang_timu = wenzhang_timu;
	}
	public String getWenzhang_neirong() {
		return wenzhang_neirong;
	}
	public void setWenzhang_neirong(String wenzhang_neirong) {
		this.wenzhang_neirong = wenzhang_neirong;
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
