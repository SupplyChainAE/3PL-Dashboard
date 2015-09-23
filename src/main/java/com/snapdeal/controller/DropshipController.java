package com.snapdeal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snapdeal.dao.DropshipDao;
import com.snapdeal.dao.PincodeDao;
import com.snapdeal.dto.DropshipFilter;
import com.snapdeal.dto.Filters;
import com.snapdeal.entity.Dropship;
import com.snapdeal.entity.Shipper;
import com.snapdeal.entity.User;
import com.snapdeal.util.DateConvertor;
import com.snapdeal.util.ShipperNames;

@Controller
@RequestMapping("/Dashboard")
public class DropshipController {

	@Inject
	@Named("dropshipDao")
	DropshipDao dropshipDao;


	@Inject
	@Named("pincodeDao")
	PincodeDao pincodeDao;

	public static final Logger LOGGER = Logger
			.getLogger(DropshipController.class);

	@RequestMapping("dropship")
	public String getcompleteData(ModelMap map) {
		String date = DateConvertor.convertToString(new Date());
		date += ":" + date;
		User currentUser = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<Shipper> userShipper = currentUser.getShippers();
		List<String> shipperNames = ShipperNames
				.getNamesFromShippers(userShipper);
		List<Dropship> dropshipList = dropshipDao
				.getAllData(shipperNames, date);
		List<String> modeList = dropshipDao.getModes();
		List<String> zoneList = pincodeDao.getZones();

		map.put("shipper", userShipper);
		map.put("mode", modeList);
		map.put("data", dropshipList);
		map.put("zone", zoneList);
		return "/Dashboard/dropship";
	}

	@RequestMapping("dropship/filterData")
	public String filterData(@ModelAttribute("filterform") Filters filter,
			ModelMap map) {
		String group = filter.getShippergroup();
		String shipper = filter.getShipper();
		String daterange = filter.getDaterange();
		String dates[] = daterange.split(":");
		String startDate = dates[0].trim();
		String endDate = dates[1].trim();
		String zone = filter.getZone();
		List<String> zoneList = pincodeDao.getZones();
		System.out.println("group" + group);
		System.out.println("shipper" + shipper);
		System.out.println("daterange" + daterange);
		System.out.println("zone" + zone);

		List<String> modeList = dropshipDao.getModes();

		User currentUser = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		List<String> shipperNames = ShipperNames
				.getNamesFromShippers(currentUser.getShippers());
		List<String> pincodeList = pincodeDao.getPincodeForZone(zone);
		System.out.println("SIZE: " + pincodeList.size());
		String query1 = "AND drop.shipper IN (:shipperList) AND drop.sellerPinCode IN (:pincode) GROUP BY drop.shipperGroup ";
		String query2 = "AND drop.shipper IN (:shipperList) GROUP BY drop.shipperGroup";
		String query3 = query1;
		String query4 = query2;
		String query5 = "AND drop.shipper IN (:shipperList) AND drop.shipper= :shipper AND drop.sellerPinCode IN (:pincode) GROUP BY drop.shipper ";
		String query6 = "AND drop.shipper IN (:shipperList) AND drop.shipper= :shipper GROUP BY drop.shipperGroup";
		String query7 = "AND drop.shipper IN (:shipperList) AND drop.sellerPinCode IN (:pincode)";
		String query8= "AND drop.shipper IN (:shipperList)";
		List<DropshipFilter> dropshipData = new ArrayList<DropshipFilter>();
		// 8
		if (zone.equals("") && daterange != null && (group == null)
				&& shipper.equals("")) {
			System.out.println("dropship QUERY 8 ");
			dropshipData = dropshipDao.genericGroup(query8, shipperNames, "",
					startDate, endDate, new ArrayList<String>());
			map.put("group_aggr", 10);

			}
		// 1
		else if (!zone.equals("") && daterange != null && !(group == null)
				&& !shipper.equals("")) {
			System.out.println("dropship QUERY 1");

			dropshipData = dropshipDao.genericGroup(query1, shipperNames, "",
					startDate, endDate, pincodeList);
			map.put("group_aggr", 1);

		}
		// 2
		else if (zone.equals("") && daterange != null && !(group == null)
				&& !shipper.equals("")) {
			System.out.println("dropship QUERY 2");

			dropshipData = dropshipDao.genericGroup(query2, shipperNames, "",
					startDate, endDate, new ArrayList<String>());
			map.put("group_aggr", 1);

		}
		// 3
		else if (!zone.equals("") && daterange != null && !(group == null)
				&& shipper.equals("")) {
			System.out.println("QUERY 3");

			dropshipData = dropshipDao.genericGroup(query3, shipperNames, "",
					startDate, endDate, pincodeList);
			map.put("group_aggr", 1);

		}
		// 4
		else if (zone.equals("") && daterange != null && !(group == null)
				&& shipper.equals("")) {
			System.out.println("QUERY 4");

			dropshipData = dropshipDao.genericGroup(query4, shipperNames, "",
					startDate, endDate, new ArrayList<String>());
			map.put("group_aggr", 1);

		}
		// 5
		else if (!zone.equals("") && daterange != null && (group == null)
				&& !shipper.equals("")) {
		
			System.out.println("QUERY 5");
			dropshipData = dropshipDao.genericGroup(query5, shipperNames,
					shipper, startDate, endDate, pincodeList);
			map.put("group_aggr", 10);

		}
		// 6
		else if (zone.equals("") && daterange != null && (group == null)
				&& !shipper.equals("")) {
			System.out.println("QUERY 6");

			dropshipData = dropshipDao.genericGroup(query6, shipperNames,
					shipper, startDate, endDate, new ArrayList<String>());
			map.put("group_aggr", 10);

		}
		// 7
		else if (!zone.equals("") && daterange != null && (group == null)
				&& shipper.equals("")) {
			System.out.println("QUERY 7");

			dropshipData = dropshipDao.genericGroup(query7, shipperNames, "",
					startDate, endDate, pincodeList);
			map.put("group_aggr", 10);

		}

		map.put("shipper", currentUser.getShippers());
		map.put("mode", modeList);
		map.put("zone", zoneList);
		map.put("filterData", dropshipData);
		return "/Dashboard/dropship";
	}
}
