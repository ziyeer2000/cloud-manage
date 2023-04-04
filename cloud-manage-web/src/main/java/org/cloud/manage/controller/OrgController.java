package org.cloud.manage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.interceptor.annotation.NeedRoleUrl;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.model.Org;
import org.cloud.manage.service.OrgService;
import org.cloud.manage.utils.Constants;

/**
 * 组织架构Controller
 */
@Controller
@RequestMapping("/org")
public class OrgController {

	@Autowired
	private OrgService orgService;

	/**
	 * 查询组织架构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getOrgTreeList")
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> getOrgTreeList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		String pid = data.get("pid");

		List<Org> orgList = orgService.findByParentId(BaseUtil.isEmpty(pid) ? null : Long.parseLong(pid), null);

		for (Org orgInfo : orgList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", orgInfo.getId() + "");
			map.put("name", orgInfo.getName());
			map.put("isParent", orgInfo.isHasChild() ? "true" : "false");
			map.put("nocheck", "false");
			if(orgInfo.isHasChild()){
				map.put("drag", "false");
			}

			list.add(map);
		}
		
		result.put("list", list);

		return result;
	}

	/**
	 * 查询组织架构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getOrgList")
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> getOrgList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();

		String pid = data.get("pid");
		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPageNum(Integer.parseInt(data.get("page")));
		pageBounds.setPageSize(Integer.parseInt(data.get("rows")));
		
		Page<Org> page = orgService.findByParentIdPage(BaseUtil.isEmpty(pid) ? null : Long.parseLong(pid), pageBounds);

		result.put("total", page.getTotal());
		result.put("rows", page);
		return result;
	}

	/**
	 * 删除组织架构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/remove")
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> remove(@RequestParam Map<String, String> data) {

		orgService.delete(Long.parseLong(data.get("id")));
		
		return Constants.standardControllerSuccessReturnMap;
	}

	/**
	 * 获取组织架构
	 */
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findById(@RequestParam Map<String, String> data) {

		Map<String, Object> result = new HashMap<String, Object>();

		Org org = this.orgService.findById(Long.parseLong(data.get("id")));

		result.put("bean", org);

		return result;
	}

	/**
	 * 修改组织架构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> modify(@RequestParam Map<String, String> data) {

		Org bean = new Org();
		bean.setComments(data.get("comments"));
		bean.setCreateDate(new Date());
		bean.setHasChild(Integer.parseInt(data.get("hasChild")) == 1);
		bean.setName(data.get("name"));
		bean.setParentId(data.get("parentId"));
		bean.setId(Long.parseLong(data.get("id")));
		
		orgService.update(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}

	/**
	 * 增加组织架构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Org bean = new Org();
		bean.setComments(data.get("comments"));
		bean.setCreateDate(new Date());
		bean.setHasChild(Integer.parseInt(data.get("hasChild")) == 1);
		bean.setName(data.get("name"));
		bean.setParentId(data.get("parentId"));
		
		orgService.add(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}
}
