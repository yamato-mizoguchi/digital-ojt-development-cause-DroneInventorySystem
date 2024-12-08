package com.digitalojt.web.consts;

/**
 * エラーメッセージ定数クラス
 * 
 * @author yamato mizoguchi
 *
 */
public class ErrorMessage {
	
	// ログイン情報の入力に誤りがあった場合に、出力するエラーメッセージのID
	public static final String  LOGIN_WRONG_INPUT = "login.wrongInput";

	// すべての項目が空の場合のエラーメッセージ
	public static final String ALL_FIELDS_EMPTY_ERROR_MESSAGE = "allField.empty";
	
	// 分類名ですべての項目が空の場合のエラーメッセージ
	public static final String CATEGORY_ALL_FIELDS_EMPTY_ERROR_MESSAGE = "categoryName.allField.empty";

	// 空文字検索に関するエラーメッセージ
	public static final String UNEXPECTED_INPUT_ERROR_MESSAGE = "unexpected.input";

	// 不正な文字列を使用した検索に関するエラーメッセージ
	public static final String INVALID_INPUT_ERROR_MESSAGE = "invalid.input";
	
	// 分類名で不正な文字列を使用した検索に関するエラーメッセージ
	public static final String CATEGORY_INVALID_INPUT_ERROR_MESSAGE = "categoryName.invalid.input";

	// 文字超過に関するエラーメッセージ
	public static final String CENTER_NAME_LENGTH_ERROR_MESSAGE = "centerName.length.wrongInput";
	
	// 文字超過に関するエラーメッセージ
	public static final String CATEGORY_NAME_LENGTH_ERROR_MESSAGE = "categoryName.length.wrongInput";
	
	// 不正な入力値を使用した検索に関するエラーメッセージ
	public static final String AMOUNT_INVALID_INPUT_ERROR_MESSAGE = "stockInfo.amount.wrongInput";
	
	// 不正な入力値を使用した検索に関するエラーメッセージ
	public static final String AMOUNT_LENGTH_ERROR_MESSAGE = "stockInfo.amount.length.wrongInput";
	
	// 
	public static final String CENTER_CAPACITY_INVALID_INPUT = "centerCapacity.invalid.input";

	public static final String CENTER_CAPACITY_INVALID_LOGIC_INPUT ="centerCapacity.invalid.logic.input";
	
	public static final String CENTER_NAME = "centerName";
	public static final String POST_CODE = "postCode";
	public static final String ADDRESS = "address";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String MANAGER_NAME = "managerName";
	public static final String MAX_STORAGE_CAPACITY = "maxStorageCapacity";
	public static final String CURRENT_STORAGE_CAPACITY = "currentStorageCapacity";
	public static final String NOTES = "notes";
	
	public static final String FIELD_EMPTY = ".field.empty";
	public static final String INVALID_INPUT = ".invalid.input";
	public static final String LENGTH_WRONG_INPUT = ".length.wrongInput";
	public static final String MIN_INPUT = ".min.input";
	public static final String MAX_INPUT = ".max.input";
	public static final String INTEGER_INPUT = ".integer.input";
	public static final String INVALID_FORMAT = ".invalid.format";
	public static final String INVALID_LOGIC_INPUT = ".invalid.logic.input";
	
}
