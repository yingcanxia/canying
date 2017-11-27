package com.cust.ctrls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cust.easyutil.MD5;
import com.cust.easyutil.PageList;
import com.cust.easyutil.UUIDTool;
import com.cust.services.FEDService;
import com.cust.util.PageData;

@Controller
@RequestMapping(value = "/RP")
public class RPCtrl {
	@Resource(name = "FEDS")
	private FEDService feds;

	@RequestMapping("/register_rp") // rp注册
	public ModelAndView fedRegisterRp(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		PageData pd = new PageData(request);
		String ogid = (String) session.getAttribute("ogid");
		String password = "123456";
		password = MD5.MD5(password);
		pd.put("passwd", password);
		pd.put("ogid", ogid);
		pd.put("id", UUIDTool.getUUID());
		try {
			feds.excutRegisterRp(pd);
			mv.setViewName("redirect:/RP/selectrp.do?nowpage=1&&ogid=" + ogid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping("/selectrp.do") // 查询所有rp
	public ModelAndView fedSelectrp(HttpServletRequest request) {
		/* 此处应有分页 */
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		PageData pd = new PageData(request);
		PageList<PageData> pl = new PageList<PageData>();
		String ogid = request.getParameter("ogid");
		String nowpage = request.getParameter("nowpage");
		if (ogid == null || ogid.equals("null")) {
			ogid = (String) session.getAttribute("ogid");
			pd.put("ogid", ogid);
		} else if (ogid != null) {
			pd.put("ogid", ogid);
			session.setAttribute("ogid", ogid);
		}
		try {
			Map map = feds.excutSelectAllRp(pd);
			List<PageData> list = (List<PageData>) map.get("list");
			List<PageData> list2 = new ArrayList<PageData>();
			pl.setNowpage(Integer.parseInt(nowpage));
			if(list!=null&&list.size()!=0){
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
			}else{
				pl.setNumber(0);
				pl.setSumPage(0);
			}
			pl.setPlist(list2);
			session.setAttribute("rplist", pl);
			session.setAttribute("rptiaojian", pd);
			mv.setViewName("redirect:/tablRp.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mv;
	}
	@RequestMapping("/update_rp")
	public ModelAndView update_rp(HttpServletRequest request) {
		/* 此处应有分页相应的修改 */
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData(request);
		String password = "123456";
		String ogid = (String) pd.get("ogid");
		password = MD5.MD5(password);
		pd.put("passwd", password);
		try {
			feds.excutUpdateRp(pd);
			mv.setViewName("redirect:/RP/selectrp.do?nowpage=1&&ogid=" + ogid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping("/delete_rp")
	public ModelAndView delete_rp(HttpServletRequest request) {
		/* 此处应有分页相应的修改 */
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String rpid = request.getParameter("rpid");
		PageData pd = new PageData(request);
		String ogid = (String) session.getAttribute("ogid");
		pd.put("id", rpid);
		try {
			feds.excutdeleteRp(pd);
			mv.setViewName("redirect:/RP/selectrp.do?nowpage=1&&ogid=" + ogid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
}
