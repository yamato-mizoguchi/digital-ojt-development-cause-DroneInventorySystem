package com.digitalojt.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.CategoryInfo;

/**
 * 分類情報テーブルリポジトリー
 *
 * @author yamato mizoguchi
 * 
 */
@Repository
public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Integer>{

}
