package com.lock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 1、启动类
 * 项目名称：boot_lock 
 * 类名称：Application
 * 开发者：Lenovo
 * 开发时间：2019年3月27日下午2:55:21
 */
@SpringBootApplication 
@MapperScan("com.lock.mapper")
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
