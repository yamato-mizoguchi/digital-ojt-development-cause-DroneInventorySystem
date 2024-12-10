package com.digitalojt.web.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

// アノテーションがフィールドに適用されることを示す
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CompareCapacityEditValidatorImpl.class) // バリデータクラスを指定
public @interface CompareCapacityEditValidator {
    String message() default ""; // デフォルトエラーメッセージ
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
