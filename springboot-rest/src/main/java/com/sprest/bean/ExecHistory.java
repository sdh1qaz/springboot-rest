package com.sprest.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sprest.util.DateUtils;

@Service
public class ExecHistory {
	
	public HashMap<String,List<OperHistory>> sqlhistory= new HashMap<>();
	
	
	public HashMap<String, List<OperHistory>> getSqlhistory() {
		return sqlhistory;
	}
	public void setSqlhistory(HashMap<String, List<OperHistory>> sqlhistory) {
		this.sqlhistory = sqlhistory;
	}
	public class OperHistory implements Comparable<OperHistory>{
		private String operId;
		private String sql;//执行的sql
		private String opDate;//执行sql的日期
		private String result;//执行sql的结果
		public String getSql() {
			return sql;
		}
		public void setSql(String sql) {
			this.sql = sql;
		}
		public String getOpDate() {
			return opDate;
		}
		public void setOpDate(String opDate) {
			this.opDate = opDate;
		}
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		public String getOperId() {
			return operId;
		}
		public void setOperId(String operId) {
			this.operId = operId;
		}
		@Override
		public int compareTo(OperHistory o) {
			return this.getOpDate().compareTo(o.getOpDate()) * -1;
		}
		
	}
	/**
	 * 增加一条记录
	 */
	public void addRecord(String operId,String sql,String opDate,String result) {
		//获取改operId的历史记录
		List<OperHistory> opHisList = sqlhistory.get(operId);
		//如果没有改id的记录
		if (opHisList == null) {
			opHisList = new ArrayList<>();
		}
		//每个id最多存放1000个记录
		if (opHisList.size() > 1000) {
			opHisList.remove(0);
		}
		OperHistory operHistory = new OperHistory();
		operHistory.setOpDate(opDate);
		operHistory.setResult(result);
		operHistory.setSql(sql);
		operHistory.setOperId(operId);
		opHisList.add(operHistory);
		sqlhistory.put(operId, opHisList);
	}
}
