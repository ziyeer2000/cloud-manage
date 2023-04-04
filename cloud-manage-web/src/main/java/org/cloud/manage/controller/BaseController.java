package org.cloud.manage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cloud.lang.exception.BaseRuntimeException;
import org.cloud.manage.service.BaseService;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基础Controller
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2020-01-09 17:24:28
 * @author Cloud
 */
@Controller
@RequestMapping("/base")
public class BaseController {
	
	@Autowired
	private BaseService service;
	
	public static final String TEST_TOKEN = "tewSWx63Dfx7Dq";
	
	/**
	 * 测试. 用于测试项目是否正确启动
	 * 
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> test(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		if (!TEST_TOKEN.equals(data.get("token"))) {
			throw new HttpMessageNotReadableException("权限验证失败");
		}
		
		//测试缓存
		if (!service.cacheTest()) {
			throw new BaseRuntimeException("缓存连接失败");
		}
		
		//测试数据库
		if (!service.dbTest()) {
			throw new BaseRuntimeException("数据库连接失败");
		}
		
		return Constants.standardControllerSuccessReturnMap;
	}
}
