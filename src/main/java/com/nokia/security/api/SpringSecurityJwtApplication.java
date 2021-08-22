package com.nokia.security.api;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nokia.security.api.entity.User;
import com.nokia.security.api.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	@Autowired
	UserRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}
	
	
	
	
	@PostConstruct
	public void initUsers() {
		
		User user  = new User(101,"user","password","abc@gmail.com");
		User user1 = new User(101,"user1","password1","abc@gmail.com");
		User user2 = new User(101,"user2","password2","abc@gmail.com");
		User user3 = new User(101,"user3","password3","abc@gmail.com");
		List<User> list = Arrays.asList(user,user1,user2,user3);		
		repo.saveAll(list);
		
	}

}
