package com.shop.fashion.dto.chart;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ShoppingCountAndSumDto {
	private Date salesDate;
	private BigDecimal totalIncome;
	private BigDecimal totalCount;
}
