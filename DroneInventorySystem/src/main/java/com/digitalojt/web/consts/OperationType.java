package com.digitalojt.web.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作種類Enum
 * 
 * @author yamato mizoguchi
 *
 */
public enum OperationType {

	CREATE("1", "登録"), UPDATE("2", "更新"), DELETE("3", "削除"), SEARCH("4", "検索");

	private final String typeCode; // 操作タイプのコード
	private final String typeName; // 操作タイプの表示文言

	private static final Map<String, OperationType> OPERATION_TYPE_MAP = new HashMap<>();

	static {
		for (OperationType type : values()) {
			OPERATION_TYPE_MAP.put(type.getTypeCode(), type);
		}
	}

	OperationType(String typeCode, String typeName) {
		this.typeCode = typeCode;
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	/**
	 * 操作タイプのコードから対応する Enum を取得
	 *
	 * @param val 操作タイプのコード
	 * @return 操作タイプ名 (該当しない場合は "不明な操作" を返す)
	 */
	public static String fromTypeCode(String typeCode) {
		OperationType type = OPERATION_TYPE_MAP.get(typeCode);
		if(type != null) {
			return type.getTypeName();
		}
		return "不明な操作";
	}

	//	// 操作種類の一覧を取得
	//	public static List<String> getTypeNames() {
	//		return Arrays.stream(OperationType.values())
	//				.map(OperationType::getTypeName)
	//				.collect(Collectors.toList());
	//	}
}
