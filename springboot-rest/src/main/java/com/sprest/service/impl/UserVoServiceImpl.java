package com.sprest.service.impl;

import org.apache.ibatis.annotations.Param;
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
	 * 增加一个用户
	 */
	public int insertUserVo(UserVo record) {
		return userDao.insertUserVo(record);
	}
	
	/**
	 * 删除一个用户
	 */
    public int deleteByPrimaryKey(Integer uid) {
    	return userDao.deleteByPrimaryKey(uid);
    }

    /**
	 * 修改一个用户
	 */
    public int updateByUserVo(@Param("record") UserVo record) {
    	return userDao.updateByUserVo(record);
    }
    
	/**
	 * 根据uid查询一个用户
	 */
	public UserVo selectByPrimaryKey(Integer uid) {
		
		return userDao.selectByPrimaryKey(uid);
	}

}
