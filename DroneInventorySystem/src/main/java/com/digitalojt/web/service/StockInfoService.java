package com.digitalojt.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.entity.StockInfo;
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

	private final StockInfoRepository repository;
	
	/** ログのカテゴリ　画面名の取得*/
	private static Logger logger = LoggerFactory.getLogger(LogMessage.STOCK_LIST);
	
	/**
	 * 未削除在庫を全件検索で取得
	 * 
	 * @return
	 */
	public List<StockInfo> getStockInfoData() {

		// 在庫情報未削除検索
		List<StockInfo> stockInfoList = repository.findAllDeleteFlagFalse();
		
		return stockInfoList;
	}

	/**
	 * 引数に合致する未削除在庫を取得
	 * 
	 * @categoryId
	 * @name
	 * @amount
	 * @amountCondition
	 * @return
	 */
	public List<StockInfo> getStockInfoData(Integer categoryId, String name, Integer amount, Integer amountCondition) {
		// 在庫検索処理開始のログ
		logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.SUCCESS + LogMessage.SEARCH_START);
		// 在庫情報検索
		List<StockInfo> stockInfoList = repository.findByCategoryIdAndNameAndAmount(categoryId, name, amount, amountCondition);
		// 在庫検索処理正常終了のログ
		logger.info(LogMessage.POST + LogMessage.APPLICATION_LOG + LogMessage.SUCCESS + LogMessage.SearchResult(stockInfoList));
		
		return stockInfoList;
	}
	
	/**
	 * 引数に合致する未削除在庫を取得
	 * 
	 * @categoryId
	 * @return
	 */
	public List<StockInfo> getStockNamesByCategoryId(Integer categoryId) {
        // カテゴリIDに基づいて名称のリストを取得
        return repository.findByCategoryId(categoryId);
	}
}