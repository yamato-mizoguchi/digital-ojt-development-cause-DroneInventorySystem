package com.digitalojt.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分類情報DTOクラス
 * 
 * @author yamato mizoguchi
 *
 */
@Data
@AllArgsConstructor
public class CategoryInfoDTO {
	private Integer categoryId;
	private String categoryName;
}
