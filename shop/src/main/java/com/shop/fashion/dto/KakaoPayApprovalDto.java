package com.shop.fashion.dto;

import java.sql.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoPayApprovalDto {

	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partnerOrderId;
	private String partnerUserId;
	private String paymentMethodType;
	private AmountVODto amount;
	private CardVODto cardInfo;
	private String itemName;
	private String itemCode;
	private String payLoad;
	private Integer quantity;
	private Integer taxFreeAmount;
	private Integer vatAmount;
	private Date createdAt;
	private Date approvedAt;
	
}
