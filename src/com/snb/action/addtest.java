package com.snb.action;


import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.snb.hbm.orm.Cuncuntong_cun;
import com.snb.services.impl.SnbService;

public class addtest extends ActionSupport{
	private int fk_zhen_id;//
	private String cun_mingcheng;//村的名称
	private String cun_miaoshu;//村的描述
	private int fk_user_id;//信息维护员
	private  SnbService snbService;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map paraMap=new HashMap();
		Cuncuntong_cun cc=new Cuncuntong_cun();
		cc.setFk_zhen_id(fk_zhen_id);
		cc.setCun_mingcheng(cun_mingcheng);
		cc.setFk_user_id(fk_user_id);
		paraMap.put("cctc", cc);
		snbService.savecun(paraMap);
		return SUCCESS;
	}

	public void setSnbService(SnbService snbService) {
		this.snbService = snbService;
	}

	public int getFk_zhen_id() {
		return fk_zhen_id;
	}

	public void setFk_zhen_id(int fk_zhen_id) {
		this.fk_zhen_id = fk_zhen_id;
	}

	public String getCun_mingcheng() {
		return cun_mingcheng;
	}

	public void setCun_mingcheng(String cun_mingcheng) {
		this.cun_mingcheng = cun_mingcheng;
	}

	public String getCun_miaoshu() {
		return cun_miaoshu;
	}

	public void setCun_miaoshu(String cun_miaoshu) {
		this.cun_miaoshu = cun_miaoshu;
	}

	public int getFk_user_id() {
		return fk_user_id;
	}

	public void setFk_user_id(int fk_user_id) {
		this.fk_user_id = fk_user_id;
	}
	
	
}
