package com.myapp.candyshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "candy")
public class Candy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candy_id")
	private int candyId;

	@Column(name = "candy_name", nullable = false)
	private String candyName;

	@Column(name = "candy_stock", nullable = false)
	private int candyStock;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Shop shop;

	public Candy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candy(int candyId, String candyName, int candyStock, Shop shop) {
		super();
		this.candyId = candyId;
		this.candyName = candyName;
		this.candyStock = candyStock;
		this.shop = shop;
	}

	public int getCandyId() {
		return candyId;
	}

	public void setCandyId(int candyId) {
		this.candyId = candyId;
	}

	public String getCandyName() {
		return candyName;
	}

	public void setCandyName(String candyName) {
		this.candyName = candyName;
	}

	public int getCandyStock() {
		return candyStock;
	}

	public void setCandyStock(int candyStock) {
		this.candyStock = candyStock;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
