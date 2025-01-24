package com.digitalojt.api.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 在庫情報Entity
 * 
 * @author yamato mizoguchi
 *
 */
@Data
@Getter
@Setter
@Entity
public class StockInfo {

	/**
	 * 在庫ID
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer stockId;
	
	/**
	 * 分類情報との多対一のリレーション
	 */
	@ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryInfo categoryInfo;

	/**
	 * 在庫名
	 */
	private String name;
	
	/**
	 * 在庫センター情報との多対一のリレーション
	 */
    @ManyToOne
    @JoinColumn(name = "centerId")
    private CenterInfo centerInfo;
	
	/**
	 * 説明
	 */
	private String description;
	
	/**
	 * 数量
	 */
	private Integer amount;
	
	/**
	 * 削除フラグ
	 */
	private boolean deleteFlag;
	
	/**
	 * 作成日
	 */
	private Timestamp create_date;
	
	/**
	 * 更新日
	 */
	private Timestamp update_date;
}
