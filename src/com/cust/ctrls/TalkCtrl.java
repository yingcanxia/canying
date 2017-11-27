package com.cust.ctrls;

import java.util.HashMap;
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
@RequestMapping(value = "/TALK")
public class TalkCtrl {
	@Resource(name = "FEDS")
	private FEDService feds;
	@RequestMapping("/selectrp") // 查询所有rp
	public ModelAndView talkSelectrp(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		HttpSession session = request.getSession();
		PageData pd=new PageData(request);
		try {
			Map map=feds.findRpById(pd);
			List<PageData> list = (List<PageData>) map.get("list");
			PageData rp=list.get(0);
			Map receiver=new HashMap();
			receiver.put("receiver", rp);
			receiver.put("role", "rp");
			session.setAttribute("receivermap", receiver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("redirect:/talk.jsp");
		return mv;
	}
	@RequestMapping("/selectis") // 查询所有rp
	public ModelAndView talkSelectis(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		HttpSession session = request.getSession();
		PageData pd=new PageData(request);
		try {
			Map map=feds.findIsById(pd);
			List<PageData> list = (List<PageData>) map.get("list");
			PageData is=list.get(0);
			Map receiver=new HashMap();
			receiver.put("receiver", is);
			receiver.put("role", "is");
			session.setAttribute("receivermap", receiver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("redirect:/talk.jsp");
		return mv;
	}
}
