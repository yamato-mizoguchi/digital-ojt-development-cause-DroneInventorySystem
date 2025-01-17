package com.digitalojt.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockNameDTO {
	private Integer stockid;
	private String name;
}
