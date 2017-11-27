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
import com.snb.hbm.orm.Fabu_nongmin_suqiu;
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Zhanxiao_xinxi;
import com.snb.services.impl.SnbService;

public class FBSQ extends ActionSupport{
	private  SnbService snbService;
	private String timu;
	private FujianModel fj=new FujianModel();
	private String fabu_nongmin_suqiu_timu;
	private String nongmin_suqiu_neirong;
	private String fabu_huifu_nongmin_suqiu_neirong;
	private String fabu_suqiu_renyuan_xingming;
	private String fabu_suqiu_renyuan_dianhua;
	
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		//Map paraMapA=new HashMap();
		Map paraMap=new HashMap();
		PageList<Fabu_nongmin_suqiu> pl=new PageList<Fabu_nongmin_suqiu>();
		pl.setNowpage(1);
		
		paraMap.put("timu", timu);
		List c=new ArrayList();
		List<Fabu_nongmin_suqiu> fbsq=new ArrayList<Fabu_nongmin_suqiu>();
		Map mdp=snbService.queryFb(paraMap);
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
		
		
		for(int i=0;i<10;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*10<c.size()){
				Fabu_nongmin_suqiu zx=new Fabu_nongmin_suqiu();
				zx=(Fabu_nongmin_suqiu)c.get(i+(pl.getNowpage()-1)*10);
				fbsq.add(zx);
				}else{
					break;
				}
		}
		pl.setPlist(fbsq);
		request.setAttribute("para", paraMap);
		session.setAttribute("fbsq", pl);//放入session中供前台获取
		return "success_query";
	}
	public String save() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();//通过request获取session对象
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		
		Fabu_nongmin_suqiu fb=new Fabu_nongmin_suqiu();
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
			fb.setFabu_nongmin_suqiu_tupian_lianjie(imgroot);
			}
		int user_id=(Integer)session.getAttribute("user_id");
		
		fb.setFabu_biaozhiwei(1);
		fb.setFabu_huifu_nongmin_suqiu_neirong(fabu_huifu_nongmin_suqiu_neirong);
		fb.setFabu_nongmin_suqiu_neirong(nongmin_suqiu_neirong);
		fb.setFabu_nongmin_suqiu_timu(fabu_nongmin_suqiu_timu);
		
		fb.setFk_fabu_nongmin_suqiu_user_id(user_id);
		fb.setFabu_suqiu_renyuan_xingming(fabu_suqiu_renyuan_xingming);
		fb.setFabu_suqiu_renyuan_dianhua(fabu_suqiu_renyuan_dianhua);
		fb.setFabu_nongmin_suqiu_gengxin_shijian(d);
		paraMap.put("fb", fb);
		snbService.saveFb(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Fabu_nongmin_suqiu");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_save";
	}
	public String update() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();//通过request获取session对象
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		int user_id=(Integer)session.getAttribute("user_id");
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		
		PageList<Fabu_nongmin_suqiu> b=(PageList)session.getAttribute("fbsq");
		int a=(Integer)session.getAttribute("whe");
		Fabu_nongmin_suqiu fb=b.getPlist().get(a-1);
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
			fb.setFabu_nongmin_suqiu_tupian_lianjie(imgroot);
			}
	
		
		fb.setFabu_huifu_nongmin_suqiu_neirong(fabu_huifu_nongmin_suqiu_neirong);
		fb.setFabu_nongmin_suqiu_timu(fabu_nongmin_suqiu_timu);
		
		fb.setFabu_nongmin_suqiu_neirong(nongmin_suqiu_neirong);
		fb.setFabu_suqiu_renyuan_xingming(fabu_suqiu_renyuan_xingming);
		fb.setFabu_suqiu_renyuan_dianhua(fabu_suqiu_renyuan_dianhua);
		fb.setFabu_nongmin_suqiu_gengxin_shijian(d);
		paraMap.put("fb", fb);
		snbService.updatefb(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Fabu_nongmin_suqiu");
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
		PageList<Fabu_nongmin_suqiu> b=(PageList)session.getAttribute("fbsq");
		Fabu_nongmin_suqiu fb=b.getPlist().get(whe-1);
		fb.setFabu_biaozhiwei(0);
		paraMap.put("fb", fb);
		snbService.updatefb(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Fabu_nongmin_suqiu");
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
	public FujianModel getFj() {
		return fj;
	}
	public void setFj(FujianModel fj) {
		this.fj = fj;
	}
	public String getFabu_nongmin_suqiu_timu() {
		return fabu_nongmin_suqiu_timu;
	}
	public void setFabu_nongmin_suqiu_timu(String fabu_nongmin_suqiu_timu) {
		this.fabu_nongmin_suqiu_timu = fabu_nongmin_suqiu_timu;
	}
	
	public String getNongmin_suqiu_neirong() {
		return nongmin_suqiu_neirong;
	}
	public void setNongmin_suqiu_neirong(String nongmin_suqiu_neirong) {
		this.nongmin_suqiu_neirong = nongmin_suqiu_neirong;
	}
	public String getFabu_huifu_nongmin_suqiu_neirong() {
		return fabu_huifu_nongmin_suqiu_neirong;
	}
	public void setFabu_huifu_nongmin_suqiu_neirong(String fabu_huifu_nongmin_suqiu_neirong) {
		this.fabu_huifu_nongmin_suqiu_neirong = fabu_huifu_nongmin_suqiu_neirong;
	}
	public String getFabu_suqiu_renyuan_xingming() {
		return fabu_suqiu_renyuan_xingming;
	}
	public void setFabu_suqiu_renyuan_xingming(String fabu_suqiu_renyuan_xingming) {
		this.fabu_suqiu_renyuan_xingming = fabu_suqiu_renyuan_xingming;
	}
	public String getFabu_suqiu_renyuan_dianhua() {
		return fabu_suqiu_renyuan_dianhua;
	}
	public void setFabu_suqiu_renyuan_dianhua(String fabu_suqiu_renyuan_dianhua) {
		this.fabu_suqiu_renyuan_dianhua = fabu_suqiu_renyuan_dianhua;
	}
	
}
