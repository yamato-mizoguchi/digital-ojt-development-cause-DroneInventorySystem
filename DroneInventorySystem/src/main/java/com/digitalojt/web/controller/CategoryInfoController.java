package com.digitalojt.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalojt.web.consts.CategoryInfo;
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
		
		// 分類情報画面に表示するデータを格納するリストの作成
		List<String> categoryInfoList = new ArrayList<String>();
		
		categoryInfoList.add(CategoryInfo.FRAME);
		categoryInfoList.add(CategoryInfo.PROPELLER);
		categoryInfoList.add(CategoryInfo.ELECTRIC_MOTOR);
		categoryInfoList.add(CategoryInfo.ELECTRONIC_SPEED_REGULATOR);
		categoryInfoList.add(CategoryInfo.BATTERY);
		categoryInfoList.add(CategoryInfo.FLIGHT_CONTROLLER);
		categoryInfoList.add(CategoryInfo.REMOTE_CONTROLLER);
		categoryInfoList.add(CategoryInfo.RECEIVER);
		categoryInfoList.add(CategoryInfo.GPS_MODULE);
		categoryInfoList.add(CategoryInfo.CAMERA_SENSOR);
		
		// 画面表示用に分類情報リストをセット
		model.addAttribute("categoryInfoList", categoryInfoList);
		
		return "admin/categoryInfo/index";
	}
}
