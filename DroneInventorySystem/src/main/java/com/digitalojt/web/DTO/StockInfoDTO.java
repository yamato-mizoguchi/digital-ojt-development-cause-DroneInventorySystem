package com.digitalojt.web.DTO;

import java.util.List;

import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.entity.StockInfo;

import lombok.Data;

@Data
public class StockInfoDTO {
	
	// プルダウンに表示する分類情報
	private List<CategoryInfo> categoryInfoList;
	
	// 在庫一覧に表示する在庫情報
	private List<StockInfo> stockInfoList;
	
	// プルダウンに表示する在庫情報
	private List<StockInfo> stockInfoListPullDown;
	
	// フォームに入力された値
	private StockInfo form;
}
