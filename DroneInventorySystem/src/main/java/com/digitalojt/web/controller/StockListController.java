package com.digitalojt.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digitalojt.web.DTO.StockInfoDTO;
import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.form.StockInfoForm;
import com.digitalojt.web.service.StockInfoService;
import com.digitalojt.web.util.MessageManager;

import lombok.RequiredArgsConstructor;

/**
 * 在庫一覧画面コントローラークラス
 * 
 * @author yamato mizoguchi
 *
 */
@Controller
@RequiredArgsConstructor
public class StockListController extends AbstractController {

	/** 在庫情報 サービス*/
	private final StockInfoService stockInfoService;

	/** メッセージソース */
	private final MessageSource messageSource;

	/** ログのカテゴリ　画面名の取得*/
	private static Logger logger = LoggerFactory.getLogger(LogMessage.STOCK_LIST);

	
	/**
	 * 初期表示
	 * @param form
	 * @return String(path)
	 */
	@GetMapping(UrlConsts.STOCK_LIST)
	public String index(Model model, StockInfoForm form, BindingResult bindingResult) {		

		// 画面表示用にセット
		setView(model, stockInfoService.setStockInfoDTO(form), form);

		logger.info(LogMessage.GET + LogMessage.ACCESS_LOG + LogMessage.SUCCESS);
		return UrlConsts.STOCK_LIST_INDEX;
	}

	/**
	 * 検索結果
	 * @param form
	 * @return String(path)
	 */
	@PostMapping(UrlConsts.STOCK_LIST_SEARCH)
	public String search(Model model, @Valid StockInfoForm form, BindingResult bindingResult) {
		
		// Valid項目チェック
		if (bindingResult.hasErrors()) {

			// 画面表示用にセット
			setView(model, stockInfoService.setStockInfoDTO(form), form);

			// エラーメッセージをプロパティファイルから取得してセット
			String errorMsg = MessageManager.getMessage(messageSource, bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute("errorMsg", errorMsg);

			logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.FAILURE + "：" + errorMsg);
			return UrlConsts.STOCK_LIST_INDEX;
		}

		// 画面表示用にセット
		setView(model, stockInfoService.setStockInfoDTO_notValidError(form), form);

		return UrlConsts.STOCK_LIST_INDEX;
	}

	// AJAXリクエストに対して名称のリストを返す
	@GetMapping(UrlConsts.STOCK_LIST_PULLDOWN_NAMES_BY_CATEGORYID)
	@ResponseBody
	public List<String> getStockNamesByCategoryId(Integer categoryId) {

		// カテゴリに対応する名称を取得
		return stockInfoService.getStockNamesByCategoryId(categoryId).stream()
				.map(StockInfo::getName) // 在庫情報から商品名を取り出す
				.collect(Collectors.toList());
	}

	// 分類で「すべて」を選択した場合に全ての名称を返す
	@GetMapping(UrlConsts.STOCK_LIST_PULLDOWN_ALL_NAMES)
	@ResponseBody
	public List<String> getAllNames() {

		// 全取得
		return stockInfoService.getStockInfoData().stream()
				.map(StockInfo::getName) // 在庫情報から商品名を取り出す
				.collect(Collectors.toList());
	}

	
	// 遷移後の画面に表示する内容をセットするメソッド
	public void setView(Model model, StockInfoDTO stockInfoDTO, StockInfoForm form) {

		// 画面表示用に商品情報リストをセット
		model.addAttribute("stockInfoList", stockInfoDTO.getStockInfoList());

		// プルダウンに表示する分類をセット
		model.addAttribute("categories", stockInfoDTO.getCategoryInfoList());

		// プルダウンに表示する名称をセット
		model.addAttribute("stockInfoListPullDown", stockInfoDTO.getStockInfoListPullDown());

		// プルダウンに表示する前フォームでの入力情報をセット
		model.addAttribute("inputtedValue", form);

	}

}