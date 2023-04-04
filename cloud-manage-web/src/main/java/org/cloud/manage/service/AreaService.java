package org.cloud.manage.service;

import java.util.List;

import org.cloud.manage.model.Area;

/**
 * 区域Service 接口
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2021-06-08 21:58:52
 * @author Cloud
 */
public interface AreaService {

    /**
     * 主键:中国(大陆)
     */
    public static long ID_CHINA = 101L;
    
    /**
     * 获取所有
     * @return
     */
    public List<Area> findAll();
    
    /**
     * 获取洲
     */
    public List<Area> findContinent();

    /**
     * 获取国家
     */
    public List<Area> findCountryAll();

    /**
     * 获取国家
     */
    public List<Area> findCountry(long parentId);

    /**
     * 获取省
     */
    public List<Area> findProvince(long parentId);
    
    /**
     * 获取省
     */
    public List<Area> findProvinceByName(long parentId, String name);

    /**
     * 获取城市
     */
    public List<Area> findCity(long parentId);
    
    /**
     * 获取城市
     */
    public List<Area> findCityByName(long parentId, String name);

    /**
     * 获取乡镇
     */
    public List<Area> findTown(long parentId);
    
    /**
     * 根据父节点,获取数据
     */
    public List<Area> findByParentId(long parentId, String name);
    
}
