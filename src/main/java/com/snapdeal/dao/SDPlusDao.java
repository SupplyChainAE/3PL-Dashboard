package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.dto.SdPlusFilter;
import com.snapdeal.entity.SdPlus;

public interface SDPlusDao {
	
	public List<SdPlus> getAllData();
	
	public List<String> getModes();
	
	public List<String> getShippers();
	
	public List<String> getShipperGroups();

	public List<SdPlusFilter> groupByModeShipper();

	public List<SdPlusFilter> groupByModeGroupShipper();

	public List<SdPlusFilter> groupByGroup();

	public List<SdPlusFilter> groupByMode();

	public List<SdPlusFilter> groupByShipper();

	public List<SdPlusFilter> groupByGroupShipper();

	public List<SdPlusFilter> groupByModeGroup();
}
