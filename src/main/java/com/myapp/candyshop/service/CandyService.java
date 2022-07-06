package com.myapp.candyshop.service;

import java.util.List;

import com.myapp.candyshop.entity.Candy;
import com.myapp.candyshop.exception.service.CandyShopServiceException;

public interface CandyService {
	Candy addCandy(Candy candy,int shopId) throws CandyShopServiceException;

	List<Candy> getAllCandy() throws CandyShopServiceException;

	String deleteCandy(int candyId) throws CandyShopServiceException;

	Candy updateCandy(Candy candy, int candyId) throws CandyShopServiceException;
	
	List<Candy> getAllCandyStockLessThanFive();
	
	List<Candy> getAllCandyOfPerticularShop(int shopId) throws CandyShopServiceException;
}
