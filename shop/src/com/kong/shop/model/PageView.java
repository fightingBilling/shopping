package com.kong.shop.model;

public class PageView<T> {
	/** result for scroll data **/
	private QueryResult<T> qr;
	/** Current page in view **/
	private int currentPage = 1;
	/** Total page of results **/
	private long totalPage;
	/** max result per page **/
	private int maxResult = 12;
	/** index of page including start page and end page **/
	private PageIndex pageIndex;
	
	public PageView() {
		this.currentPage = 1;
	}

	public PageView(int currentPage) {
		this.currentPage = currentPage;
	}

	public PageView(int currentPage, int maxResult) {
		this.currentPage = currentPage;
		this.maxResult = maxResult;
	}

	public QueryResult<T> getQr() {
		return qr;
	}

	public void setQr(QueryResult<T> qr) {
		this.qr = qr;
		setTotalPage(qr.getTotalRecord() % this.maxResult == 0 ? qr
				.getTotalRecord() / this.maxResult : qr.getTotalRecord()
				/ this.maxResult + 1);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
		this.pageIndex = PageIndex.getPageIndex(maxResult, currentPage, totalPage);
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public PageIndex getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(PageIndex pageIndex) {
		this.pageIndex = pageIndex;
	}

}
