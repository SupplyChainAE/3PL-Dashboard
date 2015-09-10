package com.snapdeal.dto;

public class Filters {

	private String shipper;
	private String mode;
	private String shippergroup;
	private String daterange;
	private String pincode;

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getDaterange() {
		return daterange;
	}

	public void setDaterange(String daterange) {
		this.daterange = daterange;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getShippergroup() {
		return shippergroup;
	}

	public void setShippergroup(String shippergroup) {
		this.shippergroup = shippergroup;
	}

}
