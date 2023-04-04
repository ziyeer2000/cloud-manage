package org.cloud.manage.controller;

import java.util.Map;

import org.cloud.interceptor.annotation.NeedLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(@RequestParam Map<String, String> data){
		
		ModelAndView ret = new ModelAndView();
		ret.setViewName("login.jsp");
		ret.addAllObjects(data);
		
		return ret;
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	@NeedLogin
	public ModelAndView index(){
		
		ModelAndView ret = new ModelAndView();
		ret.setViewName("index.html");
		
		return ret;
	}
}
