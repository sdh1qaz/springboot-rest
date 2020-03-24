package com.sprest.controller;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sprest.bean.ExecHistory;
import com.sprest.bean.ExecHistory.OperHistory;
import com.sprest.util.BeanUtils;
import com.sprest.util.DBUtils;
import com.sprest.util.DateUtils;

@Controller
@RequestMapping("dbmanager")
public class DbManageController {
	
	@Autowired
	ExecHistory execHistory;

	/**
	 * 页面跳转
	 */
	@RequestMapping("/execSql.html")
	public String execSql() {
		return "execSql";
	}
	
	/**
	 * 执行sql
	 * @throws Exception 
	 */
	@RequestMapping("/execSql")
	@ResponseBody
	public Object getExecResult(String sql,String operId){
		String operDate = DateUtils.getOperDate();
		Object res = null;
		DataSource ds = null;
		//如果操作甘肃的表，就是用数据源2
		if (DBUtils.isoperateGStable(sql)) {
			ds = (DataSource)BeanUtils.getBean("defaultDataSource2");
		}else {//不是甘肃的表，用数据源1
			ds = (DataSource)BeanUtils.getBean("defaultDataSource1");
		}
		try {
			//如果是查询语句
			if (DBUtils.isSelectSql(sql)) {
				res = DBUtils.executeQuery(ds, sql).replace("<", "&lt;").replace(">", "&gt;");
				execHistory.addRecord(operId, sql, operDate, "查询成功");
			}else {
				res = DBUtils.executeUpdate(ds, sql);
				execHistory.addRecord(operId, sql, operDate, "执行成功，影响的行数："  + res );
			}
		} catch (Exception e) {
			res = "异常：" + e.getMessage();
			execHistory.addRecord(operId, sql, operDate, "执行失败，异常信息：" + res);
		}
		return res;
	}
	
	
	/**
	 * 查询操作Id的历史记录
	 * @throws Exception 
	 */
	@RequestMapping("/getExecHistory")
	@ResponseBody
	public Object getHisById(String operId){
		List<OperHistory> list = execHistory.getSqlhistory().get(operId);
		if (list==null || list.size()==0) {
			return "无记录";
		}
		Collections.sort(list);
		return list;
	}
	
	
	
}
