package com.snapdeal.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.snapdeal.dao.EntityDao;
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
	public String saveUser(@RequestParam("user") User user, ModelMap map) {
		System.out.println(user.getUsername());
		// if(user.getId() == null)
		// {
		// userService.saveOrUpdateUser(user);
		// }
		// else
		// {
		// User persistedUser = userService.findUserById(user.getId());
		// persistedUser.setUserName(user.getUsername());
		// persistedUser.setUserRoles(user.getUserRoles());
		// persistedUser.setShippers(user.getShippers());
		//
		// userService.saveOrUpdateUser(persistedUser);
		// }

		List<User> userList = userService.getUsersExceptLoggedIn("mohit");
		map.put("users", userList);

		return "User/view";
	}

	@RequestMapping("/view")
	public String showUsers(ModelMap map) {
		List<User> userList = userService.getUsersExceptLoggedIn("mohit");
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
		System.out.println(persistedUser.getShippers()+"\n \n \n");
		return "User/edit";
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
