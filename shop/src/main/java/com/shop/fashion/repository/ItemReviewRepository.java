package com.shop.fashion.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.fashion.model.ItemReview;

public interface ItemReviewRepository extends JpaRepository<ItemReview, Integer> {

	@Query(value = "SELECT * FROM ItemReview WHERE itemId = :itemId", nativeQuery = true)
	Page<ItemReview> ItemFindById(@Param("itemId") int itemId, Pageable pageable);
	
	@Query(value = "SELECT * FROM ItemReview WHERE id = :id", nativeQuery = true)
	List<ItemReview> ItemReviewFindById(@Param("id") int id);
}
