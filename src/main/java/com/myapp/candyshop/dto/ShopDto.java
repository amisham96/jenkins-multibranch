package com.myapp.candyshop.dto;

import javax.validation.constraints.Size;

public class ShopDto {
	@Size(min = 5, message = "Shop name must be greater than or equal to 5")
	private String shopName;

	public ShopDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShopDto(@Size(min = 5, message = "Shop name must be greater than or equal to 5") String shopName) {
		super();
		this.shopName = shopName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
}
