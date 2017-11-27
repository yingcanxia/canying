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
import com.snb.hbm.orm.ZhuanJia;
import com.snb.services.impl.SnbService;

public class ZhuanJiaAct extends ActionSupport{
	private  SnbService snbService;

	private String zhuanjia_mingcheng;
	private String zhuanjia_lingyu;
	private String zhuanjia_zhiban_shijian;
	private String zhuanjia_zhicheng;
	
	public String query() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();// 获取request对象
		HttpSession session = request.getSession();
		String ksid=request.getParameter("ksid");
		if(ksid==null||"".equals(ksid)){
			ksid=(String)session.getAttribute("ksid_use");//医院ID
		}else{
			session.setAttribute("ksid_use", ksid);
		}
		
		String nowpage = request.getParameter("nowpage");
		Map paraMap = new HashMap();
		paraMap.put("ksid", ksid);
		List l = new ArrayList();
		List<ZhuanJia> zjList = new ArrayList<ZhuanJia>();// 医院列表
		Map mdp = snbService.queryZhuanjia(paraMap);
		PageList<ZhuanJia> pl = new PageList<ZhuanJia>();
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
				ZhuanJia zj = new ZhuanJia();
				zj = (ZhuanJia) l.get(i + (pl.getNowpage() - 1) * 10);
				zjList.add(zj);
				pl.setPlist(zjList);
			} else {
				break;
			}
		}
	
		
		Map mp=snbService.queryKeshi(paraMap);
		List list=(List)mp.get("list");
		session.setAttribute("keshi_use", (Keshi)list.get(0));
		

		request.setAttribute("para", paraMap);
		session.setAttribute("zj", pl);// 放入session中供前台获取
		return "success_query";
	}
	
	public String save() throws Exception {
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String ksid_use=(String)session.getAttribute("ksid_use");
		int ksid=0;
		if(ksid_use!=null && !"".equals(ksid_use) && !"null".equals(ksid_use)){
			ksid=Integer.parseInt(ksid_use);
		}
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		ZhuanJia zhuanjia=new ZhuanJia();
		
		zhuanjia.setZhuanjia_zhicheng(zhuanjia_zhicheng);
		zhuanjia.setZhuanjia_zhidingwei(0);
		zhuanjia.setZhuanjia_biaozhiwei(1);
		zhuanjia.setZhuanjia_gengxin_shijian(d);
		zhuanjia.setZhuanjia_mingcheng(zhuanjia_mingcheng);
		zhuanjia.setFk_keshi_id(ksid);
		zhuanjia.setZhuanjia_lingyu(zhuanjia_lingyu);
		zhuanjia.setZhuanjia_zhiban_shijian(zhuanjia_zhiban_shijian);
		paraMap.put("zhuanjia", zhuanjia);
		snbService.savezhuanjia(paraMap);
		
		int user_id=(Integer)session.getAttribute("user_id");//写入日志
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("zhuanjia");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
	
		return "success_save";
	}
//	
	public String update() throws Exception {
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		PageList<ZhuanJia> b=(PageList)session.getAttribute("zj");
		int a=(Integer)session.getAttribute("whe");
		ZhuanJia zhuanjia=b.getPlist().get(a-1);

		zhuanjia.setZhuanjia_gengxin_shijian(d);
		
		zhuanjia.setZhuanjia_zhicheng(zhuanjia_zhicheng);
		zhuanjia.setZhuanjia_mingcheng(zhuanjia_mingcheng);
		zhuanjia.setZhuanjia_lingyu(zhuanjia_lingyu);
		zhuanjia.setZhuanjia_zhiban_shijian(zhuanjia_zhiban_shijian);
		paraMap.put("zhuanjia", zhuanjia);
		snbService.updatezhuanjia(paraMap);
		
		int user_id=(Integer)session.getAttribute("user_id");//写入日志
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("zhuanjia");
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
		PageList<ZhuanJia> b=(PageList)session.getAttribute("zj");
		ZhuanJia zhuanjia=b.getPlist().get(whe-1);
		
		zhuanjia.setZhuanjia_biaozhiwei(0);
		zhuanjia.setZhuanjia_gengxin_shijian(d);
		paraMap.put("zhuanjia", zhuanjia);
		snbService.updatezhuanjia(paraMap);
		
		
		int user_id=(Integer)session.getAttribute("user_id");//写入日志
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("zhuanjia");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		
		return "success_delete";
	}
	
	
	
	
	public String getZhuanjia_mingcheng() {
		return zhuanjia_mingcheng;
	}

	public void setZhuanjia_mingcheng(String zhuanjia_mingcheng) {
		this.zhuanjia_mingcheng = zhuanjia_mingcheng;
	}

	public String getZhuanjia_lingyu() {
		return zhuanjia_lingyu;
	}

	public void setZhuanjia_lingyu(String zhuanjia_lingyu) {
		this.zhuanjia_lingyu = zhuanjia_lingyu;
	}

	public String getZhuanjia_zhiban_shijian() {
		return zhuanjia_zhiban_shijian;
	}

	public void setZhuanjia_zhiban_shijian(String zhuanjia_zhiban_shijian) {
		this.zhuanjia_zhiban_shijian = zhuanjia_zhiban_shijian;
	}

	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}

	public String getZhuanjia_zhicheng() {
		return zhuanjia_zhicheng;
	}

	public void setZhuanjia_zhicheng(String zhuanjia_zhicheng) {
		this.zhuanjia_zhicheng = zhuanjia_zhicheng;
	}

	
}
