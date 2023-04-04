package org.cloud.manage.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.cloud.lang.BaseUtil;
import org.cloud.manage.model.vo.BaseQuery;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example.OrderBy;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.weekend.Fn;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.reflection.Reflections;

/**
 * DB工具类
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2018-10-19 16:49:00
 * @author Cloud
 */
public class DBUtil {

	/**
	 * 填充排序
	 */
	@SuppressWarnings("rawtypes")
	public static void fillOrderBy(Weekend weekend, BaseQuery query) {
		
		String orderByField = query.getOrderByField();
		if (BaseUtil.isNotEmpty(orderByField)) {
			OrderBy ob = weekend.orderBy(query.getOrderByField());
			if (query.isDesc()) {
				ob.desc();
			} else {
				ob.asc();
			}
		}
	}
	
	/**
	 * 获取属性列表
	 */
	public static <T> String toProperty(Class<T> clazz, Fn<T, Object> fn) {
		
		return Reflections.fnToFieldName(fn);
	}
	
	/**
	 * 获取属性列表
	 * @param delFns
	 * 			需要删除的属性
	 */
	@SafeVarargs
	public static <T> List<String> getPropertyByDel(Class<T> clazz, Fn<T, Object>... delFns) {
		
		List<String> props = new ArrayList<String>();
		List<String> delProps = new ArrayList<String>();
		
		if (BaseUtil.isNotEmpty(delFns)) {
			for (Fn<T, Object> fn : delFns) {
				delProps.add(Reflections.fnToFieldName(fn));
			}
		}
		
		Set<EntityColumn> columnSet = EntityHelper.getColumns(clazz);
		for (EntityColumn entityColumn : columnSet) {
			String prop = entityColumn.getProperty();
			if (delProps.contains(prop)) {
				continue;
			}
			props.add(prop);
        }
		
		return props;
	}
	
	/**
	 * 获取属性列表
	 * @param delFns
	 * 			需要的属性
	 */
	@SafeVarargs
	public static <T> List<String> getProperty(Class<T> clazz, Fn<T, Object>... fns) {
		
		List<String> props = new ArrayList<String>();
		
		if (BaseUtil.isNotEmpty(fns)) {
			for (Fn<T, Object> fn : fns) {
				props.add(Reflections.fnToFieldName(fn));
			}
		}
		
		return props;
	}
}
