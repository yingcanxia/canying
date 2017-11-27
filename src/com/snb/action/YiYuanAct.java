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
import com.snb.hbm.orm.Rizhi;
import com.snb.hbm.orm.YiYuan;
import com.snb.services.impl.SnbService;

public class YiYuanAct extends ActionSupport {
	private SnbService snbService;

	private String yiyuan_dizhi;
	private String yiyuan_mingcheng;
	private String yiyuan_xiangqing;
	
	public String query() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();// 获取request对象
		// String id=(String)request.getParameter("zhenid");
		HttpSession session = request.getSession();
		String mingcheng=request.getParameter("mingcheng");
		String nowpage = request.getParameter("nowpage");
		Map paraMap = new HashMap();
		paraMap.put("mingcheng", mingcheng);
		List l = new ArrayList();
		List<YiYuan> yyList = new ArrayList<YiYuan>();// 医院列表
		Map mdp = snbService.queryYiYuan(paraMap);
		PageList<YiYuan> pl = new PageList<YiYuan>();
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
				YiYuan yiyuan = new YiYuan();
				yiyuan = (YiYuan) l.get(i + (pl.getNowpage() - 1) * 10);
				yyList.add(yiyuan);
				pl.setPlist(yyList);
			} else {
				break;
			}
		}
		request.setAttribute("para", paraMap);
		session.setAttribute("yy", pl);// 放入session中供前台获取
		return "success_query";
	}
	
	public String save() throws Exception {
		Map paraMap=new HashMap();
		Map riz=new HashMap();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		
		String dizhi=request.getParameter("yiyuan_dizhi");
		String mingcheng=request.getParameter("yiyuan_mingcheng");
		String xiangqing=request.getParameter("yiyuan_xiangqing");
		
		YiYuan yiyuan=new YiYuan();
		yiyuan.setYiyuan_biaozhiwei(1);
		yiyuan.setYiyuan_dizhi(dizhi);
		yiyuan.setYiyuan_gengxin_shijian(d);
		yiyuan.setYiyuan_mingcheng(mingcheng);
		yiyuan.setYiyuan_xiangqing(xiangqing);
		yiyuan.setYiyuan_zhidingwei(0);
		
		paraMap.put("yiyuan", yiyuan);
		snbService.saveyiyuan(paraMap);
		
		
		int user_id=(Integer)session.getAttribute("user_id");//写入日志
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("yiyuan");
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
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		PageList<YiYuan> b=(PageList)session.getAttribute("yy");
		int a=(Integer)session.getAttribute("whe");
		YiYuan yiyuan=b.getPlist().get(a-1);
//		zhen.setZhen_mingcheng(zhen_mingcheng);
//		zhen.setZhen_miaoshu(zhen_miaoshu);
		yiyuan.setYiyuan_dizhi(yiyuan_dizhi);
		yiyuan.setYiyuan_gengxin_shijian(d);
		yiyuan.setYiyuan_mingcheng(yiyuan_mingcheng);
		yiyuan.setYiyuan_xiangqing(yiyuan_xiangqing);
		paraMap.put("yiyuan", yiyuan);
		snbService.updateyiyuan(paraMap);
		
		int user_id=(Integer)session.getAttribute("user_id");//写入日志
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("yiyuan");
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
		PageList<YiYuan> b=(PageList)session.getAttribute("yy");
		YiYuan yiyuan=b.getPlist().get(whe-1);
		
		yiyuan.setYiyuan_biaozhiwei(0);
		
		paraMap.put("yiyuan", yiyuan);
		snbService.updateyiyuan(paraMap);
		
		
		int user_id=(Integer)session.getAttribute("user_id");//写入日志
		Rizhi rz=new Rizhi();
		rz.setFk_user_id(user_id);
		rz.setRizhi_biaozhiwei(1);
		rz.setRizhi_duix_mingcheng("yiyuan");
		rz.setRizhi_shij(d);
		riz.put("rz", rz);
		snbService.saveRz(riz);
		
		return "success_delete";
	}
	
	
	
	
	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}

	public String getYiyuan_dizhi() {
		return yiyuan_dizhi;
	}

	public void setYiyuan_dizhi(String yiyuan_dizhi) {
		this.yiyuan_dizhi = yiyuan_dizhi;
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


}
