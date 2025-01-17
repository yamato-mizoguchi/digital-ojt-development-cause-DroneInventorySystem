package com.digitalojt.api.service;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.digitalojt.api.entity.AdminInfo;
import com.digitalojt.api.entity.OperationLog;
import com.digitalojt.api.repository.OperationLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperationLogService {

	private final OperationLogRepository operationLogRepository;
	
	 public void createOperationLog(AdminInfo adminInfo, String tableKey, String operateType, String status) {
	        OperationLog operationLog = new OperationLog();
	        operationLog.setAdminInfo(adminInfo);  // 操作を行った管理者の情報
	        operationLog.setTableKey(tableKey);    // 操作対象のテーブルキー
	        operationLog.setOperateType(operateType);  // 操作の種類（例：検索、更新、削除など）
	        operationLog.setStatus(status);        // 操作のステータス（成功、失敗など）
	        operationLog.setDeleteFlag("0");       // 論理削除フラグ（適宜設定）
	        operationLog.setUpdateDate(new Timestamp(System.currentTimeMillis())); // 更新日時
	        operationLog.setCreateDate(new Timestamp(System.currentTimeMillis())); // 登録日時

	        // 操作履歴をデータベースに保存
	        operationLogRepository.save(operationLog);
	    }
}
