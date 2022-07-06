package com.myapp.candyshop.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "shop")
public class Shop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shop_id", nullable = false)
	private int shopId;
	@Column(name = "shop_name", nullable = false)
	private String shopName;
	@OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST)
	List<Candy> candy;

	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Shop(int shopId, String shopName, List<Candy> candy) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.candy = candy;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<Candy> getCandy() {
		return candy;
	}

	public void setCandy(List<Candy> candy) {
		this.candy = candy;
	}
}
