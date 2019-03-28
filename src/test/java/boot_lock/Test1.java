package boot_lock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lock.Application;
import com.lock.entity.Catalog;
import com.lock.mapper.CatalogMapper;

/**
 * 1、测试类
 * 项目名称：boot_lock 
 * 类名称：Test1
 * 开发者：Lenovo
 * 开发时间：2019年3月27日下午8:08:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test1 {

	@Autowired
	private CatalogMapper catalogMapper;
	@Test  
	public void goodsDaoTest(){  
		int id = 1;  
		//根据相同的id查询出商品信息，赋给2个对象  
		Catalog catalog1 = catalogMapper.selectByPrimaryKey(id);
		Catalog catalog2 = catalogMapper.selectByPrimaryKey(id);

		//打印当前商品信息  
		System.out.println(catalog1);  
		System.out.println(catalog2);  

		//更新商品信息1  
		catalog1.setBrowseCount(3);//修改status为2  
		int updateResult1 = this.catalogMapper.updateByPrimaryKey(catalog1);  
		System.out.println("修改商品信息1"+(updateResult1==1?"成功":"失败"));  

		//更新商品信息2  
		catalog1.setBrowseCount(3);//修改status为2  
		int updateResult2 = this.catalogMapper.updateByPrimaryKey(catalog1); 
		System.out.println("修改商品信息2"+(updateResult2==1?"成功":"失败"));  
	}  
}
