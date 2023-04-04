package org.cloud.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.interceptor.annotation.NeedRoleUrl;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.interceptor.OnlineDataVo;
import org.cloud.manage.service.OnlineService;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;


@Controller
@RequestMapping("/online")
public class OnlineController {

	@Autowired
	private OnlineService service;
	
	@RequestMapping(value="/findPage",method=RequestMethod.POST)
    @ResponseBody
    @NeedLogin
	@NeedRoleUrl
	public Map<String,Object> findPage(@RequestParam Map<String, String> data, HttpServletRequest request,HttpServletResponse response) {
    	
		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPageNum(Integer.parseInt(data.get("page")));
		pageBounds.setPageSize(Integer.parseInt(data.get("rows")));
		
		String userIdStr = data.get("userId");
		long userId;
		if (BaseUtil.isNotEmpty(userIdStr)) {
			userId = Long.parseLong(userIdStr);
		} else {
			userId = -1;
		}
		
		Page<OnlineDataVo> page = service.findPage(userId, pageBounds);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotal());
		map.put("rows", page);
		
		return Constants.returnSuccessReturnMap(map);
	}
	
	@RequestMapping(value="/offline",method=RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String,Object> offline(@RequestParam Map<String, String> data, HttpServletRequest request,HttpServletResponse response) {
    	
		service.offline(Long.parseLong(data.get("userId")), data.get("sessionId"));
		
		return Constants.standardControllerSuccessReturnMap;
	}
}
