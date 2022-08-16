package com.shop.fashion.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.fashion.dto.BasketSumDto;
import com.shop.fashion.dto.FormatPriceDto;
import com.shop.fashion.model.Basket;
import com.shop.fashion.model.Item;
import com.shop.fashion.model.User;
import com.shop.fashion.repository.BasketRepository;
import com.shop.fashion.repository.BasketSumRepository;

@Service
public class BasketService {

	@Autowired
	private BasketRepository basketRepository;

	@Autowired
	private BasketSumRepository basketSumRepository;

	@Autowired
	private ShoppingService shoppingService;

	@Transactional
	public void putCart(Item Itemid, User userId) {
		Basket basket = new Basket();
		basket.setItem(Itemid);
		basket.setUser(userId);
		basket.setCount(1);
		List<Basket> basketEntity = basketRepository.findByItemIdAndUserId(Itemid.getId(), userId.getId());

		if (basketEntity.size() != 0) {
			basketEntity.get(0).setCount(basketEntity.get(0).getCount() + 1);

		} else {
			basketRepository.save(basket);
		}

	}

	@Transactional
	public int sum(int userId) {
		List<BasketSumDto> basketSum = basketSumRepository.getBasketSum(userId);
		if (basketSum != null) {
			return basketSum.get(0).getSum().intValue();
		} else {
			return 0;
		}

	}

	@Transactional
	public void deleteBasketItemById(int basketId) {
		basketRepository.deleteById(basketId);
	}

	@Transactional
	public void deleteId(int id) {
		basketRepository.deleteById(id);
	}

	@Transactional
	public List<Basket> getBasket(int id) {
		return basketRepository.findByUserId(id);
	}

	// 품절로 인한 장바구니 삭제 처리
	@Transactional
	public void soldoutDeleteBasket(int userId) {
		List<Basket> Baskets = shoppingService.getOnUserCart(userId);

		if (Baskets != null) {
			for (Basket basket : Baskets) {
				if(basket.getItem().getAmount() - basket.getCount() < 0 ) {
					basketRepository.deleteById(basket.getId());
				}
			}
		}
		
	}
	
	// 가격 포맷
	public List<FormatPriceDto> formatPrice(List<Basket> Baskets){
		List<FormatPriceDto> list = new ArrayList<FormatPriceDto>();
		for (Basket basket : Baskets) {
			int price = basket.getItem().getPrice() * basket.getCount();
			NumberFormat formatter = NumberFormat.getNumberInstance();
			String fPrice = formatter.format(price);
			list.add(new FormatPriceDto(basket.getId(), fPrice));
		}
		return list;
	}

}
