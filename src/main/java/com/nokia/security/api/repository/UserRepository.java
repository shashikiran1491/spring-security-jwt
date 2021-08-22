package com.nokia.security.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nokia.security.api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUname(String userName);
	
}
