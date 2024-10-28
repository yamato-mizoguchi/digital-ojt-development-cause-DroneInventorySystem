package com.digitalojt.web.consts;

/**
 * 分類名の不正文字を管理するEnumクラス
 * 
 * @author yamato mizoguchi
 */

public enum CategoryInvalidCharacter {
	
	HALF_WIDTH_SPACE(' '),
    CURLY_BRACE_OPEN('{'),
    CURLY_BRACE_CLOSE('}'),
    PARENTHESIS_OPEN('('),
    PARENTHESIS_CLOSE(')'),
    SINGLE_QUOTE('\''),
    ASTERISK('*'),
    SEMICOLON(';'),
    DOLLAR_SIGN('$'),
    EQUAL_SIGN('='),
    AMPERSAND('&');

    private final char character;

    CategoryInvalidCharacter(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
