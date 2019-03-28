package com.lock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1、注解
 * 项目名称：boot_lock 
 * 类名称：RetryOnFailure
 * 开发者：Lenovo
 * 开发时间：2019年3月27日下午3:11:03
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryOnFailure {

}
