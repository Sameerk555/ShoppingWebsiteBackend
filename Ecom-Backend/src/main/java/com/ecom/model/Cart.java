package com.ecom.model;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cartId;
	@OneToMany(mappedBy="cart", cascade= CascadeType.ALL, orphanRemoval=true)
	private Set<CartItem> items= new HashSet<>();
	@OneToOne
	private User user;
	public Cart(int cartId, Set<CartItem> items) {
		super();
		this.cartId = cartId;
		this.items = items;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Set<CartItem> getItems() {
		return items;
	}
	public void setItems(Set<CartItem> items) {
		this.items = items;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
