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
public class CommunityDto {

	
	private MultipartFile file;
	private String uuid; 
	private String content; 
	private String title;
	
	public CommunityBoard toEntity(String storyImageUrl, User user) {
		return CommunityBoard.builder()
				.imageUrl(storyImageUrl)
				.user(user)
				.content(content)
				.title(title)
				.originImageTitle(file.getOriginalFilename())
				.build();
	}
	
}
