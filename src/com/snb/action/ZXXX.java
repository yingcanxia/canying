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
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Zhanxiao_xinxi;
import com.snb.services.impl.SnbService;

public class ZXXX extends ActionSupport{
	private  SnbService snbService;
	private String timu;
	private String zhanxiao_xinxi_shangpin_miaoshu;
	private String zhanxiao_xinxi_timu;
	private String zhanxiao_xinxi_shangpin_mingcheng;
	private FujianModel fj=new FujianModel();
	private String zhanxiao_xinxi_dizhi;
	private String zhanxiao_xinxi_lianxifangshi;
	private String imgSrc;
	private int nowpageA=0;
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		//Map paraMapA=new HashMap();
		Map paraMap=new HashMap();
		PageList<Zhanxiao_xinxi> pl=new PageList<Zhanxiao_xinxi>();
		pl.setNowpage(1);
		paraMap.put("timu", timu);
		List c=new ArrayList();
		List<Zhanxiao_xinxi> zxxx=new ArrayList<Zhanxiao_xinxi>();
		Map mdp=snbService.queryZx(paraMap);
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
		}
		else if(nowpageA>0){
			pl.setNowpage(nowpageA);
		}
		
		
		for(int i=0;i<15;i++){//将获取道德list重新压入有范型的list里
			if(i+(pl.getNowpage()-1)*15<c.size()){
				Zhanxiao_xinxi zx=new Zhanxiao_xinxi();
				zx=(Zhanxiao_xinxi)c.get(i+(pl.getNowpage()-1)*15);
				zxxx.add(zx);
				}else{
					break;
				}
		}
		pl.setPlist(zxxx);
		request.setAttribute("para", paraMap);
		session.setAttribute("zxxx", pl);//放入session中供前台获取
		return "success_query";
	}

	public String save() throws Exception {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();//通过request获取session对象
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		int user_id=(Integer)session.getAttribute("user_id");
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		Zhanxiao_xinxi zx=new Zhanxiao_xinxi();
		if(fj.getUploadFileName()!=null&&fj.getUploadFileName().equals("")){
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
		zx.setZhanxiao_xinxi_tupian_lianjie(imgroot);
		}
		zx.setZhanxiao_xinxi_timu(zhanxiao_xinxi_timu);
		zx.setZhanxiao_xinxi_shangpin_miaoshu(zhanxiao_xinxi_shangpin_miaoshu);
		zx.setZhanxiao_xinxi_dizhi(zhanxiao_xinxi_dizhi);
		zx.setZhanxiao_xinxi_lianxifangshi(zhanxiao_xinxi_lianxifangshi);
		zx.setZhanxiao_xinxi_shangpin_mingcheng(zhanxiao_xinxi_shangpin_mingcheng);
		zx.setZhanxiao_xinxi_biaozhiwei(1);
		zx.setFk_zhanxiao_xinxi_user_id(user_id);
		zx.setZhanxiao_xinxi_gengxin_shijian(d);
		paraMap.put("zx", zx);
		snbService.saveZx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Zhanxiao_xinxi");
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
		PageList<Zhanxiao_xinxi> b=(PageList)session.getAttribute("zxxx");
		int a=(Integer)session.getAttribute("whe");
		Zhanxiao_xinxi zx=b.getPlist().get(a-1);
		if(fj.getUploadFileName()!=null&&fj.getUploadFileName().equals("")){
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
			zx.setZhanxiao_xinxi_tupian_lianjie(imgroot);
			}
		zx.setZhanxiao_xinxi_timu(zhanxiao_xinxi_timu);
		zx.setZhanxiao_xinxi_shangpin_mingcheng(zhanxiao_xinxi_shangpin_mingcheng);
		zx.setZhanxiao_xinxi_shangpin_miaoshu(zhanxiao_xinxi_shangpin_miaoshu);
		zx.setZhanxiao_xinxi_dizhi(zhanxiao_xinxi_dizhi);
		zx.setZhanxiao_xinxi_lianxifangshi(zhanxiao_xinxi_lianxifangshi);
		
		zx.setZhanxiao_xinxi_gengxin_shijian(d);
		paraMap.put("zx", zx);
		snbService.updatezx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Zhanxiao_xinxi");
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
		PageList<Zhanxiao_xinxi> b=(PageList)session.getAttribute("zxxx");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Zhanxiao_xinxi zx=b.getPlist().get(whe-1);
		
		zx.setZhanxiao_xinxi_zhidingwei(1);
		zx.setZhanxiao_xinxi_gengxin_shijian(d);
		paraMap.put("zx", zx);
		snbService.updatezx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Zhanxiao_xinxi");
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
		PageList<Zhanxiao_xinxi> b=(PageList)session.getAttribute("zxxx");
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		Zhanxiao_xinxi zx=b.getPlist().get(whe-1);
		
		zx.setZhanxiao_xinxi_zhidingwei(0);
		zx.setZhanxiao_xinxi_gengxin_shijian(d);
		paraMap.put("zx", zx);
		snbService.updatezx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Zhanxiao_xinxi");
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
		PageList<Zhanxiao_xinxi> b=(PageList)session.getAttribute("zxxx");
		Zhanxiao_xinxi zx=b.getPlist().get(whe-1);
		zx.setZhanxiao_xinxi_biaozhiwei(0);
		paraMap.put("zx", zx);
		snbService.updatezx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Zhanxiao_xinxi");
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

	public String getZhanxiao_xinxi_shangpin_miaoshu() {
		return zhanxiao_xinxi_shangpin_miaoshu;
	}

	public void setZhanxiao_xinxi_shangpin_miaoshu(String zhanxiao_xinxi_shangpin_miaoshu) {
		this.zhanxiao_xinxi_shangpin_miaoshu = zhanxiao_xinxi_shangpin_miaoshu;
	}

	public String getZhanxiao_xinxi_dizhi() {
		return zhanxiao_xinxi_dizhi;
	}

	public void setZhanxiao_xinxi_dizhi(String zhanxiao_xinxi_dizhi) {
		this.zhanxiao_xinxi_dizhi = zhanxiao_xinxi_dizhi;
	}

	public String getZhanxiao_xinxi_lianxifangshi() {
		return zhanxiao_xinxi_lianxifangshi;
	}

	public void setZhanxiao_xinxi_lianxifangshi(String zhanxiao_xinxi_lianxifangshi) {
		this.zhanxiao_xinxi_lianxifangshi = zhanxiao_xinxi_lianxifangshi;
	}

	public String getZhanxiao_xinxi_timu() {
		return zhanxiao_xinxi_timu;
	}

	public void setZhanxiao_xinxi_timu(String zhanxiao_xinxi_timu) {
		this.zhanxiao_xinxi_timu = zhanxiao_xinxi_timu;
	}

	public String getZhanxiao_xinxi_shangpin_mingcheng() {
		return zhanxiao_xinxi_shangpin_mingcheng;
	}

	public void setZhanxiao_xinxi_shangpin_mingcheng(String zhanxiao_xinxi_shangpin_mingcheng) {
		this.zhanxiao_xinxi_shangpin_mingcheng = zhanxiao_xinxi_shangpin_mingcheng;
	}

	public FujianModel getFj() {
		return fj;
	}

	public void setFj(FujianModel fj) {
		this.fj = fj;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public int getNowpageA() {
		return nowpageA;
	}

	public void setNowpageA(int nowpageA) {
		this.nowpageA = nowpageA;
	}
	
}
