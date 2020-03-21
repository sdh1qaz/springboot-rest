package com.sprest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dbmanager")
public class DbManageController {

	/**
	 * 页面跳转
	 */
	@RequestMapping("/execSql.html")
	public String execSql() {
		return "execSql";
	}
	
	/**
	 * 查询表头
	 */
	
	
}
