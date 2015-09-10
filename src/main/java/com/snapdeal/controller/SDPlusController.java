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

import com.snapdeal.dao.PincodeDao;
import com.snapdeal.dao.SDPlusDao;
import com.snapdeal.dto.Filters;
import com.snapdeal.dto.SdPlusFilter;
import com.snapdeal.entity.SdPlus;
import com.snapdeal.entity.Shipper;
import com.snapdeal.entity.User;
import com.snapdeal.util.ShipperNames;

@Controller
@RequestMapping("/Dashboard")
public class SDPlusController {

	@Inject
	@Named("sdplusDao")
	SDPlusDao sdplusDao;

	@Inject
	@Named("pincodeDao")
	PincodeDao pincodeDao;

	@RequestMapping("sdplus")
	public String getcompleteData(ModelMap map) {
		User currentUser = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		List<String> shipperNames = ShipperNames
				.getNamesFromShippers(currentUser.getShippers());
		List<String> zoneList = pincodeDao.getZones();

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date d = new Date();
		String date = dateFormat.format(d);
		List<SdPlus> sdplusList = sdplusDao
				.getAllData(shipperNames, date, date);
		List<String> shipperList = sdplusDao.getShippers();
		List<String> modeList = sdplusDao.getModes();

		map.put("shipper", shipperList);
		map.put("mode", modeList);
		map.put("data", sdplusList);
		map.put("zone", zoneList);
		return "/Dashboard/sdplus";
	}

	@RequestMapping("sdplus/filterData")
	public String filterData(@ModelAttribute("filterform") Filters filter,
			ModelMap map) {
		String mode = filter.getMode();
		String group = filter.getShippergroup();
		String shipper = filter.getShipper();
		String daterange = filter.getDaterange();
		String dates[] = daterange.split(":");
		String startDate = dates[0].trim();
		String endDate = dates[1].trim();
		System.out.println("mode" + mode);
		System.out.println("group" + group);
		System.out.println("shipper" + shipper);
		System.out.println("daterange" + daterange);
		List<String> shipperList = sdplusDao.getShippers();
		List<String> modeList = sdplusDao.getModes();
		List<String> groupList = sdplusDao.getShipperGroups();

		User currentUser = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		List<String> shipperNames = ShipperNames
				.getNamesFromShippers(currentUser.getShippers());

		String query1 = "AND sdp.mode= :mode  AND sdp.shipper IN (:shipperList) GROUP BY sdp.shipperGroup ";
		String query2 = "AND sdp.shipper IN (:shipperList) GROUP BY sdp.shipperGroup ";
		String query3 = query1;
		String query4 = "AND sdp.shipper IN (:shipperList) GROUP BY sdp.shipperGroup";
		String query5 = "AND sdp.mode= :mode  AND sdp.shipper IN (:shipperList) AND sdp.shipper= :shipper GROUP BY sdp.shipper";
		String query6 = "AND sdp.mode= :mode AND sdp.shipper IN (:shipperList) GROUP BY sdp.shipper";
		String query7 = "AND sdp.mode = :mode";

		List<SdPlusFilter> sdPlusData = new ArrayList<SdPlusFilter>();
		// 8
		if (daterange != null && (group == null) && shipper.equals("")
				&& mode.equals("")) {
			System.out.println("sdplus QUERY 16");
			return "redirect:/Dashboard/sdplus";
		}
		// 1
		else if (daterange != null && !(group == null) && !shipper.equals("")
				&& !mode.equals("")) {
			System.out.println("QUERY 1");

			sdPlusData = sdplusDao.genericGroup(query1, shipperNames, "",
					startDate, endDate, mode);
		}
		// 2
		else if (daterange != null && !(group == null) && !shipper.equals("")
				&& mode.equals("")) {
			System.out.println("QUERY 2");

			sdPlusData = sdplusDao.genericGroup(query2, shipperNames, "",
					startDate, endDate, "");
		}
		// 3
		else if (daterange != null && !(group == null) && shipper.equals("")
				&& !mode.equals("")) {
			System.out.println("QUERY 3");

			sdPlusData = sdplusDao.genericGroup(query3, shipperNames, "",
					startDate, endDate, mode);
		}
		// 4
		else if (daterange != null && !(group == null) && shipper.equals("")
				&& mode.equals("")) {
			System.out.println("QUERY 4");

			sdPlusData = sdplusDao.genericGroup(query4, shipperNames, "",
					startDate, endDate, "");
		}
		// 5
		else if (daterange != null && (group == null) && !shipper.equals("")
				&& !mode.equals("")) {
			System.out.println("QUERY 5");
			sdPlusData = sdplusDao.genericGroup(query5, shipperNames, shipper,
					startDate, endDate, mode);
		}
		// 6
		else if (daterange != null && (group == null) && !shipper.equals("")
				&& mode.equals("")) {
			System.out.println("QUERY 6");

			sdPlusData = sdplusDao.genericGroup(query6, shipperNames, shipper,
					startDate, endDate, "");
		}
		// 7
		else if (daterange != null && (group == null) && shipper.equals("")
				&& !mode.equals("")) {
			System.out.println("QUERY 7");

			sdPlusData = sdplusDao.genericGroup(query7, shipperNames, "",
					startDate, endDate, mode);
		}
		// // 8
		// else if (zone.equals("") && daterange != null && !(group == null)
		// && shipper.equals("") && mode.equals("")) {
		// System.out.println("QUERY 8");
		//
		// sdPlusData = sdplusDao.genericGroup(query8, shipperNames, "",
		// startDate, endDate, "", new ArrayList<String>());
		// }
		// // 9
		// else if (!zone.equals("") && daterange != null && (group == null)
		// && !shipper.equals("") && !mode.equals("")) {
		// System.out.println("QUERY 9");
		//
		// sdPlusData = sdplusDao.genericGroup(query9, shipperNames, shipper,
		// startDate, endDate, mode, pincodeList);
		// }
		// // 10
		// else if (!zone.equals("") && daterange != null && (group == null)
		// && !shipper.equals("") && mode.equals("")) {
		// System.out.println("QUERY 10");
		//
		// sdPlusData = sdplusDao.genericGroup(query10, shipperNames, shipper,
		// startDate, endDate, "", pincodeList);
		// }
		// // 11
		// else if (!zone.equals("") && daterange != null && (group == null)
		// && shipper.equals("") && !mode.equals("")) {
		// System.out.println("QUERY 11");
		// sdPlusData = sdplusDao.genericGroup(query11, shipperNames, "",
		// startDate, endDate, mode, pincodeList);
		// }
		// // 12
		// else if (!zone.equals("") && daterange != null && (group == null)
		// && shipper.equals("") && mode.equals("")) {
		// System.out.println("QUERY 12");
		// sdPlusData = sdplusDao.genericGroup(query12, shipperNames, "",
		// startDate, endDate, "", pincodeList);
		// }
		// // 13
		// else if (zone.equals("") && daterange != null && (group == null)
		// && !shipper.equals("") && !mode.equals("")) {
		// System.out.println("QUERY 13");
		// sdPlusData = sdplusDao.genericGroup(query13, shipperNames, shipper,
		// startDate, endDate, mode, new ArrayList<String>());
		// }
		// // 14
		// else if (zone.equals("") && daterange != null && (group == null)
		// && !shipper.equals("") && mode.equals("")) {
		// System.out.println("QUERY 14");
		// sdPlusData = sdplusDao.genericGroup(query14, shipperNames, shipper,
		// startDate, endDate, "", new ArrayList<String>());
		// }
		// // 15
		// else if (zone.equals("") && daterange != null && (group == null)
		// && shipper.equals("") && !mode.equals("")) {
		// System.out.println("QUERY 15");
		// sdPlusData = sdplusDao.genericGroup(query1, shipperNames, "",
		// startDate, endDate, mode, new ArrayList<String>());
		// }

		/*
		 * else if (!(group == null) && !shipper.equals("") && !mode.equals(""))
		 * { sdPlusData = sdplusDao.groupByModeGroup(startDate, endDate, mode);
		 * } else if (!(group == null) && !shipper.equals("") &&
		 * mode.equals("")) { sdPlusData = sdplusDao.groupByGroup(startDate,
		 * endDate); } else if (!(group == null) && shipper.equals("") &&
		 * !mode.equals("")) { sdPlusData =
		 * sdplusDao.groupByModeGroup(startDate, endDate, mode); } else if
		 * (!(group == null) && shipper.equals("") && mode.equals("")) {
		 * sdPlusData = sdplusDao.groupByGroup(startDate, endDate); } else if
		 * ((group == null) && !shipper.equals("") && !mode.equals("")) {
		 * sdPlusData = sdplusDao.groupByModeShipper(startDate, endDate, mode,
		 * shipper); } else if ((group == null) && !shipper.equals("") &&
		 * mode.equals("")) { sdPlusData = sdplusDao.groupByShipper(startDate,
		 * endDate, shipper); } else if ((group == null) && shipper.equals("")
		 * && !mode.equals("")) { sdPlusData = sdplusDao.groupByMode(startDate,
		 * endDate, mode); }
		 */

		map.put("shipper", shipperList);
		map.put("mode", modeList);
		map.put("group", groupList);

		map.put("filterData", sdPlusData);

		return "/Dashboard/sdplus";
	}

}