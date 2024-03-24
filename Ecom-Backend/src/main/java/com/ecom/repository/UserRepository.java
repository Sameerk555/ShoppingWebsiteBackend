package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User>findByemail(String email);

}
