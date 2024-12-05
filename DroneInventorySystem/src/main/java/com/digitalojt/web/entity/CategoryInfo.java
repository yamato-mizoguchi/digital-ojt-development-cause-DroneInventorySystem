package com.digitalojt.web.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 分類情報情報Entity
 * 
 * @author yamato mizoguchi
 *
 */
@Data
@Getter
@Setter
@Entity
public class CategoryInfo {

	/**
	 * 分類情報ID
	 */
	@Id
	private Integer categoryId;
	
	/**
	 * 分類名
	 */
	private String categoryName;
	
	/**
	 * 在庫情報との一対多のリレーション
	 */
	@OneToMany(mappedBy = "categoryInfo")
    private List<StockInfo> stockInfo;
}