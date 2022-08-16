package com.shop.fashion.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.fashion.dto.PurCountAndIdDto;
import com.shop.fashion.model.Purchasehistory;
import com.shop.fashion.repository.PurchaseHistoryRepository;

@Service
public class PurchaseHistoryService {

	@Autowired
	PurchaseHistoryRepository purchaseHistoryRepository;
	
	@Transactional
	public void save(Purchasehistory purchasehistory) {
		purchaseHistoryRepository.save(purchasehistory);
	}
	
	@Transactional
	public List<Purchasehistory> getPurchaseHistoryGroupList(int userId) {
		return purchaseHistoryRepository.purchaseHistoryGroupList(userId);
	}
	
	@Transactional
	public List<Purchasehistory> getPurchaseHistoryList(int userId) {
		return purchaseHistoryRepository.purchaseHistoryList(userId);
	}
	
	@Transactional
	public List<Purchasehistory> getPurchaseItemId(int userId, int itemId) {
	return purchaseHistoryRepository.purchaseitemId(userId, itemId);
	}

	// 결제 완료 했을때 히스토리 테이블에 count 셋팅 해주기 
	@Transactional
	public void setCount(List<PurCountAndIdDto> baskets, int userId) {
		// 히스토리 생성 날짜를 가져오기 위함
		Purchasehistory purEntity = purchaseHistoryRepository.mFindByUserId(userId);
		
		// 해당 날짜와 userId로 ItemId를 가져옴 
		List<Purchasehistory> userBuyList = purchaseHistoryRepository.mFindByUserIdAndCreateDate(userId, purEntity.getCreateDate());
		
		for (int i = 0; i < baskets.size(); i++) {
			
			for (int j = 0; j < userBuyList.size(); j++) {
				if(baskets.get(i).getId() == userBuyList.get(j).getItem().getId()) {
					userBuyList.get(j).setCount(baskets.get(i).getCount());
				}
			}
		}
		
	}
	
}
