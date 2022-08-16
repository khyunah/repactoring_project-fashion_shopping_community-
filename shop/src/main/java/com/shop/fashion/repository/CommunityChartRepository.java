package com.shop.fashion.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.shop.fashion.dto.chart.CommunityActionCountDto;
import com.shop.fashion.dto.chart.CommunityBoardCountDto;
import com.shop.fashion.dto.chart.CommunityTotalCountDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CommunityChartRepository {
	
	private final EntityManager em;
	
	// 좋아요 top5
	public List<CommunityActionCountDto> getHeartCountList(){
		List<CommunityActionCountDto> list = new ArrayList<>();
		
		String query = "SELECT b.id, b.title, c.username, COUNT(a.boardId) AS likeCount "
				+ "FROM communityLike AS a "
				+ "INNER JOIN communityBoard AS b "
				+ "ON a.boardId = b.id "
				+ "INNER JOIN user AS c "
				+ "ON b.userId = c.id "
				+ "GROUP BY a.boardId "
				+ "ORDER BY likeCount DESC "
				+ "LIMIT 5 ";
		
		Query nativeQuery = em.createNativeQuery(query);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		list = jpaResultMapper.list(nativeQuery, CommunityActionCountDto.class);
		
		return list;
	}
	
	// 댓글 top5
	public List<CommunityActionCountDto> getReplyCountList(){
		List<CommunityActionCountDto> list = new ArrayList<>();
		
		String query = "SELECT b.id, b.title, c.username, COUNT(a.boardId) AS count "
				+ "FROM reply AS a "
				+ "INNER JOIN communityBoard AS b "
				+ "ON a.boardId = b.id "
				+ "INNER JOIN user AS c "
				+ "ON b.userId = c.id "
				+ "GROUP BY a.boardId "
				+ "ORDER BY count DESC "
				+ "LIMIT 5 ";
		
		Query nativeQuery = em.createNativeQuery(query);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		list = jpaResultMapper.list(nativeQuery, CommunityActionCountDto.class);
		
		return list;
	}
	
	// 한달 게시글 수 
	public List<CommunityTotalCountDto> getBoardCountList(){
		List<CommunityTotalCountDto> list = new ArrayList<>();
		
		String query = "SELECT day(createDate) AS date, COUNT(createDate) AS count "
				+ "FROM communityBoard "
				+ "WHERE MONTH(createDate) = MONTH(NOW()) "
				+ "GROUP BY DAYOFYEAR(createDate) ";
		
		Query nativeQuery = em.createNativeQuery(query);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		list = jpaResultMapper.list(nativeQuery, CommunityTotalCountDto.class);
		
		return list;
	}
	
	// 총 게시글 수 
	public List<CommunityBoardCountDto> getBoardTotalCountList(){
		List<CommunityBoardCountDto> list = new ArrayList<>();
		
		String query = "SELECT day(createDate) AS date, COUNT(*) AS count "
				+ "FROM communityBoard ";
		
		Query nativeQuery = em.createNativeQuery(query);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		list = jpaResultMapper.list(nativeQuery, CommunityBoardCountDto.class);
		
		return list;
	}
	
	// 오늘 게시글 수
	public List<CommunityBoardCountDto> getBoardTodayCountList(){
		List<CommunityBoardCountDto> list = new ArrayList<>();
		
		String query = "SELECT day(createDate) AS date, COUNT(*) AS count "
				+ "FROM communityBoard "
				+ "WHERE DAY(createDate) = DAY(NOW()) ";
		
		Query nativeQuery = em.createNativeQuery(query);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		list = jpaResultMapper.list(nativeQuery, CommunityBoardCountDto.class);
		
		return list;
	}
	
	// 이번주 게시글 수
	public List<CommunityBoardCountDto> getBoardWeekCountList(){
		List<CommunityBoardCountDto> list = new ArrayList<>();
		
		String query = "SELECT day(createDate) AS date, COUNT(*) AS count "
				+ "FROM communityBoard "
				+ "WHERE WEEK(createDate) = WEEK(NOW()) ";
		
		Query nativeQuery = em.createNativeQuery(query);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		list = jpaResultMapper.list(nativeQuery, CommunityBoardCountDto.class);
		
		return list;
	}
	
}
