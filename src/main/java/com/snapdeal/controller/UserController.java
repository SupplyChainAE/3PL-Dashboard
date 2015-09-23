package com.snapdeal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.snapdeal.entity.Roles;
import com.snapdeal.entity.Shipper;
import com.snapdeal.entity.User;
import com.snapdeal.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController {

	@Inject
	@Named("userService")
	UserService userService;

	@RequestMapping("/create")
	public String createUser(ModelMap map) {
		List<Shipper> shipperList = userService.getAllShippers();
		List<Roles> roleList = userService.getAllRoles();

		map.put("shippers", shipperList);
		map.put("roles", roleList);
		map.put("user", new User());
		return "User/create";
	}

	@RequestMapping("/save")
	public String saveUser(@ModelAttribute("user") User user,
			@RequestParam("role") Long[] roles,
			@RequestParam("shipper") Long[] shippers, ModelMap map) {

		List<Roles> finalRoles = new ArrayList<Roles>();
		List<Shipper> finalShippers = new ArrayList<Shipper>();

		for (Long roleId : roles) {
			Roles r = new Roles();
			r.setId(roleId);
			finalRoles.add(r);
		}
		for (Long shipperId : shippers) {
			Shipper sh = new Shipper();
			sh.setId(shipperId);
			finalShippers.add(sh);
		}
		if (user.getId() == null) {
			user.setUserRoles(finalRoles);
			user.setShippers(finalShippers);
			userService.saveOrUpdateUser(user);
		} else {
			User persistedUser = userService.findUserById(user.getId());
			persistedUser.setUserName(user.getUsername());
			persistedUser.setUserRoles(finalRoles);
			persistedUser.setShippers(finalShippers);

			userService.saveOrUpdateUser(persistedUser);
		}
		String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		List<User> userList = userService.getUsersExceptLoggedIn(currentUser);
		map.put("users", userList);

		return "User/view";
	}

	@RequestMapping("/view")
	public String showUsers(ModelMap map) {
		String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		List<User> userList = userService.getUsersExceptLoggedIn(currentUser);
		map.put("users", userList);

		return "User/view";
	}

	@RequestMapping("/edit/{id}")
	public String editUser(@PathVariable("id") Long id, ModelMap map) {
		User persistedUser = userService.findUserById(id);
		List<Shipper> shipperList = userService.getAllShippers();
		List<Roles> roleList = userService.getAllRoles();

		map.put("shippers", shipperList);
		map.put("roles", roleList);
		map.put("user", persistedUser);

		return "User/edit";
	}

	@RequestMapping("/enableDisable")
	public String enableOrDisable(@RequestParam("id") Long id,
			@RequestParam("enabled") boolean enabled, ModelMap map) {
		if (enabled) {
			userService.disableUser(id);
		} else {
			userService.enableUser(id);
		}

		String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		List<User> userList = userService.getUsersExceptLoggedIn(currentUser);
		map.put("users", userList);

		return "User/view";
	}

	@RequestMapping("/changePassword/{id}")
	public String changePassword(@PathVariable("id") Long id, ModelMap map) {
		User user = userService.findUserById(id);

		map.put("userName", user.getUsername());
		map.put("userid", id);

		return "User/changePassword";
	}

	@RequestMapping("/updatePassword")
	public String updatePassword(@ModelAttribute("id") Long id,
			@ModelAttribute("password") String password, ModelMap map) {
		User persistedUser = userService.findUserById(id);
		persistedUser.setPassword(getHashedPassword(password));
		userService.saveOrUpdateUser(persistedUser);

		return "User/view";
	}

	public String getHashedPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
}