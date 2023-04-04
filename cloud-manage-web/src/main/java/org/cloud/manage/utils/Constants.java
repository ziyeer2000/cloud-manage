package org.cloud.manage.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * 基本常量类
 * @author 
 *
 */
public class Constants {
	
    /**
     * 项目名
     */
	public static String SYSTEM_PATH = null;
	
	/**
	 * 项目文件路径
	 */
	public static String ROOT_PATH = null;
	
	/**
	 * 系统返回至前台成功失败标志
	 */
    public static final boolean RESULT_FLAG_SUCCESS = true;
    public static final boolean RESULT_FLAG_FAILURE = false;
    
    /**
     * 默认初始密码
     */
    public static final String PASSWORD = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
    
    /**
     * 上传文件存放路径
     */
    public static final String UPLOAD_FILE_PATH = "/upload";
    
    /**
     * 登录拦截器跳转地址名称
     */
    public static final String NEED_LOGIN_REDIRECT_URL_NAME = "NEED_LOGIN_REDIRECT_URL";
    
    /**
     * 登录拦截器跳转页面名称
     */
    public static final String NEED_LOGIN_REDIRECT_PAGE_NAME = "NEED_LOGIN_REDIRECT_PAGE";
    
    /**
     * 操作成功
     */
    public static final String OPERATOR_SUCCESS = "success";

    /**
     * 操作消息
     */
    public static final String OPERATOR_MESSAGE = "message";
    
    /**
     * 标准Controller返回成功Map数据
     */
    public static final Map<String, Object> standardControllerSuccessReturnMap;
    
    /**
     * 返回失败
     */
    public static Map<String, Object> returnFailReturnMap(String message) {
    		
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put(Constants.OPERATOR_SUCCESS, RESULT_FLAG_FAILURE);
	    	map.put(OPERATOR_MESSAGE, message != null ? message : "处理失败");
	    	return map;
    }
    
    /**
     * 返回成功
     */
    public static Map<String, Object> returnSuccessReturnMap(Map<String, Object> map) {
    		
	    	map.put(Constants.OPERATOR_SUCCESS, RESULT_FLAG_SUCCESS);
	    	return map;
    }
    
    static {
        standardControllerSuccessReturnMap = new HashMap<String, Object>();
        standardControllerSuccessReturnMap.put(Constants.OPERATOR_SUCCESS, RESULT_FLAG_SUCCESS);
    }
    
}
