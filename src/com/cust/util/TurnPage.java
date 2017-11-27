package com.cust.util;


/**
 */
public class TurnPage {

	/**
	 * ��ǰҳ��ļ�¼��
	 */
	private int rowInOnePage = 10;

	/**
	 * ���ݵ��ܹ���
	 */
	private long total;

	/**
	 * ��ǰҳ��
	 */
	private int pageNum = 1;

	/**
	 * �ܹ�ҳ��
	 */
	private int pageCount = 0;
	
	/**
	 * ִ�в�ѯʱ����ʵ�ļ�¼��
	 */
	private int startNum=0;
	/**
	 * 
	 */
	public TurnPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return Returns the pageCount.
	 */
	public int getPageCount() {
		long tmp = total/(new Long(rowInOnePage)).longValue();
		pageCount = (new Long(tmp)).intValue();
		if((total%rowInOnePage)>0)
			pageCount++;
		return pageCount;
	}
	/**
	 * @param pageCount The pageCount to set.
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	/**
	 * @return Returns the pageNum.
	 */
	public int getPageNum() {
		return pageNum;
	}
	/**
	 * @param pageNum The pageNum to set.
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	/**
	 * @return Returns the rowInOnePage.
	 */
	public int getRowInOnePage() {
		return rowInOnePage;
	}
	/**
	 * @param rowInOnePage The rowInOnePage to set.
	 */
	public void setRowInOnePage(int rowInOnePage) {
		this.rowInOnePage = rowInOnePage;
	}
	/**
	 * @return Returns the total.
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param total The total to set.
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	public int getStartNum() {
		startNum=(pageNum-1)*rowInOnePage;
		return startNum;
	}

}
