package com.inst.invocation;

import java.lang.reflect.Method;

/**
 * 
 * @description 代理封装对象信息
 * @author xxl
 * @version 1.0
 * @createDate 2018年12月26日 上午10:27:05
 *
 */
public class ProxyEntity {
	private final Class<?> clazz;
	private final Object targetObject;
	private final Object object;
	private final Method method;
	private final Object[] args;
	
	public Object getTargetObject() {
		return targetObject;
	}
	
	public Object getObject() {
		return object;
	}

	public Object[] getArgs() {
		return args;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Method getMethod() {
		return method;
	}

	public ProxyEntity(Class<?> clazz, Object targetObject,Object object, Method method, Object[] args) {
		this.clazz = clazz;
		this.targetObject = targetObject;
		this.object = object;
		this.method = method;
		this.args = args;
	}
}
