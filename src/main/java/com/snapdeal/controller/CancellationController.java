package com.snapdeal.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snapdeal.dao.CancellationDao;
import com.snapdeal.entity.Cancellation;

@Controller
@RequestMapping("/Dashboard")
public class CancellationController {

	@Inject
	@Named("cancellationDao")
	CancellationDao cancelDao;
	
	@RequestMapping("cancellation")
	public String getcompleteData(ModelMap map)
	{
		List<Cancellation>  cancellationList = cancelDao.getAllData();
		List<String> shipperList = cancelDao.getShippers();
		List<String> modeList = cancelDao.getModes();
		List<String> groupList = cancelDao.getShipperGroups();
		
		map.put("data", cancellationList);
		map.put("shipper",shipperList );
		map.put("mode",modeList );
		map.put("group",groupList );
		
		return "/Dashboard/cancellation";
	}
	
}
