package com.shop.fashion.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.fashion.dto.ResponseDto;
import com.shop.fashion.model.Item;
import com.shop.fashion.service.AdminService;

@RestController
public class AdminApiController {

	@Autowired
	private AdminService adminService;

	// 회원 삭제
	@DeleteMapping("/admin/user/delete/{id}")
	public ResponseDto<String> deleteUser(@PathVariable int id, HttpServletRequest request) {
		adminService.deleteUser(id);
		String responceResult = "0";
		// 바로 이전페이지로 이동
		if (request.getHeader("Referer") != null) {
			responceResult = String.valueOf(request.getHeader("Referer"));
		}
		return new ResponseDto<>(HttpStatus.OK.value(), responceResult);
	}

	// 회원 권한 설정
	@PutMapping("/admin/user/change-role/{id}/{result}")
	public ResponseDto<String> changeUserRole(@PathVariable int id, @PathVariable boolean result,
			HttpServletRequest request) {
		adminService.changeUserRole(id, result);

		String responceResult = "0";
		if (request.getHeader("Referer") != null) {
			responceResult = String.valueOf(request.getHeader("Referer"));
		}
		return new ResponseDto<>(HttpStatus.OK.value(), responceResult);
	}

	// 상품 삭제
	@DeleteMapping("/admin/shopping-item/delete/{id}")
	public ResponseDto<String> deleteItem(@PathVariable int id, HttpServletRequest request) {
		adminService.deleteItem(id);

		String responceResult = "0";
		if (request.getHeader("Referer") != null) {
			responceResult = String.valueOf(request.getHeader("Referer"));
		}
		return new ResponseDto<>(HttpStatus.OK.value(), responceResult);
	}

	// 상품 수정
	@PostMapping("/admin/shopping-item/update/{id}")
	public ResponseDto<String> updateItem(@RequestBody Item item, Model model) {
		Item itemEntity = adminService.updateItem(item);
		model.addAttribute("item", itemEntity);
		return new ResponseDto<>(HttpStatus.OK.value(), "ok");
	}

	// 커뮤니티 삭제
	@DeleteMapping("/admin/community/delete/{id}")
	public ResponseDto<String> deleteCommunityBoard(@PathVariable int id, HttpServletRequest request) {
		adminService.deleteCommunityBoard(id);
		
		String responceResult = "0";
		if (request.getHeader("Referer") != null) {
			responceResult = String.valueOf(request.getHeader("Referer"));
		}
		return new ResponseDto<>(HttpStatus.OK.value(), responceResult);
	}

}
