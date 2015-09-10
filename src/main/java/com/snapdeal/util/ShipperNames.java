package com.snapdeal.util;

import java.util.ArrayList;
import java.util.List;

import com.snapdeal.entity.Shipper;

public class ShipperNames {

	public static List<String> getNamesFromShippers(List<Shipper> shipperList)
	{
		List<String> names = new ArrayList<String>();
		
		for(Shipper shipper : shipperList)
		{
			names.add(shipper.getCourier());
		}
		return names;
	}
}
