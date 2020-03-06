package com.iflytek.renshou.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.iflytek.renshou.pojo.UserVo;

/**
 * UserVo操作接口
 * @author 苏登辉
 */
@Component
public interface UserVoMapper {
	
	/**
	 * 增
	 */
	int insertUserVo(UserVo record);
	
	/**
	 * 删
	 */
    int deleteByPrimaryKey(Integer uid);

    /**
	 * 改
	 */
    int updateByUserVo(@Param("record") UserVo record);
    
    /**
	 * 查
	 */
    UserVo selectByPrimaryKey(Integer uid);
}