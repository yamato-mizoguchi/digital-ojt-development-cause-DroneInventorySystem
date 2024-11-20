package com.digitalojt.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.MessageConsts;
import com.digitalojt.web.consts.UrlConsts;

/**
 * 在庫一覧画面コントローラークラス
 * 
 * @author yamato mizoguchi
 *
 */
@Controller
public class StockListController extends AbstractController {

	/**
	 * 初期表示
	 * 
	 * @return String(path)
	 */
	@GetMapping(UrlConsts.STOCK_LIST)
	public String index() {
		Logger logger = LoggerFactory.getLogger(StockListController.class);
        logger.info(MessageConsts.STOCK_LIST_ACCESS);
		return "admin/stockList/index";
	}
}
