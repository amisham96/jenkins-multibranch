package com.myapp.candyshop.service.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.candyshop.entity.Shop;
import com.myapp.candyshop.exception.custom.NoShopPresentException;
import com.myapp.candyshop.exception.custom.ShopAlreadyPresentException;
import com.myapp.candyshop.exception.custom.ShopNotFoundException;
import com.myapp.candyshop.exception.service.CandyShopServiceException;
import com.myapp.candyshop.repository.ShopRepository;
import com.myapp.candyshop.service.ShopService;



@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	ShopRepository shopRepository;

	@Override
	public Shop addShop(Shop shop) throws CandyShopServiceException {
		// TODO Auto-generated method stub
		if (shopRepository.findByShopName(shop.getShopName()).isPresent())
			throw new ShopAlreadyPresentException("Shop by this name already present");
		return shopRepository.save(shop);
	}

	@Override
	public List<Shop> getAllShops() throws CandyShopServiceException {
		// TODO Auto-generated method stub
		if (shopRepository.findAll().isEmpty())
			throw new NoShopPresentException("No shop present in record");
		List<Shop> data = shopRepository.findAll().stream().collect(Collectors.toList());
		return data;
	}

	@Override
	public String deleteShop(int shopId) throws CandyShopServiceException {
		// TODO Auto-generated method stub
		shopRepository.findById(shopId).orElseThrow(() -> new ShopNotFoundException("shop by this id not found"));
		shopRepository.deleteById(shopId);
		return "Shop deleted successfully";
	}

	@Override
	public Shop updateShop(Shop shop, int shopId) throws CandyShopServiceException {
		// TODO Auto-generated method stub
		Shop shop1 = shopRepository.findById(shopId)
				.orElseThrow(() -> new ShopNotFoundException("shop by this id not found"));
		shop1.setShopName(shop.getShopName());
		return shopRepository.save(shop1);
	}

}
