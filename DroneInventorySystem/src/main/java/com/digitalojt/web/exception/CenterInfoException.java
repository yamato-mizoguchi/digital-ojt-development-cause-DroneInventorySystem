package com.digitalojt.web.exception;

/**
* 在庫センター情報のカスタム例外クラス
* 
* @author yamato mizoguchi
*
*/
public class CenterInfoException extends RuntimeException {
    public CenterInfoException(String message) {
        super(message);
    }
}
