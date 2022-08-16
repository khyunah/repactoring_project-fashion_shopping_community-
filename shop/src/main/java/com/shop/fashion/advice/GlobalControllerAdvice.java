package com.shop.fashion.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(value = NumberFormatException.class)
	public String numberFormatException(NumberFormatException e, Model model) {
		model.addAttribute("e", e.getMessage());
		return "error";
	}
}
