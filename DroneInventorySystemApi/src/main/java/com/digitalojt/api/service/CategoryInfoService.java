package com.digitalojt.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digitalojt.api.dto.CategoryInfoDTO;
import com.digitalojt.api.entity.CategoryInfo;
import com.digitalojt.api.repository.CategoryInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報サービスクラス
 * 
 * @author yamato mizoguchi
 *
 */
@Service
@RequiredArgsConstructor
public class CategoryInfoService {

    private final CategoryInfoRepository categoryInfoRepository;
    
    // すべてのカテゴリ情報をDTOに変換して返すメソッド
    public List<CategoryInfoDTO> getAllCategoryInfoDTO() {
        List<CategoryInfo> categoryInfoList = categoryInfoRepository.findAll();  // リポジトリからすべてのカテゴリ情報を取得
        
        // CategoryInfoをDTOに変換してリストで返す
        return convertToCategoryInfoDTO(categoryInfoList);
    }

    // CategoryInfoのリストをCategoryInfoDTOに変換する共通メソッド
    private List<CategoryInfoDTO> convertToCategoryInfoDTO(List<CategoryInfo> categoryInfoList) {
        return categoryInfoList.stream()
            .map(category -> new CategoryInfoDTO(
                category.getCategoryId(),
                category.getCategoryName()
            ))
            .collect(Collectors.toList());
    }
}
