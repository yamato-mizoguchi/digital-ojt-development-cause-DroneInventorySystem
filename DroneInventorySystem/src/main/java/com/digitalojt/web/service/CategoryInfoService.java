package com.digitalojt.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.repository.CategoryInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報画面のサービスクラス
 *
 * @author yamato mizoguchi
 * 
 */
@Service
@RequiredArgsConstructor
public class CategoryInfoService {
	
	/** 分類情報テーブル リポジトリー */
	private final CategoryInfoRepository repository;
	
	/**
	 * 分類情報を全件検索で取得
	 * 
	 * @return
	 */
	public List<CategoryInfo> getCategoryInfoData() {

		// 分類情報全件検索
		List<CategoryInfo> categoryInfoList = repository.findAll();
		
		return categoryInfoList;
	}
	
	/**
	 * 引数に合致する分類情報を取得
	 * 
	 * @param categoryName
	 * @return 
	 */
	public List<CategoryInfo> getCategoryInfoData(String categoryName) {

		// 分類情報名検索
		List<CategoryInfo> categoryInfoList = repository.findByCategoryName(categoryName);

		return categoryInfoList;
	}
}
