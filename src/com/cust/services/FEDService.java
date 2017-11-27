package com.cust.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cust.dao.DaoSupport;

import com.cust.util.PageData;

@Service("FEDS")
public class FEDService {
	@Resource(name = "daoSupport")
	private DaoSupport daoSupport;

	public Map excutlogin(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List<PageData> list = (List<PageData>) daoSupport.findForList("fed.login", pd);
		if (list.size() != 0) {
			returnmap.put("list", list);
			returnmap.put("loginResult", 1);
		} else {
			returnmap.put("loginResult", -1);
		}
		return returnmap;
	}
	public Map excutSelectUnreadMessage(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List<PageData> list = (List<PageData>) daoSupport.findForList("log.userUnread_messages", pd);
		returnmap.put("list", list);
		return returnmap;
	}
	public Map excutSelectUntreatedAccident(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List<PageData> list = (List<PageData>) daoSupport.findForList("log.Untreated_accident", pd);
		returnmap.put("list", list);
		return returnmap;
	}
	public Map excutRegister(PageData pd) throws Exception {// 注册FED
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("fed.insert_fed", pd);
		if (list == null || list.isEmpty()) {
			returnmap.put("result", 1);
		} else {
			PageData resultPD = (PageData) list.get(0);
			returnmap.put("result", -1);
		}
		return returnmap;

	}

	public Map excutRegisterRp(PageData pd) throws Exception {// 注册rp
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("fed.insert_rp", pd);
		if (list == null || list.isEmpty()) {
			returnmap.put("result", 1);
		} else {
			PageData resultPD = (PageData) list.get(0);
			returnmap.put("result", -1);
		}
		return returnmap;

	}

	public Map excutUpdateRp(PageData pd) throws Exception {// 更新rp
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("fed.update_rp", pd);
		if (list == null || list.isEmpty()) {
			returnmap.put("result", 1);
		} else {
			PageData resultPD = (PageData) list.get(0);
			returnmap.put("result", -1);
		}
		return returnmap;

	}

	public Map excutdeleteRp(PageData pd) throws Exception {// 更新rp
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("fed.delete_rp", pd);
		returnmap.put("list", list);
		return returnmap;

	}

	public Map excutSelectAllRp(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("fed.findRp", pd);
		returnmap.put("list", list);
		return returnmap;
	}

	public Map excutSelectAllIs(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("fed.findIs", pd);
		returnmap.put("list", list);
		return returnmap;
	}

	public Map excutinsertOrganization(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("organization.organization_insert", pd);
		returnmap.put("list", list);
		return returnmap;
	}

	public Map excutupdateOrganization(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("organization.update_og", pd);
		returnmap.put("list", list);
		return returnmap;
	}

	public Map excutdeleteOrganization(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("organization.delete_og", pd);
		List list2 = (List) daoSupport.findForList("organization.rp_jielian", pd);
		returnmap.put("list", list);
		return returnmap;
	}

	public Map excutSelectAllOrganization(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("organization.all", pd);
		if (list.size() != 0) {
			returnmap.put("statue", false);
			returnmap.put("list", list);
		} else {
			returnmap.put("statue", true);
		}
		return returnmap;
	}

	public Map excutSelectOne(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		return returnmap;
	}

	public Map excutUpdateRole(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		return returnmap;
	}

	public Map excutUpdatePwd(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("fed.update_passwd", pd);
		
		return returnmap;
	}

	public Map excutAddAccident_log(Map pd) throws Exception {// 向Accident_log表中添加数据
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("log.insert_accident_log", pd);
		if (list == null || list.isEmpty()) {
			returnmap.put("result", 1);
		} else {
			PageData resultPD = (PageData) list.get(0);
			returnmap.put("result", -1);
		}
		return returnmap;
	}

	public Map excutAddMessage_log(Map pd) throws Exception {// 向Message_log表中添加数据
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("log.insert_message_log", pd);
		if (list == null || list.isEmpty()) {
			returnmap.put("result", 1);
		} else {
			PageData resultPD = (PageData) list.get(0);
			returnmap.put("result", -1);
		}
		return returnmap;
	}

	public Map excutAlarmManagement(PageData pd) throws Exception { // 找出accident_log未处理的警情
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("log.find_untreated_accident_log", pd);
		if (list.isEmpty() || list == null) {
			returnmap.put("result", -1);
		} else {
			returnmap.put("result", 1);
			returnmap.put("list", list);
		}
		return returnmap;
	}public Map excuttreatedAlarmManagement(PageData pd) throws Exception { // 找出accident_log已经被处理的
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("log.find_treated_accident_log", pd);
		if (list.isEmpty() || list == null) {
			returnmap.put("result", -1);
		} else {
			returnmap.put("result", 1);
			returnmap.put("list", list);
		}
		return returnmap;
	}
	public Map excutDeleteAlarm(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("log.delete_accident", pd);
		returnmap.put("list", list);
		return returnmap;
	}
	public Map findRpById(PageData pd) throws Exception { // 找出accident_log中的数据
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("fed.findRp", pd);
		returnmap.put("list", list);
		return returnmap;
	}
	public Map findIsById(PageData pd) throws Exception { // 找出accident_log中的数据
		Map returnmap = new HashMap();
		List list = (List) daoSupport.findForList("fed.findIs", pd);
		returnmap.put("list", list);
		return returnmap;
	}
	public Map excutChachongName(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List<PageData> list = (List<PageData>) daoSupport.findForList("fed.chachong_fed_name", pd);
		if (list.size() != 0) {
			returnmap.put("list", list);
			returnmap.put("loginResult", 1);
		} else {
			returnmap.put("loginResult", -1);
		}
		return returnmap;
	}
	public void setDaoSupport(DaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
}
