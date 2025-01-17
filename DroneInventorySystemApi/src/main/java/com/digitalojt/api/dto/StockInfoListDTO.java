package com.digitalojt.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockInfoListDTO {
	private Integer stockId;
	private Integer categoryId;
	private String categoryName;
	private String name;
	private Integer centerInfo;
	private String centerName;
	private String description;
	private Integer amount;
}
