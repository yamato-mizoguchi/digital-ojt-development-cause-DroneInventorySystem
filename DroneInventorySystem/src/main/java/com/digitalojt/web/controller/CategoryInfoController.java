package com.digitalojt.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.entity.CategoryInfo;
import com.digitalojt.web.service.CategoryInfoService;

import lombok.RequiredArgsConstructor;

/**
* 分類情報画面のコントローラークラス
* 
* @author yamato mizoguchi
*
*/
@Controller
@RequiredArgsConstructor
public class CategoryInfoController {
	
	/** 分類情報 サービス */
	private final CategoryInfoService categoryInfoService;
	
	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CATEGORY_INFO)
	public String index(Model model) {
		
		// 分類情報画面に表示するデータを格納するリストの作成
		List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoData();
		
		// 画面表示用に分類情報リストをセット
		model.addAttribute("categoryInfoList", categoryInfoList);
		
		return "admin/categoryInfo/index";
	}
}
