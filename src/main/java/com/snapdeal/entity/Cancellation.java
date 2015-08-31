package com.snapdeal.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cancellations")
public class Cancellation {
	
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
	
	@Column(name="AWB_number")
	private String awbNumber;
	
	@Column(name="suborder_code")
	private String suborderCode;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="soi_status_code")
	private String soiStatusCode;
	
	@Column(name="Seller_Code")
	private String sellerCode;
	
	@Column(name="Seller_Name")
	private String sellerName;

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

	public String getAwbNumber() {
		return awbNumber;
	}

	public void setAwbNumber(String awbNumber) {
		this.awbNumber = awbNumber;
	}

	public String getSuborderCode() {
		return suborderCode;
	}

	public void setSuborderCode(String suborderCode) {
		this.suborderCode = suborderCode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getSoiStatusCode() {
		return soiStatusCode;
	}

	public void setSoiStatusCode(String soiStatusCode) {
		this.soiStatusCode = soiStatusCode;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	

}