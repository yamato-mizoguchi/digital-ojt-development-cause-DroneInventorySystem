package com.digitalojt.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.form.CenterInfoEditForm;

public class CompareCapacityEditValidatorImpl implements ConstraintValidator<CompareCapacityEditValidator, CenterInfoEditForm> {

    @Override
    public boolean isValid(CenterInfoEditForm form, ConstraintValidatorContext context) {
    	
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