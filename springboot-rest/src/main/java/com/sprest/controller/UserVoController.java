package com.sprest.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprest.pojo.BaseResult;
import com.sprest.pojo.UserVo;
import com.sprest.service.IAsyncService;
import com.sprest.service.IUserVoService;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserVoController {

	@Autowired
	private IUserVoService userService;
	
	@Autowired
	private IAsyncService asyncService;
	
	//日志记录器
	private static final Logger logger = Logger.getLogger(UserVoController.class);
	
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
		logger.info("新增一个用户，新增结果：" + br.getResult());
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
		logger.info("查询uid=" + uid + "的用户信息...");
		return userService.selectByPrimaryKey(uid);
	}
	
	/**
	 * 返回一个UserVo,使用redis
	 */
	@ApiOperation(value="查询一个用户，使用redis",notes="查询一个用户，使用redis",httpMethod="GET")
	@ApiResponses({@ApiResponse(code=200,message="success",response=UserVo.class)})
	@ApiImplicitParams({})
	@RequestMapping(path="/user/get/redis/{uid}",method=RequestMethod.GET)
	public @ResponseBody UserVo getUserWithRedis(@PathVariable("uid") Integer uid) {
		
		return userService.selectByPrimaryKeyWithRedis(uid);
	}
	
	/**
	 * 使用redis共享session测试
	 */
	@ApiOperation(value="使用redis共享session测试",notes="使用redis共享session测试",httpMethod="GET")
	@ApiResponses({@ApiResponse(code=200,message="success",response=Map.class)})
	@ApiImplicitParams({})
	@RequestMapping(path="/getsession",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getsession(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();  
        map.put("sessionId", request.getSession().getId());  
		return map;
	}
	
	/**
	 * 异步方法-打开一个记事本
	 * @throws Exception 
	 */
	@ApiOperation(value="异步方法-打开一个记事本",notes="异步方法-打开一个记事本",httpMethod="GET")
	@ApiResponses({@ApiResponse(code=200,message="success",response=String.class)})
	@ApiImplicitParams({})
	@RequestMapping(path="/async/openCmd",method=RequestMethod.GET)
	public @ResponseBody String openCmd(HttpServletRequest request) throws Exception {
		asyncService.openCmd();
		return "5秒后打开记事本";
	}
	
}
