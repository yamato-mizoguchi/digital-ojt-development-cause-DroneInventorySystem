package com.digitalojt.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalojt.api.dto.StockInfoDTO;
import com.digitalojt.api.entity.AdminInfo;
import com.digitalojt.api.service.AdminInfoService;
import com.digitalojt.api.service.OperationLogService;
import com.digitalojt.api.service.StockInfoService;
import com.digitalojt.api.util.InputValidator;

import lombok.RequiredArgsConstructor;

/**
 * API在庫一覧コントローラークラス
 * 
 * @author yamato mizoguchi
 *
 */
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
    public ResponseEntity<List<StockInfoDTO>> getStockInfoAll() {
        List<StockInfoDTO> stockInfoListDTO = stockInfoService.getStockInfoAll();  // サービスでDTO変換済み
        return ResponseEntity.ok(stockInfoListDTO);
    }

    // カテゴリ別で在庫情報を取得
    @GetMapping("/category")
    public ResponseEntity<List<StockInfoDTO>> getStockInfoByCategory(@RequestParam("categoryId") Integer categoryId) {
        List<StockInfoDTO> stockNameDTO = stockInfoService.getStockInfoByCategory(categoryId);  // サービスでDTO変換済み
        return ResponseEntity.ok(stockNameDTO);
    }

 // 在庫情報検索の処理
    @GetMapping("/search")
    public ResponseEntity<List<StockInfoDTO>> searchStockInfo(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer amountMax,
            @RequestParam(required = false) Integer amountMin) {
        inputValidator.validateAmount(amountMax, amountMin);
        // 管理者情報を取得して
        // 操作履歴を保存
        AdminInfo adminInfo = adminInfoService.getAdminInfoById("dotlife");
        operationLogService.createOperationLog(adminInfo, "stockList.title", "4", "0");

        // 在庫情報を検索し、DTOに変換して返す
        List<StockInfoDTO> stockInfoListDTO = stockInfoService.searchStockInfo(categoryId, name, amountMax, amountMin);  // サービスでDTO変換済み
        return ResponseEntity.ok(stockInfoListDTO);
    }
}
