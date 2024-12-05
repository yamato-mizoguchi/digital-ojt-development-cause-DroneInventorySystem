package com.digitalojt.web.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import com.digitalojt.web.consts.ErrorMessage;

/**
 * 在庫情報画面のバリデーションチェック インターフェース
 * 
 * @author yamato mizoguchi
 */
@Constraint(validatedBy = StockInfoFormValidatorImpl.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface StockInfoFormValidator {

	String message() default ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
