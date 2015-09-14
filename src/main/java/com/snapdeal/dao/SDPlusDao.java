package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.dto.SdPlusFilter;
import com.snapdeal.entity.SdPlus;

public interface SDPlusDao {

	public List<SdPlus> getAllData(List<String> shipperNames, String startDate,
			String endDate);

	public List<String> getModes();

	public List<String> getShippers();

	public List<String> getShipperGroups();

	public List<SdPlusFilter> groupByModeShipper(List<String> shipperNames,
			String startDate, String endDate, String mode, String shipper);

	public List<SdPlusFilter> groupByModeGroupShipper(
			List<String> shipperNames, String startDate, String endDate);

	public List<SdPlusFilter> groupByGroup(List<String> shipperNames,
			String startDate, String endDate);

	public List<SdPlusFilter> groupByMode(List<String> shipperNames,
			String startDate, String endDate, String mode);

	public List<SdPlusFilter> groupByShipper(List<String> shipperNames,
			String startDate, String endDate, String shipper);

	public List<SdPlusFilter> groupByGroupShipper(List<String> shipperNames,
			String startDate, String endDate);

	public List<SdPlusFilter> groupByModeGroup(List<String> shipperNames,
			String startDate, String endDate, String mode);

	public List<SdPlusFilter> groupByModeGroupPincode(
			List<String> shipperNames, String startDate, String endDate,
			String mode, String pincode);

	public List<SdPlusFilter> genericGroup(String q, List<String> shipperNames,String shipper,
			String startDate, String endDate, String mode);

	public List<SdPlusFilter> groupByGroupPincode(List<String> shipperNames,
			String startDate, String endDate, String pincode);
}
