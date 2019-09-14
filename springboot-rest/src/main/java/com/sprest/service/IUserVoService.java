package com.sprest.service;

import org.apache.ibatis.annotations.Param;

import com.sprest.pojo.UserVo;

/**
 * 用户操作接口
 * @author 苏登辉
 */
public interface IUserVoService {
	
	/**
	 * 增加一个用户
	 */
	int insertUserVo(UserVo record);
	
	/**
	 * 删除一个用户
	 */
    int deleteByPrimaryKey(Integer uid);

    /**
	 * 修改一个用户
	 */
    int updateByUserVo(@Param("record") UserVo record);
    
	/**
	 * 根据uid查询一个用户
	 */
	UserVo selectByPrimaryKey(Integer uid);
}
