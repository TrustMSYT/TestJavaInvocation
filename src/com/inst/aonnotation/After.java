package com.inst.aonnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @description 后置通知注解
 * @author xxl
 * @version 1.0
 * @createDate 2018年12月26日 下午4:46:30
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface After {
	String value();
}
