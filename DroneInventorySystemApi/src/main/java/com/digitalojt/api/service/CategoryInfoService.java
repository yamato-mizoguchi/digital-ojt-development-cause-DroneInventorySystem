package com.digitalojt.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.api.entity.CategoryInfo;
import com.digitalojt.api.repository.CategoryInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryInfoService {

	private final CategoryInfoRepository categoryInfoRepository;
	
	public List<CategoryInfo> getCategoryInfoAll(){
		return categoryInfoRepository.findAll();
	}
}
