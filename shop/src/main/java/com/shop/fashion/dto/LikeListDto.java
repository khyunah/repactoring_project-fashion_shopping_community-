package com.shop.fashion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class LikeListDto {
	private String imageUrl;
	private String username;
	private String name;
	private int id;
}
