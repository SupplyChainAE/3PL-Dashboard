package com.snapdeal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snapdeal.dao.DropshipDao;
import com.snapdeal.dto.DropshipFilter;
import com.snapdeal.dto.Filters;
import com.snapdeal.entity.Dropship;

@Controller
@RequestMapping("/Dashboard")
public class DropshipController {
	
	@Inject
	@Named("dropshipDao")
	DropshipDao dropshipDao;
	
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
//		System.out.println(dropshipData.get(0).getMode());
//		System.out.println(dropshipData.get(0).getShipper());
//		System.out.println(dropshipData.get(0).getShipperGroup());

		
		map.put("shipper",shipperList);
		map.put("mode",modeList);
		map.put("group",groupList);
		
		map.put("filterData",dropshipData);
		return "/Dashboard/dropship";
	}

}
