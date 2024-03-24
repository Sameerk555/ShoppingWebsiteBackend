package com.ecom.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecom.exception.ResourceNotFoundException;
import com.ecom.model.Cart;
import com.ecom.model.CartItem;
import com.ecom.model.Order;
import com.ecom.model.OrderItem;
import com.ecom.model.User;
import com.ecom.payload.CartDao;
import com.ecom.payload.OrderDao;
import com.ecom.payload.OrderRequest;
import com.ecom.payload.OrderResponse;
import com.ecom.repository.CartRepository;
import com.ecom.repository.OrderRepository;
import com.ecom.repository.UserRepository;

@Service
public class OrderService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private OrderRepository orderRepo;
	//order created method
	public OrderDao orderCreate(OrderRequest request, String username) {
		User user = this.userRepo.findByemail(username).orElseThrow(()-> new ResourceNotFoundException("user not found"));
		
		int cartId= request.getCartId();
		String orderAddress= request.getOrderAddress();
		Cart cart = this.cartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("cart not found"));
		Set<CartItem> items = cart.getItems();
		Order order= new Order();
		AtomicReference<Double> totalOrderPrice= new AtomicReference<Double>(0.0);
		Set<OrderItem>orderItems=items.stream().map((cartItem)->{
			OrderItem orderItem=new OrderItem();
			//set cartItem into orderItem
			
			//set product in orderItem
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setProductQuantity(cartItem.getQuantity());
			orderItem.setTotalProductprice(cartItem.getTotalprice());
			orderItem.setOrder(order);
			totalOrderPrice.set(totalOrderPrice.get()+orderItem.getTotalProductprice());
			int productId= orderItem.getProduct().getProductId();
			return orderItem;
			
		}).collect(Collectors.toSet());
		
		order.setBillingAddress(orderAddress);
		order.setOrderDelivered(null);
		order.setOrderStatus("CREATED");
		order.setPaymentStatus("NOT PAID");
		order.setUser(user);
		order.setOrderItem(orderItems);
		order.setOrderAmt(totalOrderPrice.get());
		order.setOrderCreateAt(new Date());
		Order save;
		if(order.getOrderAmt()>0) {
			save = this.orderRepo.save(order);
			cart.getItems().clear();
			 this.cartRepo.save(cart);
		}else {
			throw new ResourceNotFoundException("Please add cart first and place order");
		}
		
		return this.mapper.map(save, OrderDao.class);
	}
	//cancel order
	public void cancelOrder(int orderId) {
		
		Order order = this.orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("order not found"));
		this.orderRepo.delete(order);
	}
	
	//order find by id
	public OrderDao findById(int orderId) {
		Order order = this.orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
		return this.mapper.map(order, OrderDao.class);
		
	}
	//pagination(find all orders)
	public OrderResponse findAllOrders(int pageNo, int pageSize) {
		
		Pageable pageable= PageRequest.of(pageNo, pageSize);
		Page<Order> findAll =  this.orderRepo.findAll(pageable);
		
		List<Order> content = findAll.getContent();
		
		//change order to orderDao
		List<OrderDao> collect = content.stream().map((each)->this.mapper.map(each, OrderDao.class)).collect(Collectors.toList());
		OrderResponse response= new OrderResponse();
		response.setContent(collect);
		response.setPageNo(findAll.getNumber());
		response.setLastPage(findAll.isLast());
		response.setPageSize(findAll.getSize());
		response.setTotalPage(findAll.getTotalPages());
		
		//get total element return long
		response.setTotalElement(findAll.getTotalElements());
		return response;
		
		
	}

}
