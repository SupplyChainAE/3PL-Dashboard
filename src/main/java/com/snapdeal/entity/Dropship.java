package com.snapdeal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dropship")
public class Dropship {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	 
	
	@Column(name="Shipper_group")
	private String shipperGroup;		 
	
	@Column(name="Shipper")
	private String shipper;	 
	
	@Column(name="Mode")
	private  String mode;		 
	
	@Column(name="Center")
	private String center;
	
	@Column(name="Seller_pin_code")
	private String sellerPinCode;
	
	@Column(name="Seller_city")
	private String sellerCity;
	
	@Column(name="Seller_state")
	private  String sellerState;	 
	
	@Column(name="Shipped_today")	 
	private Integer shippedToday;
	
	@Column(name="Not_Shipped_One_Day")	 
	private Integer notshippedOneDay;
	
	@Column(name="Not_Shipped_Two_Days")	 
	private Integer notshippedTwoDays;
	
	@Column(name="Not_Shipped_Three_Days")	 
	private Integer notshippedThreeDays;
	
	@Column(name="Not_Shipped_Four_Days")	 
	private Integer notshippedFourDays;
	
	@Column(name="Not_Shipped_More_than_Four_Days")
	private Integer notshippedMoreFourDays;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public Integer getShippedToday() {
		return shippedToday;
	}

	public void setShippedToday(Integer shippedToday) {
		this.shippedToday = shippedToday;
	}

	public Integer getNotshippedOneDay() {
		return notshippedOneDay;
	}

	public void setNotshippedOneDay(Integer notshippedOneDay) {
		this.notshippedOneDay = notshippedOneDay;
	}

	public Integer getNotshippedTwoDays() {
		return notshippedTwoDays;
	}

	public void setNotshippedTwoDays(Integer notshippedTwoDays) {
		this.notshippedTwoDays = notshippedTwoDays;
	}

	public Integer getNotshippedThreeDays() {
		return notshippedThreeDays;
	}

	public void setNotshippedThreeDays(Integer notshippedThreeDays) {
		this.notshippedThreeDays = notshippedThreeDays;
	}

	public Integer getNotshippedFourDays() {
		return notshippedFourDays;
	}

	public void setNotshippedFourDays(Integer notshippedFourDays) {
		this.notshippedFourDays = notshippedFourDays;
	}

	public Integer getNotshippedMoreFourDays() {
		return notshippedMoreFourDays;
	}

	public void setNotshippedMoreFourDays(Integer notshippedMoreFourDays) {
		this.notshippedMoreFourDays = notshippedMoreFourDays;
	}

	public String getSellerPinCode() {
		return sellerPinCode;
	}

	public void setSellerPinCode(String sellerPinCode) {
		this.sellerPinCode = sellerPinCode;
	}

	public String getSellerCity() {
		return sellerCity;
	}

	public void setSellerCity(String sellerCity) {
		this.sellerCity = sellerCity;
	}

	public String getSellerState() {
		return sellerState;
	}

	public void setSellerState(String sellerState) {
		this.sellerState = sellerState;
	}

	
}
