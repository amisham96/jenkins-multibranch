package com.myapp.candyshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.candyshop.entity.Shop;


@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
	Optional<Shop> findByShopName(String shopName);
}
