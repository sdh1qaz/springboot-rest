package com.iflytek.renshou.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iflytek.renshou.dao.UserVoMapper;
import com.iflytek.renshou.pojo.UserVo;
import com.iflytek.renshou.service.IRedisService;
import com.iflytek.renshou.service.IUserVoService;

/**
 * 用户操作接口实现类
 * @author 苏登辉
 */
@Service
public class UserVoServiceImpl implements IUserVoService{
	
	@Autowired
	private UserVoMapper userDao;
	
	@Autowired
	private IRedisService redisService;

	
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
	
	/**
	 * 根据uid查询一个用户,先查redis，查不到再查数据库
	 */
	public UserVo selectByPrimaryKeyWithRedis(Integer uid) {
		//先查redis
		String key = "USER_UID_" + uid;
		UserVo userVo = redisService.getObject(key, UserVo.class);
		//如果查不到，就查数据库，然后把查到的结果放入redis
		if (userVo==null) {
			userVo = userDao.selectByPrimaryKey(uid);
			redisService.setObject(key, userVo);
		}
		return userVo;
	}

}
