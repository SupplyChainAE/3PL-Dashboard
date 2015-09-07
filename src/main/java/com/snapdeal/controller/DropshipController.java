package com.snapdeal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.dao.DropshipDao;
import com.snapdeal.dto.DropshipFilter;
import com.snapdeal.dto.Filters;
import com.snapdeal.entity.Dropship;
import com.snapdeal.service.DropshipService;

@Controller
@RequestMapping("/Dashboard")
public class DropshipController {
	
	@Inject
	@Named("dropshipDao")
	DropshipDao dropshipDao;
	
	@Inject
	@Named("dropshipService")
	DropshipService dropshipService;
	
	public static final Logger LOGGER = Logger.getLogger(DropshipController.class);
		
	@RequestMapping("dropship")
	public String getcompleteData(ModelMap map)
	{
		List<Dropship>  dropshipList = dropshipDao.getAllData();
		List<String> shipperList = dropshipDao.getShippers();
		List<String> modeList = dropshipDao.getModes();
		List<String> groupList = dropshipDao.getShipperGroups();
		
		map.put("shipper",shipperList );
		map.put("mode",modeList );
		map.put("group",groupList );
		map.put("data", dropshipList);
		
		return "/Dashboard/dropship";
	}
	
	@RequestMapping("dropship/filterData")
	public String filterData(@ModelAttribute("filterform") Filters filter,ModelMap map)
	{
		String mode = filter.getMode();
		String group = filter.getShippergroup();
		String shipper = filter.getShipper();
	
		List<String> shipperList = dropshipDao.getShippers();
		List<String> modeList = dropshipDao.getModes();
		List<String> groupList = dropshipDao.getShipperGroups();

		List<DropshipFilter> dropshipData = new ArrayList<DropshipFilter>();
		
		if(!mode.equals("") && !group.equals("")  && !shipper.equals(""))
		{
			dropshipData =  dropshipDao.groupByModeGroupShipper();
		}
		else if( !mode.equals("") && group.equals("") && shipper.equals(""))
		{
			dropshipData =  dropshipDao.groupByMode();
		}
		else if( mode.equals("") && !group.equals("") && shipper.equals(""))
		{
			dropshipData =  dropshipDao.groupByGroup();
		}
		else if(mode.equals("") && group.equals("") && !shipper.equals(""))
		{
			dropshipData =  dropshipDao.groupByShipper();
		}
		else if(!mode.equals("") && !group.equals("") && shipper.equals(""))
		{
			dropshipData =  dropshipDao.groupByModeGroup();
		}
		else if(mode.equals("") && !group.equals("") && !shipper.equals(""))
		{
			dropshipData =  dropshipDao.groupByGroupShipper();
		}
		else if(!mode.equals("") && group.equals("") && !shipper.equals(""))
		{
			dropshipData =  dropshipDao.groupByModeShipper();
		}
		
		map.put("shipper",shipperList);
		map.put("mode",modeList);
		map.put("group",groupList);
		
		map.put("filterData",dropshipData);
		return "/Dashboard/dropship";
	}
	
	@RequestMapping(value="dropship/saveToFile1",method=RequestMethod.POST,consumes="application/json")
	public void  downloadFile1(@RequestBody  DropshipFilter[] data,HttpServletResponse response)
	{	
		String content ="";
		String currentDate = new Date(System.currentTimeMillis()).toString();
		List<DropshipFilter> dropshipFilterData = new ArrayList<DropshipFilter>();
//			dropshipFilterData.addAll(data);
		for(DropshipFilter obj : data)
		{
			dropshipFilterData.add((DropshipFilter)obj);
		}
		content = dropshipService.generateDropshipFilterData(dropshipFilterData);
		System.out.println(content);

		try 
		{
			response.setContentType("");
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename=DropshipReport"+currentDate+".csv");
			response.setContentLength(content.length());
			response.getWriter().write(content);
		
		} catch (IOException e) {
			LOGGER.error("IO Exception in sending template",e);
		}catch (Exception e) {
			LOGGER.error("Exception in sending template",e);
		}
	}

	@RequestMapping(value="dropship/saveToFile2",method=RequestMethod.POST,consumes="application/json")
	public @ResponseBody void  downloadFile2(@RequestBody  Dropship[] data,HttpServletResponse response)
	{	
		String content ="";
		String currentDate = new Date(System.currentTimeMillis()).toString();
		List<Dropship> dropshipData = new ArrayList<Dropship>();
		
		for(Object obj : data)
		{
			dropshipData.add((Dropship)obj);
		}
		content = dropshipService.generateDropshipData(dropshipData);	
		System.out.println(content);

		try 
		{
			response.setContentType("");
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename=DropshipReport"+currentDate+".csv");
			response.setContentLength(content.length());
			response.getWriter().write(content);
		
		} catch (IOException e) {
			LOGGER.error("IO Exception in sending template",e);
		}catch (Exception e) {
			LOGGER.error("Exception in sending template",e);
		}
	}

}
