package com.snapdeal.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.snapdeal.dao.CancellationDao;
import com.snapdeal.entity.Cancellation;
import com.snapdeal.entity.User;

@Controller
@RequestMapping("/Dashboard")
public class CancellationController {

	@Inject
	@Named("cancellationDao")
	CancellationDao cancelDao;
	
	@RequestMapping("cancellation")
	public String getcompleteData(@RequestParam("daterange")String date,ModelMap map)
	{
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Cancellation>  cancellationList = cancelDao.getAllData(currentUser.getShippers(),date);
		
		map.put("data", cancellationList);
		
		return "/Dashboard/cancellation";
	}
	
	@RequestMapping("cancellationHome")
	public String cancellationHome(ModelMap map)
	{
		return "/Dashboard/cancellation";
	}
	
}
