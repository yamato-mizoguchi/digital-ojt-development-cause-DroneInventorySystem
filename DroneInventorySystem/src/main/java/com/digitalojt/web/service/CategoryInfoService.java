package com.digitalojt.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

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

		// 分類情報作成
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

		// 分類情報作成
		List<CategoryInfo> categoryInfoList = repository.findAll();
		
		// 検索処理
		categoryInfoList = searchCategoryInfoData(categoryInfoList, categoryName);

		return categoryInfoList;
	}
	
	/**
	 * 検索処理
	 * 
	 * @param categoryInfoList
	 * @param categoryName
	 * @return
	 */
	private List<CategoryInfo> searchCategoryInfoData(List<CategoryInfo> categoryInfoList, String categoryName) {

		List<CategoryInfo> hitCategoryInfoList = new ArrayList<>();
		
		// 引数の文字列と合致する要素のみリストに追加
		categoryInfoList.forEach(item -> {
			if (categoryName.equals(item.getCategoryName())|| StringUtils.isEmpty(categoryName)) {
				hitCategoryInfoList.add(item);
			}
		});

		return hitCategoryInfoList;
	}
}
