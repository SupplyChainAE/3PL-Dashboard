package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.dto.SdPlusFilter;
import com.snapdeal.entity.SdPlus;

public interface SDPlusDao {
	
	public List<SdPlus> getAllData(String startDate,String endDate);
	
	public List<String> getModes();
	
	public List<String> getShippers();
	
	public List<String> getShipperGroups();

	public List<SdPlusFilter> groupByModeShipper(String startDate,String endDate,String mode,String shipper);

	public List<SdPlusFilter> groupByModeGroupShipper(String startDate,String endDate);

	public List<SdPlusFilter> groupByGroup(String startDate,String endDate);

	public List<SdPlusFilter> groupByMode(String startDate,String endDate,String mode);

	public List<SdPlusFilter> groupByShipper(String startDate,String endDate,String shipper);

	public List<SdPlusFilter> groupByGroupShipper(String startDate,String endDate);

	public List<SdPlusFilter> groupByModeGroup(String startDate,String endDate,String mode);
}
