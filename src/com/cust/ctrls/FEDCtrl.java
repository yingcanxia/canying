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

import com.cust.easyutil.MD5;
import com.cust.easyutil.PageList;
import com.cust.easyutil.UUIDTool;
import com.cust.services.FEDService;
import com.cust.util.PageData;

@Controller
@RequestMapping(value = "/FED")
public class FEDCtrl {
	@Resource(name = "FEDS")
	private FEDService feds;

	@RequestMapping("/login") // 登录
	public ModelAndView fedlogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData(request);
		HttpSession session = request.getSession();
		try {
			Map map = feds.excutlogin(pd);
			Map mbp=feds.excutSelectUnreadMessage(pd);
			Map mcp=feds.excutSelectUntreatedAccident(pd);
			List<PageData> list = (List<PageData>) map.get("list");
			List<PageData> messagelist=(List<PageData>) mbp.get("list");
			List<PageData> accdientlist=(List<PageData>) mcp.get("list");
			String password = (String) pd.get("password");
			password = MD5.MD5(password);
			if (map.get("loginResult").equals(1) && list.get(0).get("passwd").equals(password)) {
				session.setAttribute("pduser", list.get(0));
				session.setAttribute("unread", messagelist);
				session.setAttribute("untreated", accdientlist);
				mv.setViewName("redirect:/loginsuccess.jsp");
			} else {
				mv.setViewName("redirect:/index.html");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mv;
	}
	@RequestMapping("/logout") // 登出
	public ModelAndView fedlogout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		session.invalidate();
		mv.setViewName("redirect:/index.html");
		return mv;
	}
	@RequestMapping("/updatepwd") 
	public ModelAndView updatepwd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		PageData pd = new PageData(request);
		String textpwd=(String)pd.get("oldpwd");
		textpwd = MD5.MD5(textpwd);
		PageData user=(PageData)session.getAttribute("pduser");
		String userpwd=(String) user.get("passwd");
		if(userpwd.equals(textpwd)){
			String newpwd=(String)pd.get("newpwd");
			newpwd = MD5.MD5(newpwd);
			user.put("newpwd", newpwd);
			try {
				feds.excutUpdatePwd(user);
				mv.setViewName("redirect:/map.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			mv.setViewName("redirect:/xiugaimima.jsp");
		}
		return mv;
	}
	@RequestMapping("/register") // FES注册
	public ModelAndView fedRegister(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData(request);
		pd.put("id", UUIDTool.getUUID());
		String password = (String) pd.get("passwd");
		password = MD5.MD5(password);
		pd.put("passwd", password);
		try {
			Map map = feds.excutRegister(pd);
			mv.setViewName("redirect:/loginsuccess.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mv;
	}

	

	

	

	

	

	

	

	
	
	public void setFeds(FEDService feds) {
		this.feds = feds;
	}

}
