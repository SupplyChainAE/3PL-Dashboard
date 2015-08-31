package com.snapdeal.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Used to let user login top the system **/
@Controller
public class LoginController {

	/** Redirects to login page if not logged in else to user home page **/
	@RequestMapping("/login")  
	public String login(@RequestParam(required=false) String authFailed, ModelMap map) {
		String message = "";
		String page = "";
		if(authFailed != null){
			message = "Invalid Username or Password. Please try again.";
			page="login";
			map.put("message",message);
		}
		else if(SecurityContextHolder.getContext().getAuthentication() != null && 
				SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
		{
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				page="redirect:/home";  
			} 
			else {
				page="login";
			}
		}
		else{
			page="login";
		}
		return page;  
	}  

	/** Logs out a user **/
	@RequestMapping("/logout")  
	public String logOut(ModelMap map) {
		map.put("message", "You have been logged out.");
		map.put("success",true);
		return "login";  
	}
	
	/** Redirects to home page after successful login by the user **/
	@RequestMapping("/")
	public String getDefault()
	{
		return "redirect:/home";
	}
	@RequestMapping("/home")
	public String getHome()
	{
		return "home";
	}
}
