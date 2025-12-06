package com.exam;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.Services.UserService;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;



@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner{
   @Autowired
	UserService service;
	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}
/*
	@Override
	public void run(String... args) throws Exception {
		System.out.print("Project started");
		User user=new User();
		user.setFirstName("Dhruvesh");
		user.setLastName("kumalta");
		user.setUsername("dhruvesh222");
		user.setPassword("1234");
		user.setEmail("abc@gmail.com");
		user.setProfile("abc.jpg");
		
		Role role1=new Role();
		role1.setRoleId(45L);
		role1.setRoleName("ADMIN");
		
		Set<UserRole> userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role1);
		
		userRoleSet.add(userRole);
		
		User user1=this.service.createUser(user, userRoleSet);
		System.out.print(user1.getUsername());
	}
*/

	@Override
	public void run(String... args) throws Exception {
		System.out.print("Application started");
		/*
		User user=new User();
		user.setFirstName("Dhruvesh");
		user.setLastName("kumalta");
		user.setUsername("dhruvesh222");
		user.setPassword("1234");
		user.setEmail("abc@gmail.com");
		user.setProfile("abc.jpg");
		
		Role role1=new Role();
		role1.setRoleId(45L);
		role1.setRoleName("ADMIN");
		
		Set<UserRole> userRoleSet=new HashSet<UserRole>();
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role1);
		
		userRoleSet.add(userRole);
		
		User user1=this.service.createUser(user, userRoleSet);
		System.out.print(user1.getUsername());
	*/}
}
