package com.shop.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.fashion.model.Follow;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
	
	@Query(value = "SELECT count(*) FROM follow WHERE myId = :myId", nativeQuery = true)
	long mCountByMyId(@Param(value = "myId") int myId);
	
	@Query(value = "SELECT * FROM follow WHERE myId = :myId AND followUserId = :followUserId", nativeQuery = true)
	Follow mFindByMyIdAndFollowUserId(@Param(value = "myId") int myId, @Param(value = "followUserId") int followUserId);
	
	@Query(value = "SELECT * FROM follow WHERE myId = :myId", nativeQuery = true)
	List<Follow> mFindByMyId(@Param(value = "myId") int myId);
}
