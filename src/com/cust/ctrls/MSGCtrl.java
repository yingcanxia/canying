package com.cust.ctrls;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cust.services.FEDService;
import com.cust.util.PageData;

@Controller
@RequestMapping(value = "/MSG")
public class MSGCtrl {
	@Resource(name = "FEDS")
	private FEDService feds;
	@RequestMapping("/messagepush") // 消息推送
	public ModelAndView messagepush(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		PageData pd = new PageData(request);
		try {
			Map map = feds.excutSelectAllOrganization(pd);
			Map mbp=feds.excutSelectAllRp(pd);
			Map mcp=feds.excutSelectAllIs(pd);
			List<PageData> list = (List<PageData>) map.get("list");
			List<PageData> rplist = (List<PageData>) mbp.get("list");
			List<PageData> islist = (List<PageData>) mcp.get("list");
			session.setAttribute("oglist2", list);
			session.setAttribute("rplist", rplist);
			session.setAttribute("islist", islist);
			
			mv.setViewName("redirect:/Messagepush.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	
}
