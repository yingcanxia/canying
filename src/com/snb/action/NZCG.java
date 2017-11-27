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
import com.snb.hbm.orm.Jiage_tongbao;
import com.snb.hbm.orm.Nongzi_caigou;
import com.snb.hbm.orm.Rizhi;
import com.snb.services.impl.SnbService;

public class NZCG extends ActionSupport{
	private SnbService snbService;
	private String timu;
	
	private String nongzi_mingcheng;//
	private String nongzi_miaoshu;//
	private String nongzi_qitaneirong;//
	private int nongzi_shuliang;//
	private String nongzi_biaozhu;
	private String nongzi_guige;
	private String nongzi_jiage;
	private String nongzi_changjiadianhua;
	private int nongzi_kucunliang;
	private String nongzi_changjia_dizhi;
	private String imgSrc;
	private FujianModel fj=new FujianModel();
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		Map paraMap=new HashMap();
		paraMap.put("timu", timu);
		PageList<Nongzi_caigou> pl=new PageList<Nongzi_caigou>();
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
		List c=new ArrayList();
		List<Nongzi_caigou> nzcx=new ArrayList<Nongzi_caigou>();
		Map mdp=snbService.queryNz(paraMap);
		c=(List)mdp.get("list");
		pl.setNumber(c.size());
		pl.setSumPage((c.size()+10-1)/10);
		for(int i=0;i<10;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*10<c.size()){
				Nongzi_caigou nz=new Nongzi_caigou();
				nz=(Nongzi_caigou)c.get(i+(pl.getNowpage()-1)*10);
				nzcx.add(nz);
				}else{
					break;
				}
		}
		pl.setPlist(nzcx);
		request.setAttribute("para", paraMap);
		session.setAttribute("nzcg", pl);//放入session中供前台获取
		return "success_query";
	}
	public String save() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		int user_id=(Integer)session.getAttribute("user_id");
		
		//上传组件开始
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
		//上传组件结束
		Nongzi_caigou nz=new Nongzi_caigou();
		nz.setNongzi_mingcheng(nongzi_mingcheng);
		nz.setNongzi_miaoshu(nongzi_miaoshu);
		nz.setNongzi_jiage(nongzi_jiage);
		nz.setNongzi_guige(nongzi_guige);
		nz.setNongzi_kucunliang(nongzi_kucunliang);
		nz.setNongzi_biaozhu(nongzi_biaozhu);
		nz.setNongzi_caigou_biaozhiwei(1);
		nz.setNongzi_qitaneirong(nongzi_qitaneirong);
		nz.setNongzi_shuliang(nongzi_shuliang);
		nz.setNongzi_tupian_lianjie(imgroot);
		nz.setNongzi_changjia_dizhi(nongzi_changjia_dizhi);
		nz.setNongzi_changjiadianhua(nongzi_changjiadianhua);
		nz.setNongzi_caigou_gengxin_shijian(d);
		paraMap.put("nz", nz);
		snbService.saveNz(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongzi_caigou");
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
		int user_id=(Integer)session.getAttribute("user_id");
		//上传组件开始
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
		//上传组件结束
		PageList<Nongzi_caigou> nzcx=(PageList)session.getAttribute("nzcg");
		int a=(Integer)session.getAttribute("whe");
		Nongzi_caigou nz=nzcx.getPlist().get(a-1);
		nz.setNongzi_mingcheng(nongzi_mingcheng);
		nz.setNongzi_miaoshu(nongzi_miaoshu);
		nz.setNongzi_qitaneirong(nongzi_qitaneirong);
		nz.setNongzi_jiage(nongzi_jiage);
		nz.setNongzi_shuliang(nongzi_shuliang);
		nz.setNongzi_biaozhu(nongzi_biaozhu);
		nz.setNongzi_guige(nongzi_guige);
		nz.setNongzi_changjiadianhua(nongzi_changjiadianhua);
		nz.setNongzi_kucunliang(nongzi_kucunliang);
		nz.setNongzi_changjia_dizhi(nongzi_changjia_dizhi);
		nz.setNongzi_tupian_lianjie(imgroot);
		nz.setNongzi_caigou_gengxin_shijian(d);
		paraMap.put("nz", nz);
		snbService.updatenz(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongzi_caigou");
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
		PageList<Nongzi_caigou> nzcx=(PageList)session.getAttribute("nzcg");
		Nongzi_caigou nz=nzcx.getPlist().get(whe-1);
		nz.setNongzi_caigou_biaozhiwei(0);
		
		paraMap.put("nz", nz);
		snbService.updatenz(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Nongzi_caigou");
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
	public String getNongzi_mingcheng() {
		return nongzi_mingcheng;
	}
	public void setNongzi_mingcheng(String nongzi_mingcheng) {
		this.nongzi_mingcheng = nongzi_mingcheng;
	}
	public String getNongzi_miaoshu() {
		return nongzi_miaoshu;
	}
	public void setNongzi_miaoshu(String nongzi_miaoshu) {
		this.nongzi_miaoshu = nongzi_miaoshu;
	}
	public String getNongzi_qitaneirong() {
		return nongzi_qitaneirong;
	}
	public void setNongzi_qitaneirong(String nongzi_qitaneirong) {
		this.nongzi_qitaneirong = nongzi_qitaneirong;
	}
	
	public int getNongzi_shuliang() {
		return nongzi_shuliang;
	}
	public void setNongzi_shuliang(int nongzi_shuliang) {
		this.nongzi_shuliang = nongzi_shuliang;
	}
	public String getNongzi_biaozhu() {
		return nongzi_biaozhu;
	}
	public void setNongzi_biaozhu(String nongzi_biaozhu) {
		this.nongzi_biaozhu = nongzi_biaozhu;
	}
	public String getNongzi_guige() {
		return nongzi_guige;
	}
	public void setNongzi_guige(String nongzi_guige) {
		this.nongzi_guige = nongzi_guige;
	}
	public String getNongzi_jiage() {
		return nongzi_jiage;
	}
	public void setNongzi_jiage(String nongzi_jiage) {
		this.nongzi_jiage = nongzi_jiage;
	}
	public String getNongzi_changjiadianhua() {
		return nongzi_changjiadianhua;
	}
	public void setNongzi_changjiadianhua(String nongzi_changjiadianhua) {
		this.nongzi_changjiadianhua = nongzi_changjiadianhua;
	}
	public int getNongzi_kucunliang() {
		return nongzi_kucunliang;
	}
	public void setNongzi_kucunliang(int nongzi_kucunliang) {
		this.nongzi_kucunliang = nongzi_kucunliang;
	}
	public String getNongzi_changjia_dizhi() {
		return nongzi_changjia_dizhi;
	}
	public void setNongzi_changjia_dizhi(String nongzi_changjia_dizhi) {
		this.nongzi_changjia_dizhi = nongzi_changjia_dizhi;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public FujianModel getFj() {
		return fj;
	}
	public void setFj(FujianModel fj) {
		this.fj = fj;
	}
	
	
}
