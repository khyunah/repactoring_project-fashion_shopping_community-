package com.shop.fashion.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Domain {

	private Map<String, List<String>> genderCategory = Map.of(
				"MAN", List.of("SHIRTS","PANTS", "SHOES", "OUTER", "ACCESSORY"),
				"WOMAN", List.of("SHIRTS","PANTS","SHOES","SKIRT","ONEPIECE", "OUTER", "ACCESSORY")
			);
	
	private Map<String, List<String>> categorySize = Map.of(
				"SHIRTS", List.of("S","M","L","XL", "XXL"),
				"PANTS", List.of("S","M","L","XL", "XXL"),
				"SHOES", List.of("230","240","250","260", "270", "280"),
				"OUTER", List.of("S","M","L","XL", "XXL"),
				"ACCESSORY", List.of("S","M","L"),
				"SKIRT", List.of("XS","S","M","L","XL", "XXL"),
				"ONEPIECE", List.of("XS","S","M","L","XL", "XXL")
			);

}
