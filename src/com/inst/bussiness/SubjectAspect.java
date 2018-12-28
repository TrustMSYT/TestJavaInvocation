package com.inst.bussiness;

import com.inst.aonnotation.After;
import com.inst.aonnotation.Before;
import com.inst.aonnotation.MyAspect;

@MyAspect
public class SubjectAspect {

	/**
	 * 
	 * @description sayheay前置方法
	 * @author xxl
	 * @version 1.0
	 * @createDate 2018年12月26日 下午5:06:18
	 *
	 */
	@Before("com.inst.bussiness.RealSubject.sayHey()")
	public void beforeSayHey() {
		System.out.println("说hey开始前");
	}

	/**
	 * 
	 * @description sayheay后置方法
	 * @author xxl
	 * @version 1.0
	 * @createDate 2018年12月26日 下午5:06:18
	 *
	 */
	@After("com.inst.bussiness.RealSubject.sayHey()")
	public void afterSayHey() {
		System.out.println("说hey结束后");
	}

	/**
	 * 
	 * @description saHello前置方法
	 * @author xxl
	 * @version 1.0
	 * @createDate 2018年12月26日 下午5:06:18
	 *
	 */
	@Before("com.inst.bussiness.RealSubject.sayHello()")
	public void beforeSayHello() {
		System.out.println("说hello开始前");
	}

	/**
	 * 
	 * @description saHello后置方法
	 * @author xxl
	 * @version 1.0
	 * @createDate 2018年12月26日 下午5:06:18
	 *
	 */
	@After("com.inst.bussiness.RealSubject.sayHello()")
	public void afterSayHello() {
		System.out.println("说hello结束后");
	}
}
