package com.sprest.service;

import com.sprest.pojo.UserVo;

/**
 * 用户操作接口
 * @author 苏登辉
 */
public interface IUserVoService {
	
	/**
	 * 根据uid查询一个用户
	 */
	UserVo selectByPrimaryKey(Integer uid);
}
