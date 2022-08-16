package com.shop.fashion.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/community/my-social")
	public String mySocialPage() {
		return "test/detail";
	}
}
