package com.digitalojt.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.CenterInfoRegisterForm;

public class CompareCapacityValidatorImpl implements ConstraintValidator<CompareCapacityValidator, CenterInfoRegisterForm> {

    @Override
    public void initialize(CompareCapacityValidator constraintAnnotation) {
        // 初期化が必要な場合は記述
    }

    @Override
    public boolean isValid(CenterInfoRegisterForm form, ConstraintValidatorContext context) {
    	
        if (form.getMaxStorageCapacity() != null && form.getCurrentStorageCapacity() != null) {
            // 現在容量が最大容量より大きい場合はエラー
            if (form.getCurrentStorageCapacity() > form.getMaxStorageCapacity()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{"+ ErrorMessage.CURRENT_STORAGE_CAPACITY + ErrorMessage.INVALID_LOGIC_INPUT + "}")
                       .addPropertyNode("currentStorageCapacity")
                       .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
