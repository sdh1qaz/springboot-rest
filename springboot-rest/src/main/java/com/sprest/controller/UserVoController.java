package com.sprest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprest.pojo.UserVo;
import com.sprest.service.IUserVoService;

@RestController
@RequestMapping("user/")
public class UserVoController {

	@Autowired
	private IUserVoService userService;

	/**
	 * 返回一个UserVo
	 */
	@RequestMapping("{uid}")
	public @ResponseBody UserVo getUser(@PathVariable("uid") Integer uid) {

		return userService.selectByPrimaryKey(uid);
	}
}
