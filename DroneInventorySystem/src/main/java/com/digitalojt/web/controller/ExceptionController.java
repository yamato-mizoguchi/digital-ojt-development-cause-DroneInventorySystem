package com.digitalojt.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.exception.CenterInfoException;
import com.digitalojt.web.exception.StockInfoException;

/**
* 例外コントローラークラス
* 
* @author yamato mizoguchi
*
*/
@ControllerAdvice
public class ExceptionController {

	@Autowired
	private MessageSource messageSource;
	
	/** ログのカテゴリ　画面名の取得*/
	private static Logger stockInfoLogger = LoggerFactory.getLogger(LogMessage.STOCK_LIST);
	
	/** ログのカテゴリ　画面名の取得*/
	private static Logger centerInfoLogger = LoggerFactory.getLogger(LogMessage.CENTER_INFO);

	@ExceptionHandler(StockInfoException.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleStockInfoException(StockInfoException ex, Model model) {
		String errorMsg = messageSource.getMessage(ex.getMessage(), null, null);
		stockInfoLogger.error(LogMessage.POST + LogMessage.ERROR_LOG + LogMessage.FAILURE
				+ errorMsg);
		model.addAttribute("errorMsg", errorMsg);

		return "admin/stockList/error";
	}
	
	@ExceptionHandler(CenterInfoException.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleCenterInfoException(CenterInfoException ex, Model model) {
		String errorMsg = messageSource.getMessage(ex.getMessage(), null, null);
		centerInfoLogger.error(LogMessage.POST + LogMessage.ERROR_LOG + LogMessage.FAILURE
				+ errorMsg);
		model.addAttribute("errorMsg", errorMsg);

		return "admin/centerInfo/error";
	}
}
