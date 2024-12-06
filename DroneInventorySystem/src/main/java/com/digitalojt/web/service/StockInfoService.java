package com.digitalojt.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.digitalojt.web.DTO.StockInfoDTO;
import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.entity.StockInfo;
import com.digitalojt.web.exception.StockInfoException;
import com.digitalojt.web.form.StockInfoForm;
import com.digitalojt.web.repository.StockInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 在庫情報画面のサービスクラス
 *
 * @author yamato mizoguchi
 * 
 */
@Service
@RequiredArgsConstructor
public class StockInfoService {

	/** 在庫情報　リポジトリ */
	private final StockInfoRepository repository;

	/** 分類情報　サービス */
	private final CategoryInfoService categoryInfoService;

	/** ログのカテゴリ　画面名の取得*/
	private static Logger logger = LoggerFactory.getLogger(LogMessage.STOCK_LIST);

	/**
	 * 未削除在庫を全件検索で取得
	 * 
	 * @return 検索結果
	 */
	public List<StockInfo> getStockInfoData() {

		try {
			// 在庫情報未削除検索
			List<StockInfo> stockInfoList = repository.findAllDeleteFlagFalse();
			return stockInfoList;
			
		} catch (Exception e) {
			logger.error(LogMessage.POST + LogMessage.ERROR_LOG + LogMessage.FAILURE
					+ ErrorMessage.STOCK_DB_EXCEPTION);
			throw new StockInfoException(ErrorMessage.STOCK_DB_EXCEPTION);
		}
	}

	/**
	 * 引数に合致する未削除在庫を取得
	 * 
	 * @param categoryId
	 * @param name
	 * @param amount
	 * @param amountCondition
	 * @return 検索結果
	 */
	public List<StockInfo> getStockInfoData(Integer categoryId, String name, Integer amount, Integer amountCondition) {

		try {
			// 在庫検索処理開始のログ
			logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.SUCCESS + LogMessage.SEARCH_START);

			// 在庫情報検索
			List<StockInfo> stockInfoList = repository.findByCategoryIdAndNameAndAmount(categoryId, name, amount,
					amountCondition);

			// 在庫検索処理正常終了のログ
			logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.SUCCESS
					+ LogMessage.SearchResult(stockInfoList));

			return stockInfoList;
		} catch (Exception e) {
			logger.error(LogMessage.POST + LogMessage.ERROR_LOG + LogMessage.FAILURE
					+ ErrorMessage.STOCK_DB_EXCEPTION);
			throw new StockInfoException(ErrorMessage.STOCK_DB_EXCEPTION);
		}
	}

	/**
	 * 引数に合致する未削除在庫を取得
	 * 
	 * @param categoryId
	 * @return 検索結果
	 */
	public List<StockInfo> getStockNamesByCategoryId(Integer categoryId) {
		
		try {
		// 分類IDに基づいて名称のリストを取得
		return repository.findByCategoryId(categoryId);
		}catch(Exception e) {
			logger.error(LogMessage.POST + LogMessage.ERROR_LOG + LogMessage.FAILURE
					+ ErrorMessage.STOCK_DB_EXCEPTION);
			throw new StockInfoException(ErrorMessage.STOCK_DB_EXCEPTION);
		}
	}

	/**
	 * 未検索時とバリデーションエラー時、画面表示用にデータを取得
	 * 
	 * @param form
	 * @return 在庫一覧画面DTO
	 */
	public StockInfoDTO setStockInfoDTO(StockInfoForm form) {
		StockInfoDTO stockInfoDTO = new StockInfoDTO();

		// 在庫情報画面に表示するデータを取得
		stockInfoDTO.setStockInfoList(getStockInfoData());

		// 分類プルダウンに表示する分類情報を取得
		stockInfoDTO.setCategoryInfoList(categoryInfoService.getCategoryInfoData());

		// 名称プルダウンに表示する在庫情報を取得（遷移時にstockInfoListと異なる）
		stockInfoDTO.setStockInfoListPullDown(getStockNamesByCategoryId(form.getCategoryId()));

		return stockInfoDTO;
	}

	/**
	 * バリデーション突破時、画面表示用にデータを取得
	 * 
	 * @param form
	 * @return 在庫一覧画面DTO
	 */
	public StockInfoDTO setStockInfoDTO_notValidError(StockInfoForm form) {
		StockInfoDTO stockInfoDTO = new StockInfoDTO();

		// 検索時、在庫情報画面に表示するデータを取得
		stockInfoDTO.setStockInfoList(
				getStockInfoData(form.getCategoryId(), form.getName(),
						((form.getAmount() == null) ? null : Integer.parseInt(form.getAmount())),
						form.getAmountCondition()));

		// 分類プルダウンに表示する分類情報を取得
		stockInfoDTO.setCategoryInfoList(categoryInfoService.getCategoryInfoData());

		// 名称プルダウンに表示する在庫情報を取得（遷移時にstockInfoListと異なる）
		stockInfoDTO.setStockInfoListPullDown(getStockNamesByCategoryId(form.getCategoryId()));

		return stockInfoDTO;
	}
}