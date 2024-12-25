package com.digitalojt.web.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.OperationLog;

/**
 * 操作履歴テーブルリポジトリー
 *
 * @author yamato mizoguchi
 * 
 */
@Repository
public interface OperationLogRepository extends JpaRepository<OperationLog, Integer> {
	// 1ヶ月以内の操作履歴を取得する
	@Query("SELECT o FROM OperationLog o JOIN FETCH o.adminInfo WHERE (o.createDate >= :oneMonthAgo) AND"
			+ "(o.deleteFlag='0')")
	List<OperationLog> findLogsFromLastMonth(LocalDateTime oneMonthAgo);
}
