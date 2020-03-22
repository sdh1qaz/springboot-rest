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
	 * 页面跳转
	 */
	@RequestMapping("/md5.html")
	public String md5() {
		return "md5";
	}
	
	/**
	 * test
	 * @throws Exception 
	 */
	@RequestMapping("/execSql")
	@ResponseBody
	public Object test(String sql){
		Object res = null;
		DataSource ds = null;
		//如果操作甘肃的表，就是用数据源2
		if (DBUtils.isoperateGStable(sql)) {
			ds = (DataSource)BeanUtils.getBean("defaultDataSource2");
		}else {
			ds = (DataSource)BeanUtils.getBean("defaultDataSource1");
		}
		try {
			//如果是查询语句
			if (DBUtils.isSelectSql(sql)) {
				res = DBUtils.executeQuery(ds, sql);
			}else {
				res = DBUtils.executeUpdate(ds, sql);
			}
		} catch (Exception e) {
			res = "异常：" + e.getMessage();
		}
		return res;
	}
	
}
