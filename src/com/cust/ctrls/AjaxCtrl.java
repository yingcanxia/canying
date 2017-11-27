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

import com.cust.services.FEDService;
import com.cust.services.MSGServer;
import com.cust.util.PageData;

@Controller
@RequestMapping(value="/AJAX")
public class AjaxCtrl {
	@Resource(name="FEDS")
	private FEDService feds;
	@Resource(name="MESSAGE")
	private MSGServer msg;
	@ResponseBody
	@RequestMapping("/selectrp")//查询所有rp
	public List<PageData> fedSelectrp(HttpServletRequest request){
		PageData pd =new PageData(request);
		String ogid=request.getParameter("ogid");
		List<PageData>list = new ArrayList<PageData>();
		pd.put("ogid", ogid);
		try {
			Map map=feds.excutSelectAllRp(pd);
			list=(List<PageData>) map.get("list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/selectis")//查询所有is
	public List<PageData> fedSelectis(HttpServletRequest request){
		PageData pd =new PageData(request);
		String ogid=request.getParameter("ogid");
		List<PageData>list = new ArrayList<PageData>();
		pd.put("ogid", ogid);
		try {
			Map map=feds.excutSelectAllIs(pd);
			list=(List<PageData>) map.get("list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/selectog")//查询所有og
	public List<PageData> fedSelectog(HttpServletRequest request){
		PageData pd =new PageData(request);
		List<PageData>list = new ArrayList<PageData>();
		try {
			Map map=feds.excutSelectAllOrganization(pd);
			list=(List<PageData>) map.get("list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/chachong_fed_phone")
	public String chachong_fed_phone(HttpServletRequest request){
		String resule=null;
		PageData pd =new PageData(request);
		try {
			Map map=feds.excutlogin(pd);
			int loginResult=(Integer) map.get("loginResult");
			if(loginResult==-1){
				resule="1";
			}else{
				resule="0";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resule;
	}
	@ResponseBody
	@RequestMapping("/chachong_fed_name")
	public String chachong_fed_name(HttpServletRequest request){
		String resule=null;
		PageData pd =new PageData(request);
		try {
			Map map=feds.excutChachongName(pd);
			int loginResult=(Integer) map.get("loginResult");
			if(loginResult==-1){
				resule="1";
			}else{
				resule="0";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resule;
	}
	@ResponseBody
	@RequestMapping("/updateMsgStatue")
	public String updateMessage(HttpServletRequest request){
		String resule=null;
		PageData pd =new PageData(request);
		try {
			msg.excutUpdateMessageStatue(pd);
			resule="1";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resule;
	}
	@ResponseBody
	@RequestMapping("/historymessage") // 历史消息
	public List historyMessage(HttpServletRequest request) {
		List<PageData>list = new ArrayList<PageData>();
		PageData pd =new PageData(request);
		try {
			Map map=msg.excutSelectHistoryMessage(pd);
			list=(List<PageData>) map.get("list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/historypush") // 历史推送推送
	public List historypush(HttpServletRequest request) {
		List<PageData>list = new ArrayList<PageData>();
		PageData pd =new PageData(request);
		try {
			Map map=msg.excutSelectMessagePush(pd);
			list=(List<PageData>) map.get("list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/updateStatue") // 历史推送推送
	public void updateMessageStatue(HttpServletRequest request) {
		PageData pd =new PageData(request);
		try {
			msg.excutUpdateMessageStatue(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
