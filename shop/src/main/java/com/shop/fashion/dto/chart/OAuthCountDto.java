package com.shop.fashion.dto.chart;

import java.math.BigInteger;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class OAuthCountDto {
	private String oauth;
	private BigInteger oauthCount;
}
