package com.sprest.pojo;


import java.util.Date;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 数据验证，JSR-303
 * 
 * @author 苏登辉
 */
public class ValidatePojo {

	// 非空判断
	@NotNull(message = "id不能为空")
	private long id;

	// 只能是将来的日期
	@Future(message = "需要一个将来的日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date date;

	// 不能为空
	@NotNull
	@DecimalMin(value = "0.1") // 最小值0.1
	@DecimalMax(value = "1000.00") // 最大值
	private Double doubleValue;

	@Min(value = 1, message = "最小值为1")
	@Max(value = 88, message = "最大值为88")
	@NotNull
	private Integer integer;

	// 邮箱验证
	@Email(message = "邮箱格式错误")
	private String email;

	@Size(min = 20, max = 30, message = "字符串长度要求20到30之间")
	private String size;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public Integer getInteger() {
		return integer;
	}

	public void setInteger(Integer integer) {
		this.integer = integer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
