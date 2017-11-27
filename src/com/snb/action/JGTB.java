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
import com.snb.hbm.orm.Nongyejishu;
import com.snb.hbm.orm.Rizhi;
import com.snb.services.impl.SnbService;

public class JGTB extends ActionSupport{
	private  SnbService snbService;
	private String timu;
	private int jiage_tongbao_yuefen;
	private String jiage_tongbao_timu;
	private String imgSrc;
	private FujianModel fj=new FujianModel();
	private int nowpageA=0;
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//��ȡrequest����
		request.setCharacterEncoding("UTF-8");
		String nowpage=request.getParameter("nowpage");
		HttpSession session=request.getSession();//ͨ��request��ȡsession����
		//Map paraMapA=new HashMap();
		Map paraMap=new HashMap();
		PageList<Jiage_tongbao> pl=new PageList<Jiage_tongbao>();
		pl.setNowpage(1);
		paraMap.put("timu", timu);
		List c=new ArrayList();
		List<Jiage_tongbao> jgtb=new ArrayList<Jiage_tongbao>();
		Map mdp=snbService.queryJg(paraMap);
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
		
		for(int i=0;i<15;i++){//����ȡ����list����ѹ���з��͵�list��
			if(i+(pl.getNowpage()-1)*15<c.size()){
				Jiage_tongbao jg=new Jiage_tongbao();
				jg=(Jiage_tongbao)c.get(i+(pl.getNowpage()-1)*15);
				jgtb.add(jg);
				}else{
					break;
				}
		}
		pl.setPlist(jgtb);
		request.setAttribute("para", paraMap);
		session.setAttribute("jgtb", pl);//����session�й�ǰ̨��ȡ
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
		Jiage_tongbao jg=new Jiage_tongbao();
		if(fj.getUploadFileName()!=null&&!fj.getUploadFileName().equals("")){
		//�ϴ�������ʼ
		System.out.println(fj.getUploadFileName());
		fj.getUpload().getName();
		//��ȡweb��·��
		String webroot=ServletActionContext.getServletContext().getRealPath("/");
		//ƴװ����·��
		String newPath=webroot+File.separator+"upload";
		System.out.println(newPath);
		String name=fj.getUploadFileName().substring(fj.getUploadFileName().lastIndexOf("."));
		String newFileName=UUID.randomUUID().toString().replace("-", "")+name;
		File newFile=new File(newPath,newFileName); 
		String imgroot="Upload/"+newFileName;
		System.out.println(imgroot);
		fj.getUpload().renameTo(newFile);
		//�ϴ���������
		jg.setJiage_tongbao_neirong_wenjian_lianjie(imgroot);
		}
		jg.setJiage_tongbao_timu(jiage_tongbao_timu);
		jg.setJiage_tongbao_yuefen(jiage_tongbao_yuefen);
		jg.setJiage_tongbao_biaozhiwei(1);
		jg.setJiage_tongbao_chuanjian_shijian(d);
		jg.setFk_jiage_tongbao_user_id(user_id);
		jg.setJiage_tongbao_gengxin_shijian(d);
		paraMap.put("jg", jg);
		snbService.saveJg(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Jiage_tongbao");
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
		PageList<Jiage_tongbao> jgtb=(PageList)session.getAttribute("jgtb");
		int a=(Integer)session.getAttribute("whe");
		Jiage_tongbao jg=jgtb.getPlist().get(a-1);
		if(fj.getUploadFileName()!=null&&!fj.getUploadFileName().equals("")){
			//�ϴ�������ʼ
			System.out.println(fj.getUploadFileName());
			fj.getUpload().getName();
			//��ȡweb��·��
			String webroot=ServletActionContext.getServletContext().getRealPath("/");
			//ƴװ����·��
			String newPath=webroot+File.separator+"upload";
			System.out.println(newPath);
			String name=fj.getUploadFileName().substring(fj.getUploadFileName().lastIndexOf("."));
			String newFileName=UUID.randomUUID().toString().replace("-", "")+name;
			File newFile=new File(newPath,newFileName); 
			String imgroot="Upload/"+newFileName;
			System.out.println(imgroot);
			fj.getUpload().renameTo(newFile);
			//�ϴ���������
			jg.setJiage_tongbao_neirong_wenjian_lianjie(imgroot);
			}
		jg.setJiage_tongbao_yuefen(jiage_tongbao_yuefen);
		jg.setJiage_tongbao_timu(jiage_tongbao_timu);
		jg.setJiage_tongbao_gengxin_shijian(d);
		paraMap.put("jg", jg);
		snbService.updatejg(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Jiage_tongbao");
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
		PageList<Jiage_tongbao> jgtb=(PageList)session.getAttribute("jgtb");
		Jiage_tongbao jg=jgtb.getPlist().get(whe-1);
		jg.setJiage_tongbao_biaozhiwei(0);
		paraMap.put("jg", jg);
		snbService.updatejg(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Jiage_tongbao");
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
	public int getJiage_tongbao_yuefen() {
		return jiage_tongbao_yuefen;
	}
	public void setJiage_tongbao_yuefen(int jiage_tongbao_yuefen) {
		this.jiage_tongbao_yuefen = jiage_tongbao_yuefen;
	}
	public String getJiage_tongbao_timu() {
		return jiage_tongbao_timu;
	}
	public void setJiage_tongbao_timu(String jiage_tongbao_timu) {
		this.jiage_tongbao_timu = jiage_tongbao_timu;
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
	public int getNowpageA() {
		return nowpageA;
	}
	public void setNowpageA(int nowpageA) {
		this.nowpageA = nowpageA;
	}
	
}
