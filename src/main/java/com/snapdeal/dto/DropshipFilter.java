package com.snapdeal.dto;

public class DropshipFilter {

	private String shipperGroup;
    private String shipper;
    private String mode;
    private Long shippedToday;
    private Long notshippedOneDay;
    private Long notshippedTwoDays;
    private Long notshippedThreeDays;
    private Long notshippedFourDays;
    private Long notshippedMoreFourDays;
	
    public String getShipperGroup() {
		return shipperGroup;
	}
	public void setShipperGroup(String shipperGroup) {
		this.shipperGroup = shipperGroup;
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
	public Long getShippedToday() {
		return shippedToday;
	}
	public void setShippedToday(Long shippedToday) {
		this.shippedToday = shippedToday;
	}
	public Long getNotshippedOneDay() {
		return notshippedOneDay;
	}
	public void setNotshippedOneDay(Long notshippedOneDay) {
		this.notshippedOneDay = notshippedOneDay;
	}
	public Long getNotshippedTwoDays() {
		return notshippedTwoDays;
	}
	public void setNotshippedTwoDays(Long notshippedTwoDays) {
		this.notshippedTwoDays = notshippedTwoDays;
	}
	public Long getNotshippedThreeDays() {
		return notshippedThreeDays;
	}
	public void setNotshippedThreeDays(Long notshippedThreeDays) {
		this.notshippedThreeDays = notshippedThreeDays;
	}
	public Long getNotshippedFourDays() {
		return notshippedFourDays;
	}
	public void setNotshippedFourDays(Long notshippedFourDays) {
		this.notshippedFourDays = notshippedFourDays;
	}
	public Long getNotshippedMoreFourDays() {
		return notshippedMoreFourDays;
	}
	public void setNotshippedMoreFourDays(Long notshippedMoreFourDays) {
		this.notshippedMoreFourDays = notshippedMoreFourDays;
	}
    
    
}
