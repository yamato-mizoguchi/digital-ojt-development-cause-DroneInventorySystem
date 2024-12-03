package com.digitalojt.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.StockInfo;

/**
 * 在庫情報テーブルリポジトリー
 *
 * @author yamato mizoguchi
 * 
 */
@Repository
public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> {
	
	/**
	 * 引数に合致する在庫情報を取得
	 * 
	 * @param 
	 * @return deleteFlagが0（未削除）在庫情報
	 */
	@Query("SELECT s FROM StockInfo s WHERE s.deleteFlag = false")
	   List<StockInfo> findAllDeleteFlagFalse();
	
	/**
	 * 引数に合致する在庫情報を取得
	 * 
	 * @param categoryId
	 * @param name
	 * @param amount
	 * @param amountCondition
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM StockInfo s WHERE" +
			"(:categoryId IS NULL OR s.categoryInfo.categoryId = :categoryId) AND " +
			"(:name IS NULL OR s.name LIKE %:name%) AND " +
			"((:amountCondition = 0 AND (:amount IS NULL OR s.amount >= :amount)) OR (:amountCondition = 1 AND (:amount IS NULL OR s.amount <= :amount))) AND " +
			"(s.deleteFlag = false)")
		List<StockInfo> findByCategoryIdAndNameAndAmount(
			Integer categoryId,
            String name,
            Integer amount,
            Integer amountCondition);
	
	/**
	 * 引数に合致する在庫情報を取得
	 * 
	 * @param categoryId
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM StockInfo s WHERE" +
			"(:categoryId IS NULL OR s.categoryInfo.categoryId = :categoryId) AND " +
			"(s.deleteFlag = false)")
		List<StockInfo> findByCategoryId(Integer categoryId);
}