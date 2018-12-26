package com.inst.invocation.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.inst.invocation.DynamicProxy;
import com.inst.bussiness.RealSubject;
import com.inst.bussiness.Subject;

/**
 * 
 * @description 测试java动态代理
 * @author xxl
 * @version 1.0
 * @createDate 2018年12月26日 下午5:36:22
 *
 */
public class TestInvocation {
	public static void main(String[] args) throws ClassNotFoundException {
		Subject realSub = new RealSubject();
		InvocationHandler handler = new DynamicProxy(realSub);

		Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
				realSub.getClass().getInterfaces(), handler);
		//System.out.println(subject.getClass().getName());
		subject.sayHey("test");
		subject.sayHello("test");
	}
}
