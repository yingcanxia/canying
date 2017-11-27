package com.cust.ctrls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cust.easyutil.PageList;
import com.cust.easyutil.UUIDTool;
import com.cust.services.FEDService;
import com.cust.util.PageData;

@Controller
@RequestMapping(value = "/OG")
public class OGCtrl {
	@Resource(name = "FEDS")
	private FEDService feds;
	
	@ResponseBody
	@RequestMapping("/chachong") // 查重
	public String chachongOrganization(HttpServletRequest request) {
		String result = null;
		PageData pd = new PageData(request);
		try {
			Map map = feds.excutSelectAllOrganization(pd);
			if ((Boolean) map.get("statue")) {
				result = "1";
			} else {
				result = "0";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("/SelectAllog") // 查询所有单位
	public ModelAndView fedSelectog(HttpServletRequest request) {
		/* 此处应有分页 */
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData(request);
		HttpSession session = request.getSession();
		String nowpage="1";
		if(request.getParameter("nowpage")!=null&&request.getParameter("nowpage").equals("")){
			nowpage= request.getParameter("nowpage");
		}
		PageList<PageData> pl = new PageList<PageData>();
		try {
			Map map = feds.excutSelectAllOrganization(pd);
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
			session.setAttribute("oglist", pl);
			session.setAttribute("tiaojian", pd);
			mv.setViewName("redirect:/table.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping("/SelectAllog2") // 查询所有单位
	public ModelAndView fedSelectog2(HttpServletRequest request) {
		/* 此处应有分页 */
		ModelAndView mv = new ModelAndView();
		String biaoshi=request.getParameter("biaoshi");
		PageData pd = new PageData(request);
		HttpSession session = request.getSession();
		try {
			Map map = feds.excutSelectAllOrganization(pd);
			Map mbp=feds.excutSelectAllRp(pd);
			Map mcp=feds.excutSelectAllIs(pd);
			Map mdp=feds.excutSelectUnreadMessage(pd);
			List<PageData> list = (List<PageData>) map.get("list");
			List<PageData> rplist = (List<PageData>) mbp.get("list");
			List<PageData> islist = (List<PageData>) mcp.get("list");
			List<PageData> messagelist = (List<PageData>) mdp.get("list");
			session.setAttribute("oglist2", list);
			session.setAttribute("rplist", rplist);
			session.setAttribute("islist", islist);
			session.setAttribute("islist", islist);
			session.setAttribute("unread", messagelist);
			if(biaoshi.equals("a")){
				mv.setViewName("redirect:/message.jsp");
			}else if(biaoshi.equals("b")){
				mv.setViewName("redirect:/Messagepush.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping("/insertOrganization") // 添加公司或者单位
	public ModelAndView insertOrganization(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String biaoshi = request.getParameter("biaoshi");
		HttpSession session = request.getSession();
		PageData pd = new PageData(request);
		String id = UUIDTool.getUUID();
		session.setAttribute("ogid", id);
		pd.put("id", id);
		String location = (String) pd.get("location");
		String x = location.split(",")[1];
		String y = location.split(",")[0];
		pd.put("x", x);
		pd.put("y", y);
		try {
			Map map = feds.excutinsertOrganization(pd);
			if (biaoshi.equals("x")) {
				mv.setViewName("redirect:/registerrp.jsp?biaoshi=a");
			} else if (biaoshi.equals("y")) {
				mv.setViewName("redirect:/OG/SelectAllog?nowpage=1");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping("/updateOrganization") // 修改公司或者单位
	public ModelAndView updateOrganization(HttpServletRequest request) {
		/* 此处应有分页相应的修改 */
		ModelAndView mv = new ModelAndView();
		String biaoshi = request.getParameter("biaoshi");
		HttpSession session = request.getSession();
		PageData pd = new PageData(request);
		String location = (String) pd.get("location");
		String x = location.split(",")[0];
		String y = location.split(",")[1];
		pd.put("x", x);
		pd.put("y", y);
		try {
			Map map = feds.excutupdateOrganization(pd);
			mv.setViewName("redirect:/OG/SelectAllog?nowpage=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping("/deleteog") // 删除公司或者单位
	public ModelAndView deleteog(HttpServletRequest request) {
		/* 此处应有分页相应的修改 */
		ModelAndView mv = new ModelAndView();
		String ogid = request.getParameter("ogid");
		HttpSession session = request.getSession();
		PageData pd = new PageData(request);
		pd.put("id", ogid);
		try {
			Map map = feds.excutdeleteOrganization(pd);
			mv.setViewName("redirect:/OG/SelectAllog?nowpage=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
}
