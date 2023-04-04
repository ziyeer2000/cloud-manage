package org.cloud.manage.utils;

import java.util.Date;

import org.cloud.lang.date.DateFormatConvertor;
import org.cloud.lang.date.DateUtil;
import org.cloud.lang.date.DefaultDateFormat;

/**
 * 工具集
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2021-06-09 10:10:09
 * @author Cloud
 */
public class CommonUtil {

	/**
	 * 日期转字符串, null转换成空字符串
	 */
	public static String dateToString(Date date, DateFormatConvertor dateFormat) {
		
		if (date != null) {
			return DateUtil.dateToString(date, DefaultDateFormat.DAY);
		} else {
			return "";
		}
	}
}
