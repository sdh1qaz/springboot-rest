package com.sprest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprest.pojo.BaseResult;
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
	 * 新增一个UserVo
	 */
	@ApiOperation(value="新增一个用户",notes="新增一个用户",httpMethod="POST")
	@ApiResponses({@ApiResponse(code=200,message="success",response=BaseResult.class)})
	@ApiImplicitParams({})
	@RequestMapping(path="/user/add",method=RequestMethod.POST)
	public @ResponseBody BaseResult addUser(@RequestBody(required=true) UserVo record) {
		BaseResult br = new BaseResult();
		br.setResult(userService.insertUserVo(record));
		return br;
	}
	
	/**
	 * 删除一个UserVo
	 */
	@ApiOperation(value="删除一个用户",notes="删除一个用户",httpMethod="GET")
	@ApiResponses({@ApiResponse(code=200,message="success",response=BaseResult.class)})
	@ApiImplicitParams({})
	@RequestMapping(path="/user/del/{uid}",method=RequestMethod.GET)
	public @ResponseBody BaseResult delUser(@PathVariable("uid") Integer uid) {
		BaseResult br = new BaseResult();
		br.setResult(userService.deleteByPrimaryKey(uid));
		return br;
	}
	
	/**
	 * 修改一个UserVo
	 */
	@ApiOperation(value="修改一个用户",notes="修改一个用户",httpMethod="POST")
	@ApiResponses({@ApiResponse(code=200,message="success",response=BaseResult.class)})
	@ApiImplicitParams({})
	@RequestMapping(path="/user/update",method=RequestMethod.POST)
	public @ResponseBody BaseResult updateUser(@RequestBody(required=true) UserVo record) {
		BaseResult br = new BaseResult();
		br.setResult(userService.updateByUserVo(record));
		return br;
	}

	/**
	 * 返回一个UserVo
	 */
	@ApiOperation(value="查询一个用户",notes="查询一个用户",httpMethod="GET")
	@ApiResponses({@ApiResponse(code=200,message="success",response=UserVo.class)})
	@ApiImplicitParams({})
	@RequestMapping(path="/user/get/{uid}",method=RequestMethod.GET)
	public @ResponseBody UserVo getUser(@PathVariable("uid") Integer uid) {

		return userService.selectByPrimaryKey(uid);
	}
	
	
	
	
}
