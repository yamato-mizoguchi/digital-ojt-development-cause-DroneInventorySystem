package com.digitalojt.web.consts;

/**
 * URL定数クラス
 *
 * @author yamato mizoguchi
 * 
 */
public class UrlConsts {

	// ログイン
	public static final String LOGIN = "/login";

	// 認証
	public static final String AUTHENTICATE = "/authenticate";

	// 在庫一覧画面
	public static final String STOCK_LIST = "/admin/stockList";
	
	// 在庫一覧画面HTML
	public static final String STOCK_LIST_INDEX =  "admin/stockList/index";

	// 在庫一覧画面 検索
	public static final String STOCK_LIST_SEARCH = "/admin/stockList/search";
	
	// AJAXリクエストで分類毎の名称
	public static final String STOCK_LIST_PULLDOWN_NAMES_BY_CATEGORYID = "/admin/stockList/getStockNamesByCategoryId" ;
	
	// AJAXリクエストで全名称
	public static final String STOCK_LIST_PULLDOWN_ALL_NAMES = "/admin/stockList/getAllNames";
	
	// 在庫センター情報画面
	public static final String  CENTER_INFO = "/admin/centerInfo";

	// 在庫センター情報画面 検索
	public static final String CENTER_INFO_SEARCH = "/admin/centerInfo/search";
	
	// 在庫センター情報画面 登録
	public static final String CENTER_INFO_REGISTER = "/admin/centerInfo/register";
	
	// 在庫センター情報画面 更新
	public static final String CENTER_INFO_EDIT = "/admin/centerInfo/edit";
	
	// 在庫センター情報画面 削除
	public static final String CENTER_INFO_DELETE = "/admin/centerInfo/delete";
	
	// 分類情報画面
	public static final String CATEGORY_INFO = "/admin/categoryInfo";
	
	// 分類情報画面 検索
	public static final String CATEGORY_INFO_SEARCH = "/admin/categoryInfo/search";
	
	// 操作履歴画面
	public static final String  OPERATION_LOG = "/admin/operationLog";
	
	// 操作履歴画面 検索
	public static final String  OPERATION_LOG_SEARCH = "/admin/operationLog/search";

	// 認証不要画面
	public static final String[] NO_AUTHENTICATION = {LOGIN, AUTHENTICATE};
}
