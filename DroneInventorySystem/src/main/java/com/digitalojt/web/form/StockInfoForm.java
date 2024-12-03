package com.digitalojt.web.form;

import com.digitalojt.web.validation.StockInfoFormValidator;

import lombok.Data;

/**
 * 在庫情報画面のフォームクラス
 * 
 * @author yamato mizoguchi
 *
 */
@Data
@StockInfoFormValidator
public class StockInfoForm {

	/**
	 * 分類ID
	 */
	private Integer categoryId;

	/**
	 * 在庫名
	 */
	private String name;

	/**
	 * 個数
	 * StringからIntegerに変換する
	 */
	private String amount;

	/**
	 * 個数の以上以下
	 */
	private Integer amountCondition;
}
