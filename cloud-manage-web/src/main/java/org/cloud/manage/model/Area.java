package org.cloud.manage.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

/**
 * 区域
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2021-06-08 22:12:16
 * @author Cloud
 */
@Table(name = "t_area")
public class Area implements Serializable {

    /**
     * 洲
     */
    public static final int   LEVEL_CONTINENT  = 1;

    /**
     * 国家
     */
    public static final int   LEVEL_COUNTRY    = 2;

    /**
     * 省
     */
    public static final int   LEVEL_PROVINCE   = 3;

    /**
     * 市
     */
    public static final int   LEVEL_CITY       = 4;

    /**
     * 区县
     */
    public static final int   LEVEL_TOWN       = 5;

    private static final long serialVersionUID = -5818823450290372856L;
    
    private List<Area> children = new ArrayList<Area>();

    /**
     * 主键
     */
	@Id
	@KeySql(useGeneratedKeys = true)
    private Long              id;

    /**
     * 名称
     */
    private String            name;

    /**
     * 级别(1.州, 2.国家 3.省 4.市 5.区县)
     */
    private int               level;

    /**
     * 父ID
     */
    private Long            parentId;
    
    private int              isValid;

    
    
    public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	/**
     * 获取主键
     * 
     * @return 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     * 
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取名称
     * 
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取级别(1.州2.国家3.省4.市5.区县)
     * 
     * @return 级别(1.州2.国家3.省4.市5.区县)
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设置级别(1.州2.国家3.省4.市5.区县)
     * 
     * @param level 级别(1.州2.国家3.省4.市5.区县)
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 获取父ID
     * 
     * @return 父ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父ID
     * 
     * @param parentId 父ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

	public List<Area> getChildren() {
		return children;
	}

	public void setChildren(List<Area> children) {
		this.children = children;
	}

}
