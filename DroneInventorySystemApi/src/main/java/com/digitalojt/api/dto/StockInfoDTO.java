package com.digitalojt.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 在庫情報DTOクラス
 * 
 * @author yamato mizoguchi
 *
 */
@Data
@AllArgsConstructor
public class StockInfoDTO {
	private Integer stockId;
	private Integer categoryId;
	private String categoryName;
	private String name;
	private Integer centerInfo;
	private String centerName;
	private String description;
	private Integer amount;
}
