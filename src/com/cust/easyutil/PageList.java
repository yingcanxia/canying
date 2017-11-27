package com.cust.easyutil;

import java.util.List;

public class PageList<E> {
	private int number;//��������Ϣ
	private int sumPage;//һ����ҳ
	private int nowpage;//��ǰҳ��
	private List<E> plist;//��Ա�б�
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
