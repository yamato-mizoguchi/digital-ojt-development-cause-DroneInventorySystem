package com.digitalojt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.UrlConsts;

import lombok.RequiredArgsConstructor;

/**
* 在庫センター情報画面のコントローラークラス
* 
* @author yamato mizoguchi
*
*/
@Controller
@RequiredArgsConstructor
public class CategoryInfoController {
	
	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(UrlConsts.CATEGORY_INFO)
	public String index(Model model) {
		return "admin/categoryInfo/index";
	}
}
