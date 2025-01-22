package com.digitalojt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digitalojt.api.entity.StockInfo;

/**
 * 在庫情報リポジトリ
 * 
 * @author yamato mizoguchi
 *
 */
public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> {

	/**
	 * 引数に合致する在庫情報を取得
	 * 
	 * @param categoryId
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM StockInfo s " +
			"JOIN FETCH s.categoryInfo " + //n+1問題対策
			"JOIN FETCH s.centerInfo " + //n+1問題対策
			"WHERE (:categoryId IS NULL OR s.categoryInfo.categoryId = :categoryId) AND " +
			"(s.deleteFlag = false)")
	List<StockInfo> findByCategoryId(Integer categoryId);

	/**
	 * 在庫情報を複数の検索条件に基づいてフィルタリングして取得するクエリ。
	 * 引数の条件がNULLの場合、対応する条件は無視されます。
	 * 
	 * @param categoryId カテゴリID
	 * @param name 在庫の名称
	 * @param amountMin 在庫の最小個数
	 * @param amountMax 在庫の最大個数
	 * @return 検索条件に一致する在庫情報のリスト
	 */
	@Query("SELECT s FROM StockInfo s " +
			"JOIN FETCH s.categoryInfo " + //n+1問題対策
			"WHERE (:categoryId IS NULL OR s.categoryInfo.categoryId = :categoryId) AND "
			+ "(:name IS NULL OR s.name LIKE :name%) AND "
			+ "(:amountMin IS NULL OR s.amount >= :amountMin) AND "
			+ "(:amountMax IS NULL OR s.amount <= :amountMax) AND "
			+ "(s.deleteFlag = false)")
	List<StockInfo> findByStockInfo(
			@Param("categoryId") Integer categoryId,
			@Param("name") String name,
			@Param("amountMin") Integer amountMin,
			@Param("amountMax") Integer amountMax);
}
