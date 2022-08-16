package com.shop.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.fashion.model.Reply;

public interface CommunityReplyRepository extends JpaRepository<Reply, Integer>{
	
}
