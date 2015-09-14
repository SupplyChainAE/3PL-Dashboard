package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.dto.DropshipFilter;
import com.snapdeal.entity.Dropship;

public interface DropshipDao {
	public List<Dropship> getAllData();

	public List<String> getModes();

	public List<String> getShippers();

	public List<String> getShipperGroups();

	public List<DropshipFilter> groupByModeGroupShipper();

	public List<DropshipFilter> groupByMode();

	public List<DropshipFilter> groupByGroup();

	public List<DropshipFilter> groupByShipper();

	public List<DropshipFilter> groupByGroupShipper();

	public List<DropshipFilter> groupByModeShipper();

	public List<DropshipFilter> groupByModeGroup();

	public List<DropshipFilter> genericGroup(String q, List<String> shipperNames,
			String shipper, String startDate, String endDate,
			List<String> pincode);

}
