package com.snb.bean;

import java.util.List;

public class PageList<E> {
	private int number;//共几条信息
	private int sumPage;//一共几页
	private int nowpage;//当前页数
	private List<E> plist;//人员列表
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getSumPage() {
		return sumPage;
	}
	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}
	public int getNowpage() {
		return nowpage;
	}
	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}
	public List<E> getPlist(){
		return plist;
	}
	public void setPlist(List<E> plist) {
		this.plist = plist;
	}
}
