package com.digitalojt.web.validation;

import java.math.BigInteger;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.StockInfoForm;

/**
 * 分類情報画面のバリデーションチェック 実装クラス
 * 
 * @author yamato mizoguchi
 */
public class StockInfoFormValidatorImpl implements ConstraintValidator<StockInfoFormValidator, StockInfoForm> {

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(StockInfoForm form, ConstraintValidatorContext context) {

		boolean allFieldsEmpty = (form.getCategoryId() == null || form.getCategoryId() == 0)
				&& StringUtils.isEmpty(form.getName()) && StringUtils.isEmpty(form.getAmount());

		// すべてのフィールドが空かをチェック
		if (allFieldsEmpty) {
			setErrorMessage(context, ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE);
			return false;
		}

		// 個数のチェック
		if (StringUtils.isEmpty(form.getAmount())) {
			// 空文字の場合、nullに変換して許可
			form.setAmount(null);
			return true; // 空文字はnullとして許可
		}
		try {
			// 文字列をBigIntegerに変換
			BigInteger amount = new BigInteger(form.getAmount());

			// Integerの範囲を超えていないかチェック
			if (amount.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0
					|| amount.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
				setErrorMessage(context, ErrorMessage.AMOUNT_LENGTH_ERROR_MESSAGE);
				return false;
			}
			// 範囲内の数値であれば許可
			Integer.parseInt(form.getAmount()); // Integerに変換して範囲内か確認
			return true;

		} catch (NumberFormatException e) {
			setErrorMessage(context, ErrorMessage.AMOUNT_INVALID_INPUT_ERROR_MESSAGE);
			return false; // 数値でない場合は無効
		}
	}
	
	// エラーメッセージをセットするメソッド
	public void setErrorMessage(ConstraintValidatorContext context, String errorMessage) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(errorMessage)
				.addConstraintViolation();
	}
}
