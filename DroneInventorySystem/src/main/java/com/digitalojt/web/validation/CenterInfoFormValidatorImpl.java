package com.digitalojt.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.thymeleaf.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.SearchParams;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.util.CategoryParmCheckUtil;
import com.digitalojt.web.util.ParmCheckUtil;

/**
 * 在庫センター情報画面のバリデーションチェック 実装クラス
 * 
 * @author yamato mizoguchi
 */
public class CenterInfoFormValidatorImpl implements ConstraintValidator<CenterInfoFormValidator, CenterInfoForm> {

	/**
	 * バリデーションチェック
	 */
	@Override
	public boolean isValid(CenterInfoForm form, ConstraintValidatorContext context) {

		boolean allFieldsEmpty = StringUtils.isEmpty(form.getCenterName()) &&
				StringUtils.isEmpty(form.getRegion()) &&
				StringUtils.isEmpty(form.getStorageCapacityFrom()) &&
				StringUtils.isEmpty(form.getStorageCapacityTo());

		// すべてのフィールドが空かをチェック
		if (allFieldsEmpty) {
			setErrorMessage(context, ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE);
			return false;
		}

		// センター名のチェック
		if (form.getCenterName() != null) {

			// 不正文字列チェック
			if (CategoryParmCheckUtil.isParameterInvalid(form.getCenterName())) {
				setErrorMessage(context, ErrorMessage.INVALID_INPUT_ERROR_MESSAGE);
				return false;
			}

			// 文字数チェック
			if (form.getCenterName().length() > SearchParams.MAX_LENGTH) {
				setErrorMessage(context, ErrorMessage.CENTER_NAME_LENGTH_ERROR_MESSAGE);
				return false;
			}
		}

		// 都道府県のチェック
		if (form.getRegion() != null) {

			// 不正文字列チェック
			if (ParmCheckUtil.isParameterInvalid(form.getRegion())) {
				setErrorMessage(context, ErrorMessage.INVALID_INPUT_ERROR_MESSAGE);
				return false;
			}
		}

		if(StringUtils.isEmpty(form.getStorageCapacityFrom())) {
			form.setStorageCapacityFrom(null);
		}
		if(StringUtils.isEmpty(form.getStorageCapacityTo())) {
			form.setStorageCapacityTo(null);
		}
		if (StringUtils.isEmpty(form.getStorageCapacityFrom())&&StringUtils.isEmpty(form.getStorageCapacityTo())) {
			return true;
		}
		
		try {
			if (form.getStorageCapacityFrom() != null) {
				// 数値チェック (数値でないまたはint型の範囲外の場合にNumberFormatException)
				Integer.parseInt(form.getStorageCapacityFrom());
			}
			if (form.getStorageCapacityTo() != null) {
				Integer.parseInt(form.getStorageCapacityTo());
			}
		} catch (NumberFormatException e) {
			setErrorMessage(context, ErrorMessage.CENTER_CAPACITY_INVALID_INPUT);
			return false;
		}

		// capacity論理チェック
		if (form.getStorageCapacityFrom() != null && form.getStorageCapacityTo() != null) {

			// 両方に入力がある場合
			if (Integer.parseInt(form.getStorageCapacityFrom()) > Integer.parseInt(form.getStorageCapacityTo())) {
				// fromがtoより大きい場合
				setErrorMessage(context, ErrorMessage.CENTER_CAPACITY_INVALID_LOGIC_INPUT);
				
				return false;
			}

		}

		return true;
	}
	
	public void setErrorMessage(ConstraintValidatorContext context, String errorMessage) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(errorMessage)
				.addConstraintViolation();
	}
}