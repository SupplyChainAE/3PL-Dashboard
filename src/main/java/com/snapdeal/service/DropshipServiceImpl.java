package com.snapdeal.service;

import java.util.List;

import javax.inject.Named;

import com.snapdeal.dto.DropshipFilter;
import com.snapdeal.entity.Dropship;

@Named("dropshipService")
public class DropshipServiceImpl implements DropshipService {

	@Override
	public String generateDropshipData(List<Dropship> data)
	{
		StringBuffer content = new StringBuffer();
		
		content.append("Shipper Group,Shipper,Mode,Center,Seller Pincode,Seller State,Seller City," +
				"Shipped Today,Not Shipped One Day,Not Shipped Two Days,Not Shipped Three Days," +
				"Not Shipped Four Days,Not Shipped More than Four Days");
		
		for(Dropship dropship : data)
		{
			content.append("\n"+dropship.getShipperGroup()+","+dropship.getShipper()+","+dropship.getMode()
					+","+dropship.getCenter()+","+dropship.getSellerState()+","+dropship.getSellerCity()
					+","+dropship.getShippedToday()+","+dropship.getNotshippedOneDay()+","+dropship.getNotshippedTwoDays()
					+","+dropship.getNotshippedThreeDays()+","+dropship.getNotshippedThreeDays()
					+","+dropship.getNotshippedFourDays()+","+dropship.getNotshippedFourDays());
		}
		return content.toString();
		
	}

	@Override
	public String generateDropshipFilterData(List<DropshipFilter> data)
	{
		StringBuffer content = new StringBuffer();
		
		content.append("Shipper Group,Shipper,Shipped Today,Not Shipped One Day," +
				"Not Shipped Two Days,Not Shipped Three Days,Not Shipped Four Days,Not Shipped More than Four Days");
		
		for(DropshipFilter dropship : data)
		{
			content.append("\n"+dropship.getShipperGroup()+","+dropship.getShipper()+","+dropship.getMode()
					+","+dropship.getShippedToday()+","+dropship.getNotshippedOneDay()+","+dropship.getNotshippedTwoDays()
					+","+dropship.getNotshippedThreeDays()+","+dropship.getNotshippedThreeDays()
					+","+dropship.getNotshippedFourDays()+","+dropship.getNotshippedFourDays());
		}
		return content.toString();
		
	}
}
