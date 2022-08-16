package com.shop.fashion.dto;

import org.springframework.web.multipart.MultipartFile;

import com.shop.fashion.model.CommunityBoard;
import com.shop.fashion.model.Item;
import com.shop.fashion.model.ItemReview;
import com.shop.fashion.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemReviewDto {

	
	private MultipartFile file;
	private String uuid; 
	private String content; 
	private String title;
	
	
	public ItemReview itemtoEntity(String storyImageUrl, User user, Item itemId) {
		return ItemReview.builder()
				.imageUrl(storyImageUrl)
				.user(user)
				.item(itemId)
				.content(content)
				.originImageTitle(file.getOriginalFilename())
				.build();
	}
}
