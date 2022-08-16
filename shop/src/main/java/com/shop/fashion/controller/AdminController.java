package com.shop.fashion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.fashion.dto.FormatPriceDto;
import com.shop.fashion.model.CommunityBoard;
import com.shop.fashion.model.Item;
import com.shop.fashion.model.User;
import com.shop.fashion.service.AdminService;
import com.shop.fashion.service.ShoppingService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private ShoppingService shoppingService;

	// 회원정보 조회 / 검색
	@GetMapping("/admin/user/select")
	public String selectUser(@RequestParam String keyword, @RequestParam String column,
			@PageableDefault(size = 20, sort = "id", direction = Direction.DESC) Pageable pageable, Model model) {
		Page<User> userPage = null;

		switch (column) {
		case "":
			userPage = adminService.getUserAll(pageable);
			break;
		default:
			userPage = adminService.searchUserKeywordPage(keyword, column, pageable);
			model.addAttribute("column", column);
			model.addAttribute("keyword", keyword);
			break;
		}

		int nowPage = userPage.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 2, 1);
		int endPage = Math.min(nowPage + 2, userPage.getTotalPages());

		ArrayList<Integer> pageNumbers = new ArrayList<>();

		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}

		model.addAttribute("userPage", userPage);
		model.addAttribute("pageNumbers", pageNumbers);
		return "admin/setting_user";
	}

	// 상품정보 조회 / 검색
	@GetMapping("/admin/shopping/select")
	public String selectShopping(@RequestParam String keyword, @RequestParam String column,
			@PageableDefault(size = 16, sort = "id", direction = Direction.DESC) Pageable pageable, Model model) {
		Page<Item> itemPage = null;

		switch (column) {
		case "":
			itemPage = adminService.getItemAll(pageable);
			break;
		default:
			itemPage = adminService.searchItemKeywordPage(keyword, column, pageable);
			model.addAttribute("column", column);
			model.addAttribute("keyword", keyword);
			break;
		}
		
		List<Item> itemList = itemPage.toList();
		List<FormatPriceDto> formatPriceList = shoppingService.formatPrice(itemList);

		int nowPage = itemPage.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 2, 1);
		int endPage = Math.min(nowPage + 2, itemPage.getTotalPages());

		ArrayList<Integer> pageNumbers = new ArrayList<>();

		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}

		model.addAttribute("itemPage", itemPage);
		model.addAttribute("formatPriceList", formatPriceList);
		model.addAttribute("pageNumbers", pageNumbers);

		return "admin/setting_shopping";
	}

	// 상품 상세보기
	@GetMapping("/admin/shopping/item-detail/{id}")
	public String detailItemDetail(@PathVariable int id, Model model) {
		Item item = adminService.getItem(id);
		model.addAttribute("item", item);
		return "admin/admin_shopping_detail";
	}

	// 상품 등록 페이지
	@GetMapping("/admin/shopping/save_form")
	public String shoppingSaveForm() {
		return "admin/admin_shopping_save_form";
	}

	// 상품 수정 페이지
	@GetMapping("/admin/shopping-item/update_form/{id}")
	public String updateFormItem(@PathVariable int id, Model model) {
		Item item = adminService.getItem(id);
		model.addAttribute("item", item);
		return "admin/admin_shopping_update_form";
	}

	// 커뮤니티 정보 조회 / 검색
	@GetMapping("/admin/community/select")
	public String searchCommunity(@RequestParam String keyword, @RequestParam String column,
			@PageableDefault(size = 20, sort = "id", direction = Direction.DESC) Pageable pageable, Model model) {
		Page<CommunityBoard> communityBoardPage = null;

		switch (column) {
		case "":
			communityBoardPage = adminService.getBoardAll(pageable);
			break;
		default:
			communityBoardPage = adminService.searchCommunityKeywordPage(keyword, column, pageable);
			model.addAttribute("column", column);
			model.addAttribute("keyword", keyword);
			break;
		}

		int nowPage = communityBoardPage.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 2, 1);
		int endPage = Math.min(nowPage + 2, communityBoardPage.getTotalPages());

		ArrayList<Integer> pageNumbers = new ArrayList<>();

		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}

		model.addAttribute("communityBoardPage", communityBoardPage);
		model.addAttribute("pageNumbers", pageNumbers);
		return "admin/setting_community";
	}
	
	// 커뮤니티 상세보기
	@GetMapping("/admin/community-detail/{id}")
	public String detailCommunity(@PathVariable int id, Model model) {
		CommunityBoard communityBoard = adminService.detailCommunityBoard(id);
		model.addAttribute("communityBoard", communityBoard);
		return "admin/admin_commu_detail";
	}
	
}
