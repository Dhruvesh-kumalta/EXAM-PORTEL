package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.AuthRequest;
import com.exam.util.JwtUtil;

@RestController
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	JwtUtil jwtUtil;
    @PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) {
    	try {
    	
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        	System.err.print("User autherized with this"+"   "+authRequest.getUsername()+"username");

    		return jwtUtil.generateToken(authRequest.getUsername());
    	}catch(Exception e) {
    		throw e; 
    	}
	}
}
