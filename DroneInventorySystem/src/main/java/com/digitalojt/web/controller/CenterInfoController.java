package com.digitalojt.web.controller;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.digitalojt.web.DTO.CenterInfoDTO;
import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.service.CenterInfoService;
import com.digitalojt.web.util.MessageManager;

import lombok.RequiredArgsConstructor;

/**
 * 在庫センター情報画面のコントローラークラス
 * 
 * @author yamato mizoguchi
 *
 */
@Controller
@RequiredArgsConstructor
public class CenterInfoController {

	/** センター情報 サービス */
	private final CenterInfoService centerInfoService;

	/** メッセージソース */
	private final MessageSource messageSource;
	
	/** ログのカテゴリ　画面名の取得*/
	private static Logger logger = LoggerFactory.getLogger(LogMessage.CENTER_INFO);

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CENTER_INFO)
	public String index(Model model, CenterInfoForm form, BindingResult bindingResult) {
		
		setView(model, centerInfoService.setCenterInfoDTO(), form);

		logger.info(LogMessage.GET + LogMessage.ACCESS_LOG + LogMessage.SUCCESS);
		return "admin/centerInfo/index";
	}

	/**
	 * 検索結果表示
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping(UrlConsts.CENTER_INFO_SEARCH)
	public String search(Model model, @Valid CenterInfoForm form, BindingResult bindingResult) {

		// Valid項目チェック
		if (bindingResult.hasErrors()) {

			String errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute("errorMsg", errorMsg);
			
			setView(model, centerInfoService.setCenterInfoDTO(), form);
			
			logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.FAILURE + "：" + errorMsg);
			return "admin/centerInfo/index";
		}
		
		setView(model, centerInfoService.setCenterInfoDTO(form), form);

		return "admin/centerInfo/index";
	}
	
	public void setView(Model model, CenterInfoDTO centerInfoDTO, CenterInfoForm form) {
		model.addAttribute("centerInfoList", centerInfoDTO.getCenterInfoList());
		model.addAttribute("regions", centerInfoDTO.getRegions());
		model.addAttribute("centerInfoForm", form);
	}
}