package com.shop.fashion.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.shop.fashion.dto.chart.JoinCountDto;
import com.shop.fashion.dto.chart.OAuthCountDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserChartRepository {

	private final EntityManager em;

	// 오늘까지의 7일간 일별 가입자수
	public List<JoinCountDto> getWeekJoinCountList() {
		List<JoinCountDto> list = new ArrayList<JoinCountDto>();

		String query = "SELECT DATE(a.createDate) AS joinDate,COUNT(createDate) AS joinCount "
				+ "FROM user AS a "
				+ "WHERE DAYOFYEAR(createDate) BETWEEN DAYOFYEAR(NOW()) -6 AND DAYOFYEAR(NOW()) "
				+ "GROUP BY DAYOFYEAR(createDate) "
				+ "ORDER BY joinDate ";

		Query nativeQuery = em.createNativeQuery(query);

		JpaResultMapper jpaResultMapper = new JpaResultMapper();

		list = jpaResultMapper.list(nativeQuery, JoinCountDto.class);
		return list;
	}
	
	// 이번주 총 가입자 수 
	public List<JoinCountDto> getWeekTotalJoinCountList() {
		List<JoinCountDto> list = new ArrayList<JoinCountDto>();

		String query = "SELECT DATE(a.createDate) AS joinDate,COUNT(createDate) AS joinCount "
				+ "FROM user AS a "
				+ "WHERE DAYOFYEAR(createDate) BETWEEN DAYOFYEAR(NOW()) -6 AND DAYOFYEAR(NOW()) ";

		Query nativeQuery = em.createNativeQuery(query);

		JpaResultMapper jpaResultMapper = new JpaResultMapper();

		list = jpaResultMapper.list(nativeQuery, JoinCountDto.class);
		return list;
	}

	// 오늘 가입자수
	public List<JoinCountDto> getTodayJoinCountList() {
		List<JoinCountDto> list = new ArrayList<JoinCountDto>();

		String query = "SELECT DATE(a.createDate) AS joinDate,COUNT(createDate) AS joinCount "
				+ "FROM user AS a "
				+ "WHERE DAYOFYEAR(createDate) = DAYOFYEAR(now()) "
				+ "GROUP BY DAYOFYEAR(createDate) ";

		Query nativeQuery = em.createNativeQuery(query);

		JpaResultMapper jpaResultMapper = new JpaResultMapper();

		list = jpaResultMapper.list(nativeQuery, JoinCountDto.class);
		return list;
	}

	// 총 가입자수
	public List<JoinCountDto> getTotalJoinCountList() {
		List<JoinCountDto> list = new ArrayList<JoinCountDto>();

		String query = "SELECT DATE(a.createDate) AS joinDate, COUNT(createDate) AS joinCount "
				+ "FROM user AS a ";

		Query nativeQuery = em.createNativeQuery(query);

		JpaResultMapper jpaResultMapper = new JpaResultMapper();

		list = jpaResultMapper.list(nativeQuery, JoinCountDto.class);
		return list;
	}
	
	// OAuth 별 총 가입자 수 
	public List<OAuthCountDto> getOAuthJoinCountList() {
		List<OAuthCountDto> list = new ArrayList<OAuthCountDto>();

		String query = "SELECT oauth, COUNT(*) AS count "
				+ "FROM user "
				+ "GROUP BY oauth ";

		Query nativeQuery = em.createNativeQuery(query);

		JpaResultMapper jpaResultMapper = new JpaResultMapper();

		list = jpaResultMapper.list(nativeQuery, OAuthCountDto.class);
		return list;
	}
	
	// OAuth 별 오늘 가입자 수 
	public List<OAuthCountDto> getOAuthTodayJoinCountList() {
		List<OAuthCountDto> list = new ArrayList<OAuthCountDto>();

		String query = "SELECT oauth, COUNT(*) AS count "
				+ "FROM user "
				+ "WHERE DAYOFYEAR(createDate) = DAYOFYEAR(NOW()) "
				+ "GROUP BY oauth ";

		Query nativeQuery = em.createNativeQuery(query);

		JpaResultMapper jpaResultMapper = new JpaResultMapper();

		list = jpaResultMapper.list(nativeQuery, OAuthCountDto.class);
		return list;
	}

}
