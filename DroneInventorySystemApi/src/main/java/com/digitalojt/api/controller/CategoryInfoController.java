package com.digitalojt.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalojt.api.entity.CategoryInfo;
import com.digitalojt.api.service.CategoryInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category-info")
@CrossOrigin(origins = "*")
public class CategoryInfoController {

	private final CategoryInfoService categoryInfoService;
	
	@GetMapping
	public List<CategoryInfo> getAll(){
		return categoryInfoService.getCategoryInfoAll();
	}
}
