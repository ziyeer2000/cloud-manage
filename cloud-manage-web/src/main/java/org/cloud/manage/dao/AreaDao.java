package org.cloud.manage.dao;

import java.util.List;

import org.cloud.manage.model.Area;
import org.cloud.manage.model.vo.AreaQuery;

import tk.mybatis.mapper.common.Mapper;


/**
 * 区域Dao 接口
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2021-06-08 17:19:59
 * @author Cloud
 */
public interface AreaDao extends Mapper<Area>{

    /**
     * 根据条件查询
     */
    public List<Area> findByCondition(AreaQuery query);
    
}
