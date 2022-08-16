package com.shop.fashion.dto.chart;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CommunityTotalCountDto {
	private Integer date;
	private BigInteger count;
}
