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
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.Shuoming_wendang;
import com.snb.services.impl.SnbService;

public class SMWD extends ActionSupport{
	private SnbService snbService;
	private int shuoming_type;
	private String shuoming_neirong;
	
	public String query() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
		HttpSession session=request.getSession();
		Map paraMap=new HashMap();
		List c=new ArrayList();
		List<Shuoming_wendang> smwd=new ArrayList<Shuoming_wendang>();
		Map mdp=snbService.querySm(paraMap);
		c=(List)mdp.get("list");
		for(int i=0;i<c.size();i++){
			Shuoming_wendang si=(Shuoming_wendang)c.get(i);
			smwd.add(si);
		}
		request.setAttribute("para", paraMap);
		session.setAttribute("smwd", smwd);//放入session中供前台获取
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
		Shuoming_wendang sm=new Shuoming_wendang();
		sm.setType(shuoming_type);
		sm.setNeirong(shuoming_neirong);
		sm.setShangchuan_shijian(d);
		paraMap.put("sm", sm);
		snbService.saveSm(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Shuoming_wendang");
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
		List<Shuoming_wendang> smwd=(List)session.getAttribute("smwd");
		int a=(Integer)session.getAttribute("whe");
		Shuoming_wendang sm=smwd.get(a-1);
		sm.setType(shuoming_type);
		sm.setNeirong(shuoming_neirong);
		sm.setShangchuan_shijian(d);
		paraMap.put("sm", sm);
		snbService.updatesm(paraMap);
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("Shuoming_wendang");
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
	public int getShuoming_type() {
		return shuoming_type;
	}
	public void setShuoming_type(int shuoming_type) {
		this.shuoming_type = shuoming_type;
	}
	public String getShuoming_neirong() {
		return shuoming_neirong;
	}
	public void setShuoming_neirong(String shuoming_neirong) {
		this.shuoming_neirong = shuoming_neirong;
	}
	
}
