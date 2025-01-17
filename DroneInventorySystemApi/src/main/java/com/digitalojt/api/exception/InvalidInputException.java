package com.digitalojt.api.exception;

public class InvalidInputException extends RuntimeException {
	// コンストラクタ
	public InvalidInputException(String message) {
		super(message); // 親クラスのコンストラクタにメッセージを渡す
	}
}
