package com.digitalojt.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalojt.api.dto.CategoryInfoDTO;
import com.digitalojt.api.service.CategoryInfoService;

import lombok.RequiredArgsConstructor;

/**
 * API分類情報コントローラークラス
 * 
 * @author yamato mizoguchi
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categoryinfo")
@CrossOrigin(origins = "*")  // CORSを有効にする
public class CategoryInfoController {

    private final CategoryInfoService categoryInfoService;

    // すべてのカテゴリ情報をDTO形式で取得するエンドポイント
    @GetMapping
    public ResponseEntity<List<CategoryInfoDTO>> getAll() {
        // サービスを呼び出して、DTO形式でカテゴリ情報を取得
        List<CategoryInfoDTO> categoryInfoDTOList = categoryInfoService.getAllCategoryInfoDTO();//サービスでDTO変換済み
        return ResponseEntity.ok(categoryInfoDTOList);
    }
}
