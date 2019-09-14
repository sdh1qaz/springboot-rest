package com.sprest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprest.pojo.UserVo;
import com.sprest.service.IUserVoService;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserVoController {

	@Autowired
	private IUserVoService userService;

	/**
	 * 返回一个UserVo
	 */
	@ApiOperation(value="根据uid查用户信息",notes="根据uid查用户信息",httpMethod="GET")
	@ApiResponses({@ApiResponse(code=200,message="success",response=UserVo.class)})
	@ApiImplicitParams({})
	@RequestMapping(path="/user/{uid}",method=RequestMethod.GET)
	public @ResponseBody UserVo getUser(@PathVariable("uid") Integer uid) {

		return userService.selectByPrimaryKey(uid);
	}
	
}
