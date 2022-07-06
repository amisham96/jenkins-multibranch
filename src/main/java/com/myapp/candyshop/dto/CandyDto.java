package com.myapp.candyshop.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CandyDto {
	@Size(min = 4, message = "Candy name must be greater than or equal to 4")
	@NotNull
	private String candyName;
	@Min(value = 50, message = "Candy stock must be above or equal to 50")
	@Max(value = 300, message = "Candy stock must be below or equal to 300")
	private int candyStock;

	public CandyDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CandyDto(@Size(min = 4, message = "Candy name must be greater than or equal to 4") @NotNull String candyName,
			@Min(value = 50, message = "Candy stock must be above or equal to 50") @Max(value = 300, message = "Candy stock must be below or equal to 300") int candyStock) {
		super();
		this.candyName = candyName;
		this.candyStock = candyStock;
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


}
