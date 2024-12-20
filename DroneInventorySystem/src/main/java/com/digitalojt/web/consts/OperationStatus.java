package com.digitalojt.web.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作ステータスEnum
 * 
 * @author yamato mizoguchi
 *
 */
public enum OperationStatus {

	SUCCESS("1", "成功"), 
	FAILURE("0", "失敗");

	private final String statusCode; // ステータスコード
	private final String statusName; // ステータスの表示文言
	
	private static final Map<String, OperationStatus> OPERATION_STATUS_MAP = new HashMap<>();

	static {
		for (OperationStatus status : values()) {
			OPERATION_STATUS_MAP.put(status.getStatusCode(), status);
		}
	}
	
	OperationStatus(String statusCode, String statusName) {
		this.statusCode = statusCode;
		this.statusName = statusName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	/**
	 * ステータスコードから対応するステータス名を取得
	 *
	 * @param statusCode 操作ステータスのコード
	 * @return 操作ステータス名 (該当しない場合は "不明なステータス" を返す)
	 */
	public static String fromStatusCode(String statusCode) {
		OperationStatus status = OPERATION_STATUS_MAP.get(statusCode);
		if(status != null) {
			return status.getStatusName();
		}
		return "不明なステータス";
	}

//	// 操作ステータスの一覧を取得
//	public static List<String> getStatusNames() {
//		return Arrays.stream(OperationStatus.values())
//				.map(OperationStatus::getStatusName)
//				.collect(Collectors.toList());
//	}
}
