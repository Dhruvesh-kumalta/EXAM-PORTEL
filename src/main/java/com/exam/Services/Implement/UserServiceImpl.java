package com.exam.Services.Implement;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.Repo.RoleRepository;
import com.exam.Repo.UserRepository;
import com.exam.Services.UserService;
import com.exam.model.User;
import com.exam.model.UserRole;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
	private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
	     User local=userRepository.findByUsername(user.getUsername());
	     user.setPassword(encoder.encode(user.getPassword()));
	     if(local!=null) {
	    	 System.err.println("User already available with this username");
	    	 throw new Exception("User already available");
	     }
	     else {
	    	 for(UserRole ur:userRoles) {
	    		 roleRepository.save(ur.getRole());
	    	 }
	    	 user.getUserRoles().addAll(userRoles);
	    	 local= userRepository.save(user);
	     }
		return local;
	}
	@Override
	public User getUser(String username) {
		
		return userRepository.findByUsername(username);
	}
	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}
	@Override
	public User updateUser(User user, Long id) {
	    User updatedUser=	userRepository.findById(id).orElseThrow();
	    updatedUser.setFirstName(user.getFirstName());
	    updatedUser.setLastName(user.getLastName());
	    updatedUser.setEmail(user.getEmail());
	    updatedUser.setPhone(user.getPhone());
	   
		return userRepository.save(updatedUser);
	}

}
