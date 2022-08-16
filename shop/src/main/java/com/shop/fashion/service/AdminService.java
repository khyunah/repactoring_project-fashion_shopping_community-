package com.shop.fashion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.fashion.model.CommunityBoard;
import com.shop.fashion.model.Item;
import com.shop.fashion.model.RollType;
import com.shop.fashion.model.User;
import com.shop.fashion.repository.CommunityRepository;
import com.shop.fashion.repository.ShoppingRepository;
import com.shop.fashion.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShoppingRepository shoppingRepository;
	@Autowired
	private CommunityRepository communityRepository;

	// 회원정보 전체 조회
	@Transactional
	public Page<User> getUserAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	// 회원정보 검색 키워드로 조회
	public Page<User> searchUserKeywordPage(String keyword, String column, Pageable pageable) {
		Page<User> resultPage = null;
		switch (column) {
		case "ID":
			int id = Integer.parseInt(keyword);
			resultPage = userRepository.mFindById(id, pageable);
			break;
		case "USERNAME":
			resultPage = userRepository.mFindByUsername(keyword, pageable);
			break;
		case "NAME":
			resultPage = userRepository.mFindByName(keyword, pageable);
			break;
		case "ADDRESS":
			resultPage = userRepository.mFindByAddress(keyword, pageable);
			break;
		case "OAUTH":
			resultPage = userRepository.mFindByOauth(keyword, pageable);
			break;
		}
		return resultPage;
	}

	// 회원 삭제
	@Transactional
	public void deleteUser(int id) {
		communityRepository.deleteByUserId(id);
		userRepository.deleteById(id);
	}

	// 회원에게 관리자 권한 부여
	@Transactional
	public void changeUserRole(int id, boolean result) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("회원이 존재하지 않습니다.");
		});
		if (result) {
			user.setRole(RollType.ADMIN);
		} else {
			user.setRole(RollType.USER);
		}
	}

	// 상품정보 전체 조회
	@Transactional
	public Page<Item> getItemAll(Pageable pageable) {
		return shoppingRepository.findAll(pageable);
	}

	// 상품 삭제
	@Transactional
	public void deleteItem(int id) {
		shoppingRepository.deleteById(id);
	}

	// 상품 상세 조회
	@Transactional
	public Item getItem(int id) {
		return shoppingRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("상품이 존재하지 않습니다.");
		});
	}

	// 상품정보 검색 키워드로 조회
	@Transactional
	public Page<Item> searchItemKeywordPage(String keyword, String column, Pageable pageable) {
		Page<Item> resultPage = null;
		switch (column) {
		case "ID":
			int id = Integer.parseInt(keyword);
			resultPage = shoppingRepository.mFindById(id, pageable);
			break;
		case "ITEMNAME":
			resultPage = shoppingRepository.mFindByName(keyword, pageable);
			break;
		case "CATEGORY":
			resultPage = shoppingRepository.mFindByCategory(keyword, pageable);
			break;
		case "PRICE":
			resultPage = shoppingRepository.mFindByPrice(keyword, pageable);
			break;
		case "GENDER":
			resultPage = shoppingRepository.mFindByGender(keyword, pageable);
			break;
		}
		return resultPage;
	}

	// 상품 수정
	@Transactional
	public Item updateItem(Item item) {
		Item itemEntity = shoppingRepository.findById(item.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("상품이 존재하지 않습니다.");
		});
		itemEntity.setName(item.getName());
		itemEntity.setContent(item.getContent());
		itemEntity.setAmount(item.getAmount());
		itemEntity.setCategory(item.getCategory());
		itemEntity.setGender(item.getGender());
		itemEntity.setImageurl(item.getImageurl());
		itemEntity.setPrice(item.getPrice());
		itemEntity.setSize(item.getSize());

		return itemEntity;
	}

	// 커뮤니티 전체 조회
	@Transactional
	public Page<CommunityBoard> getBoardAll(Pageable pageable) {
		return communityRepository.findAll(pageable);
	}

	// 커뮤니티 검색 키워드로 조회
	@Transactional
	public Page<CommunityBoard> searchCommunityKeywordPage(String keyword, String column, Pageable pageable) {
		Page<CommunityBoard> resultPage = null;
		switch (column) {
		case "ID":
			int id = Integer.parseInt(keyword);
			resultPage = communityRepository.mFindById(id, pageable);
			break;
		case "USERNAME":
			User user = userRepository.findByUsername(keyword).orElse(null);
			if(user != null) {
				resultPage = communityRepository.mFindByUser(user.getId(), pageable);
			} else {
				resultPage = communityRepository.mFindByUser(0, pageable);
			}
			
			break;
		case "TITLE":
			resultPage = communityRepository.mFindByTitle(keyword, pageable);
			break;
		}
		return resultPage;
	}

	// 커뮤니티 보드 상세보기
	@Transactional
	public CommunityBoard detailCommunityBoard(int id) {
		return communityRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("게시물이 존재하지 않습니다.");
		});
	}

	// 커뮤니티 보드 삭제
	@Transactional
	public void deleteCommunityBoard(int id) {
		communityRepository.deleteById(id);
	}
	
	@Transactional
	public Page<User> selectTodayJoinUser(Pageable pageable) {
		return userRepository.mFindByTodayJoinUser(pageable);
	}
	
}
