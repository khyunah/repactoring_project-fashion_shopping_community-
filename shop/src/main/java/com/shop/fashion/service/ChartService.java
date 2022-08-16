package com.shop.fashion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.fashion.dto.chart.CommunityActionCountDto;
import com.shop.fashion.dto.chart.CommunityBoardCountDto;
import com.shop.fashion.dto.chart.CommunityTotalCountDto;
import com.shop.fashion.dto.chart.JoinCountDto;
import com.shop.fashion.dto.chart.OAuthCountDto;
import com.shop.fashion.dto.chart.ShoppingCountAndSumDto;
import com.shop.fashion.dto.chart.ShoppingItemDto;
import com.shop.fashion.repository.CommunityChartRepository;
import com.shop.fashion.repository.ShoppingChartRepository;
import com.shop.fashion.repository.UserChartRepository;

@Service
public class ChartService {
	
	@Autowired
	private UserChartRepository userChartRepository;
	@Autowired
	private ShoppingChartRepository shoppingChartRepository;
	@Autowired
	private CommunityChartRepository communityChartRepository;
	
	// 일자별 가입자수 
	public List<JoinCountDto> getWeekJoinCountList(){
		return userChartRepository.getWeekJoinCountList();
	}

	// 총 가입자수
	public List<JoinCountDto> getTotalJoinCountList(){
		return userChartRepository.getTotalJoinCountList();
	}
	
	// oauth 별 가입자 수
	public List<OAuthCountDto> getOAuthJoinCountList(){
		return userChartRepository.getOAuthJoinCountList();
	}
	
	// oauth 별 오늘 가입자 수
	public List<OAuthCountDto> getOAuthTodayJoinCountList(){
		return userChartRepository.getOAuthTodayJoinCountList();
	}
	
	// 금주 판매 금액, 판매량
	public List<ShoppingCountAndSumDto> getWeekSalesList(){
		return shoppingChartRepository.getWeekSalesList();
	}
	
	// 총 판매금액, 판매량 
	public List<ShoppingCountAndSumDto> getTotalSalesList(){
		return shoppingChartRepository.getTotalSalesList();
	}
	
	// 금주 아이템별 판매금액, 판매량
	public List<ShoppingItemDto> getItemSalesList(){
		return shoppingChartRepository.getItemSalesList();
	}
	
	// 이번달 가장 많이 구매한 유저 top5 
	public List<ShoppingItemDto> getTop5UserList(){
		return shoppingChartRepository.getTop5UserList();
	}
	
	// 카테고리별 판매 금액, 판매량 
	public List<ShoppingItemDto> getMonthCategoryList(){
		return shoppingChartRepository.getMonthCategoryList();
	}
	
	// 금주 커뮤니티 좋아요 top5
	public List<CommunityActionCountDto> getHeartCountList(){
		return communityChartRepository.getHeartCountList();
	}
	
	// 금주 커뮤니티 댓글 top5
	public List<CommunityActionCountDto> getReplyCountList(){
		return communityChartRepository.getReplyCountList();
	}
	
	// 한달 게시글 수 
	public List<CommunityTotalCountDto> getBoardCountList(){
		return communityChartRepository.getBoardCountList();
	}
	
	// 총 게시글 수
	public List<CommunityBoardCountDto> getBoardTotalCountList(){
		return communityChartRepository.getBoardTotalCountList();
	}
	
	// 오늘 게시글 수
	public List<CommunityBoardCountDto> getBoardTodayCountList(){
		return communityChartRepository.getBoardTodayCountList();
	}
	
	// 이번주 게시글 수
	public List<CommunityBoardCountDto> getBoardWeekCountList(){
		return communityChartRepository.getBoardWeekCountList();
	}
}
