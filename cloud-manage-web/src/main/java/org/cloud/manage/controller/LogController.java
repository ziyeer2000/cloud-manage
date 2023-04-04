package org.cloud.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.interceptor.annotation.NeedRoleUrl;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.date.DateUtil;
import org.cloud.lang.date.DefaultDateFormat;
import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.model.Log;
import org.cloud.manage.model.vo.LogQuery;
import org.cloud.manage.service.LogService;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;



@Controller
@RequestMapping("/log")
public class LogController {

	@Autowired
	private LogService service;
	
	@RequestMapping(value="/findPage",method=RequestMethod.POST)
    @ResponseBody
    @NeedLogin
	@NeedRoleUrl
	public Map<String,Object> findPage(@RequestParam Map<String, String> data, HttpServletRequest request,HttpServletResponse response){
		
		String type = data.get("type");
		String createDateStart = data.get("createDateStart");
		String createDateEnd = data.get("createDateEnd");
		
		LogQuery query = new LogQuery();
		query.setCreateDateEnd(BaseUtil.isEmpty(createDateEnd) ? null : DateUtil.stringToDate(createDateEnd, DefaultDateFormat.SECOND));
		query.setCreateDateStart(BaseUtil.isEmpty(createDateStart) ? null : DateUtil.stringToDate(createDateStart, DefaultDateFormat.SECOND));
		query.setType(type);
		
		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPageNum(Integer.parseInt(data.get("page")));
		pageBounds.setPageSize(Integer.parseInt(data.get("rows")));
		
		Page<Log> page = service.findPage(query, pageBounds);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", page);
		result.put("total", page.getTotal());
		
		return Constants.returnSuccessReturnMap(result);
		
	}
}
