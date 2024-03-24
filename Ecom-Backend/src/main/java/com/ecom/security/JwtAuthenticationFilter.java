package com.ecom.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private JwtHelper jwtHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	Logger logger= LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get token from header
		String requestToken=request.getHeader("Authorization");
		logger.info( "message {}", requestToken);
		  
		String Username=null;
		String jwtToken=null;
		
		if(requestToken!=null && requestToken.trim().startsWith("Bearer")) {
			//get actual token
			jwtToken=requestToken.substring(7);
			try {
				Username=this.jwtHelper.getUsername(jwtToken);
			}catch(ExpiredJwtException e) {
				logger.info("Invalid token message","Jwt token expire");
			}catch(MalformedJwtException e) {
				logger.info("Invald token message", "Invalid jwt Token");
			}catch(IllegalArgumentException e) {
				logger.info("Invalid token message", "unable to get Token");
			}
			if(Username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				//validate 
				UserDetails userDetails=  this.userDetailsService.loadUserByUsername(Username);
				if(this.jwtHelper.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken auth= new 
					UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(auth);
				}else {
					logger.info("not validate Message", "invalid jwt token");
				}
			}else {
				logger.info("User Message", "username is null auth is already there");
			}
		}
		else {
			logger.info("Token message {}","token does not start with bearer");
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
