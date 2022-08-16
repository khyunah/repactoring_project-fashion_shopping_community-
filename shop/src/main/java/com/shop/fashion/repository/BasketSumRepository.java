package com.shop.fashion.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.shop.fashion.dto.BasketSumDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BasketSumRepository {
	
	private final EntityManager em;
	
	// ..
	public List<BasketSumDto> getBasketSum(int userid) {

		List<BasketSumDto> dto = new ArrayList<BasketSumDto>();
		
		// DB mysql ㅠBIndg 
		// iINt 
		String quertyStr = "SELECT "
				+ " IFNULL("
				+ "			(SELECT sum(b.count * i.price) as sum  "
				+ "			FROM basket as b inner join item as i on  b.item_id = i.id "
				+ "         WHERE userid ="+userid+"), 0) AS sum " ;
	
		Query nativeQuery = em.createNativeQuery(quertyStr);
		
		// QLRM  
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		// list 
		dto = jpaResultMapper.list(nativeQuery, BasketSumDto.class);
		
		return dto;
	}
}
