package org.cloud.manage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.interceptor.annotation.NeedRoleUrl;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面路由Controller
 * @author Cloud
 *
 */
@Controller
@RequestMapping("/page")
public class PageRouteController {

	@RequestMapping(value = "/{folder}/{file}", method = RequestMethod.GET)
    @NeedLogin
    @NeedRoleUrl
    public ModelAndView toPdaPage(@RequestParam Map<String, String> data, @PathVariable("folder") String folder, @PathVariable("file") String file) {
        ModelAndView ret = new ModelAndView();
        
        String url = "/pages/" + folder + "/" + file + ".html";
        if (BaseUtil.isNotEmpty(data)) {
        	List<String> params = new ArrayList<>();
        	for (String name : data.keySet()) {
        		params.add(name + "=" + data.get(name));
        	}
        	url += "?" + StringUtil.join(params, "&");
        }
        
        ret.setViewName(url);
        ret.addAllObjects(data);
        
        return ret;
    }
}
