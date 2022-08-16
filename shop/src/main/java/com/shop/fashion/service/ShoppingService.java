package com.shop.fashion.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.fashion.dto.FormatPriceDto;
import com.shop.fashion.dto.ItemReviewDto;
import com.shop.fashion.model.Basket;
import com.shop.fashion.model.Item;
import com.shop.fashion.model.ItemReview;
import com.shop.fashion.model.User;
import com.shop.fashion.repository.BasketRepository;
import com.shop.fashion.repository.ItemReviewRepository;
import com.shop.fashion.repository.ShoppingRepository;

@Service
public class ShoppingService {
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Autowired
	ShoppingRepository shoppingRepository;

	@Autowired
	BasketRepository basketRepository;
	
	@Autowired
	ItemReviewRepository itemReviewRepository; 

	@Transactional(readOnly = true)
	public Page<Item> searchMans(Pageable pageable) {
		return shoppingRepository.CategoryItemMans(pageable);
	}

	@Transactional(readOnly = true)
	public Page<Item> searchWomans(Pageable pageable) {
		return shoppingRepository.CategoryItemWomans(pageable);
	}

	@Transactional
	public Item saveItem(Item item) {
		return shoppingRepository.save(item);
	}

	@Transactional(readOnly = true)
	public Page<Item> searchItemCategory(String category, String gender, Pageable pageable) {
		return shoppingRepository.CategoryItem(category, gender, pageable);
	}

	@Transactional
	public void modifyBoard(int id, Item item) { // title, content
		Item itemEntity = shoppingRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 게시글은 찾을 수 없습니다.");
		});
		itemEntity.setCategory(item.getCategory());
		itemEntity.setContent(item.getContent());
		itemEntity.setGender(item.getGender());
		itemEntity.setId(item.getId());
		itemEntity.setImageurl(item.getImageurl());
		itemEntity.setName(item.getName());
		itemEntity.setPrice(item.getPrice());
		// 더티체킹 - @Transactional만 걸어주면 됨
	}

	@Transactional(readOnly = true)
	public List<Basket> getOnUserCart(int userid) {
		List<Basket> basket = basketRepository.findByUserId(userid);
		return basket;
	}

	@Transactional
	public Item itemDetail(int itemId) {
		return shoppingRepository.findById(itemId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 게시글은 찾을 수 없습니다.");
		});
	}

	@Transactional
	public List<Item> itemDetails(String name, String gender) {
		return shoppingRepository.detailItemSizeColor(name, gender);
	}

	@Transactional
	public void putCart(int itemid, User user, int basketid) {
		Basket basket = basketRepository.findById(basketid).get();
		basket.setUser(user);
	}
	
	@Transactional
	public void writeReview(ItemReviewDto fileDto, User user, int itemId) {
		String newFileName = fileNameSet(fileDto);
		Item item = shoppingRepository.findById(itemId).get();
		ItemReview itemReviewEntity = fileDto.itemtoEntity(newFileName, user, item);
		itemReviewRepository.save(itemReviewEntity);
	}
	
	@Transactional
	private String fileNameSet(ItemReviewDto fileDto) {

		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid.toString() + "." + extracktExt(fileDto.getFile().getOriginalFilename());

		String newFileName = (imageFileName.trim()).replaceAll("\\s", "");

		Path imageFilePath = Paths.get(uploadFolder + newFileName);

		try {
			Files.write(imageFilePath, fileDto.getFile().getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return newFileName;
	}
	
	@Transactional
	private String extracktExt(String originalFileName) {
		int pos = originalFileName.lastIndexOf(".");
		return originalFileName.substring(pos + 1);
	}
	
	@Transactional(readOnly = true)
	public Page<ItemReview> findItemReviews(int id, Pageable pageable) {
		return itemReviewRepository.ItemFindById(id, pageable);
	}
	
	@Transactional
	public void deleteItemReivewById(int ItemReviewId) {
		itemReviewRepository.deleteById(ItemReviewId);
	}
	
	@Transactional
	public ItemReview itemReviewUpdate(int id, ItemReviewDto dto) {
		ItemReview itemreview = itemReviewRepository.findById(id).get();

		if (!dto.getFile().getOriginalFilename().isEmpty()) {
			String updateFileName = fileNameSet(dto);
			itemreview.setImageUrl(updateFileName);
		}

		itemreview.setContent(dto.getContent());

		return itemreview;
	}
	
	@Transactional
	public void modifyItemReview(int id, ItemReview itemReview) {
		ItemReview itemreview = itemReviewRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글은 찾을 수 없습니다");
		});
		itemreview.setContent(itemreview.getContent());
		itemreview.setImageUrl(itemreview.getImageUrl());
	}
	
	@Transactional
	public void UpdateItemAmount(int amount, int id) {
		shoppingRepository.updateStock(amount, id);
	}

	// 재고확인 하기 
	@Transactional
	public int checkAmount(int ItemId) {
		return shoppingRepository.mFindByItemAmount(ItemId);
	}
	
	// 장바구니의 수량를 아이템의 현 재고와 비교하기
	@Transactional
	public List<Basket> checkAmountList(List<Integer> itemId, int userId){
		// item 재고
		List<Item> list = new ArrayList<>();
		for (Integer id : itemId) {
			list.add(shoppingRepository.mFindByAmount(id));
		}
		// 장바구니 아이템ID와 수량
		List<Basket> basket = basketRepository.mFindByItemCount(userId);
		
		// 품절이거나 수량 부족일때 넣어줄 리스트 
		List<Basket> soldoutList = new ArrayList<>();
		for (int i = 0; i < basket.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if(basket.get(i).getCount() > list.get(j).getAmount()) {
					soldoutList.add(basket.get(i));
				}
			}
		}
		
		return soldoutList;
	}
	
	// 가격 포맷 
	public String formatPrice(int price) {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		return formatter.format(price);
	}
	
	// 가격 포맷
	public List<FormatPriceDto> formatPrice(List<Item> items){
		List<FormatPriceDto> list = new ArrayList<FormatPriceDto>();
		for (Item item : items) {
			NumberFormat formatter = NumberFormat.getNumberInstance();
			String fPrice = formatter.format(item.getPrice());
			list.add(new FormatPriceDto(item.getId(), fPrice));
		}
		return list;
	}

}
