package com.myapp.candyshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myapp.candyshop.entity.Candy;


@Repository
public interface CandyRepository extends JpaRepository<Candy, Integer> {
	Optional<Candy> findBycandyName(String candyName);
	
	@Query("select c from Candy c")
	List<Candy> getAllCandy();
}
