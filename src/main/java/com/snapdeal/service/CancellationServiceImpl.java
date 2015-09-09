package com.snapdeal.service;

import javax.inject.Named;

import com.snapdeal.entity.Cancellation;

@Named("cancellationService")
public class CancellationServiceImpl implements CancellationService {

	@Override
	public String generateCancellationData(Cancellation[] data) {
		StringBuffer content = new StringBuffer();
		
		content.append("Shipper Group,Shipper,Mode,Center,Seller Pincode,Seller Name,Seller Code," +
				"AWB Number,Suborder Code,Order Date,SOI Status Code,Created");
		
		for(Cancellation cancellation : data)
		{
			content.append("\n"+cancellation.getShipperGroup()+","+cancellation.getShipper()+","+cancellation.getMode()
					+","+cancellation.getCenter()+","+cancellation.getSellerPinCode()+","+cancellation.getSellerName()
					+","+cancellation.getSellerCode()+","+cancellation.getAwbNumber()+","+cancellation.getSuborderCode()
					+","+cancellation.getOrderDate()+","+cancellation.getSoiStatusCode()+","+cancellation.getCreated());
		}
		return content.toString();
		
	}
}
