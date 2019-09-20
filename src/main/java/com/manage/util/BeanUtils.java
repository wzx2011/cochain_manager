package com.manage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 反射的Utils函数集合.扩展自Apache Commons BeanUtils
 */
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	private static final Logger log= LoggerFactory.getLogger(BeanUtils.class);
	private BeanUtils() {}

	/**
	 * 根据目标对象，属性名得到属性Field
	 * @param object 目标对象
	 * @param propertyName 属性名
	 * @return
	 * @throws NoSuchFieldException
	 */
	public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
		return getDeclaredField(object.getClass(), propertyName);
	}

	/**
	 * 根据目标类，属性名得到属性Field
	 * @param clazz 目标类
	 * @param propertyName 属性名
	 * @return
	 * @throws NoSuchFieldException
	 */
	public static Field getDeclaredField(Class clazz, String propertyName) throws NoSuchFieldException {
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
	}

	/**
	 * 获取属性值
	 * @param object 目标对象
	 * @param propertyName 属性名
	 * @return
	 */
	public static Object getObjProperty(Object object, String propertyName) {
		Field field=null;
		try {
			field = getDeclaredField(object, propertyName);
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		}
		Object result = null;
		if(null!=field){
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			try {
				result = field.get(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
			field.setAccessible(accessible);
		}
		return result;
	}

	/**
	 * 获取属性值(包括Map)
	 * @param object 目标对象
	 * @param propertyName 属性名
	 * @return
	 */
	public static Object getObjValue(Object object, String propertyName) {
		Object result=null;
		try {
			if(object instanceof Map) {
				result = ((Map)object).get(propertyName);
			} else {
				result = getObjProperty(object, propertyName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 给对象的属性设置值
	 * @param object 目标对象
	 * @param propertyName 属性名
	 * @param newValue 属性值
	 * @return
	 */
	public static void setObjProperty(Object object, String propertyName, Object newValue)
			throws NoSuchFieldException {

		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(object, newValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		field.setAccessible(accessible);
	}

	/**
	 * 给对象的属性设置值（包括Map）
	 * @param object 目标对象
	 * @param propertyName 属性名
	 * @param newValue 属性值
	 * @return
	 */
	public static void setObjValue(Object object, String propertyName, Object newValue) {
		if(null==object||StringUtil.isBlank(propertyName)){return;}
		String[] s=propertyName.split("\\.");
		if(s.length==0){return;}
//		if(null==s)return;
		for (int i = 0; i < s.length-1; i++) {
			object=getObjValue(object, s[i]);
		}
		try {
			if(object instanceof Map) {
				((Map)object).put(propertyName, newValue);
			} else {
				setObjProperty(object,propertyName,newValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 调用对象的私有方法
	 * @param object 目标对象
	 * @param methodName 方法名
	 * @param params
	 * @return
	 * @throws NoSuchMethodException
	 */
	public static Object invokePrivateMethod(Object object, String methodName, Object... params)
			throws NoSuchMethodException {
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}

		Class clazz = object.getClass();
		Method method = null;
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				method = superClass.getDeclaredMethod(methodName, types);
				break;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

		if (method == null) {
			throw new NoSuchMethodException("No Such Method:" + clazz.getSimpleName() + methodName);
		}
		boolean accessible = method.isAccessible();
		method.setAccessible(true);
		Object result = null;
		try {
			result = method.invoke(object, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		method.setAccessible(accessible);
		return result;
	}


	/**
	 * 只拷贝超类里的数据
	 * @param dest
	 * @param orig
	 * @throws Exception
	 */
	public static void copySupperPropertys(Object dest,Object orig) throws Exception{
		if(null!=dest&&null!=orig){
			Object value=null;
			if(orig instanceof Map) {
				for (String key : ((Map<String,Object>)orig).keySet()) {
					value=getObjValue(orig, key);
					setObjValue(dest, key, value);
				}
			}else{
				Field[] field= getObjSupperProperty(orig);
				if(null!=field){
					for (int i = 0; i < field.length; i++) {
						value=getObjValue(orig, field[i].getName());
						setObjValue(dest, field[i].getName(), value);
					}
				}
			}
		}else{
			throw new Exception("参数为空");
		}
	}
	/**
	 * 只拷贝超类里的数据
	 * @param dest
	 * @param orig
	 * @throws Exception
	 */
	public static void copyObjValue(Object dest,Object orig,String propertyName) throws Exception{
		if(null!=dest&&null!=orig){
			Object value=getObjValue(orig, propertyName);
			setObjValue(dest, propertyName, value);
		}else{
			throw new Exception("参数为空");
		}
	}
	/**
	 * 只拷贝超类里的数据
	 * @param dest
	 * @param orig
	 * @throws Exception
	 */
	public static void copyAllPropertys(Object dest,Object orig) throws Exception{
		if(null!=dest&&null!=orig){
			Object value=null;
			if(orig instanceof Map) {
				for (String key : ((Map<String,Object>)orig).keySet()) {
					value=getObjValue(orig, key);
					setObjValue(dest, key, value);
				}
			}else{
				Field[] field= getObjAllProperty(orig);
				if(null!=field){
					for (int i = 0; i < field.length; i++) {
						value=getObjValue(orig, field[i].getName());
						setObjValue(dest, field[i].getName(), value);
					}
				}
			}
		}else{
			throw new Exception("参数为空");
		}
	}
	/**
	 * 拷贝的数据(不包括继承的)
	 * @param dest
	 * @param orig
	 * @throws Exception
	 */
	public static void copyOwnPropertys(Object dest,Object orig) throws Exception{
		if(null!=dest&&null!=orig){
			Object value=null;
			if(orig instanceof Map) {
				for (String key : ((Map<String,Object>)orig).keySet()) {
					value=getObjValue(orig, key);
					setObjValue(dest, key, value);
				}
			}else{
				Field[] field= getObjOwnProperty(orig);
				if(null!=field){
					for (int i = 0; i < field.length; i++) {
						value=getObjValue(orig, field[i].getName());
						setObjValue(dest, field[i].getName(), value);
					}
				}
			}
		}else{
			throw new Exception("参数为空");
		}
	}

	/**
	 * 把orig拷贝给dest，不包括null信息
	 * @param dest
	 * @param orig
	 * @throws Exception
	 */
	public static void copyPropertiesNotNull(Object dest, Object orig) throws Exception {
		if(null!=dest&&null!=orig){
			Field[] origFields = getObjAllProperty(orig);
			for(Field origField:origFields){
				Object value=getObjValue(orig, origField.getName());
				if(null!=value){
					setObjValue(dest, origField.getName(), value);
				}
			}

		}else{
			throw new Exception("参数为空");
		}
	}

	/**
	 * 获取对象的属性(不包括继承的)
	 * @param obj
	 * @return Field[]
	 */
	public static Field[] getObjOwnProperty(Object obj){
		Class c = obj.getClass();
		Field[] field=c.getDeclaredFields();
		return field;
	}

	/**
	 * 获取对象祖先的属性
	 * @param obj
	 * @return Field[]
	 */
	public static Field[] getObjSupperProperty(Object obj){
		Class c = obj.getClass();
		Class supper=c.getSuperclass();
		List<Field> list = new ArrayList<Field>();
		if(null!=supper){
			for (Class superClass = supper; superClass != Object.class; superClass = superClass.getSuperclass()) {
				Field[] fieldchild=superClass.getDeclaredFields();
				if(null!=fieldchild){
					for (Field field2 : fieldchild) {
						list.add(field2);
					}
				}
			}
		}
		Field[] field=new Field[list.size()];
		field=list.toArray(field);
		return field;
	}

	/**
	 * 获取对象所有的属性(包括继承的)
	 * @param obj
	 * @return Field[]
	 */
	public static Field[] getObjAllProperty(Object obj){
		List<Field> list = new ArrayList<Field>();
		for (Class superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			Field[] fieldchild=superClass.getDeclaredFields();
			if(null!=fieldchild){
				for (Field field2 : fieldchild) {
					list.add(field2);
				}
			}
		}
		Field[] field=new Field[list.size()];
		field=list.toArray(field);
		return field;
	}

	/**
	 * 查询集合objects中的属性集合，用","隔开
	 * @param objects 数据集合
	 * @param propertyName 属性
	 * 			repetitionMark:propertyName值重复分割标识，如propertyName值为1，2，3
	 * @return
	 */
	public static <T> String getObjectValsByName(Collection<T> objects,String propertyName){
		return getObjectValsByName(objects, propertyName, null);
	}
	/**
	 * 查询集合objects中的属性集合，用","隔开
	 * @param objects 数据集合
	 * @param propertyName 属性
	 * @param options 其他参数信息
	 * 			repetitionMark:propertyName值重复分割标识，如propertyName值为1，2，3
	 * @return
	 */
	public static <T> String getObjectValsByName(Collection<T> objects,String propertyName,Map<String, String> options){
		try {
			if(options==null){ options=new HashMap<String, String>();}
			String division=options.get("division")==null?",":options.get("division");

			Set<String> valueSet=new HashSet<String>();
			for(T bean:objects){
				String value= BeanUtils.getProperty(bean, propertyName);
				if(StringUtil.isBlank(value)) {
					continue;
				}
				if(StringUtil.isNotBlank(options.get("repetitionMark"))){
					for(String valueItem:value.split(options.get("repetitionMark"))){
						valueSet.add(valueItem.trim());
					}
				}else{
					valueSet.add(value.trim());
				}
			}
			StringBuilder values=new StringBuilder();
			for(String valueItem:valueSet){
				values.append(division).append(valueItem);
			}
			if(values.length()>0) {
				return values.substring(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 返回Set集合的色string格式，用division隔开
	 * @param valueSet
	 * @param division
	 * @return
	 */
	public static String getSetString(Set<String> valueSet,String division){
		StringBuilder values=new StringBuilder();
		for(String valueItem:valueSet){
			values.append(division).append(valueItem);
		}
		if(values.length()>0) {
			return values.substring(1);
		}
		return "";
	}


	/**
	 * 根据objects的List集合转换成Map记录
	 * @param objects
	 * @param propertyNames
	 * @return
	 */
	public static <T> Map<String, T> getObjectsToMap(Collection<T> objects,String propertyNames){
		Map<String, T> objectMap=new HashMap<String, T>();
		try {
			String[] propertyNameArr=propertyNames.split(",");
			if(null==objects) {
				return objectMap;
			}
			for(T bean:objects){
				StringBuffer value=new StringBuffer();
				for(String propertyName:propertyNameArr){
					value.append(BeanUtils.getProperty(bean, propertyName));
				}
				if(StringUtil.isBlank(value)) {
					continue;
				}
				objectMap.put(value.toString(), bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectMap;
	}

	public static void main(String[] args){

	}

}

