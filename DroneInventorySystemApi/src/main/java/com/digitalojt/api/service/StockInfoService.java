package com.digitalojt.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digitalojt.api.dto.StockInfoListDTO;
import com.digitalojt.api.dto.StockInfoSearchCriteriaDTO;
import com.digitalojt.api.dto.StockNameDTO;
import com.digitalojt.api.entity.StockInfo;
import com.digitalojt.api.repository.StockInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StockInfoService {

    private final StockInfoRepository stockInfoRepository;
    
    // すべての在庫情報を取得し、DTOに変換して返す
    public List<StockInfoListDTO> getStockInfoAll() {
        List<StockInfo> stockInfoList = stockInfoRepository.findAll();
        return convertToStockInfoListDTO(stockInfoList);  // DTOに変換して返す
    }

    // カテゴリ別で在庫情報を取得し、DTOに変換して返す
    public List<StockNameDTO> getStockInfoByCategory(Long categoryId) {
        List<StockInfo> stockInfoList = stockInfoRepository.findByCategoryId(categoryId);
        return convertToStockNameDTO(stockInfoList);  // StockNameDTOに変換して返す
    }

    // 在庫情報を検索し、DTOに変換して返す
    public List<StockInfoListDTO> searchStockInfo(StockInfoSearchCriteriaDTO stockInfoDTO) {
        List<StockInfo> stockInfoList = stockInfoRepository.findByStockInfo(
            stockInfoDTO.getCategoryId(),
            stockInfoDTO.getName(),
            stockInfoDTO.getAmountMin(),
            stockInfoDTO.getAmountMax()
        );
        return convertToStockInfoListDTO(stockInfoList);  // DTOに変換して返す
    }

    // StockInfoのリストをStockInfoListDTOに変換する共通メソッド
    private List<StockInfoListDTO> convertToStockInfoListDTO(List<StockInfo> stockInfoList) {
        return stockInfoList.stream()
            .map(stockInfo -> new StockInfoListDTO(
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

    // StockInfoのリストをStockNameDTOに変換する共通メソッド
    private List<StockNameDTO> convertToStockNameDTO(List<StockInfo> stockInfoList) {
        return stockInfoList.stream()
            .map(stockInfo -> new StockNameDTO(
                    stockInfo.getStockId(),
                    stockInfo.getName()))
            .collect(Collectors.toList());
    }
}
