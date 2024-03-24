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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.ApiResponse;
import com.ecom.payload.OrderDao;
import com.ecom.payload.OrderRequest;
import com.ecom.payload.OrderResponse;
import com.ecom.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	//create order
	@PostMapping("/")
	public ResponseEntity<OrderDao> createOrder(@RequestBody OrderRequest orderReq, Principal p) {
		String username= p.getName();
		OrderDao order = this.orderService.orderCreate(orderReq, username);
		return new ResponseEntity<OrderDao>(order, HttpStatus.CREATED);
		
	}
	@DeleteMapping("/{orderId}")
	public ResponseEntity<?> cancelOrderById(@PathVariable int orderId) {
		this.orderService.cancelOrder(orderId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Order deleted", true), HttpStatus.OK);
		
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDao>findById(@PathVariable int orderId){
		OrderDao orderDao = this.orderService.findById(orderId);
		return new ResponseEntity<OrderDao>(orderDao, HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/findAll")
	public OrderResponse findAllOrders(@RequestParam(defaultValue="2", value="pageSize") int pageSize,
			@RequestParam(defaultValue="0", value="pageNo") int pageNo) {
		OrderResponse findAllOrders = this.orderService.findAllOrders(pageNo, pageSize);
		return findAllOrders;
		
	}
	
	
	

}
