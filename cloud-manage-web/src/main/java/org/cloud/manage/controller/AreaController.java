package org.cloud.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.lang.StringUtil;
import org.cloud.lang.exception.ExceptionUtil;
import org.cloud.manage.service.AreaService;
import org.cloud.manage.utils.AreaUtil;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 区域Controller
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2021-06-09 15:10:27
 * @author Cloud
 */
@Controller
@RequestMapping("/area")
public class AreaController {
	
	@Autowired
	private AreaService service;

	@RequestMapping(value = "/selectByParentId")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> selectByParentId(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			map.put("data", service.findByParentId(StringUtil.toLong(data.get("parentId"), AreaUtil.ID_CHINA),
												   data.get("name")));
			map.put("code", 0);
			
			return Constants.returnSuccessReturnMap(map);
		} catch (Exception e) {
			return Constants.returnFailReturnMap(ExceptionUtil.getRootException(e).getMessage());
		}
	}
	
}
