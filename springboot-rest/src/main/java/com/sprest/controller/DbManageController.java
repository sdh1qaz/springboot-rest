package com.sprest.controller;


import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sprest.util.BeanUtils;
import com.sprest.util.DBUtils;

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
	 * test
	 * @throws Exception 
	 */
	@RequestMapping("/execSql")
	@ResponseBody
	public int test(String sql) throws Exception {
		DataSource ds = (DataSource)BeanUtils.getBean("defaultDataSource2");
		int executeUpdate = DBUtils.executeUpdate(ds, sql);
		return executeUpdate;
	}
	
	
	
}
