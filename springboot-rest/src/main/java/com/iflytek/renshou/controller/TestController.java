package com.iflytek.renshou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 重定向到页面
 */
@Controller
public class TestController {
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
}
