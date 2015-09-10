package com.snapdeal.dao;

import java.util.List;

import com.snapdeal.entity.Cancellation;
import com.snapdeal.entity.Shipper;

public interface CancellationDao {

	public List<Cancellation> getAllData(List<String> shipperList,String date);
	
	public List<Cancellation> getPincodeData(List<String> shipperList,List<String> pincodes,String date);
	
	public List<String> getModes();
	
	public List<String> getShippers();
	
	public List<String> getShipperGroups();
	
}
