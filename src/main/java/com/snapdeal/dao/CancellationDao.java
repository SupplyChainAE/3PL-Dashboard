package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.entity.Cancellation;

public interface CancellationDao {

	public List<Cancellation> getAllData();
	
	public List<String> getModes();
	
	public List<String> getShippers();
	
	public List<String> getShipperGroups();
	
}
