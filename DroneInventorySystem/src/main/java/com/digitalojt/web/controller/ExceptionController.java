package com.digitalojt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

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

	@ExceptionHandler(StockInfoException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleMyCustomException(StockInfoException ex, Model model) {

		model.addAttribute("errorMsg", messageSource.getMessage(ex.getMessage(), null, null));

		return "admin/stockList/error";
	}
}
