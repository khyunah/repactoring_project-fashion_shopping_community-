package com.shop.fashion.dto.chart;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CommunityActionCountDto {
	private Integer id;
	private String title;
	private String username;
	private BigInteger count;
}
