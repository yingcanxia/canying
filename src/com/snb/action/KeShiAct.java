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
import com.snb.bean.PageList;
import com.snb.hbm.orm.Keshi;
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.YiYuan;
import com.snb.services.impl.SnbService;

public class KeShiAct extends ActionSupport {
	private SnbService snbService;

	private String yiyuan_mingcheng;
	private String yiyuan_xiangqing;
	private int fk_yiyuan_id;
	
	public String query() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();// 获取request对象
		HttpSession session = request.getSession();
		String yyid=request.getParameter("yyid");
		if(yyid==null||"".equals(yyid)){
			yyid=(String)session.getAttribute("yyid_use");//医院ID
		}else{
			session.setAttribute("yyid_use", yyid);
		}
		
		
		String mingcheng=request.getParameter("mingcheng");
		String nowpage = request.getParameter("nowpage");
		Map paraMap = new HashMap();
		paraMap.put("mingcheng", mingcheng);
		paraMap.put("yyid", yyid);
		session.setAttribute("yyid_use", yyid);
		List l = new ArrayList();
		List<Keshi> ksList = new ArrayList<Keshi>();// 医院列表
		Map mdp = snbService.queryKeshi(paraMap);
		PageList<Keshi> pl = new PageList<Keshi>();
		l = (List) mdp.get("list");
		pl.setNumber(l.size());
		pl.setSumPage((l.size() + 10 - 1) / 10);
		pl.setNowpage(1);
		if (nowpage != null && !nowpage.equals(" ")) {
			int now = Integer.parseInt(nowpage);
			if (now < 1) {
				now = 1;
			}else if(now>pl.getSumPage()){
				now=pl.getSumPage();
			}
			pl.setNowpage(now);
		}
		for (int i = 0; i < 10; i++) {// 将获取到的list重新压入有范型的list里
			if (i + (pl.getNowpage() - 1) * 10 < l.size()) {
				Keshi keshi = new Keshi();
				keshi = (Keshi) l.get(i + (pl.getNowpage() - 1) * 10);
				ksList.add(keshi);
				pl.setPlist(ksList);
			} else {
				break;
			}
		}
		
		Map mp=snbService.queryYiYuan(paraMap);
		List list=(List)mp.get("list");
		session.setAttribute("yiyuan_use", (YiYuan)list.get(0));
		

		request.setAttribute("para", paraMap);
		session.setAttribute("ks", pl);// 放入session中供前台获取
		return "success_query";
	}
	
	public String save() throws Exception {
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		Keshi keshi = new Keshi();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		
		String xiangqing=request.getParameter("keshi_xiangqing");
		String mingcheng=request.getParameter("keshi_mingcheng");
		String yyid=(String)session.getAttribute("yyid_use");
		if(yyid!=null&&!"".equals(yyid)&&!"null".equals(yyid)){
			int id=Integer.parseInt(yyid);
			keshi.setFk_yiyuan_id(id);
		}
		keshi.setKeshi_biaozhiwei(1);
		keshi.setKeshi_gengxin_shijian(d);
		keshi.setKeshi_mingcheng(mingcheng);
		keshi.setKeshi_xiangqing(xiangqing);
		keshi.setKeshi_zhidingwei(0);
		paraMap.put("keshi", keshi);
		snbService.savekeshi(paraMap);
		
		
		int user_id=(Integer)session.getAttribute("user_id");//写入日志
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("keshi");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_save";
	}
	
	public String update() throws Exception {
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String xiangqing=request.getParameter("keshi_xiangqing");
		String mingcheng=request.getParameter("keshi_mingcheng");
		PageList<Keshi> b=(PageList)session.getAttribute("ks");
		int a=(Integer)session.getAttribute("whe");
		String yyid=(String)session.getAttribute("yyid");
		Keshi keshi=b.getPlist().get(a-1);
		if(yyid!=null&&!"".equals(yyid)){
			int id=Integer.parseInt(yyid);
			keshi.setFk_yiyuan_id(id);
		}
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		keshi.setKeshi_gengxin_shijian(d);
		keshi.setKeshi_xiangqing(xiangqing);
		keshi.setKeshi_mingcheng(mingcheng);
		paraMap.put("keshi", keshi);
		snbService.updatekeshi(paraMap);
		
		int user_id=(Integer)session.getAttribute("user_id");//写入日志
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("keshi");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		return "success_update";
	}
	public String delete() throws Exception {
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		
		String i=request.getParameter("i");
		int whe=Integer.parseInt(i);
		PageList<Keshi> b=(PageList)session.getAttribute("ks");
		Keshi keshi=b.getPlist().get(whe-1);
		
		keshi.setKeshi_biaozhiwei(0);
		
		paraMap.put("keshi", keshi);
		snbService.updatekeshi(paraMap);
		
		
		int user_id=(Integer)session.getAttribute("user_id");//写入日志
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("keshi");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		
		return "success_delete";
	}
	
	
	
	
	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}


	public String getYiyuan_mingcheng() {
		return yiyuan_mingcheng;
	}

	public void setYiyuan_mingcheng(String yiyuan_mingcheng) {
		this.yiyuan_mingcheng = yiyuan_mingcheng;
	}

	public String getYiyuan_xiangqing() {
		return yiyuan_xiangqing;
	}

	public void setYiyuan_xiangqing(String yiyuan_xiangqing) {
		this.yiyuan_xiangqing = yiyuan_xiangqing;
	}
	public int getFk_yiyuan_id() {
		return fk_yiyuan_id;
	}

	public void setFk_yiyuan_id(int fk_yiyuan_id) {
		this.fk_yiyuan_id = fk_yiyuan_id;
	}

}