package com.digitalojt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalojt.api.entity.OperationLog;

/**
 * 操作履歴リポジトリ
 * 
 * @author yamato mizoguchi
 *
 */
public interface OperationLogRepository extends JpaRepository<OperationLog, Integer> {

}
