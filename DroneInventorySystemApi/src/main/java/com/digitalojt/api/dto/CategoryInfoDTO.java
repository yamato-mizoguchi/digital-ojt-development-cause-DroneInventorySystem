package com.digitalojt.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryInfoDTO {
	private Integer categoryId;
	private String categoryName;
}
