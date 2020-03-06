package com.iflytek.renshou.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iflytek.renshou.pojo.BaseResult;
import com.iflytek.renshou.pojo.UserVo;
import com.iflytek.renshou.pojo.ValidatePojo;
import com.iflytek.renshou.service.IAsyncService;
import com.iflytek.renshou.service.IUserVoService;
import com.iflytek.renshou.service.ZkLockService;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserVoController {

	@Autowired
	private IUserVoService userService;
	
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
	 * 数据验证- JSR-303验证
	 * @throws Exception 
	 */
	@ApiOperation(value="数据验证- JSR-303验证",notes="数据验证- JSR-303验证",httpMethod="POST")
	@ApiResponses({@ApiResponse(code=200,message="success",response=Map.class)})
	@ApiImplicitParams({})
	@RequestMapping(path="/validator",method=RequestMethod.POST)
	public Map<String, Object> validate(@Valid @RequestBody ValidatePojo vp,Errors errors ){
		Map<String, Object> errMap = new HashMap<>();
		//获取错误列表
		List<ObjectError> oes = errors.getAllErrors();
		for(ObjectError oe : oes) {
			String key = null;
			String msg = null;
			//字段错误
			if (oe instanceof FieldError) {
				FieldError fe = (FieldError) oe;
				key = fe.getField();
			}else {
				//非字段错误
				key = oe.getObjectName();//获取验证对象名称
			}
			//错误信息
			msg = oe.getDefaultMessage();
			errMap.put(key, msg);
		}
		return errMap;
	}
	
}
