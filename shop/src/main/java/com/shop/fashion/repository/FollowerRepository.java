package com.shop.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.fashion.model.Follower;

public interface FollowerRepository extends JpaRepository<Follower, Integer> {
	
	@Query(value = "SELECT count(*) FROM follower WHERE myId = :myId", nativeQuery = true)
	long mCountByMyId(@Param(value = "myId") int myId);
	
	@Query(value = "SELECT * FROM follower WHERE myId = :myId AND followerUserId = :followerUserId", nativeQuery = true)
	Follower mFindByMyIdAndFollowerUserId(@Param(value = "myId") int myId, @Param(value = "followerUserId") int followerUserId);
	
	@Query(value = "SELECT * FROM follower WHERE myId = :myId", nativeQuery = true)
	List<Follower> mFindByMyId(@Param(value = "myId") int myId);
}
