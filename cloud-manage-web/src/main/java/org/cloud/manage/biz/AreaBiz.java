package org.cloud.manage.biz;

import java.util.List;

import org.cloud.cache.CacheClient;
import org.cloud.manage.dao.AreaDao;
import org.cloud.manage.model.Area;
import org.cloud.manage.model.vo.AreaQuery;
import org.cloud.manage.utils.CacheKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 区域Biz
 * 
 * @since v1.0
 * @version v1.0, 2014-12-26 17:27:53
 * @author Cloud
 */
@Component
public class AreaBiz {

    @Autowired
    private AreaDao dao;

    /**
     * 根据条件查询
     */
    @SuppressWarnings("unchecked")
    public List<Area> findByCondition(AreaQuery query) {

        String key = getKey("findByCondition", query);

        // 从缓存中获取数据
        List<Area> list = CacheClient.get(CacheKey.CACHE_AREA_METHOD + key, List.class);

        // 从数据库获取
        if (list == null) {
            list = dao.findByCondition(query);
            putToCache(key, list);
        }

        return list;
    }

    /**
     * 缓存区域
     * 
     * @param user
     */
    private void putToCache(String key, List<Area> list) {

        CacheClient.put(CacheKey.CACHE_AREA_METHOD + key, list);
    }

    private String getKey(String method, AreaQuery query) {

        return method + 
        	   "&o=" + query.getOrderByField() + 
        	   "&l=" + query.getLevel() + 
        	   "&p=" + query.getParentId()+ 
        	   "&i=" + query.getIsValid() + 
        	   "&n=" + query.getName();
    }

}
