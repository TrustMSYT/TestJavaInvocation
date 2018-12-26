package com.inst.invocation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.inst.aonnotation.After;
import com.inst.aonnotation.Before;
import com.inst.aonnotation.MyAspect;

/**
 * 反射类
 * @author xxl
 * @version 1.0
 * @createDate 2018年12月25日 下午5:13:43
 *
 */
public class Reflect {
	Map<String,String> map; // 存放方法名及其注解
	
	/**
	 * 
	 * @description TODO
	 * @author xxl
	 * @version 1.0
	 * @createDate 2018年12月26日 上午10:29:25
	 *
	 * @throws ClassNotFoundException
	 */
	public Reflect() throws ClassNotFoundException {
		map = new HashMap<>();
		getAnnotationClass();
	} 
	
	// 返回存好的map方便ProxyUtil使用
	public Map<String, String> getMap(){
		return map;
	}
	
	/**
	 * 
	 * @description 加载自定义注解类及其注解配置的方法信息
	 * @author xxl
	 * @version 1.0
	 * @createDate 2018年12月26日 上午11:54:16
	 *
	 * @throws ClassNotFoundException
	 */
	private void getAnnotationClass() throws ClassNotFoundException {
		// 获取注解类
		String className = "com.inst.bussiness.SubjectAspect";
		// 直接动态加载该类
		Class<?> clazz = Class.forName(className, false, Thread.currentThread().getContextClassLoader());
		// 判断是否为注解类
		if(clazz.isAnnotationPresent(MyAspect.class)) {
			// 获取所加载类的所有方法
			Method[] methods = clazz.getDeclaredMethods(); // 遍历方法
			for (Method method : methods) {
				// 判读该方法是否为前置方法
				if(method.isAnnotationPresent(Before.class)) {
					Before before = method.getAnnotation(Before.class);
					// 获取注解的值以及当前类的名字的调用方法
					String beforeValue = before.value();
					// 存入方法名和注解名及执行的顺序
					map.put(method.getName()+"-"+className+"-before", beforeValue.substring(0,beforeValue.length()-2));
				}
				
				if (method.isAnnotationPresent(After.class)) {
                    After after =method.getAnnotation(After.class); 
                    String afterValue=after.value();
                    map.put(method.getName()+ "-"+className+"-"+"after",afterValue.substring(0,afterValue.length()-2));
                }
			}
		}
	}

}
