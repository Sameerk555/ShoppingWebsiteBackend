package com.ecom.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class OrderItem {
	//feild
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int orderItemId;
	@OneToOne
	private Product product;
	private double totalProductprice;
	@ManyToOne
	private Order order;
	private int productQuantity;
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItem(int orderItemId, Product product, double totalProductprice, Order order, int productQuantity) {
		super();
		this.orderItemId = orderItemId;
		this.product = product;
		this.totalProductprice = totalProductprice;
		this.order = order;
		this.productQuantity = productQuantity;
	}
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getTotalProductprice() {
		return totalProductprice;
	}
	public void setTotalProductprice(double totalProductprice) {
		this.totalProductprice = totalProductprice;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	

}
