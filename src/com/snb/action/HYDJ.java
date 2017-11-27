package com.snb.action;

import java.io.File;
import java.net.URLDecoder;
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
import com.snb.hbm.orm.Huangye_dianjia;
import com.snb.hbm.orm.Rizhi;
import com.snb.services.impl.SnbService;

public class HYDJ extends ActionSupport{
	private  SnbService snbService;
	private int type=-1;
	private String timu;
	private int huangye_dianjia_type;
	private String huangye_dianjia_mingcheng;
	private String huangye_dianjia_dianhua;
	private String huangye_dianjia_dizhi;
	private FujianModel fj=new FujianModel();
	private String huangye_xiangxi;
	private String huangye_dianjia_suoshuzhen;
	private int nowpageA=0;
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		//Map paraMapA=new HashMap();
		Map paraMap=new HashMap();
		PageList<Huangye_dianjia> pl=new PageList<Huangye_dianjia>();
		pl.setNowpage(1);
		Huangye_dianjia quer=new Huangye_dianjia();
		quer.setHuangye_dianjia_type(type);
		quer.setHuangye_dianjia_mingcheng(timu);
		quer.setHuangye_dianjia_suoshuzhen(huangye_dianjia_suoshuzhen);
		paraMap.put("quer", quer);
		List c=new ArrayList();
		List<Huangye_dianjia> hydj=new ArrayList<Huangye_dianjia>();
		Map mdp=snbService.queryHy(paraMap);
		c=(List)mdp.get("list");
		pl.setNumber(c.size());
		pl.setSumPage((c.size()-1)/15+1);
		if(nowpage!=null&&!nowpage.equals("")){
			int now=Integer.parseInt(nowpage);
			if(now<1){
				now=1;
			}else if(now>pl.getSumPage()){
				now=pl.getSumPage();
			}
			pl.setNowpage(now);
		}else if(nowpageA!=0){
			pl.setNowpage(nowpageA);
		}
		
		for(int i=0;i<15;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*15<c.size()){
				Huangye_dianjia jg=new Huangye_dianjia();
				jg=(Huangye_dianjia)c.get(i+(pl.getNowpage()-1)*15);
				hydj.add(jg);
				}else{
					break;
				}
		}
		pl.setPlist(hydj);
		request.setAttribute("para", paraMap);
		request.setAttribute("condition", quer);
		session.setAttribute("hydj", pl);//放入session中供前台获取
		return "success_query";
	}
	public String save() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int user_id=(Integer)session.getAttribute("user_id");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		Huangye_dianjia hy=new Huangye_dianjia();
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
		hy.setHuangye_dianjia_tupian(imgroot);
		}
		hy.setHuangye_biaozhiwei(1);
		hy.setHuangye_dianjia_type(huangye_dianjia_type);
		hy.setHuangye_dianjia_mingcheng(huangye_dianjia_mingcheng);
		hy.setHuangye_dianjia_dianhua(huangye_dianjia_dianhua);
		hy.setHuangye_dianjia_dizhi(huangye_dianjia_dizhi);
		hy.setHuangye_xiangxi(huangye_xiangxi);
		hy.setHuangye_dianjia_suoshuzhen(huangye_dianjia_suoshuzhen);
		hy.setHuangye_gengxin_shijian(d);
		paraMap.put("hy", hy);
		
		System.out.println("保存：'"+huangye_dianjia_suoshuzhen+"'");
		
		snbService.saveHy(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Huangye_dianjia");
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
		PageList<Huangye_dianjia> jgtb=(PageList)session.getAttribute("hydj");
		int a=(Integer)session.getAttribute("whe");
		Huangye_dianjia hy=jgtb.getPlist().get(a-1);
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		if(fj.getUploadFileName()!=null&&!fj.getUploadFileName().equals("")){
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
		hy.setHuangye_dianjia_tupian(imgroot);
		
		fj.getUpload().renameTo(newFile);
		}
		hy.setHuangye_dianjia_type(huangye_dianjia_type);
		hy.setHuangye_dianjia_mingcheng(huangye_dianjia_mingcheng);
		hy.setHuangye_dianjia_dianhua(huangye_dianjia_dianhua);
		hy.setHuangye_dianjia_dizhi(huangye_dianjia_dizhi);
		hy.setHuangye_xiangxi(huangye_xiangxi);
		hy.setHuangye_dianjia_suoshuzhen(huangye_dianjia_suoshuzhen);
		hy.setHuangye_gengxin_shijian(d);
		paraMap.put("hy", hy);
		
		System.out.println("更新：'"+huangye_dianjia_suoshuzhen+"'");
		
		snbService.updatehy(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Huangye_dianjia");
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
		PageList<Huangye_dianjia> b=(PageList)session.getAttribute("hydj");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Huangye_dianjia hy=b.getPlist().get(whe-1);
		hy.setHuangye_zhidingwei(1);
		hy.setHuangye_gengxin_shijian(d);
		paraMapB.put("hy", hy);
		snbService.updatehy(paraMapB);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Huangye_dianjia");
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
		PageList<Huangye_dianjia> b=(PageList)session.getAttribute("hydj");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Huangye_dianjia hy=b.getPlist().get(whe-1);
		hy.setHuangye_zhidingwei(0);
		hy.setHuangye_gengxin_shijian(d);
		paraMapB.put("hy", hy);
		snbService.updatehy(paraMapB);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Huangye_dianjia");
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
		PageList<Huangye_dianjia> jgtb=(PageList)session.getAttribute("hydj");
		Huangye_dianjia hy=jgtb.getPlist().get(whe-1);
		hy.setHuangye_biaozhiwei(0);
		paraMap.put("hy", hy);
		snbService.updatehy(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Huangye_dianjia");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_delete";
	}

	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTimu() {
		return timu;
	}
	public void setTimu(String timu) {
		this.timu = timu;
	}
	public int getHuangye_dianjia_type() {
		return huangye_dianjia_type;
	}
	public void setHuangye_dianjia_type(int huangye_dianjia_type) {
		this.huangye_dianjia_type = huangye_dianjia_type;
	}
	public String getHuangye_dianjia_mingcheng() {
		return huangye_dianjia_mingcheng;
	}
	public void setHuangye_dianjia_mingcheng(String huangye_dianjia_mingcheng) {
		this.huangye_dianjia_mingcheng = huangye_dianjia_mingcheng;
	}
	public String getHuangye_dianjia_dianhua() {
		return huangye_dianjia_dianhua;
	}
	public void setHuangye_dianjia_dianhua(String huangye_dianjia_dianhua) {
		this.huangye_dianjia_dianhua = huangye_dianjia_dianhua;
	}
	public String getHuangye_dianjia_dizhi() {
		return huangye_dianjia_dizhi;
	}
	public void setHuangye_dianjia_dizhi(String huangye_dianjia_dizhi) {
		this.huangye_dianjia_dizhi = huangye_dianjia_dizhi;
	}
	public FujianModel getFj() {
		return fj;
	}
	public void setFj(FujianModel fj) {
		this.fj = fj;
	}
	public String getHuangye_xiangxi() {
		return huangye_xiangxi;
	}
	public void setHuangye_xiangxi(String huangye_xiangxi) {
		this.huangye_xiangxi = huangye_xiangxi;
	}
	public String getHuangye_dianjia_suoshuzhen() {
		return huangye_dianjia_suoshuzhen;
	}
	public void setHuangye_dianjia_suoshuzhen(String huangye_dianjia_suoshuzhen) {
		this.huangye_dianjia_suoshuzhen = huangye_dianjia_suoshuzhen;
	}
	public int getNowpageA() {
		return nowpageA;
	}
	public void setNowpageA(int nowpageA) {
		this.nowpageA = nowpageA;
	}
	
}
