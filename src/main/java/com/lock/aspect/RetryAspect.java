package com.lock.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

/**
 * 乐观锁
 * 项目名称：boot_lock 
 * 类名称：RetryAspect
 * 开发者：Lenovo
 * 开发时间：2019年3月27日下午3:12:14
 */
@Aspect
@Component
public class RetryAspect {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	 public static final int MAX_RETRY_TIMES = 5;//max retry times

	    @Pointcut("@annotation(com.lock.annotation.RetryOnFailure)") //self-defined pointcount for RetryOnFailure
	    public void retryOnFailure(){}

	    @Around("retryOnFailure()") //around can be execute before and after the point
	    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
	        int attempts = 0;

	        do {
	            attempts++;
	            try {
	                return pjp.proceed();
	            } catch (Exception e) {
	                if(e instanceof ObjectOptimisticLockingFailureException ||
	                        e instanceof StaleObjectStateException) {
	                    log.info("retrying....times:{}", attempts);
	                    if(attempts > MAX_RETRY_TIMES) {
	                        log.info("retry excceed the max times..");
	                        throw e;
	                    }
	                }
	            }
	        } while (attempts < MAX_RETRY_TIMES);
	        return  null;
	    }
	}