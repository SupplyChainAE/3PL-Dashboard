package com.snapdeal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snapdeal.dao.SDPlusDao;
import com.snapdeal.dto.Filters;
import com.snapdeal.dto.SdPlusFilter;
import com.snapdeal.entity.SdPlus;

@Controller
@RequestMapping("/Dashboard")
public class SDPlusController {
	
	
	@Inject
	@Named("sdplusDao")
	SDPlusDao sdplusDao;
	
	@RequestMapping("sdplus")
	public String getcompleteData(ModelMap map)
	{
		List<SdPlus>  sdplusList = sdplusDao.getAllData();
		List<String> shipperList = sdplusDao.getShippers();
		List<String> modeList = sdplusDao.getModes();
		List<String> groupList = sdplusDao.getShipperGroups();
		
		map.put("shipper",shipperList );
		map.put("mode",modeList );
		map.put("group",groupList );
		map.put("data", sdplusList);
		return "/Dashboard/sdplus";
	}

	@RequestMapping("sdplus/filterData")
	public String filterData(@ModelAttribute("filterform") Filters filter,ModelMap map)
	{
		String mode = filter.getMode();
		String group = filter.getShippergroup();
		String shipper = filter.getShipper();
		
		List<String> shipperList = sdplusDao.getShippers();
		List<String> modeList = sdplusDao.getModes();
		List<String> groupList = sdplusDao.getShipperGroups();
		
		
		List<SdPlusFilter> sdPlusData = new ArrayList<SdPlusFilter>();
		
		if(!mode.equals("") && !group.equals("")  && !shipper.equals(""))
		{
			sdPlusData =  sdplusDao.groupByModeGroupShipper();
		}
		else if( !mode.equals("") && group.equals("") && shipper.equals(""))
		{
			sdPlusData =  sdplusDao.groupByMode();
		}
		else if( mode.equals("") && !group.equals("") && shipper.equals(""))
		{
			sdPlusData =  sdplusDao.groupByGroup();
		}
		else if(mode.equals("") && group.equals("") && !shipper.equals(""))
		{
			sdPlusData =  sdplusDao.groupByShipper();
		}
		else if(!mode.equals("") && !group.equals("") && shipper.equals(""))
		{
			sdPlusData =  sdplusDao.groupByModeGroup();
		}
		else if(mode.equals("") && !group.equals("") && !shipper.equals(""))
		{
			sdPlusData =  sdplusDao.groupByGroupShipper();
		}
		else if(!mode.equals("") && group.equals("") && !shipper.equals(""))
		{
			sdPlusData =  sdplusDao.groupByModeShipper();
		}
		
		map.put("shipper",shipperList);
		map.put("mode",modeList);
		map.put("group",groupList);
		
		map.put("filterData",sdPlusData);

		return "/Dashboard/sdplus";
	}

}