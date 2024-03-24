package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.Cart;
import com.ecom.model.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	public Optional<Cart>findByUser(User user);
//	public Optional<Cart> findByUserAndcartId(int cartId);

}
