package com.nokia.security.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.security.api.entity.AuthRequest;
import com.nokia.security.api.util.JwtUtil;

@RestController
public class WelcomeController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManger;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome to Spring Security";
	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		
		try {
		//validate Username and Password --> If authentication is success generate the web token
		authenticationManger.
			authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
		}
		catch(Exception ex) {
			throw new Exception("Invalid Username and Password");
		}
		
		return jwtUtil.generateToken(authRequest.getUsername());
		}
	
}
