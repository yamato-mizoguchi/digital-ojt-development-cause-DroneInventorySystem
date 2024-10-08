package com.digitalojt.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.AdminInfo;

/**
 * 管理者情報テーブルリポジトリー
 *
 * @author your name
 * 
 */
@Repository
public interface AdminInfoRepository extends JpaRepository<AdminInfo, String> {

	/**
	 * 引数に合致する管理者情報を取得
	 * 
	 * @param adminId
	 * @param password
	 * @return paramで検索した結果
	 */
	@Query("SELECT s FROM AdminInfo s WHERE " +
			"(s.adminId = :adminId) AND " +
			"(s.password = :password)")
	AdminInfo findByIdAndPass(String adminId, String password);
}
