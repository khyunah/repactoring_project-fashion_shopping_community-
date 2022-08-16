package com.shop.fashion.dto.chart;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ShoppingItemDto {
	private String name;
	private BigDecimal totalIncome;
	private BigDecimal totalCount;
}
