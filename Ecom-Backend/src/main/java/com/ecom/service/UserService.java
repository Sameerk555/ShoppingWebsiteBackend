package com.ecom.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.exception.ResourceNotFoundException;
import com.ecom.model.User;
import com.ecom.payload.UserDao;
import com.ecom.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {  
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper mapper;
	public UserDao createUser(UserDao userDao) {
		System.out.println("ser "+userDao.getAddress());
		System.out.println("ser "+userDao.getEmail());
		//userDao to User
		User user=this.mapper.map(userDao, User.class);
		String password=user.getPassword();
		String encode=this.passwordEncoder.encode(password);
		user.setPassword(encode);
		System.out.println("encode pass " +encode);
		User saveUser=this.userRepo.save(user);
		//user to userDao
		UserDao saveUserDao=this.mapper.map(saveUser, UserDao.class);
		return saveUserDao;
		
	}
	public UserDao getById(int userId) {
		User findByUserId=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException(userId+ " this user is not found"));
		UserDao userDao=this.mapper.map(findByUserId, UserDao.class);
		return userDao;
	}
	public void deleteUser(int userId) {
		User findById=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException(userId+ " this user is not found"));
		this.userRepo.delete(findById);
		
	}
	public List<UserDao>findAllIUser(){
		List<User>findAll= this.userRepo.findAll();
		List<UserDao>collect=findAll.stream().map(each->this.mapper.map(each, UserDao.class)).collect(Collectors.toList());
		return collect;
		
		
	}
}
