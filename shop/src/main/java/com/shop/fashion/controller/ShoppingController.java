package com.shop.fashion.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.fashion.auth.PrincipalUserDetail;
import com.shop.fashion.dto.FormatPriceDto;
import com.shop.fashion.dto.ItemReviewDto;
import com.shop.fashion.dto.KakaoPayApprovalDto;
import com.shop.fashion.dto.KakaoPayDto;
import com.shop.fashion.dto.PurCountAndIdDto;
import com.shop.fashion.model.Basket;
import com.shop.fashion.model.Item;
import com.shop.fashion.model.ItemReview;
import com.shop.fashion.model.Purchasehistory;
import com.shop.fashion.repository.ItemReviewRepository;
import com.shop.fashion.repository.PurchaseHistoryRepository;
import com.shop.fashion.service.BasketService;
import com.shop.fashion.service.KakaoPayService;
import com.shop.fashion.service.PurchaseHistoryService;
import com.shop.fashion.service.ShoppingService;
import com.shop.fashion.service.UserService;

@Controller
public class ShoppingController {

	@Autowired
	HttpSession httpSession;
	@Autowired
	BasketService basketService;
	@Autowired
	UserService userService;
	@Autowired
	ShoppingService shoppingService;
	@Autowired
	KakaoPayService kakaoPayService;
	@Autowired
	ItemReviewRepository itemReviewRepository;
	@Autowired
	PurchaseHistoryService purchaseHistoryService;
	
	@GetMapping({ "shop/mans_form", "/shop/search/" })
	public String mansForm(@PathParam("gender") String gender, @PathParam("category") String category, Model model,
			@PageableDefault(size = 8, sort = "id", direction = Direction.DESC) Pageable pageable) {

		Page<Item> pageItems;
		if (gender == null || category == null) {
			pageItems = shoppingService.searchMans(pageable);
		} else {
			pageItems = shoppingService.searchItemCategory(category, gender, pageable);
		}

		int nowPage = pageItems.getPageable().getPageNumber() + 1; // 현재 페이지
		int startPage = Math.max(nowPage - 2, 1); // 두 int 값 중에 큰 값 반환
		int endPage = Math.min(nowPage + 2, pageItems.getTotalPages());

		// 페이지 번호를 배열로 만들어서 던져주기
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		// 주의! 마지막 번호까지 저장하기
		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}
		
		List<Item> itemList = pageItems.toList();
		List<FormatPriceDto> formatPriceList = shoppingService.formatPrice(itemList);

		model.addAttribute("pageable", pageItems);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("formatPriceList", formatPriceList);
		
		return "shopping/mans_form";
	}

	@GetMapping({ "shop/womans_form", "/shop/search" })
	public String womansForm(@PathParam("gender") String gender, @PathParam("category") String category, Model model,
			@PageableDefault(size = 8, sort = "id", direction = Direction.DESC) Pageable pageable) {

		Page<Item> pageItems;
		if (gender == null || category == null) {
			pageItems = shoppingService.searchWomans(pageable);
		} else {
			pageItems = shoppingService.searchItemCategory(category, gender, pageable);
		}

		int nowPage = pageItems.getPageable().getPageNumber() + 1; // 현재 페이지
		int startPage = Math.max(nowPage - 2, 1); // 두 int 값 중에 큰 값 반환
		int endPage = Math.min(nowPage + 2, pageItems.getTotalPages());

		// 페이지 번호를 배열로 만들어서 던져주기
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		// 주의! 마지막 번호까지 저장하기
		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}
		
		List<Item> itemList = pageItems.toList();
		List<FormatPriceDto> formatPriceList = shoppingService.formatPrice(itemList);

		model.addAttribute("pageable", pageItems);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("formatPriceList", formatPriceList);
		
		return "shopping/womans_form";
	}

	@GetMapping("/shop/save_form")
	public String saveForm() {
		return "shopping/save_form";
	}

	@GetMapping("/shop/basket_form/{id}")
	public String cartForm(@PathVariable int id, Model model) {
		
		List<Basket> Baskets = shoppingService.getOnUserCart(id);
		
		if(Baskets != null) {
			// 장바구니에 들은 아이템 id
			List<Integer> itemIdList = new ArrayList<>();
			for (Basket basket : Baskets) {
				itemIdList.add(basket.getItem().getId());
			}
			
			// 품절된 아이템 리스트
			List<Basket> soldoutList = shoppingService.checkAmountList(itemIdList, id);
			model.addAttribute("soldoutList", soldoutList);
		}
		
		List<FormatPriceDto> formatPriceList = basketService.formatPrice(Baskets);
		model.addAttribute("formatPriceList", formatPriceList);
		
		int sum = basketService.sum(id);
		
		NumberFormat formatter = NumberFormat.getNumberInstance();
		String totalPrice = formatter.format(sum);

		model.addAttribute("Baskets", Baskets);
		model.addAttribute("sumPrince", sum);
		model.addAttribute("totalPrice", totalPrice);

		if (sum != 0) {
			model.addAttribute("hasItem", true);
		} else {
			model.addAttribute("hasItem", false);
		}
	
		return "/shopping/basket_form";
	}

	// /shopping/itemdetail_form/${item.id}
	@GetMapping("/shop/itemdetail_form/{id}")
	public String itemDetailform(@PathVariable int id, @AuthenticationPrincipal PrincipalUserDetail userDetail, Model model,
			@PageableDefault(size = 4, sort = "id", direction = Direction.DESC) Pageable pageable) {
		
		if(userDetail != null) {
			int userId = userDetail.getUser().getId();
			model.addAttribute("purchasehistory", purchaseHistoryService.getPurchaseItemId(userId, id));
		}
		
		model.addAttribute("item", shoppingService.itemDetail(id));
		
		
		Page<ItemReview> pageItems;
		
		pageItems = shoppingService.findItemReviews(id, pageable);
		
		int nowPage = pageItems.getPageable().getPageNumber() + 1; // 현재 페이지
		int startPage = Math.max(nowPage - 2, 1); // 두 int 값 중에 큰 값 반환
		int endPage = Math.min(nowPage + 2, pageItems.getTotalPages());

		// 페이지 번호를 배열로 만들어서 던져주기
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		// 주의! 마지막 번호까지 저장하기
		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}
		
		// 재고를 확인해서 품절 상태를 알려주기 위함 
		int checkAmount =  shoppingService.checkAmount(id);

		model.addAttribute("pageable", pageItems);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("checkAmount", checkAmount);
		
		return "shopping/itemdetail_form";
		
	}
	
	@GetMapping("/security/kakaoPay/callback/{id}")
	public String kakaoPayReady(@PathVariable int id, @AuthenticationPrincipal PrincipalUserDetail userDetail) {
		
		List<Basket> basketList = basketService.getBasket(userDetail.getUser().getId());

		for (int i = 0; i < basketList.size(); i++) {
			int amount = basketList.get(i).getItem().getAmount() - basketList.get(i).getCount();
			if(amount >= 0) {
				KakaoPayDto dto = kakaoPayService.kakaoPayReady(id);
				httpSession.setAttribute("kakao", dto);
				return "redirect:" + dto.getNextRedirectPcUrl();
			} 
		}
		return "shopping/payment_fail";
	}

	@GetMapping("/kakaoPaySuccess")
	public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, @AuthenticationPrincipal PrincipalUserDetail userDetail) {
		KakaoPayDto kakaopayDto = (KakaoPayDto) httpSession.getAttribute("kakao");
		KakaoPayApprovalDto dto = kakaoPayService.kakaoPaySuccess(pg_token, userDetail.getUser().getId(),
				kakaopayDto.getTid(), userDetail.getUser().getId());
		model.addAttribute("pageTokenInfo", dto);
	
		String formatPrice = shoppingService.formatPrice(dto.getAmount().getTotal());
		model.addAttribute("formatPrice", formatPrice);
		
		List<Basket> baskets = basketService.getBasket(userDetail.getUser().getId());

		for (int i = 0; i < baskets.size(); i++) {
			Purchasehistory entity = Purchasehistory.builder()
					.tid(dto.getTid())
					.paymentMethodType(dto.getPaymentMethodType())
					.total(dto.getAmount().getTotal())
					.itemName(dto.getItemName())
					.createdAt(dto.getCreatedAt())
					.user(userDetail.getUser())
					.item(baskets.get(i).getItem())
					.address(userDetail.getUser().getAddress())
					.build();
			purchaseHistoryService.save(entity);
			
		}
		
		// 결제 완료 했을때 히스토리 카운트 세팅해주기 
		// 바스켓의 id와 count
		List<PurCountAndIdDto> basketCountlist = new ArrayList<PurCountAndIdDto>();
		for(Basket basketCount : baskets) {
			basketCountlist.add(new PurCountAndIdDto(basketCount.getItem().getId(), basketCount.getCount()));
		}
		purchaseHistoryService.setCount(basketCountlist, userDetail.getUser().getId());
		
		for (int i = 0; i < baskets.size(); i++) {
			basketService.deleteId(baskets.get(i).getId());
			shoppingService.UpdateItemAmount(baskets.get(i).getCount(), baskets.get(i).getItem().getId());
		}
		return "shopping/payment_success";
	}

	@GetMapping("/kakaoPayCancel")
	public String kakaoPayCancel() {
		return "shopping/payment_cancel";
	}

	@GetMapping("/kakaoPayFail")
	public String kakaoPayFail() {
		return "shopping/payment_fail";
	}

	@PostMapping("/review/upload/{id}")
	public String ReviewUpload(ItemReviewDto fileDto, @AuthenticationPrincipal PrincipalUserDetail detail, @PathVariable int id) {
		shoppingService.writeReview(fileDto, detail.getUser(), id);
		return "redirect:/shop/itemdetail_form/"+id;
	}
	
	@PostMapping("/review/update/{id}")
	public String ReviewUpdate(@PathVariable int id, ItemReviewDto fileDto) {
		shoppingService.itemReviewUpdate(id, fileDto);
		ItemReview itemReview = itemReviewRepository.ItemReviewFindById(id).get(0);
		int itemId = itemReview.getItem().getId();
		return "redirect:/shop/itemdetail_form/"+itemId;
	}

	@GetMapping("/user/purchase_history")
	public String purchaseHistory(@AuthenticationPrincipal PrincipalUserDetail userDetail, Model model) {
		model.addAttribute("purchaseHistoryGroupList", purchaseHistoryService.getPurchaseHistoryGroupList(userDetail.getUser().getId()));
		model.addAttribute("purchaseHistoryList", purchaseHistoryService.getPurchaseHistoryList(userDetail.getUser().getId()));
		return "shopping/purchase_history";
	}
	
}
