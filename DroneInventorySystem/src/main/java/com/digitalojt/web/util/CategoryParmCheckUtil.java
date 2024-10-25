package com.digitalojt.web.util;

import java.util.Arrays;

import com.digitalojt.web.consts.CategoryInvalidCharacter;

/**
 * パラメーターチェックに関する処理を行うクラス
 * 
 * @author yamato mizoguchi
 *
 */
public class CategoryParmCheckUtil {

	/**
	 * 分類名の不正文字チェック
	 *  
	 * @param val
	 * @return
	 */
	public static Boolean isParameterInvalid(String val) {

		return Arrays.stream(CategoryInvalidCharacter.values())
                .anyMatch(invalidChar -> val.indexOf(invalidChar.getCharacter()) >= 0);
	}
}