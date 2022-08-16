package com.shop.fashion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.fashion.model.CommunityBoard;

public interface CommunityRepository extends JpaRepository<CommunityBoard, Integer> {

	Page<CommunityBoard> findByUserId(int userId, Pageable pageable);

	@Query(value = "SELECT * FROM communityBoard WHERE id = :id", nativeQuery = true)
	Page<CommunityBoard> mFindById(@Param("id") int id, Pageable pageable);

	@Query(value = "SELECT * FROM communityBoard WHERE userId = :userId", nativeQuery = true)
	Page<CommunityBoard> mFindByUser(@Param("userId") int userId, Pageable pageable);

	@Query(value = "SELECT * FROM communityBoard WHERE title LIKE %:title%", nativeQuery = true)
	Page<CommunityBoard> mFindByTitle(@Param("title") String title, Pageable pageable);

	long deleteByUserId(int userId);
}
