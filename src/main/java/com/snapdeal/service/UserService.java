package com.snapdeal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snapdeal.entity.Roles;
import com.snapdeal.entity.Shipper;
import com.snapdeal.entity.User;

@Service
public interface UserService {

	public User getUserByUserName(String userName);
	public void saveOrUpdateUser(User user);
	public List<Roles> getAllRoles();
	public void saveOrUpdateRole(Roles role);
	public List<User> getUsersExceptLoggedIn(String userName);
	public User findUserById(Long id);
	public void enableUser(Long id);
	public void disableUser(Long id);
	public boolean checkUser(String userName);
	public List<Shipper> getAllShippers();
}
