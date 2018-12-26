package com.inst.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler{
	// 要代理的真实对象
	private Object subject;
	
	// 代理方法执行工具类
	private ProxyUtil proxyUtil;

	public DynamicProxy(Object subject) throws ClassNotFoundException {
		super();
		this.subject = subject;
		proxyUtil = new ProxyUtil();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		ProxyEntity  proxyEntity = new ProxyEntity(subject.getClass(), subject, proxy, method, args);
		// 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象的关联hanler对象的invoke方法来进行调用
		//method.invoke(subject, args);
		return proxyUtil.generateEntity(proxyEntity);
	}

}
