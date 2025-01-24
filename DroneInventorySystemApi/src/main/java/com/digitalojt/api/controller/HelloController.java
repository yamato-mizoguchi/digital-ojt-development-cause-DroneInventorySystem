package com.digitalojt.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API"Hello World"コントローラークラス
 * 
 * @author yamato mizoguchi
 *
 */
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}
}
