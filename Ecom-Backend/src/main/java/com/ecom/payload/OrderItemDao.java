package com.ecom.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderItemDao {
	private int orderItemId;
	private ProductDao product;
	private double totalProductprice;
	@JsonIgnore
	private OrderDao order;
	private int productQuantity;
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public ProductDao getProduct() {
		return product;
	}
	public void setProduct(ProductDao product) {
		this.product = product;
	}
	public double getTotalProductprice() {
		return totalProductprice;
	}
	public void setTotalProductprice(double totalProductprice) {
		this.totalProductprice = totalProductprice;
	}
	public OrderDao getOrder() {
		return order;
	}
	public void setOrder(OrderDao order) {
		this.order = order;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	
	

}
