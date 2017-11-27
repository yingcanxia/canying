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
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Scroll_img;
import com.snb.services.impl.SnbService;

public class SI extends ActionSupport{
	private  SnbService snbService;
	private String img_name;
	private String ccc;
	private String imgSrc;
	private FujianModel fj=new FujianModel();
	
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();
		Map paraMap=new HashMap();
		List c=new ArrayList();
		List<Scroll_img> scr=new ArrayList<Scroll_img>();
		Map mdp=snbService.querySi(paraMap);
		c=(List)mdp.get("list");
		for(int i=0;i<c.size();i++){
			Scroll_img si=(Scroll_img)c.get(i);
			scr.add(si);
		}
		request.setAttribute("para", paraMap);
		session.setAttribute("scr", scr);//放入session中供前台获取
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
		Scroll_img si=new Scroll_img();
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
			si.setTupian_lianjie(imgroot);
			}
		si.setBiaozhiwei(1);
		si.setFk_user_id(user_id);
		si.setTimu(img_name);
		si.setShangchuan_shijan(d);
		si.setTupian_neirong(ccc);
		paraMap.put("si", si);
		snbService.saveSi(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Scroll_img");
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
		List<Scroll_img> l=(List)session.getAttribute("scr");
		int a=(Integer)session.getAttribute("whe");
		Scroll_img si=l.get(a-1);
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
			si.setTupian_lianjie(imgroot);
			}
		si.setBiaozhiwei(1);
		si.setFk_user_id(user_id);
		si.setTimu(img_name);
		si.setShangchuan_shijan(d);
		si.setTupian_neirong(ccc);
		paraMap.put("si", si);
		snbService.saveSi(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Scroll_img");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_update";
	}
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return "success_delete";
	}
	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
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
	public SnbService getSnbService() {
		return snbService;
	}
	public String getCcc() {
		return ccc;
	}
	public void setCcc(String ccc) {
		this.ccc = ccc;
	}
	
}
