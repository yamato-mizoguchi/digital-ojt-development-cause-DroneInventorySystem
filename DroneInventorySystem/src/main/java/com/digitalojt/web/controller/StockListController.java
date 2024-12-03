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

import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.form.StockInfoForm;
import com.digitalojt.web.service.CategoryInfoService;
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

	/** 分類情報 サービス*/
	private final CategoryInfoService categoryInfoService;

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

		// 在庫情報画面に表示するデータを格納するリストの作成
		List<StockInfo> stockInfoList = stockInfoService.getStockInfoData();

		// 名称プルダウンに表示する在庫情報を取得（遷移時にstockInfoListと異なる）
		List<StockInfo> stockInfoListPullDown = stockInfoService.getStockNamesByCategoryId(form.getCategoryId());
		
		// 画面表示用にセット
		setView(model, stockInfoList,stockInfoListPullDown, form);

		logger.info(LogMessage.GET + LogMessage.ACCESS_LOG + LogMessage.SUCCESS);
		return UrlConsts.STOCK_LIST_INDEX;
	}

	/**
	 * 初期表示
	 * @param form
	 * @return String(path)
	 */
	@PostMapping(UrlConsts.STOCK_LIST_SEARCH)
	public String search(Model model, @Valid StockInfoForm form, BindingResult bindingResult) {

		// Valid項目チェック
		if (bindingResult.hasErrors()) {

			// 在庫情報画面に表示するデータを取得
			List<StockInfo> stockInfoList = stockInfoService.getStockInfoData();

			// 名称プルダウンに表示する在庫情報を取得（遷移時にstockInfoListと異なる）
			List<StockInfo> stockInfoListPullDown = stockInfoService.getStockNamesByCategoryId(form.getCategoryId());

			// 画面用にセット
			setView(model, stockInfoList, stockInfoListPullDown, form);

			// エラーメッセージをプロパティファイルから取得
			String errorMsg = MessageManager.getMessage(messageSource,
					bindingResult.getGlobalError().getDefaultMessage());
			model.addAttribute("errorMsg", errorMsg);

			logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.FAILURE + "：" + errorMsg);
			return UrlConsts.STOCK_LIST_INDEX;
		}

		// 在庫情報画面に表示するデータを取得
		List<StockInfo> stockInfoList = stockInfoService.getStockInfoData(form.getCategoryId(), form.getName(),
				((form.getAmount() == null) ? null : Integer.parseInt(form.getAmount())), form.getAmountCondition()); // 条件演算子はException対策

		// 名称プルダウンに表示する在庫情報を取得（遷移時にstockInfoListと異なる）
		List<StockInfo> stockInfoListPullDown = stockInfoService.getStockNamesByCategoryId(form.getCategoryId());

		// 画面表示用にセット
		setView(model, stockInfoList, stockInfoListPullDown, form);

		return UrlConsts.STOCK_LIST_INDEX;
	}

	// AJAXリクエストに対して名称のリストを返す
	@GetMapping(UrlConsts.STOCK_LIST_PULLDOWN_NAMES_BY_CATEGORYID)
	@ResponseBody
	public List<String> getStockNamesByCategoryId(Integer categoryId) {

		// カテゴリに対応する名称を取得
		List<StockInfo> stockInfoList = stockInfoService.getStockNamesByCategoryId(categoryId);
		return stockInfoList.stream()
				.map(StockInfo::getName) // 在庫情報から商品名を取り出す
				.collect(Collectors.toList());
	}

	// 分類で「すべて」を選択した場合に全ての名称を返す
	@GetMapping(UrlConsts.STOCK_LIST_PULLDOWN_ALL_NAMES)
	@ResponseBody
	public List<String> getAllNames() {

		// 全取得
		List<StockInfo> stockInfoList = stockInfoService.getStockInfoData();

		return stockInfoList.stream()
				.map(StockInfo::getName) // 在庫情報から商品名を取り出す
				.collect(Collectors.toList());
	}

	// 遷移後の画面に表示する内容をセットするメソッド
	public void setView(Model model, List<StockInfo> stockInfoList, List<StockInfo> stockInfoListPullDown,  StockInfoForm form) {

		// 画面表示用に商品情報リストをセット
		model.addAttribute("stockInfoList", stockInfoList);

		// プルダウンに表示する分類をセット
		model.addAttribute("categories", categoryInfoService.getCategoryInfoData());
		
		// プルダウンに表示する名称をセット
		model.addAttribute("stockInfoListPullDown", stockInfoListPullDown);

		// プルダウンに表示する前フォームでの入力情報（名称以外）をセット
		model.addAttribute("inputtedValue", form);

	}

}