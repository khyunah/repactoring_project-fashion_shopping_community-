package com.shop.fashion.dto;

import java.sql.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoPayDto {
	private int basketid;
	private String tid;
	private String nextRedirectPcUrl;
	private Date createdAt;
	
}
