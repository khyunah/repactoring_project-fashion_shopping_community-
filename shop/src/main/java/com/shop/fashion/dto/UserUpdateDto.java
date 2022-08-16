package com.shop.fashion.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {

	private MultipartFile file;
	private String uuid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String address;
	private int phoneNumber;

}
