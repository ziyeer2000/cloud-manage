package org.cloud.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.interceptor.annotation.NeedRoleUrl;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.exception.ExceptionUtil;
import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.model.Button;
import org.cloud.manage.model.Menu;
import org.cloud.manage.model.vo.MenuQuery;
import org.cloud.manage.service.ButtonService;
import org.cloud.manage.service.MenuService;
import org.cloud.manage.utils.AuthUtil;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;


/**
 * 菜单Controller
 * 
 * @since v1.0
 * @version v1.0, 2013-07-29 16:39:23
 * @author Cloud
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	private static Log logger = LogFactory.getLog(MenuController.class);

	@Autowired
	private MenuService service;

	@Autowired
	private ButtonService buttonService;

	/**
	 * 菜单列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findPage", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findPage(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPageNum(Integer.parseInt(data.get("page")));
		pageBounds.setPageSize(Integer.parseInt(data.get("rows")));
		
		String hasChild = data.get("hasChild");
		String superId = data.get("superId");
		MenuQuery query = new MenuQuery();
		query.setHasChild(BaseUtil.isEmpty(hasChild) ? null : ("1".equals(data.get("hasChild")) ? true : false));
		query.setName(data.get("name"));
		query.setUrl(data.get("url"));
		query.setSuperId(BaseUtil.isEmpty(superId) ? null : Long.parseLong(superId));
		
		Page<Menu> page = service.findPage(query, pageBounds);
		map.put("total", page.getTotal());
		map.put("rows", page);
		
		return Constants.returnSuccessReturnMap(map);
	}

	/**
	 * 新增
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		Menu bean = new Menu();
		bean.setComments(data.get("comments"));
		bean.setHasChild("1".equals(data.get("hasChild")) ? true : false);
		bean.setName(data.get("name"));
		bean.setSeq(Integer.parseInt(data.get("seq")));
		bean.setSuperId(Long.parseLong(data.get("superId")));
		bean.setUrl(data.get("url"));
		bean.setAjaxUrls(data.get("ajaxUrls"));
		
		try {
			service.insert(bean);
		} catch (Exception e) {
			logger.error(e);
			return Constants.returnFailReturnMap(ExceptionUtil.getRootException(e).getMessage());
		}
		
		return Constants.standardControllerSuccessReturnMap;
	}

	/**
	 * 根据主键获取数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findById(@RequestParam Map<String, String> data) {
		
		long id = Long.parseLong(data.get("id"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", service.findById(id));
		
		return map;
	}

	/**
	 * 提交菜单修改
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> modify(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		Menu bean = new Menu();
		bean.setComments(data.get("comments"));
		bean.setHasChild("1".equals(data.get("hasChild")) ? true : false);
		bean.setName(data.get("name"));
		bean.setSeq(Integer.parseInt(data.get("seq")));
		bean.setSuperId(Long.parseLong(data.get("superId")));
		bean.setUrl(data.get("url"));
		bean.setAjaxUrls(data.get("ajaxUrls"));
		bean.setId(Long.parseLong(data.get("id")));
		
		try {
			service.update(bean);
			return Constants.standardControllerSuccessReturnMap;
		} catch (Exception e) {
			logger.error(e);
			return Constants.returnFailReturnMap(ExceptionUtil.getRootException(e).getMessage());
		}
		
	}

	/**
	 * 提交菜单删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> remove(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		
		try {
			this.service.delete(Long.parseLong(data.get("id")));
			return Constants.standardControllerSuccessReturnMap;
		} catch (Exception e) {
			logger.error(e);
			return Constants.returnFailReturnMap(ExceptionUtil.getRootException(e).getMessage());
		}
	}

	/**
	 * 获取我的能操作的菜单
	 */
	@RequestMapping(value = "/findMy")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findMy(@RequestParam Map<String, String> data, HttpServletRequest request) {

		String pid = data.get("pid");
		List<Menu> list = service.findByPidAndUserId(StringUtils.isEmpty(pid) ? null : Long.parseLong(pid), AuthUtil.getUserId());
		
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		for (Menu menu : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", menu.getId().toString());
			map.put("text", menu.getName());
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", menu.getUrl());
			map.put("attributes", attributes);
			map.put("state", menu.isHasChild() ? "closed" : "open");
			menuList.add(map);
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("list", menuList);
		
		return ret;
	}

	@RequestMapping(value = "/ztree/find/check", method = RequestMethod.GET)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findByZtreeCheck(@RequestParam Map<String, String> data, HttpServletRequest request) {

		String pid = data.get("pid");
		List<Menu> list = service.findByPid(StringUtils.isEmpty(pid) ? null : Long.parseLong(pid));
		
		List<Map<String, String>> menuList = new ArrayList<Map<String, String>>();
		for (Menu menu : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", menu.getId().toString());
			map.put("name", menu.getName());
			map.put("isParent", "true");
			menuList.add(map);
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("list", menuList);
		
		return ret;
	}
	
	@RequestMapping(value = "/ztree/all/check", method = RequestMethod.GET)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findAllByZtreeCheck(@RequestParam Map<String, String> data, HttpServletRequest request) {

		String pid = data.get("pid");
		String type = data.get("nodeType");
		Long parentMenuId = StringUtils.isEmpty(pid) ? null : Long.parseLong(pid);
		
		List<Menu> list = service.findByPid(parentMenuId);
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		
		if (BaseUtil.isEmpty(list) && "menu".equals(type) && parentMenuId != null) { //可能为最后一级菜单,查询按钮
			List<Button> buttonList = buttonService.findByMenuId(parentMenuId);
			for (Button button : buttonList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", button.getId() + "");
				map.put("name", button.getName());
				map.put("isParent", "false");
				map.put("nodeType", "button");
				menuList.add(map);
			}
		} else {
			for (Menu menu : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", menu.getId() + "");
				map.put("name", menu.getName());
				map.put("isParent", "true");
				map.put("nodeType", "menu");
				menuList.add(map);
			}
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("list", menuList);
		
		return ret;
	}

	@RequestMapping(value = "/ztree/find/radio", method = RequestMethod.GET)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findByZtreeRadio(@RequestParam Map<String, String> data, HttpServletRequest request) {

		String pid = data.get("pid");
		List<Menu> list = service.findByPid(StringUtils.isEmpty(pid) ? null : Long.parseLong(pid));
		
		List<Map<String, String>> menuList = new ArrayList<Map<String, String>>();
		for (Menu menu : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", menu.getId().toString());
			map.put("name", menu.getName());
			map.put("isParent", menu.isHasChild() ? "true" : "false");
			map.put("nocheck", "false");
			menuList.add(map);
		}

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("list", menuList);
		
		return ret;
	}

	@RequestMapping(value = "/findByPrivId", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findByPrivId(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Long privId = Long.parseLong(data.get("privId"));
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("list", service.findByPrivId(privId));
		
		return result;
	}
}
