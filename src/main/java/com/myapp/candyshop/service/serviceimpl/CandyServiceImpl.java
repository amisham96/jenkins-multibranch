package com.myapp.candyshop.service.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.candyshop.entity.Candy;
import com.myapp.candyshop.entity.Shop;
import com.myapp.candyshop.exception.custom.CandyAlreadyPresentException;
import com.myapp.candyshop.exception.custom.CandyNotFoundException;
import com.myapp.candyshop.exception.custom.NoCandyPresentException;
import com.myapp.candyshop.exception.custom.ShopNotFoundException;
import com.myapp.candyshop.exception.service.CandyShopServiceException;
import com.myapp.candyshop.repository.CandyRepository;
import com.myapp.candyshop.repository.ShopRepository;
import com.myapp.candyshop.service.CandyService;

@Service
public class CandyServiceImpl implements CandyService {
	@Autowired
	CandyRepository candyRepository;
	@Autowired
	ShopRepository shopRepository;

	@Override
	public Candy addCandy(Candy candy, int shopId) throws CandyShopServiceException {
		// TODO Auto-generated method stub
		Shop shop = shopRepository.findById(shopId)
				.orElseThrow(() -> new ShopNotFoundException("Shop by this Id not found"));
		if (candyRepository.findBycandyName(candy.getCandyName()).isPresent())
			throw new CandyAlreadyPresentException("Candy by this name already present");
		candy.setShop(shop);
		Candy candy2 = candyRepository.save(candy);
		List<Candy> candy1 = shop.getCandy();
		candy1.add(candy);
		shop.setCandy(candy1);
		this.shopRepository.save(shop);
		return candy2;
	}

	@Override
	public List<Candy> getAllCandy() throws CandyShopServiceException {
		// TODO Auto-generated method stub
		if (candyRepository.findAll().isEmpty())
			throw new NoCandyPresentException("No candy present in the record");
//		List<Candy> data = candyRepository.findAll().stream().collect(Collectors.toList());
//		return data;
		return candyRepository.getAllCandy();
	}

	@Override
	public String deleteCandy(int candyId) throws CandyShopServiceException {
		// TODO Auto-generated method stub
		candyRepository.findById(candyId).orElseThrow(() -> new CandyNotFoundException("Candy by this Id not found"));
		candyRepository.deleteById(candyId);
		return "Candy deleted successfully";
	}

	@Override
	public Candy updateCandy(Candy candy, int candyId) throws CandyShopServiceException {
		// TODO Auto-generated method stub
		Candy candy1 = candyRepository.findById(candyId)
				.orElseThrow(() -> new CandyNotFoundException("Candy by this Id not found"));
		candy1.setCandyName(candy.getCandyName());
		candy1.setCandyStock(candy.getCandyStock());
		return candyRepository.save(candy1);
	}

	@Override
	public List<Candy> getAllCandyStockLessThanFive() {
		// TODO Auto-generated method stub
		return candyRepository.findAll().stream().filter(x -> x.getCandyStock() < 5).collect(Collectors.toList());
	}

	@Override
	public List<Candy> getAllCandyOfPerticularShop(int shopId) throws CandyShopServiceException {
		// TODO Auto-generated method stub
		Shop shop = shopRepository.findById(shopId)
				.orElseThrow(() -> new ShopNotFoundException("shop by thid id not found"));
		return shop.getCandy();
	}
}
