package com.snapdeal.dao;

import java.util.List;

public interface PincodeDao {

	public List<String> getPincodeForZone(String zone);
	
	public List<String> getZones();
}
