package com.manage.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author zhangfeng
 * @Description  bean Map 工具类
 * @Date 2018/10/9 15:50
 **/
public class BeanMapUtil {
	/**
     * 把一个bean转换成map,hasNull为true则包括空值属性
     * @param object
     * @param hasNull
     * @return
     */
    public static Map<String,Object> converMap(Object object,boolean hasNull) throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        Class clazz = object.getClass();
        //获取所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            if(hasNull){
                map.put(field.getName(), field.get(object));
            }else {
                if (field.get(object) != null) {
                    map.put(field.getName(), field.get(object));
                }
            }
        }
        return map;
    }
    
    public static Collection<Map<String,Object>> converMap(Collection coll) throws Exception{
    	Collection<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
    	for(Object obj : coll){
    		result.add(converMap(obj, false));
    	}
    	return result;
    }
    

    
	/**
	 * 获取request的查询参数
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getRequestParamMap(HttpServletRequest request){
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()){
            String parameterName = parameterNames.nextElement();//调用nextElement方法获得元素
            String parameterVal = request.getParameter(parameterName);
            paramsMap.put(parameterName, parameterVal);
        }
		return paramsMap;
	}
    
}
