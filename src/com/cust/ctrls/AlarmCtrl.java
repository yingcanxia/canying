package com.cust.ctrls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cust.easyutil.PageList;
import com.cust.services.FEDService;
import com.cust.util.PageData;

@Controller
@RequestMapping(value = "/AL")
public class AlarmCtrl {
	@Resource(name = "FEDS")
	private FEDService feds;
	@RequestMapping("/untreatedAlarmt") // 警情管理
	public ModelAndView Alarmmanagement(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String nowpage = request.getParameter("nowpage");
		PageData pd = new PageData(request);
		HttpSession session = request.getSession();
		PageList<PageData> pl = new PageList<PageData>();
		try {
			Map map = feds.excutAlarmManagement(pd);
			List<PageData> list = (List<PageData>) map.get("list");
			List<PageData> list2 = new ArrayList<PageData>();
			pl.setNowpage(Integer.parseInt(nowpage));
			if(list!=null){
				pl.setNumber(list.size());
				pl.setSumPage((list.size() + 15 - 1) / 15);
				for (int i = 0; i < 15; i++) {// pagelist里的list
					if (i + (pl.getNowpage() - 1) * 15 < list.size()) {
						PageData zx = new PageData();
						zx = (PageData) list.get(i + (pl.getNowpage() - 1) * 15);
						list2.add(zx);
					} else {
						break;
					}
				}
			}else {
				pl.setNumber(0);
				pl.setSumPage(0);
			}
			pl.setPlist(list2);
			session.setAttribute("accident_log", pl);
			mv.setViewName("redirect:/Alarmmanagement.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}
	@RequestMapping("/treatedAlarmt") // 警情管理
	public ModelAndView treatedAlarmt(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String nowpage = request.getParameter("nowpage");
		PageData pd = new PageData(request);
		HttpSession session = request.getSession();
		PageList<PageData> pl = new PageList<PageData>();
		try {
			Map map = feds.excuttreatedAlarmManagement(pd);
			List<PageData> list = (List<PageData>) map.get("list");
			List<PageData> list2 = new ArrayList<PageData>();
			pl.setNowpage(Integer.parseInt(nowpage));
			if(list!=null){
				pl.setNumber(list.size());
				pl.setSumPage((list.size() + 15 - 1) / 15);
				for (int i = 0; i < 15; i++) {// pagelist里的list
					if (i + (pl.getNowpage() - 1) * 15 < list.size()) {
						PageData zx = new PageData();
						zx = (PageData) list.get(i + (pl.getNowpage() - 1) * 15);
						list2.add(zx);
					} else {
						break;
					}
				}
			}else {
				pl.setNumber(0);
				pl.setSumPage(0);
			}
			pl.setPlist(list2);
			session.setAttribute("accident_log", pl);
			mv.setViewName("redirect:/Alarmmanagement.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}
	@RequestMapping("/deleteAlarm") // 警情管理
	public ModelAndView deleteAlarmt(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String accidentid = request.getParameter("accidentid");
		PageData pd = new PageData(request);
		pd.put("id", accidentid);
		try {
			Timestamp d = new Timestamp(System.currentTimeMillis()); 
			pd.put("internal_time", d);
			feds.excutDeleteAlarm(pd);
			
			mv.setViewName("redirect:/AL/untreatedAlarmt?nowpage=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
}
