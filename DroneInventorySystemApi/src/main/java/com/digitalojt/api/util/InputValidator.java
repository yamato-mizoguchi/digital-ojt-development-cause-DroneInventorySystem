package com.digitalojt.api.util;

import org.springframework.stereotype.Component;

import com.digitalojt.api.entity.AdminInfo;
import com.digitalojt.api.exception.InvalidInputException;
import com.digitalojt.api.service.AdminInfoService;
import com.digitalojt.api.service.OperationLogService;

/**
 * バリデーションクラス
 * 
 * @author yamato mizoguchi
 *
 */
@Component
public class InputValidator {

    private final OperationLogService operationLogService;
    private final AdminInfoService adminInfoService;

    // コンストラクタインジェクションで依存性を注入
    public InputValidator(OperationLogService operationLogService, AdminInfoService adminInfoService) {
        this.operationLogService = operationLogService;
        this.adminInfoService = adminInfoService;
    }

    // インスタンスメソッドとしてValidateAmountを定義
    public void validateAmount(Integer amountMin, Integer amountMax) {
        if ((amountMin != null && amountMin < 0) || (amountMax != null && amountMax < 0)) {
            // 管理者情報
            AdminInfo adminInfo = adminInfoService.getAdminInfoById("dotlife");  // 実際にはログインした管理者情報を取得

            // 操作履歴を保存
            operationLogService.createOperationLog(adminInfo, "stockList.title", "4", "1");

            // バリデーションエラーをスロー
            throw new InvalidInputException("※個数は0以上を入力してください。");
        }
    }
}
