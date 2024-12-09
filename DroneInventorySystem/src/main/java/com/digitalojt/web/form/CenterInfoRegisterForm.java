package com.digitalojt.web.form;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.validation.CompareCapacityValidator;

import lombok.Data;

/**
 * 在庫センター情報画面のフォームクラス
 * 
 * @author yamato mizoguchi
 *
 */
@Data
@CompareCapacityValidator
public class CenterInfoRegisterForm{
	
	/**
	 * センター名
	 */
	@NotEmpty(message = "{"+ ErrorMessage.CENTER_NAME + ErrorMessage.FIELD_EMPTY + "}")
    @Size(max = 20, message = "{" + ErrorMessage.CENTER_NAME + ErrorMessage.LENGTH_WRONG_INPUT + "}")
    @Pattern(regexp = "^[^{}()=;&$?*]*$", message = "{" + ErrorMessage.INVALID_INPUT_ERROR_MESSAGE + "}")
	private String centerName;
	
	/**
	 * 郵便番号
	 */
    @NotEmpty(message = "{"+ ErrorMessage.POST_CODE + ErrorMessage.FIELD_EMPTY + "}")
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "{"+ ErrorMessage.POST_CODE + ErrorMessage.INVALID_FORMAT + "}")
	private String postCode;
	
	/**
	 * 住所
	 */
    @NotEmpty(message = "{"+ ErrorMessage.ADDRESS + ErrorMessage.FIELD_EMPTY + "}")
    @Size(max = 20, message = "{" + ErrorMessage.ADDRESS + ErrorMessage.LENGTH_WRONG_INPUT + "}")
    @Pattern(regexp = "^[^{}()=;&$?*]*$", message = "{" + ErrorMessage.INVALID_INPUT_ERROR_MESSAGE + "}")
	private String address;
	
	/**
	 * 電話番号
	 */
    @NotEmpty(message = "{"+ ErrorMessage.PHONE_NUMBER + ErrorMessage.FIELD_EMPTY + "}")
    @Size(max = 20, message = "{" + ErrorMessage.PHONE_NUMBER + ErrorMessage.LENGTH_WRONG_INPUT + "}")
    @Pattern(regexp = "^[^{}()=;&$?*]*$", message = "{" + ErrorMessage.INVALID_INPUT_ERROR_MESSAGE + "}")
	private String phoneNumber;
	
	/**
	 * 管理者名
	 */
    @NotEmpty(message = "{"+ ErrorMessage.MANAGER_NAME + ErrorMessage.FIELD_EMPTY + "}")
    @Size(max = 20, message = "{" + ErrorMessage.MANAGER_NAME + ErrorMessage.LENGTH_WRONG_INPUT + "}")
    @Pattern(regexp = "^[^{}()=;&$?*]*$", message = "{" + ErrorMessage.INVALID_INPUT_ERROR_MESSAGE + "}")
	private String managerName;
	
	/**
	 * 未使用フラグ
	 */
    @NotNull(message = "{" + ErrorMessage.INVALID_INPUT_ERROR_MESSAGE + "}")
    @Min(value = 0, message = "{" + ErrorMessage.INVALID_INPUT_ERROR_MESSAGE + "}")
    @Max(value = 1, message = "{" + ErrorMessage.INVALID_INPUT_ERROR_MESSAGE + "}")
	private int operationalStatus;
	
	/**
	 * 最大容量
	 */
    @NotNull(message = "{"+ ErrorMessage.MAX_STORAGE_CAPACITY + ErrorMessage.FIELD_EMPTY + "}")
    @Min(value = 1, message = "{"+ ErrorMessage.MAX_STORAGE_CAPACITY + ErrorMessage.MIN_INPUT + "}")
    @Max(value = Integer.MAX_VALUE, message = "{"+ ErrorMessage.MAX_STORAGE_CAPACITY + ErrorMessage.MAX_INPUT + "}")
    @Digits(integer = 10, fraction = 0, message = "{"+ ErrorMessage.MAX_STORAGE_CAPACITY + ErrorMessage.INTEGER_INPUT + "}")
	private Integer maxStorageCapacity;
	
	/**
	 * 現在容量
	 */
    @NotNull(message = "{"+ ErrorMessage.CURRENT_STORAGE_CAPACITY + ErrorMessage.FIELD_EMPTY + "}")
    @Min(value = 0, message = "{"+ ErrorMessage.CURRENT_STORAGE_CAPACITY + ErrorMessage.MIN_INPUT + "}")
    @Max(value = Integer.MAX_VALUE, message = "{"+ ErrorMessage.CURRENT_STORAGE_CAPACITY + ErrorMessage.MAX_INPUT + "}")
    @Digits(integer = 10, fraction = 0, message = "{"+ ErrorMessage.CURRENT_STORAGE_CAPACITY + ErrorMessage.INTEGER_INPUT + "}")
	private Integer currentStorageCapacity;
	
	/**
	 * 備考
	 */
    @Size(max = 255, message = "{"+ ErrorMessage.NOTES + ErrorMessage.MAX_INPUT + "}")
	private String notes;
    
    

}