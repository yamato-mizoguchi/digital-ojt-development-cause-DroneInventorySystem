package com.digitalojt.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.CenterInfo;

/**
 * センター情報テーブルリポジトリー
 *
 * @author yamato mizoguchi
 * 
 */
@Repository
public interface CenterInfoRepository extends JpaRepository<CenterInfo, Integer> {

	@Query("SELECT s FROM CenterInfo s WHERE" + 
			"(s.deleteFlag = '0') AND " + 
			"(operationalStatus = 0)")
	   List<CenterInfo> findAllOperationalStatus0DeleteFlag0();
	
	   Optional<CenterInfo> findById(Integer centerId);
	
	/**
	 * 引数に合致する在庫センター情報を取得
	 * 
	 * @param centerName
	 * @param region
	 * @param storageCapacityFrom
	 * @param storageCapacityTo
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM CenterInfo s WHERE " +
			"(:centerName = '' OR s.centerName LIKE %:centerName%) AND " +
			"(:region = '' OR s.address LIKE %:region%) AND " +
			"(:storageCapacityFrom IS NULL OR s.currentStorageCapacity >= :storageCapacityFrom) AND " +
			"(:storageCapacityTo IS NULL OR s.currentStorageCapacity <= :storageCapacityTo) AND " +
			"(s.operationalStatus = 0) AND" +
			"(s.deleteFlag = '0')")
	List<CenterInfo> findByCenterNameAndRegionAndStorageCapacity(
			String centerName,
			String region,
			Integer storageCapacityFrom,
			Integer storageCapacityTo);
}