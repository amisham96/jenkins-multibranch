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

import com.myapp.candyshop.dto.CandyDto;
import com.myapp.candyshop.dto.ResponseBody;
import com.myapp.candyshop.entity.Candy;
import com.myapp.candyshop.exception.CandyShopApplicationException;
import com.myapp.candyshop.service.CandyService;

@RestController
@RequestMapping("/controller")
public class CandyController {
	@Autowired
	CandyService candyService;
	@Autowired
	ModelMapper modelMapper;

	@PostMapping("/addCandy/{shopId}")
	public ResponseEntity<?> addCandy(@Valid @RequestBody CandyDto candy, @PathVariable int shopId)
			throws CandyShopApplicationException {

		return new ResponseEntity<ResponseBody<CandyDto>>(new ResponseBody<CandyDto>(
				modelMapper.map(candyService.addCandy(modelMapper.map(candy, Candy.class), shopId), CandyDto.class),
				null, "Candy Added Sucessfully", true), HttpStatus.OK);
	}

	@GetMapping("/getAllCandy")
	public ResponseEntity<?> getAllCandy() throws CandyShopApplicationException {
		return new ResponseEntity<ResponseBody<List<Candy>>>(
				new ResponseBody<List<Candy>>(candyService.getAllCandy(), null, "All Candy Found", true),
				HttpStatus.OK);
	}

	@DeleteMapping("/deletedCandy/{candyId}")
	public ResponseEntity<?> deletedCandy(@PathVariable int candyId) throws CandyShopApplicationException {
		candyService.deleteCandy(candyId);
		return new ResponseEntity<ResponseBody<Void>>(new ResponseBody<Void>(null, null, "Candy Deleted", true),
				HttpStatus.OK);
	}

	@PutMapping("/updateCandy/{candyId}")
	public ResponseEntity<?> updateCandy(@Valid @RequestBody Candy candy, @PathVariable int candyId)
			throws CandyShopApplicationException {

		return new ResponseEntity<ResponseBody<Candy>>(
				new ResponseBody<Candy>(candyService.updateCandy(candy, candyId), null, "Candy Updated", true),
				HttpStatus.OK);
	}

	@GetMapping("/getAllCandyStockLessThanFive")
	public ResponseEntity<?> getAllCandyStockLessThanFive() throws CandyShopApplicationException {
		return new ResponseEntity<ResponseBody<List<Candy>>>(new ResponseBody<List<Candy>>(
				candyService.getAllCandyStockLessThanFive(), null, "All Candy Found", true), HttpStatus.OK);
	}

	@GetMapping("/getAllCandyByShopId")
	public ResponseEntity<?> getAllCandyByShopId(int shopId) throws CandyShopApplicationException {
		return new ResponseEntity<ResponseBody<List<Candy>>>(new ResponseBody<List<Candy>>(
				candyService.getAllCandyOfPerticularShop(shopId), null, "All Candy Found", true), HttpStatus.OK);
	}
}
