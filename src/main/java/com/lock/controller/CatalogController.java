package com.lock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lock.annotation.RetryOnFailure;
import com.lock.service.impl.CatalogService;

/**
 * 1、测试类
 * 项目名称：boot_lock 
 * 类名称：CatalogController
 * 开发者：Lenovo
 * 开发时间：2019年3月27日下午3:25:29
 */
@RestController
public class CatalogController {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private CatalogService catalogService;

	@PostMapping("/catalog")
	@RetryOnFailure
	public String browseCatalog(Long catalogId, String user) {
		try {
			catalogService.browseCatalog(catalogId, user);
		} catch (Exception e) {
			log.error("{}", e);
		}
		return "success";
	}
	
	@PostMapping("/catalogRetry")
	public String browseCatalogWithRetry(Long catalogId, String user) {
		try {
			catalogService.browseCatalogWithRetry(catalogId, user);
		}catch (Exception e) {
			log.error("{}",e);
		}
		return "success";
	}
}
