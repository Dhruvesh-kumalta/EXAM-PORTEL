package com.exam.Services.Implement;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.exam.Repo.UserRepository;
import com.exam.model.User;
@Service
public class CustomUserDetailService implements UserDetailsService{
    @Autowired
	UserRepository userRepository;
   
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user=userRepository.findByUsername(username);
	    if(user==null) {
	    	System.out.print("User not found");
	    	throw new UsernameNotFoundException("User Not found");
	    }
		return user;
	}

}
