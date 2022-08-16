package com.shop.fashion.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.fashion.dto.ReqUserSearchDto;
import com.shop.fashion.dto.ResponseDto;
import com.shop.fashion.model.User;
import com.shop.fashion.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	// 회원가입시 아이디 중복 체크
	@PostMapping("/security/join-usernameCheck")
	public ResponseDto<User> checkUsername(@RequestBody ReqUserSearchDto dto) {
		User checkUser = userService.checkUsername(dto.getUsername());
		return new ResponseDto<>(HttpStatus.OK.value(), checkUser);
	}
	
}
