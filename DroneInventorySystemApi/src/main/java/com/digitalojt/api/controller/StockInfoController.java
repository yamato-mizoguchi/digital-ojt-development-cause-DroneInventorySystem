package com.digitalojt.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalojt.api.dto.StockInfoListDTO;
import com.digitalojt.api.dto.StockInfoSearchCriteriaDTO;
import com.digitalojt.api.dto.StockNameDTO;
import com.digitalojt.api.entity.AdminInfo;
import com.digitalojt.api.service.AdminInfoService;
import com.digitalojt.api.service.OperationLogService;
import com.digitalojt.api.service.StockInfoService;
import com.digitalojt.api.util.InputValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stockinfo")
@CrossOrigin(origins = "*")
public class StockInfoController {

    private final StockInfoService stockInfoService;
    private final OperationLogService operationLogService;
    private final AdminInfoService adminInfoService;
    private final InputValidator inputValidator;

    // すべての在庫情報を取得
    @GetMapping
    public ResponseEntity<List<StockInfoListDTO>> getStockInfoAll() {
        List<StockInfoListDTO> stockInfoListDTO = stockInfoService.getStockInfoAll();  // サービスでDTO変換済み
        return ResponseEntity.ok(stockInfoListDTO);
    }

    // カテゴリ別で在庫情報を取得
    @GetMapping("/category")
    public ResponseEntity<List<StockNameDTO>> getStockInfoByCategory(@RequestParam("categoryId") Long categoryId) {
        List<StockNameDTO> stockNameDTO = stockInfoService.getStockInfoByCategory(categoryId);  // サービスでDTO変換済み
        return ResponseEntity.ok(stockNameDTO);
    }

    // 在庫情報を検索
    @PostMapping("/search")
    public ResponseEntity<List<StockInfoListDTO>> searchStockInfo(@RequestBody StockInfoSearchCriteriaDTO stockInfoDTO) {
        inputValidator.validateAmount(stockInfoDTO.getAmountMin(), stockInfoDTO.getAmountMax());

        // 管理者情報を取得して
        // 操作履歴を保存
        AdminInfo adminInfo = adminInfoService.getAdminInfoById("dotlife");
        operationLogService.createOperationLog(adminInfo, "stockList.title", "4", "0");

        // 在庫情報を検索し、DTOに変換して返す
        List<StockInfoListDTO> stockInfoListDTO = stockInfoService.searchStockInfo(stockInfoDTO);  // サービスでDTO変換済み
        return ResponseEntity.ok(stockInfoListDTO);
    }
}
