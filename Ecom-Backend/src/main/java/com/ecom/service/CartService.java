package com.ecom.service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CartDao;
import com.ecom.payload.ItemRequest;
import com.ecom.repository.CartRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UserRepository;
import com.ecom.model.Cart;
import com.ecom.model.CartItem;
import com.ecom.model.Product;
import com.ecom.model.User;
@Service
public class CartService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private ModelMapper model;
	private Set<CartItem> items;
	public CartDao addItem(ItemRequest item, String username) {
		int productId=item.getProductId();
		int quantity= item.getQuantity();
		User user=this.userRepo.findByemail(username).orElseThrow(()->new ResourceNotFoundException("User not found"));
		System.out.println(user.getEmail()+" cart");
		Product product=this.productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));
		System.out.println(product.getProduct_name()+ " product name");
		//here we are checking product stock
		if(!product.isStock()) {
			new ResourceNotFoundException("product is out of stock");
		}
		//create cartItem with productId and qnt
		CartItem cartItem= new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		double totalPrice= product.getProduct_prize()*quantity;
		cartItem.setTotalprice(totalPrice);
		//getting cart item from user
		Cart cart=user.getCart();
		if(cart==null) {
			 cart= new Cart();
			 cart.setUser(user);
		}
		cartItem.setCart(cart);
		Set<CartItem>items= cart.getItems();
//		here we check item is available in item cartItem
		//if cartItem is available then we increase only quantity
		//else add new product in cartItem
		
		//by default false
		AtomicReference<Boolean>flag= new AtomicReference<>(false);
		Set<CartItem>newProduct=items.stream().map((i)->{
			if(i.getProduct().getProductId()==product.getProductId()) {
				i.setQuantity(quantity);
				i.setTotalprice(totalPrice);
				flag.set(true);
				
			}
			return i;
		}).collect(Collectors.toSet());
		if(flag.get()) {
			items.clear();
			items.addAll(newProduct);
		}else {
			cartItem.setCart(cart);
			items.add(cartItem);
		}
		Cart saveCart=this.cartRepo.save(cart);  
		return (this.model.map(saveCart, CartDao.class));
		
	}
	
	
	
	public CartDao getCartAll(String email) {
		//find usr
		User user = this.userRepo.findByemail(email).orElseThrow(()-> new ResourceNotFoundException("user not found"));
		//find cart
		Cart cart = this.cartRepo.findByUser(user).orElseThrow(()->new ResourceNotFoundException("cart not found"));
		return this.model.map(cart, CartDao.class);
		
	}
	// get cart by cartId
	public CartDao getCartById(int cartId) {
//		User user = this.userRepo.findByemail(username).orElseThrow(()->new ResourceNotFoundException("user not found"));
		Cart findById = this.cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("cart not found"));
		
		return this.model.map(findById, CartDao.class);
		
	}
	//delete cartItem
	public CartDao removeCartItem(String userName, int productId) {
		User user = this.userRepo.findByemail(userName).orElseThrow(()->new ResourceNotFoundException("user not found"));
		Cart cart = user.getCart();
		Set<CartItem>items = cart.getItems();
	    boolean removeIf=items.removeIf((i)->i.getProduct().getProductId()==productId);
	    Cart save = this.cartRepo.save(cart);
	    System.out.println(removeIf);
		return this.model.map(save, CartDao.class);
		
	}
}
