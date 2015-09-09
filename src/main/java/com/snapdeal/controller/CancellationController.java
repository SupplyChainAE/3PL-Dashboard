package com.snapdeal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.snapdeal.dao.CancellationDao;
import com.snapdeal.entity.Cancellation;
import com.snapdeal.entity.Dropship;
import com.snapdeal.entity.User;
import com.snapdeal.service.CancellationService;

@Controller
@RequestMapping("/Dashboard")
public class CancellationController {

	@Inject
	@Named("cancellationDao")
	CancellationDao cancelDao;
	
	@Inject
	@Named("cancellationService")
	CancellationService cancelService;
	
	public static final Logger LOGGER = Logger.getLogger(CancellationController.class);
	
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


	@RequestMapping("/Cancellation/save")
	public void saveToFile(@ModelAttribute("data") Cancellation[] data,HttpServletResponse response)
	{
		String content ="dssdsds";
		String currentDate = new Date(System.currentTimeMillis()).toString();
		
//		content = cancelService.generateCancellationData(data);	
		System.out.println(content);

		try 
		{
			response.setContentType("");
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename=CancellationReport"+currentDate+".csv");
			response.setContentLength(content.length());
			response.getWriter().write(content);
		
		} catch (IOException e) {
			LOGGER.error("IO Exception in sending template",e);
		}catch (Exception e) {
			LOGGER.error("Exception in sending template",e);
		}
	}
}
