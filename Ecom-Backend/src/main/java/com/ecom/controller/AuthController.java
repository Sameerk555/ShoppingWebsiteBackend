package com.ecom.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.JwtRequest;
import com.ecom.payload.JwtResponse;
import com.ecom.payload.UserDao;
import com.ecom.security.JwtHelper;
@RestController
@RequestMapping("/auth")  
@CrossOrigin("*")
public class AuthController {  
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ModelMapper model;
	@Autowired  
	private JwtHelper jwtHelper;
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		System.out.println(request.getUsername());
		this.authenticateUser(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtHelper.generateToken(userDetails);
		
		JwtResponse response= new JwtResponse();
		response.setToken(token);
		response.setUser(this.model.map(userDetails, UserDao.class));
		return new ResponseEntity<JwtResponse>(response, HttpStatus.ACCEPTED);
	}
	
	private void authenticateUser(String Username, String password) {
		try {
		manager.authenticate( new UsernamePasswordAuthenticationToken(Username, password));
		}catch(BadCredentialsException e) {
			throw new ResourceNotFoundException("Invalid username or password");
		}catch(DisabledException e) {
			throw new ResourceNotFoundException("User is not active");
		}
		
	}

}
