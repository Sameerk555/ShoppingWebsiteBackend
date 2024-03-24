package com.ecom.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.UserDao;
import com.ecom.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/create")  
	public ResponseEntity<UserDao>createUser(@RequestBody UserDao userDao){
		Date date= new Date();
		userDao.setDate(date);
//		System.out.println(userDao.getEmail());
		UserDao createUser=this.userService.createUser(userDao);
		return new ResponseEntity<UserDao>(createUser, HttpStatus.CREATED);
		
	}
	@GetMapping("findById/{userId}")
	public ResponseEntity<UserDao>findByUserId(@PathVariable int userId){
		UserDao byId=this.userService.getById(userId);
		return new ResponseEntity<UserDao>(byId, HttpStatus.FOUND);
		
	}
	@DeleteMapping("delete/{userId}")
	public void deleteUser(@PathVariable int userId){
		this.userService.deleteUser(userId);
		
	}
	@GetMapping("/findAllUser")
	public ResponseEntity<List<UserDao>>findAllUser(){
		List<UserDao>findAllUser=this.userService.findAllIUser();
		return new ResponseEntity<List<UserDao>>(findAllUser, HttpStatus.ACCEPTED);
		
	}
}
