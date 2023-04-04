package org.cloud.manage.utils;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Json工具类
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2015-01-01 15:00:16
 * @author 
 *		Cloud
 */
public class JsonUtil {

	private static final ObjectMapper objectMapper;
	
	static {
		objectMapper = new ObjectMapper();
	}
	
	/**
	 * 将Json字符串转换为List
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> jsonToList(String json) {
		
		try {
			return JsonUtil.objectMapper.readValue(json, List.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将Json字符串转换为数组
	 * @param json
	 * @return
	 */
	public static <T> T[] jsonToArray(String json, Class<T[]> clazz) {
		
		try {
			return JsonUtil.objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将Map对象转换为Json字符串
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, Object> map) {
	    
	    try {
            return JsonUtil.objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}
