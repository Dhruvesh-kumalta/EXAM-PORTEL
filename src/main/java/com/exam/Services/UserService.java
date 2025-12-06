package com.exam.Services;

import java.util.List;
import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {

	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	public User getUser(String username);
	
	public void deleteUser(Long id);
	
	public List<User> getAllUser();
	
	public User updateUser(User user,Long id);
}
