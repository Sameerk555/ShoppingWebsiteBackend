package com.ecom.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDao {
	
	private int orderId;
	private String orderStatus;
	private String paymentStatus;
	private Date orderDelivered;
	private double orderAmt;
	private String billingAddress;
	private UserDao user;
	private Set<OrderItemDao> orderItem= new HashSet<>();
	private Date orderCreateAt;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getOrderDelivered() {
		return orderDelivered;
	}
	public void setOrderDelivered(Date orderDelivered) {
		this.orderDelivered = orderDelivered;
	}
	public double getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(double orderAmt) {
		this.orderAmt = orderAmt;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public UserDao getUser() {
		return user;
	}
	public void setUser(UserDao user) {
		this.user = user;
	}
	public Set<OrderItemDao> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(Set<OrderItemDao> orderItem) {
		this.orderItem = orderItem;
	}
	public Date getOrderCreateAt() {
		return orderCreateAt;
	}
	public void setOrderCreateAt(Date orderCreateAt) {
		this.orderCreateAt = orderCreateAt;
	}
	
}
