package com.digitalojt.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
* グローバル例外ハンドラークラス
* 
* @author yamato mizoguchi
*
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    // MyCustomException がスローされた場合の処理
    @ExceptionHandler(StockInfoException.class)
    public ResponseEntity<String> handleMyCustomException(StockInfoException ex) {
        // エラーメッセージをJSON形式で返す
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
