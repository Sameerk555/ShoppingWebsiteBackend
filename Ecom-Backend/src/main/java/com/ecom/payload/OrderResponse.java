package com.ecom.payload;

import java.util.List;

public class OrderResponse {
	
	private int pageSize;
	private int pageNo;
	private int totalPage;
	private Long totalElement;
	private List<OrderDao>content;
	private boolean isLastPage;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public Long getTotalElement() {
		return totalElement;
	}
	public void setTotalElement(Long totalElement) {
		this.totalElement = totalElement;
	}
	public List<OrderDao> getContent() {
		return content;
	}
	public void setContent(List<OrderDao> content) {
		this.content = content;
	}
	public boolean isLastPage() {
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	
	
}
