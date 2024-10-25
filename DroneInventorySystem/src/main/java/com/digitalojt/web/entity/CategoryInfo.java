package com.digitalojt.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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
	private int categoryId;
	
	/**
	 * 分類名
	 */
	private String categoryName;
}
