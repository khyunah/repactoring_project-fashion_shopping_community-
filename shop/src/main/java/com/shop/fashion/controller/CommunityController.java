package com.shop.fashion.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.fashion.auth.PrincipalUserDetail;
import com.shop.fashion.dto.CommunityCountDto;
import com.shop.fashion.dto.CommunityDto;
import com.shop.fashion.dto.FormatPriceDto;
import com.shop.fashion.model.CommunityBoard;
import com.shop.fashion.model.CommunityLike;
import com.shop.fashion.model.Follow;
import com.shop.fashion.model.Item;
import com.shop.fashion.model.User;
import com.shop.fashion.service.CommunityService;
import com.shop.fashion.service.FollowService;
import com.shop.fashion.service.ShoppingService;
import com.shop.fashion.service.UserService;

@Controller
public class CommunityController {

	@Autowired
	private CommunityService communityService;
	@Autowired
	private ShoppingService shoppingService;
	@Autowired
	private UserService userService;
	@Autowired
	private FollowService followService;

	@GetMapping("/")
	public String index(Model model,
			@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable,
			@AuthenticationPrincipal PrincipalUserDetail userDetail) {
		Page<CommunityBoard> communityBoardList = communityService.getCommunityBoardList(pageable);
		model.addAttribute("communityBoardList", communityBoardList);

		List<Item> itemList = communityService.getItemList();
		Collections.shuffle(itemList);
		model.addAttribute("itemList", itemList);

		List<FormatPriceDto> formatPriceList = shoppingService.formatPrice(itemList);
		model.addAttribute("formatPriceList", formatPriceList);

		if (userDetail != null) {
			List<CommunityLike> likeList = communityService.myLike(userDetail.getUser().getId());
			model.addAttribute("likeList", likeList);
		}
		
		List<CommunityCountDto> replyCountList = communityService.getTotalReplyCountList();
		if(replyCountList.size() != 0) {
			model.addAttribute("replyCountList", replyCountList);
		} 

		return "index";
	}

	@GetMapping("/index/index-add")
	public String addIndex(Model model,
			@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable,
			@AuthenticationPrincipal PrincipalUserDetail userDetail) {
		Page<CommunityBoard> communityBoardList = communityService.getCommunityBoardList(pageable);
		model.addAttribute("communityBoardList", communityBoardList);

		if (userDetail != null) {
			List<CommunityLike> likeList = communityService.myLike(userDetail.getUser().getId());
			model.addAttribute("likeList", likeList);
		}
		
		List<CommunityCountDto> replyCountList = communityService.getTotalReplyCountList();
		if(replyCountList.size() != 0) {
			model.addAttribute("replyCountList", replyCountList);
		} 
		return "community/add_community_index";
	}

	@GetMapping("/board/write")
	public String write() {
		return "community/write_form";
	}

	@PostMapping("/board/upload")
	public String storyUpload(CommunityDto fileDto, @AuthenticationPrincipal PrincipalUserDetail detail) {
		CommunityBoard board = communityService.upload(fileDto, detail.getUser());
		return "redirect:/community/" + board.getId();
	}

	// 업데이트 화면
	@GetMapping("/board/{id}/update_form")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("boardList", communityService.boardDetail(id));
		return "community/update_form";
	}

	// 업데이트
	@PostMapping("/board/{id}/update")
	public String updateForm(@PathVariable int id, CommunityDto dto) {
		communityService.boardUpdate(id, dto);
		return "redirect:/community/" + id;
	}

	// 커뮤니티 상세보기
	@GetMapping("/community/{boardId}")
	public String communityDetail(@PathVariable int boardId, Model model,
			@AuthenticationPrincipal PrincipalUserDetail userDetail) {
		CommunityBoard board = communityService.detailCommunityBoard(boardId);
		model.addAttribute("communityBoard", board);

		CommunityLike checkLike = communityService.isLike(boardId, userDetail.getUser().getId());
		model.addAttribute("like", checkLike);
		
		ObjectMapper mapper = new ObjectMapper();
		String replyJsonList = null;
		try {
			replyJsonList = mapper.writeValueAsString(board.getReplies());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		model.addAttribute("replyJsonList", replyJsonList);

		return "community/community_detail";
	}

	// 소셜 메인 처음 랜더링 주소
	@GetMapping("/community/social-main")
	public String communityHome(@PageableDefault(size = 3, direction = Direction.DESC, sort = "id") Pageable pageable,
			Model model, @AuthenticationPrincipal PrincipalUserDetail userDetail) {
		Page<CommunityBoard> communityBoardList = communityService.getCommunityBoardList(pageable);
		model.addAttribute("communityBoardList", communityBoardList);

		List<CommunityLike> likeList = communityService.myLike(userDetail.getUser().getId());
		model.addAttribute("likeList", likeList);
		
		List<CommunityCountDto> replyCountList = communityService.getTotalReplyCountList();
		if(replyCountList.size() != 0) {
			model.addAttribute("replyCountList", replyCountList);
		} 
		return "community/community_social";
	}

	// 소셜 메인 스크롤시 랜더링 주소
	@GetMapping("/community/social-add")
	public String addCommunityBoard(
			@PageableDefault(size = 3, direction = Direction.DESC, sort = "id") Pageable pageable, Model model,
			@AuthenticationPrincipal PrincipalUserDetail userDetail) {
		Page<CommunityBoard> communityBoardList = communityService.getCommunityBoardList(pageable);
		model.addAttribute("communityBoardList", communityBoardList);

		List<CommunityLike> likeList = communityService.myLike(userDetail.getUser().getId());
		model.addAttribute("likeList", likeList);
		return "community/add_community_board";
	}

	// 나의 소셜 페이지
	@GetMapping("/community/user/{userId}")
	public String myCommunityPage(@PathVariable int userId, @PageableDefault(size = 9, direction = Direction.DESC, sort = "id") Pageable pageable,
			Model model, @AuthenticationPrincipal PrincipalUserDetail userDetail) {
		Page<CommunityBoard> myCommu = communityService.myCommunity(userId, pageable);
		model.addAttribute("communityBoardList", myCommu);
		model.addAttribute("boardCount", myCommu.getTotalElements());
		
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		
		long followCount = followService.followCount(userId);
		long followerCount = followService.followerCount(userId);
		Follow isFollow = followService.checkFollow(userId, userDetail.getUser().getId());
		if(isFollow != null) {
			model.addAttribute("isFollow", true);
		}
		model.addAttribute("followCount", followCount);
		model.addAttribute("followerCount", followerCount);
		return "community/community_my_social";
	}

	// 나의 소셜 페이지
	@GetMapping("/community/user/{userId}/add")
	public String addMyCommunityPage(@PathVariable int userId, 
			@PageableDefault(size = 9, direction = Direction.DESC, sort = "id") Pageable pageable, Model model) {
		Page<CommunityBoard> myCommu = communityService.myCommunity(userId, pageable);
		model.addAttribute("communityBoardList", myCommu);
		return "community/add_community_my";
	}
	
	// 나의 like 페이지
	@GetMapping("/community/my-like/{userId}")
	public String addMyLikePage(@PathVariable int userId, 
			@PageableDefault(size = 9, direction = Direction.DESC, sort = "id") Pageable pageable, Model model) {
		Page<CommunityLike> likeCommu = communityService.myLikeCommunity(userId, pageable);
		model.addAttribute("communityLikeList", likeCommu);
		return "community/add_community_my_like";
	}
	
	// 팔로우
	@GetMapping("/community/follow/{userId}")
	public String saveFollow(@PathVariable int userId, @AuthenticationPrincipal PrincipalUserDetail userDetail) {
		followService.followSave(userId, userDetail.getUser());
		return "redirect:/community/user/" + userId;
	}
	
	// 팔로우 취소
	@GetMapping("/community/follow-cancle/{userId}")
	public String cancleFollow(@PathVariable int userId, @AuthenticationPrincipal PrincipalUserDetail userDetail) {
		followService.cancleFollow(userId, userDetail.getUser().getId());
		return "redirect:/community/user/" + userId;
	}

}
