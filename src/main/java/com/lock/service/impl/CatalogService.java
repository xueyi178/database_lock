package com.lock.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lock.annotation.RetryOnFailure;
import com.lock.entity.Browse;
import com.lock.entity.Catalog;
import com.lock.mapper.BrowseMapper;
import com.lock.mapper.CatalogMapper;

/**
 * 1、service
 * 项目名称：boot_lock 
 * 类名称：CatalogService
 * 开发者：Lenovo
 * 开发时间：2019年3月27日下午3:19:00
 */
@Service
public class CatalogService {

	@Autowired
    private CatalogMapper catalogRepository;

    @Autowired
    private BrowseMapper browseRepository;

    @Transactional(rollbackFor = Exception.class)
    public void browseCatalog(Long catalogId, String user) {
        Catalog catalogOptional = catalogRepository.selectByPrimaryKey(catalogId.intValue());
//        Optional<Catalog>  catalogOptional = catalogRepository.findCatalogsForUpdate(catalogId);
//        Optional<Catalog>  catalogOptional = catalogRepository.findCatalogWithPessimisticLock(catalogId);
       /* if(!catalogOptional.isPresent()) {
            throw new RuntimeException("no catalog found");
        }*/
        //Catalog catalog = catalogOptional.get();

        Browse browse = new Browse();
        browse.setCataId(catalogOptional.getId());
        browse.setUser(user);
        browse.setCreateTime(new Date());
        browseRepository.insertSelective(browse);

        /*int result = catalogRepository.updateCatalogWithVersion(catalogId, catalog.getBrowseCount()+1,
                catalog.getVersion());
        if(result == 0) {
            throw new RuntimeException("server is busy, please retry");
        }
        log.info("version={},result={}", catalog.getVersion(), result);*/

        catalogOptional.setBrowseCount(catalogOptional.getBrowseCount() + 1);
        catalogOptional.setId(0);
        catalogOptional.setName("huhuo");
        catalogRepository.insert(catalogOptional);

    }

    /**
     * 乐观锁
     * @param catalogId
     * @param user
     */
    @RetryOnFailure
    public void browseCatalogWithRetry(Long catalogId, String user) {
       Catalog  catalogOptional = catalogRepository.selectByPrimaryKey(catalogId.intValue());
       /*   if(!catalogOptional.isPresent()) {
            throw new RuntimeException("no catalog found");
        }
        Catalog catalog = catalogOptional.get();*/
      /* Browse browse = new Browse();
       browse.setCataId(catalogOptional.getId());
       browse.setUser(user);
       browse.setCreateTime(new Date());
       browseRepository.insertSelective(browse);*/
       
       catalogOptional.setBrowseCount(catalogOptional.getBrowseCount() + 1);
       catalogOptional.setId(0);
       catalogOptional.setName("huhuo");
       catalogRepository.insertSelective(catalogOptional);
    }
}
