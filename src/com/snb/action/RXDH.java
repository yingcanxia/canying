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
import com.snb.hbm.orm.Rexian;
import com.snb.hbm.orm.Rizhi;
import com.snb.services.impl.SnbService;

public class RXDH extends ActionSupport{
	private  SnbService snbService;
	private String timu;
	private int identity;
	private String r_name;
	private String imgSrc;
	private String banshi_danwei;
	private String banshi_dizhi;
	private String zhuanjia_dianhua;
	private FujianModel fj=new FujianModel();
	public String query() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//通过request获取session对象
		paraMap.put("timu", timu);
		PageList<Rexian> pl=new PageList<Rexian>();
		pl.setNowpage(1);
		List c=new ArrayList();
		Map mdp=snbService.queryRx(paraMap);
		c=(List)mdp.get("list");
		pl.setNumber(c.size());
		pl.setSumPage((c.size()+10-1)/10);
		List<Rexian> rx=new ArrayList<Rexian>();
		if(nowpage!=null&&!nowpage.equals(" ")){
			int now=Integer.parseInt(nowpage);
			if(now<1){
				now=1;
			}else if(now>pl.getSumPage()){
				now=pl.getSumPage();
			}
			pl.setNowpage(now);
		}
		
		for (int i = 0; i < 10; i++) {
			
			if(i+(pl.getNowpage()-1)*10<c.size()){
				Rexian rexian=(Rexian)c.get(i);
				rexian=(Rexian)c.get(i+(pl.getNowpage()-1)*10);
				rx.add(rexian);
				}else{
					break;
				}
		}
		pl.setPlist(rx);
		request.setAttribute("para", paraMap);
		session.setAttribute("rx", pl);//放入session中供前台获取
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
		Rexian rexian=new Rexian();
		if(fj.getUploadFileName()!=null&&!fj.getUploadFileName().equals("")){
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
		if(identity==0){//办事员
			
			rexian.setBanshi_tupian(imgroot);
		}else {
		
			rexian.setZhuanjia_tupain(imgroot);
		}
		}
		if(identity==0){//办事员
			rexian.setBanshi_name(r_name);
			
		}else {
			rexian.setZhuanjia_name(r_name);
			
		}
		rexian.setRexian_leixing(identity);
		rexian.setBanshi_danwei(banshi_danwei);
		rexian.setBanshi_dizhi(banshi_dizhi);
		rexian.setRexian_biaozhiwei(1);
		rexian.setZhuanjia_dianhua(zhuanjia_dianhua);
		rexian.setRexian_gengxin_shijian(d);
		paraMap.put("rx", rexian);
		snbService.saveRx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Rexian");
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
		PageList<Rexian> rxdh=(PageList)session.getAttribute("rx");
		int a=(Integer)session.getAttribute("whe");
		Rexian rx=rxdh.getPlist().get(a-1);
		if(fj.getUploadFileName()!=null&&!fj.getUploadFileName().equals("")){
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
		identity = rx.getRexian_leixing();
		//上传组件结束
		if(identity==0){//办事员
			System.out.println("办事员");
			rx.setBanshi_tupian(imgroot);
		}else {
			System.out.println("专家");
			rx.setZhuanjia_tupain(imgroot);
		}
		}
		if(identity==0){//办事员
			System.out.println("办事员图片");
			rx.setBanshi_name(r_name);
		
		}else {
			System.out.println("专家图片");
			rx.setZhuanjia_name(r_name);
			
		}
		rx.setBanshi_danwei(banshi_danwei);
		rx.setBanshi_dizhi(banshi_dizhi);
		rx.setZhuanjia_dianhua(zhuanjia_dianhua);
		rx.setRexian_gengxin_shijian(d);
		paraMap.put("rx", rx);
		snbService.updaterx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Rexian");
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
		PageList<Rexian> rxdh=(PageList)session.getAttribute("rx");
		Rexian rx=rxdh.getPlist().get(whe-1);
		rx.setRexian_biaozhiwei(0);
		paraMap.put("rx", rx);
		snbService.updaterx(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Rexian");
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
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}

	
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getBanshi_danwei() {
		return banshi_danwei;
	}
	public void setBanshi_danwei(String banshi_danwei) {
		this.banshi_danwei = banshi_danwei;
	}
	public String getBanshi_dizhi() {
		return banshi_dizhi;
	}
	public void setBanshi_dizhi(String banshi_dizhi) {
		this.banshi_dizhi = banshi_dizhi;
	}
	public String getZhuanjia_dianhua() {
		return zhuanjia_dianhua;
	}
	public void setZhuanjia_dianhua(String zhuanjia_dianhua) {
		this.zhuanjia_dianhua = zhuanjia_dianhua;
	}
	public FujianModel getFj() {
		return fj;
	}
	public void setFj(FujianModel fj) {
		this.fj = fj;
	}
	
}
