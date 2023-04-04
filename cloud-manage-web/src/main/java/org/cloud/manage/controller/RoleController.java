package org.cloud.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.interceptor.annotation.NeedRoleUrl;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.StringUtil;
import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.model.Role;
import org.cloud.manage.model.vo.RoleQuery;
import org.cloud.manage.service.RoleService;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;


@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService service;

	@RequestMapping(value = "/findPage", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findPage(@RequestParam Map<String, String> data, HttpServletRequest request) {

		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPageNum(Integer.parseInt(data.get("page")));
		pageBounds.setPageSize(Integer.parseInt(data.get("rows")));
		
		RoleQuery query = new RoleQuery();
		query.setName(data.get("name"));
		
		Page<Role> page = service.findPage(query, pageBounds);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotal());
		map.put("rows", page);
		
		return Constants.returnSuccessReturnMap(map);
	}
	
	@RequestMapping(value = "/ztree/all/check", method = RequestMethod.GET)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findAllByZtreeCheck(@RequestParam Map<String, String> data, HttpServletRequest request) {

		
		List<Role> list = service.findAll();
		List<Map<String, Object>> roleList = new ArrayList<Map<String, Object>>();
		
		for (Role role : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", role.getId() + "");
			map.put("name", role.getName());
			map.put("isParent", "false");
			roleList.add(map);
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("list", roleList);
		
		return ret;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Role bean = new Role();
		bean.setComments(data.get("comments"));
		bean.setName(data.get("name"));
		
		service.add(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/changeRolePriv", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> changeRolePriv(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		List<Long> privIds = BaseUtil.isEmpty(data.get("privIds")) ? null : StringUtil.toLongList(data.get("privIds").split(","));
		service.changeRolePriv(Long.parseLong(data.get("roleId")), privIds);
		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> remove(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Long id = Long.parseLong(data.get("id"));

		service.remove(id);
		
		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findById(@RequestParam Map<String, String> data) {

		Map<String, Object> map = new HashMap<String, Object>();

		Long id = Long.parseLong(data.get("id"));
		Role role = service.findById(id);
		map.put("bean", role);

		return map;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> modify(@RequestParam Map<String, String> data) {

		Role bean = new Role();
		bean.setComments(data.get("comments"));
		bean.setName(data.get("name"));
		bean.setId(Long.parseLong(data.get("id")));
		
		service.update(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}
	
	@RequestMapping(value = "/findByUserId", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findByUserId(@RequestParam Map<String, String> data) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", service.findByUserId(Long.parseLong(data.get("userId"))));
		
		return map;
	}
}
