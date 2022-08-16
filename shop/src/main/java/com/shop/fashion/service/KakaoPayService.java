package com.shop.fashion.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.shop.fashion.dto.KakaoPayApprovalDto;
import com.shop.fashion.dto.KakaoPayDto;
import com.shop.fashion.model.Basket;
import com.shop.fashion.repository.BasketRepository;

@Service
public class KakaoPayService {
	
	@Value("${seller.key}")
	private String sellerName;
	
	@Autowired
	BasketRepository basketRepository;
	
	public KakaoPayDto kakaoPayReady(int userId) {
		List<Basket> basket = basketRepository.mfindByUserId(userId);
			
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "49288c0f3e836b32f8d5beeb7e2bde16");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", basket.get(0).getUser().getName());
        params.add("partner_user_id", sellerName);
        params.add("item_name", basket.get(0).getItem().getName() + "외 " + (basket.size()-1) + "건");
        params.add("quantity", String.valueOf(basket.size()));
        params.add("total_amount", String.valueOf(getTotalAmount(basket)));
        params.add("tax_free_amount", String.valueOf(getTotalAmount(basket)));
        params.add("approval_url", "http://localhost:9090/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:9090/kakaoPayCancel");
        params.add("fail_url", "http://localhost:9090/kakaoPayFail");
 
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoPayDto> response = restTemplate.exchange(
        		"https://kapi.kakao.com/v1/payment/ready", HttpMethod.POST, request, KakaoPayDto.class);
         
        KakaoPayDto dto = response.getBody();
        dto.setTid(dto.getTid());
        dto.setBasketid(dto.getBasketid());
        return dto;
	}
	
	
	public KakaoPayApprovalDto kakaoPaySuccess(String pg_token, int basketId, String tid, int userId) {
		List<Basket> baskets = basketRepository.findByUserId(basketId);
		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "49288c0f3e836b32f8d5beeb7e2bde16");
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		
		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		params.add("cid", "TC0ONETIME");
		params.add("tid", tid);
		params.add("partner_order_id", baskets.get(0).getUser().getName());
		params.add("partner_user_id", sellerName);
		params.add("pg_token", pg_token);
		params.add("total_amount", String.valueOf(getTotalAmount(baskets)));
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<KakaoPayApprovalDto> response = restTemplate.exchange(
				"https://kapi.kakao.com/v1/payment/approve", HttpMethod.POST, request, KakaoPayApprovalDto.class);
	
		KakaoPayApprovalDto dto = response.getBody();
		 return dto;
	}
	
	
	private int getTotalAmount(List<Basket> basket) {
		int result = 0;
		for(int i = 0; i < basket.size(); i++) {
	        int count =	basket.get(i).getCount();
	        int price =	basket.get(i).getItem().getPrice();
	        int sum = count * price;
	        result += sum;
		}
		return result;
		
	}

	
}
