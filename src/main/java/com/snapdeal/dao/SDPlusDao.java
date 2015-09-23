package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.dto.SdPlusFilter;
import com.snapdeal.entity.SdPlus;

public interface SDPlusDao {

	public List<SdPlus> getAllData(List<String> shipperNames, String startDate,
			String endDate);

	public List<String> getModes();

	public List<String> getShippers();

	public List<SdPlusFilter> genericGroup(String q, List<String> shipperNames,
			String shipper, String startDate, String endDate, String mode);

}
