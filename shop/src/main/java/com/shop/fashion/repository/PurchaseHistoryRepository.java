package com.shop.fashion.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.fashion.model.Basket;
import com.shop.fashion.model.Purchasehistory;

public interface PurchaseHistoryRepository extends JpaRepository<Purchasehistory, Integer>{
	
	@Query(value = "SELECT * FROM Purchasehistory WHERE userId = ? GROUP BY tid", nativeQuery = true)
	List<Purchasehistory> purchaseHistoryGroupList(@Param("userId") int id);
	
	@Query(value = "SELECT * FROM Purchasehistory WHERE userId = ?", nativeQuery = true)
	List<Purchasehistory> purchaseHistoryList(@Param("userId") int id);
	
	@Query(value = "SELECT * FROM Purchasehistory WHERE userId = :userId and itemId = :itemId", nativeQuery = true)
	List<Purchasehistory> purchaseitemId(@Param("userId") int userId, @Param("itemId") int itemId);
	
	@Query(value = "SELECT * FROM Purchasehistory WHERE userId = :userId and createDate = :createDate", nativeQuery = true)
	List<Purchasehistory> mFindByUserIdAndCreateDate(@Param("userId") int userId, @Param("createDate") Timestamp createDate);
	
	@Query(value = "SELECT * FROM purchasehistory WHERE userId = :userId ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Purchasehistory mFindByUserId(@Param("userId") int userId);
	
}
