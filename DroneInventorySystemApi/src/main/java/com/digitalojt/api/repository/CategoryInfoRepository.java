package com.digitalojt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalojt.api.entity.CategoryInfo;

/**
 * 分類情報リポジトリ
 * 
 * @author yamato mizoguchi
 *
 */
public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Integer> {

}
