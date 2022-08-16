package com.shop.fashion.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.shop.fashion.dto.KakaoTokenDto;
import com.shop.fashion.dto.KakaoUserInfoDto;
import com.shop.fashion.dto.UserUpdateDto;
import com.shop.fashion.model.OAuthType;
import com.shop.fashion.model.User;
import com.shop.fashion.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Value("${kakao.key}")
	private String kakaoKey;

	// 회원가입 화면
	@GetMapping("/security/join_form")
	public String joinForm() {
		return "user/join_form";
	}

	// 회원가입
	@PostMapping("/security/join-user")
	public String joinUser(User user, Model model) {
		User userEntity = userService.joinUser(user);
		model.addAttribute("user", userEntity);
		return "user/join_success";
	}

	// 로그인 화면
	@GetMapping("/security/login_form")
	public String loginForm(@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "exception", required = false) String exception, Model model) {
		
		String errorMessage = "";
		if(error != null) {
			if(exception.equals("match")) {
				errorMessage = "아이디와 비밀번호가 일치하지 않습니다.";
			} else if (exception.equals("exist")) {
				errorMessage = "존재하지 않는 아이디 입니다.";
			} else {
				errorMessage = "다시 시도해 주세요.";
			}
		}
		
		model.addAttribute("error", error);
		model.addAttribute("errorMessage", errorMessage);
		return "user/login_form";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/";
	}

	// 회원 정보 수정 화면
	@GetMapping("/user/update_form")
	public String updateUser() {
		return "user/update_form";
	}

	// 프로필 회원정보 수정
	@PostMapping("/user/profile-update/{id}")
	public String updateProfile(@PathVariable int id, UserUpdateDto dto) {
		userService.updateUserProfile(id, dto);
		return "redirect:/user/update_form";
	}

	// 카카오 로그인
	@GetMapping("/security/kakao/callback")
	public String kakaoLogin(String code) {
		return joinKakaoUser(getKakaoUserInfo(getKakaoToken(code)));
	}

	// 토큰 정보 받기
	private ResponseEntity<KakaoTokenDto> getKakaoToken(String code) {

		HttpHeaders tokenHeaders = new HttpHeaders();
		tokenHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> tokenParams = new LinkedMultiValueMap<String, String>();
		tokenParams.add("grant_type", "authorization_code");
		tokenParams.add("client_id", "0d6bcf296d67c35ad944b2a3d38df9be");
		tokenParams.add("redirect_uri", "http://localhost:9090/security/kakao/callback");
		tokenParams.add("code", code);

		// Http 메세지를 만든다.
		HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<MultiValueMap<String, String>>(
				tokenParams, tokenHeaders);

		RestTemplate tokenRest = new RestTemplate();
		ResponseEntity<KakaoTokenDto> tokenResponse = tokenRest.exchange("https://kauth.kakao.com/oauth/token",
				HttpMethod.POST, tokenRequest, KakaoTokenDto.class);
		return tokenResponse;
	}

	// 사용자 정보 받기
	private KakaoUserInfoDto getKakaoUserInfo(ResponseEntity<KakaoTokenDto> kakaoToken) {

		HttpHeaders kakaoUserInfoHeader = new HttpHeaders();
		kakaoUserInfoHeader.add("Authorization", "Bearer " + kakaoToken.getBody().getAccessToken());
		kakaoUserInfoHeader.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(kakaoUserInfoHeader);
		RestTemplate kakaoUserInfoRest = new RestTemplate();
		ResponseEntity<KakaoUserInfoDto> kakaoUserInfoResponse = kakaoUserInfoRest.exchange(
				"https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoUserInfoRequest, KakaoUserInfoDto.class);
		return kakaoUserInfoResponse.getBody();
	}

	// 사용자 가입시켜주기
	private String joinKakaoUser(KakaoUserInfoDto kakaoUserInfo) {

		// 사용자가 이미 가입되어 있는지 확인
		String name = kakaoUserInfo.getProperties().getNickname();
		String email = kakaoUserInfo.getKakaoAccount().getEmail();
		boolean hasEmail = kakaoUserInfo.getKakaoAccount().getHasEmail();

		User kakaoLoginUser = User.builder().username(email + "_" + kakaoUserInfo.getId()).password(kakaoKey)
				.email(email).oauth(OAuthType.KAKAO).name(name).build();

		if (hasEmail) {
			User originUser = userService.checkUsername(kakaoLoginUser.getUsername());

			if (originUser == null) {
				userService.joinUser(kakaoLoginUser);
			}
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(kakaoLoginUser.getUsername(), kakaoKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:/";
	}

	@GetMapping("/user/receipt")
	private String receipt() {
		return "/shopping/receipt";
	}
	
	
}
