package com.ecom.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartItemDao {
	
	private int cartItemId;
	private int quantity;
	private double totalprice;
	@JsonIgnore
	private CartDao cart;
	private ProductDao product;
	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public CartDao getCart() {
		return cart;
	}
	public void setCart(CartDao cart) {
		this.cart = cart;
	}
	public ProductDao getProduct() {
		return product;
	}
	public void setProduct(ProductDao product) {
		this.product = product;
	}
	
}
