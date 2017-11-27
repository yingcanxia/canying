package com.cust.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cust.dao.DaoSupport;
import com.cust.util.PageData;

@Service("MESSAGE")
public class MSGServer {
	@Resource(name = "daoSupport")
	private DaoSupport daoSupport;
	public Map excutSelectHistoryMessage(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List<PageData> list = (List<PageData>) daoSupport.findForList("msg.history", pd);
		returnmap.put("list", list);
		return returnmap;
	}
	public Map excutUpdateMessageStatue(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List<PageData> list = (List<PageData>) daoSupport.findForList("msg.update_statue", pd);
		return returnmap;
	}
	public Map excutSelectMessagePush(PageData pd) throws Exception {
		Map returnmap = new HashMap();
		List<PageData> list = (List<PageData>) daoSupport.findForList("msg.msgpush", pd);
		returnmap.put("list", list);
		return returnmap;
	}
	
}

