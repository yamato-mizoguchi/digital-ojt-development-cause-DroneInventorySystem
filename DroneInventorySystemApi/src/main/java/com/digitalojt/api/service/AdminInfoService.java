package com.digitalojt.api.service;

import org.springframework.stereotype.Service;

import com.digitalojt.api.entity.AdminInfo;
import com.digitalojt.api.repository.AdminInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminInfoService {

	private final AdminInfoRepository adminInfoRepository;
	
	public AdminInfo getAdminInfoById(String adminId) {
		return adminInfoRepository.findByAdminId(adminId).orElseThrow(() -> new RuntimeException("管理者ID " + adminId + " が見つかりません"));
	}
}
