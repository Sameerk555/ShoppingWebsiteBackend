package com.ecom.payload;

import java.util.List;

public class ProductResponse {
	private List<ProductDao>content;
	private int pageNo;
	private int pageSize;
	private int totalPages;
	private boolean lastPage;
	public List<ProductDao> getContent() {
		return content;
	}
	public void setContent(List<ProductDao> content) {
		this.content = content;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean getLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	

}
