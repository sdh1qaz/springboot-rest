package com.sprest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprest.dao.UserVoMapper;
import com.sprest.pojo.UserVo;
import com.sprest.service.IUserVoService;

/**
 * 用户操作接口实现类
 * @author 苏登辉
 */
@Service
public class UserVoServiceImpl implements IUserVoService{
	
	@Autowired
	private UserVoMapper userDao;
	/**
	 * 根据uid查询一个用户
	 */
	public UserVo selectByPrimaryKey(Integer uid) {
		
		return userDao.selectByPrimaryKey(uid);
	}

}
