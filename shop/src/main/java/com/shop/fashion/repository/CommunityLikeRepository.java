package com.shop.fashion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.fashion.model.CommunityLike;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Integer> {

	Optional<CommunityLike> findByBoardIdAndUserId(int boardId, int userId);

	List<CommunityLike> findByUserId(int userId);
	
	Page<CommunityLike> findByUserId(int userId, Pageable pageable);
}
