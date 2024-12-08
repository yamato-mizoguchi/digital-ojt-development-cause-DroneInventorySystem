package com.digitalojt.web.exception;

/**
* 在庫一覧のカスタム例外クラス
* 
* @author yamato mizoguchi
*
*/
public class StockInfoException extends RuntimeException {
    public StockInfoException(String message) {
        super(message);
    }

    public StockInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
