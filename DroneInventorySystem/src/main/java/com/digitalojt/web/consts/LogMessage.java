package com.digitalojt.web.consts;

import java.util.List;

/**
 * ログメッセージクラス
 *
 * @author yamato mizoguchi
 * 
 */

public class LogMessage {
	
	public static final String STOCK_LIST = "在庫一覧画面";
	
	public static final String CENTER_INFO = "在庫センター情報画面";
	
	public static final String GET = "GET-";
	
	public static final String POST = "POST-";
	
	public static final String ACCESS_LOG = "アクセスログ";
	
	public static final String APPLICATION_LOG = "アプリケーションログ";
	
	public static final String ERROR_LOG = "エラーログ";
	
	public static final String SUCCESS = "-成功：";
	
	public static final String FAILURE = "-失敗：";
	
	public static final String SEARCH_START = "検索開始";
	
	public static final String SEARCH_END = "検索結果";
	
	public static String SearchResult(List<?> a) {
		return "検索結果" + a.size() + "件";
	}
}