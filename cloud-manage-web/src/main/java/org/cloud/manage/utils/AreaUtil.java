package org.cloud.manage.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloud.lang.MapUtil;
import org.cloud.manage.model.Area;

/**
 * 区域工具类
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2021-06-08 22:00:30
 * @author Cloud
 */
public class AreaUtil {

	/**
     * 主键:中国(大陆)
     */
    public static long ID_CHINA = 101L;

    /**
     * 所有区域
     */
    private static Map<Long, Area> map = new HashMap<Long, Area>();

    private static List<Area> all = new ArrayList<>();
    
    public static List<Area> allProvince;
    
    public static Map<Long, List<Area>> cityMap;

    public static Map<Long, List<Area>> townMap;
    
    /**
     * 初始化
     */
    public static void init(List<Area> list) {

        all = list;
        allProvince = new ArrayList<>();
        cityMap = new HashMap<Long, List<Area>>();
        townMap = new HashMap<Long, List<Area>>();

    	for (Area bean : list) {
    		if (Area.LEVEL_PROVINCE == bean.getLevel() && bean.getParentId() == ID_CHINA && bean.getIsValid() == 1) {//省
                allProvince.add(bean);
    		} else if (Area.LEVEL_CITY == bean.getLevel() && bean.getIsValid() == 1) { //市
    			MapUtil.addToList(cityMap, bean.getParentId(), bean);
    		} else if (Area.LEVEL_TOWN == bean.getLevel() && bean.getIsValid() == 1) { //区县
                MapUtil.addToList(townMap, bean.getParentId(), bean);
            }
    	}
    	
        for (Area bean : list) {
            AreaUtil.map.put(bean.getId(), bean);
        }
    }
    
    /**
     * 根据主键,获取区域数据
     */
    public static Area find(long id) {
        
    	Area area = AreaUtil.map.get(id);
    	if (area != null && area.getIsValid() == 0) {
			area.setName("");
		}
        return area;
    }
    
    /**
     * 根据主键,获取区域名称
     */
    public static String findName(long id) {
        
    	Area area = find(id);
    	if (area != null) {
    		return area.getName();
    	} else {
    		return ""; 
    	}
    	
    }

    /**
     * 获取所有地区
     * @return
     */
    public static Map<Long, Area> findAll(){
    	
    	return  AreaUtil.map;
    }

    /**
     * 获取所有地区
     * @return
     */
    public static List<Area> findAllList(){

        return  AreaUtil.all;
    }
    
}
