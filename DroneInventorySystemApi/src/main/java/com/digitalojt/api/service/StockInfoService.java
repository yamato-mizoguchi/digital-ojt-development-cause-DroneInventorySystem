package com.digitalojt.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digitalojt.api.dto.StockInfoDTO;
import com.digitalojt.api.entity.StockInfo;
import com.digitalojt.api.repository.StockInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 在庫情報サービスクラス
 * 
 * @author yamato mizoguchi
 *
 */
@RequiredArgsConstructor
@Service
public class StockInfoService {

	private final StockInfoRepository stockInfoRepository;

	// すべての在庫情報を取得し、DTOに変換して返す
	public List<StockInfoDTO> getStockInfoAll() {
		List<StockInfo> stockInfoList = stockInfoRepository.findAll();
		return convertToStockInfoListDTO(stockInfoList); // DTOに変換して返す
	}

	// カテゴリ別で在庫情報を取得し、DTOに変換して返す
	public List<StockInfoDTO> getStockInfoByCategory(Integer categoryId) {
		List<StockInfo> stockInfoList = stockInfoRepository.findByCategoryId(categoryId);
		return convertToStockInfoListDTO(stockInfoList); // DTOに変換して返す
	}

	// 在庫情報を検索し、DTOに変換して返す
	public List<StockInfoDTO> searchStockInfo(Integer categoryId, String name, Integer amountMax, Integer amountMin) {
		List<StockInfo> stockInfoList = stockInfoRepository.findByStockInfo(categoryId, name, amountMax, amountMin);
		return convertToStockInfoListDTO(stockInfoList); // DTOに変換して返す
	}

	// StockInfoのリストをStockInfoListDTOに変換する共通メソッド
	private List<StockInfoDTO> convertToStockInfoListDTO(List<StockInfo> stockInfoList) {
		return stockInfoList.stream()
				.map(stockInfo -> new StockInfoDTO(
						stockInfo.getStockId(),
						stockInfo.getCategoryInfo().getCategoryId(),
						stockInfo.getCategoryInfo().getCategoryName(),
						stockInfo.getName(),
						stockInfo.getCenterInfo().getCenterId(),
						stockInfo.getCenterInfo().getCenterName(),
						stockInfo.getDescription(),
						stockInfo.getAmount()))
				.collect(Collectors.toList());
	}
}
