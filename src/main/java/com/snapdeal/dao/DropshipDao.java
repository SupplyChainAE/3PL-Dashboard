package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.dto.DropshipFilter;
import com.snapdeal.entity.Dropship;

public interface DropshipDao {
	public List<Dropship> getAllData(List<String> shipperList, String date);

	public List<String> getModes();

	public List<String> getShippers();

	public List<DropshipFilter> genericGroup(String q,
			List<String> shipperNames, String shipper, String startDate,
			String endDate, List<String> pincode);

}
