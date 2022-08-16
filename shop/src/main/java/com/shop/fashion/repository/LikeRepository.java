package com.shop.fashion.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.shop.fashion.dto.LikeListDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class LikeRepository {
	
	private final EntityManager em;
	
	public List<LikeListDto> getLikeList(int boardId){
		List<LikeListDto> list = new ArrayList<>();
		
		String query = "SELECT b.imageUrl, b.username, b.name, b.id "
				+ "FROM communityLike AS a "
				+ "INNER JOIN user AS b "
				+ "ON a.userId = b.id "
				+ "WHERE a.boardId = " + boardId;
		
		Query nativeQuery = em.createNativeQuery(query);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		list = jpaResultMapper.list(nativeQuery, LikeListDto.class);
		
		return list;
	}
}
