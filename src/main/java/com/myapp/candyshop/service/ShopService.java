package com.myapp.candyshop.service;

import java.util.List;

import com.myapp.candyshop.entity.Shop;
import com.myapp.candyshop.exception.service.CandyShopServiceException;



public interface ShopService {
	Shop addShop(Shop shop) throws CandyShopServiceException;

	List<Shop> getAllShops() throws CandyShopServiceException;

	String deleteShop(int shopId) throws CandyShopServiceException;
	
	Shop updateShop(Shop shop,int shopId) throws CandyShopServiceException;
}
