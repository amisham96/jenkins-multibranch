package com.myapp.candyshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.candyshop.dto.ResponseBody;
import com.myapp.candyshop.dto.ShopDto;
import com.myapp.candyshop.entity.Shop;
import com.myapp.candyshop.exception.CandyShopApplicationException;
import com.myapp.candyshop.service.ShopService;

@RestController
@RequestMapping("/controller")
public class ShopController {
	@Autowired
	ShopService shopService;
	@Autowired
	ModelMapper modelMapper;

	@PostMapping("/addShop")
	public ResponseEntity<?> addShop(@Valid @RequestBody ShopDto shop) throws CandyShopApplicationException {

		return new ResponseEntity<ResponseBody<ShopDto>>(new ResponseBody<ShopDto>(
				modelMapper.map(shopService.addShop(modelMapper.map(shop, Shop.class)), ShopDto.class), null,
				"Shop Added Sucessfully", true), HttpStatus.OK);
	}

	@GetMapping("/getAllShop")
	public ResponseEntity<?> getAllShop() throws CandyShopApplicationException {
		return new ResponseEntity<ResponseBody<List<Shop>>>(
				new ResponseBody<List<Shop>>(shopService.getAllShops(), null, "All Shops Found", true), HttpStatus.OK);
	}

	@DeleteMapping("/deletedShop/{shopId}")
	public ResponseEntity<?> deletedShop(@PathVariable int shopId) throws CandyShopApplicationException {
		shopService.deleteShop(shopId);
		return new ResponseEntity<ResponseBody<Void>>(new ResponseBody<Void>(null, null, "Shop Deleted", true),
				HttpStatus.OK);
	}

	@PutMapping("/updateShop/{shopId}")
	public ResponseEntity<?> updateShop(@Valid @RequestBody Shop shop, @PathVariable int shopId)
			throws CandyShopApplicationException {

		return new ResponseEntity<ResponseBody<Shop>>(
				new ResponseBody<Shop>(shopService.updateShop(shop, shopId), null, "Shop Updated", true),
				HttpStatus.OK);
	}
}
