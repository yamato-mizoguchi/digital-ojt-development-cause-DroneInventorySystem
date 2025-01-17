package com.digitalojt.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalojt.api.entity.AdminInfo;

public interface AdminInfoRepository extends JpaRepository<AdminInfo, String>{
	
    // メソッド命名規則に従って、findByAdminId を使って検索
    Optional<AdminInfo> findByAdminId(String adminId);
}
