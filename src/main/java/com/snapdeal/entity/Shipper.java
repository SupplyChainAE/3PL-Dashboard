package com.snapdeal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shipper")
public class Shipper extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "shipper", nullable = false)
	private String courier;

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public String getCourier() {
		return courier;
	}

}
