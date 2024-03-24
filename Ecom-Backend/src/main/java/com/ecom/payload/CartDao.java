package com.ecom.payload;

import java.util.HashSet;
import java.util.Set;

public class CartDao {
	private int cartId;
	private Set<CartItemDao> items= new HashSet<>();
	private UserDao user;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public Set<CartItemDao> getItems() {
		return items;
	}
	public void setItems(Set<CartItemDao> items) {
		this.items = items;
	}
	public UserDao getUser() {
		return user;
	}
	public void setUser(UserDao user) {
		this.user = user;
	}
	
	
}
