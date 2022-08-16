package com.shop.fashion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.fashion.dto.LikeListDto;
import com.shop.fashion.repository.LikeRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepository;
	
	public List<LikeListDto> getLikeList(int boardId){
		return likeRepository.getLikeList(boardId);
	}
	
}
