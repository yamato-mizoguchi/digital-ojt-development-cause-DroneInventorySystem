package com.digitalojt.api.exception;

/**
 * バリデーションのカスタム例外クラス
 * 
 * @author yamato mizoguchi
 *
 */
public class InvalidInputException extends RuntimeException {
	// コンストラクタ
	public InvalidInputException(String message) {
		super(message); // 親クラスのコンストラクタにメッセージを渡す
	}
}
