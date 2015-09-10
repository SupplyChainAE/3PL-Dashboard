package com.snapdeal.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.snapdeal.dao.CancellationDao;
import com.snapdeal.dao.PincodeDao;
import com.snapdeal.entity.Cancellation;
import com.snapdeal.entity.User;
import com.snapdeal.service.CancellationService;
import com.snapdeal.util.DateConvertor;
import com.snapdeal.util.ShipperNames;

@Controller
@RequestMapping("/Dashboard")
public class CancellationController {

	@Inject
	@Named("cancellationDao")
	CancellationDao cancelDao;
	
	@Inject
	@Named("pincodeDao")
	PincodeDao pincodeDao;
	
	@Inject
	@Named("cancellationService")
	CancellationService cancelService;
	
	public static final Logger LOGGER = Logger.getLogger(CancellationController.class);
	
	@RequestMapping("cancellation")
	public String getcompleteData(@RequestParam("daterange")String date,@RequestParam(value="zone",required=false)String zone,
			ModelMap map)
	{
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> shipperNames = ShipperNames.getNamesFromShippers(currentUser.getShippers());		
		List<Cancellation>  cancellationList;
		if(!zone.equals(""))
		{
			List<String> pincodeList = pincodeDao.getPincodeForZone(zone);
			cancellationList = cancelDao.getPincodeData(shipperNames,pincodeList,date);	
		}
		else
		{
			cancellationList = cancelDao.getAllData(shipperNames,date);
		}
		
		List<String> zoneList = pincodeDao.getZones();
		
		map.put("data", cancellationList);
		map.put("zone", zoneList);
		
		return "/Dashboard/cancellation";
	}
	
	@RequestMapping("cancellationHome")
	public String cancellationHome(ModelMap map)
	{
		String date = DateConvertor.convertToString(new Date());
		date += ":"+date;
		
		User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		List<String> shipperNames = ShipperNames.getNamesFromShippers(currentUser.getShippers());		
		List<Cancellation>  cancellationList = cancelDao.getAllData(shipperNames,date);
		List<String> zoneList = pincodeDao.getZones();
		
		map.put("zone", zoneList);
		map.put("data", cancellationList);
		
		return "/Dashboard/cancellation";
	}


//	@RequestMapping("/Cancellation/save")
//	public void saveToFile(@ModelAttribute("data") Cancellation[] data,HttpServletResponse response)
//	{
//		String content ="dssdsds";
//		String currentDate = new Date(System.currentTimeMillis()).toString();
//		
////		content = cancelService.generateCancellationData(data);	
//		System.out.println(content);
//
//		try 
//		{
//			response.setContentType("");
//			response.setContentType("text/csv");
//			response.setHeader("Content-Disposition", "attachment; filename=CancellationReport"+currentDate+".csv");
//			response.setContentLength(content.length());
//			response.getWriter().write(content);
//		
//		} catch (IOException e) {
//			LOGGER.error("IO Exception in sending template",e);
//		}catch (Exception e) {
//			LOGGER.error("Exception in sending template",e);
//		}
//	}
}
