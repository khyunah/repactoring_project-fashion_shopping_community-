package com.shop.fashion.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CommunityCountDto {
	private int id;
	private BigInteger count;
}
