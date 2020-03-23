package com.sprest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日:HH时:mm分:ss秒");
	
	/**
	 * 获取操作日期，格式yyyy年MM月dd日:HH时:mm分:ss秒
	 */
	public static String getOperDate() {
		return sf.format(new Date());
	}
	
	public static boolean compare2DateStr(String d1,String d2) {
		return d1.compareTo(d2) > 0;
	}
	
	public static void main(String[] args) {
		System.out.println(getOperDate());
	}
}
