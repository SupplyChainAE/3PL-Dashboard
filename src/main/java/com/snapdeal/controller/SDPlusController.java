package com.snapdeal.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snapdeal.dao.SDPlusDao;
import com.snapdeal.dto.Filters;
import com.snapdeal.dto.SdPlusFilter;
import com.snapdeal.entity.SdPlus;
import com.snapdeal.entity.Shipper;
import com.snapdeal.entity.User;

@Controller
@RequestMapping("/Dashboard")
public class SDPlusController {

	@Inject
	@Named("sdplusDao")
	SDPlusDao sdplusDao;

	@RequestMapping("sdplus")
	public String getcompleteData(ModelMap map) {
		User currentUser = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		List<Shipper> ShipperList = currentUser.getShippers();
		List<String> shipperNames = new ArrayList<String>();
		for (Shipper shipper : ShipperList) {
			shipperNames.add(shipper.getCourier());
		}

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date d = new Date();
		String date = dateFormat.format(d);
		List<SdPlus> sdplusList = sdplusDao
				.getAllData(shipperNames, date, date);
		List<String> shipperList = sdplusDao.getShippers();
		List<String> modeList = sdplusDao.getModes();
		// List<String> groupList = sdplusDao.getShipperGroups();

		map.put("shipper", shipperList);
		map.put("mode", modeList);
		// map.put("group",groupList );
		map.put("data", sdplusList);
		return "/Dashboard/sdplus";
	}

	@RequestMapping("sdplus/filterData")
	public String filterData(@ModelAttribute("filterform") Filters filter,
			ModelMap map) {
		String mode = filter.getMode();
		String group = filter.getShippergroup();
		String shipper = filter.getShipper();
		String daterange = filter.getDaterange();
		String pincode = filter.getPincode();
		String dates[] = daterange.split(":");
		String startDate = dates[0].trim();
		String endDate = dates[1].trim();
		List<String> shipperList = sdplusDao.getShippers();
		List<String> modeList = sdplusDao.getModes();
		List<String> groupList = sdplusDao.getShipperGroups();

		User currentUser = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		List<Shipper> ShipperList = currentUser.getShippers();
		List<String> shipperNames = new ArrayList<String>();
		for (Shipper courier : ShipperList) {
			shipperNames.add(courier.getCourier());
		}
		
		
		List<SdPlusFilter> sdPlusData = new ArrayList<SdPlusFilter>();
		if (pincode.equals("") && daterange != null && (group == null)
				&& shipper.equals("") && mode.equals("")) {
			return "redirect:/Dashboard/sdplus";
		} 
		else
			if (!pincode.equals("") && daterange != null && !(group == null)
			&& !shipper.equals("") && !mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroupPincode(shipperNames, startDate, endDate, mode,pincode);
			}		
		else
			if (!pincode.equals("") && daterange != null && !(group == null)
			&& !shipper.equals("") && mode.equals("")) {
				sdPlusData=sdplusDao.groupByGroupPincode(shipperNames, startDate, endDate,pincode);
			}	
			//3
		else
			if (!pincode.equals("") && daterange != null && !(group == null)
			&& shipper.equals("") && !mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroupPincode(shipperNames, startDate, endDate, mode,pincode);
			}
		//4
		else
			if (!pincode.equals("") && daterange != null && !(group == null)
			&& shipper.equals("") && mode.equals("")) {
				sdPlusData=sdplusDao.groupByGroupPincode(shipperNames, startDate, endDate, pincode);
			}
		//5
		else
			if (pincode.equals("") && daterange != null && !(group == null)
			&& !shipper.equals("") && !mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroupPincode(shipperNames, startDate, endDate, mode,pincode);
			}
		else
			if (!pincode.equals("") && daterange != null && !(group == null)
			&& shipper.equals("") && mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroup(shipperNames, startDate, endDate, mode);
			}
		else
			if (pincode.equals("") && daterange != null && !(group == null)
			&& shipper.equals("") && !mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroup(shipperNames, startDate, endDate, mode);
			}
		else
			if (pincode.equals("") && daterange != null && !(group == null)
			&& shipper.equals("") && mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroup(shipperNames, startDate, endDate, mode);
			}
		else
			if (!pincode.equals("") && daterange != null && (group == null)
			&& !shipper.equals("") && !mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroup(shipperNames, startDate, endDate, mode);
			}
		else
			if (!pincode.equals("") && daterange != null && (group == null)
			&& !shipper.equals("") && mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroup(shipperNames, startDate, endDate, mode);
			}
		else
			if (!pincode.equals("") && daterange != null && (group == null)
			&& shipper.equals("") && !mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroup(shipperNames, startDate, endDate, mode);
			}
		else
			if (!pincode.equals("") && daterange != null && (group == null)
			&& shipper.equals("") && mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroup(shipperNames, startDate, endDate, mode);
			}
		else
			if (pincode.equals("") && daterange != null && (group == null)
			&& !shipper.equals("") && !mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroup(shipperNames, startDate, endDate, mode);
			}		
		else
			if (pincode.equals("") && daterange != null && (group == null)
			&& !shipper.equals("") && mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroup(shipperNames, startDate, endDate, mode);
			}
		else
			if (pincode.equals("") && daterange != null && !(group == null)
			&& shipper.equals("") && !mode.equals("")) {
				sdPlusData=sdplusDao.groupByModeGroup(shipperNames, startDate, endDate, mode);
			}
	
	/*else if (!(group == null) && !shipper.equals("") && !mode.equals("")) {
			sdPlusData = sdplusDao.groupByModeGroup(startDate, endDate, mode);
		} else if (!(group == null) && !shipper.equals("") && mode.equals("")) {
			sdPlusData = sdplusDao.groupByGroup(startDate, endDate);
		} else if (!(group == null) && shipper.equals("") && !mode.equals("")) {
			sdPlusData = sdplusDao.groupByModeGroup(startDate, endDate, mode);
		} else if (!(group == null) && shipper.equals("") && mode.equals("")) {
			sdPlusData = sdplusDao.groupByGroup(startDate, endDate);
		} else if ((group == null) && !shipper.equals("") && !mode.equals("")) {
			sdPlusData = sdplusDao.groupByModeShipper(startDate, endDate, mode,
					shipper);
		} else if ((group == null) && !shipper.equals("") && mode.equals("")) {
			sdPlusData = sdplusDao.groupByShipper(startDate, endDate, shipper);
		} else if ((group == null) && shipper.equals("") && !mode.equals("")) {
			sdPlusData = sdplusDao.groupByMode(startDate, endDate, mode);
		}*/

		map.put("shipper", shipperList);
		map.put("mode", modeList);
		map.put("group", groupList);

		map.put("filterData", sdPlusData);

		return "/Dashboard/sdplus";
	}

}