package com.shop.fashion.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardVODto {

	private String purchaseCorp;
	private String purchaseCorpCode;
	private String issuerCorp;
	private String issuerCorpCode;
	private String bin;
	private String cardType;
	private String installMonth;
	private String approvedId;
	private String cardMid;
	private String interesetFreeInstall;
	private String cardItemCode;
	
}
