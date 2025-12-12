package com.exam.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.exam.Services.UserService;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
	private UserService userService;
    
    
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
    	String pre="ROLE_";
    	System.out.println(user.getUsername());
    	System.out.println(user.getPassword());
    	Role role1=new Role();
    	role1.setRoleId(90L);
    	role1.setRoleName(pre+"ADMIN");
    	
    	UserRole role=new UserRole();
    	role.setRole(role1);
    	role.setUser(user);
    	
    	Set<UserRole> roles=new HashSet<UserRole>();
    	roles.add(role);
    	
    	
    	return userService.createUser(user, roles);
    }
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
    	return userService.getUser(username);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
    	userService.deleteUser(id);
    }
    @GetMapping("/")
    public List<User> getAllUser() {
    	return userService.getAllUser();
    }
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user,@PathVariable Long id) {
		return userService.updateUser(user, id);
    	
    }
    @GetMapping("/login")
    public String afterLogin() {
    	return "You Logged in successfully";
    }
}
