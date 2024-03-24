package com.ecom.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.CartDao;
import com.ecom.payload.ItemRequest;
import com.ecom.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@PostMapping("/")
	public ResponseEntity<CartDao>addToCart(@RequestBody ItemRequest itemRequest, Principal principal){
		String email= principal.getName();
		System.out.println(email);
		CartDao addItem=this.cartService.addItem(itemRequest, principal.getName());
		return new ResponseEntity<CartDao>(addItem, HttpStatus.OK);
		
	}
	
	//create method for getting cart
	@GetMapping("/")
	public ResponseEntity<CartDao>getAllCart(Principal principal){
		CartDao getcartAll = this.cartService.getCartAll(principal.getName());
		 return new ResponseEntity<CartDao>(getcartAll, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{cartId}")
	public ResponseEntity<CartDao>getCartById(@PathVariable int cartId){
		System.out.println(cartId);
		CartDao cartById = this.cartService.getCartById(cartId);
		return new ResponseEntity<CartDao>(cartById, HttpStatus.OK);
		
	}
	@DeleteMapping("/{pid}")
	public ResponseEntity<CartDao>deleteCartItemFromCart(@PathVariable int pid, Principal p){
		
		CartDao remove = this.cartService.removeCartItem(p.getName(), pid);
		
		return new ResponseEntity<CartDao>(remove, HttpStatus.UPGRADE_REQUIRED);
		
	}
}
