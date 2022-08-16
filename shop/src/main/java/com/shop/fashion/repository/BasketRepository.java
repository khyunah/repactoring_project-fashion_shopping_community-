package com.shop.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.fashion.model.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

	@Query(value = "SELECT * FROM basket WHERE userid = :id order by id desc", nativeQuery = true)
	List<Basket> findByUserId(@Param(value = "id") int id);
	
	@Query(value = "SELECT * FROM basket WHERE userid = ?", nativeQuery = true)
	List<Basket> mfindByUserId(@Param("userid") int userid);

	@Query(value = "select * from basket where item_id = ?1 And userid = ?2", nativeQuery = true)
	List<Basket> findByItemIdAndUserId(@Param(value = "itemid") int itemid, @Param(value = "userid") int userid);

	@Query(value = "SELECT * FROM basket WHERE userId = :userId GROUP BY item_id", nativeQuery = true)
	List<Basket> mFindByItemCount(@Param("userId") int userId);

}
