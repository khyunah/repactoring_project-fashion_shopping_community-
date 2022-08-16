package com.shop.fashion.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.fashion.dto.UserUpdateDto;
import com.shop.fashion.model.OAuthType;
import com.shop.fashion.model.RollType;
import com.shop.fashion.model.User;
import com.shop.fashion.repository.UserRepository;

@Service
public class UserService {

	@Value("${file.path}")
	private String profileFolder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Value("${kakao.key}")
	private String kakaoKey;

	// 회원가입
	@Transactional
	public User joinUser(User user) {
		if (user.getOauth() == null) {
			user.setOauth(OAuthType.ORIGIN);
		}
		user.setRole(RollType.USER);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	// 아이디 중복 체크
	@Transactional()
	public User checkUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	// 회원정보 수정
	@Transactional
	public User updateUser(User user) {

		User originUser = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
		});

		originUser.setName(user.getName());
		originUser.setEmail(user.getEmail());
		originUser.setPhoneNumber(user.getPhoneNumber());
		originUser.setAddress(user.getAddress());

		if (user.getOauth() == OAuthType.ORIGIN) {
			originUser.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		return originUser;
	}

	// 프로필 회원정보 수정
	@Transactional
	public User updateUserProfile(int id, UserUpdateDto dto) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
		});
		System.out.println(user.getId());

		if (!dto.getFile().getOriginalFilename().isEmpty()) {
			user.setImageUrl(saveImageFile(dto));
			user.setOriginImageTitle(dto.getFile().getOriginalFilename());
		}

		user.setName(dto.getName());
		if (user.getOauth() == OAuthType.ORIGIN) {
			user.setPassword(passwordEncoder.encode(dto.getPassword()));
		}
		user.setEmail(dto.getEmail());
		user.setPhoneNumber(dto.getPhoneNumber());
		user.setAddress(dto.getAddress());
		
		if (user.getOauth() == OAuthType.ORIGIN) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), kakaoKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		

		return user;
	}

	private String saveImageFile(UserUpdateDto dto) {
		UUID uuid = UUID.randomUUID();
		String imgFile = uuid.toString() + "_" + dto.getUsername();
		String newFileName = imgFile.replaceAll("\\s", "");

		Path forder = Paths.get(profileFolder + newFileName);
		try {
			Files.write(forder, dto.getFile().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFileName;
	}
	
	@Transactional
	public User getUser(int id) {
		return userRepository.findById(id).get();
	}
}
