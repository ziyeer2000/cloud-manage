package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.lang.BaseUtil;
import org.cloud.manage.biz.AreaBiz;
import org.cloud.manage.model.Area;
import org.cloud.manage.model.vo.AreaQuery;
import org.cloud.manage.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 区域Service 实现
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2021-06-08 23:09:49
 * @author Cloud
 */
@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaBiz biz;

    @Override
    public List<Area> findAll() {
        
        return biz.findByCondition(new AreaQuery());
    }

    @Override
    public List<Area> findContinent() {

        AreaQuery query = new AreaQuery();
        query.setLevel(Area.LEVEL_CONTINENT);
        return biz.findByCondition(query);
    }

    @Override
    public List<Area> findCountry(long parentId) {

        AreaQuery query = new AreaQuery();
        query.setLevel(Area.LEVEL_COUNTRY);
        query.setParentId(parentId);
        return biz.findByCondition(query);
    }

    @Override
    public List<Area> findProvince(long parentId) {

        AreaQuery query = new AreaQuery();
        query.setLevel(Area.LEVEL_PROVINCE);
        query.setParentId(parentId);
        query.setIsValid(1);
        return biz.findByCondition(query);
    }
    
    @Override
    public List<Area> findProvinceByName(long parentId, String name) {
    	
    	AreaQuery query = new AreaQuery();
        query.setLevel(Area.LEVEL_PROVINCE);
        query.setParentId(parentId);
        query.setIsValid(1);
        
        if (BaseUtil.isNotEmpty(name)) {
        	query.setName(name);
        }
        
        return biz.findByCondition(query);
    }

    @Override
    public List<Area> findCity(long parentId) {

        AreaQuery query = new AreaQuery();
        query.setLevel(Area.LEVEL_CITY);
        query.setParentId(parentId);
        query.setIsValid(1);
        return biz.findByCondition(query);
    }
    
    @Override
    public List<Area> findCityByName(long parentId, String name) {
    	
    	AreaQuery query = new AreaQuery();
        query.setLevel(Area.LEVEL_CITY);
        query.setParentId(parentId);
        query.setIsValid(1);
        
        if (BaseUtil.isNotEmpty(name)) {
        	query.setName(name);
        }
        
        return biz.findByCondition(query);
    }

    @Override
    public List<Area> findCountryAll() {
        AreaQuery query = new AreaQuery();
        query.setLevel(Area.LEVEL_COUNTRY);
        return biz.findByCondition(query);
    }

    @Override
    public List<Area> findTown(long parentId) {
        AreaQuery query = new AreaQuery();
        query.setLevel(Area.LEVEL_TOWN);
        query.setParentId(parentId);
        query.setIsValid(1);
        return biz.findByCondition(query);
    }
    
    @Override
    public List<Area> findByParentId(long parentId, String name) {
    	
    	AreaQuery query = new AreaQuery();
        query.setParentId(parentId);
        query.setIsValid(1);
        
        if (BaseUtil.isNotEmpty(name)) {
        	query.setName(name);
        }
        
        return biz.findByCondition(query);
    }
}
