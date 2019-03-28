package boot_lock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.lock.Application;

/**
 * 1、测试类
 * 项目名称：boot_lock 
 * 类名称：ApplicationTest
 * 开发者：Lenovo
 * 开发时间：2019年3月27日下午3:35:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

	@Autowired
    private TestRestTemplate testRestTemplate;
	
	@Test
	public void browseCatalogTest() {
	    String url = "http://localhost:8088/catalog";
	    for(int i = 0; i < 100; i++) {
	        final int num = i;
	        new Thread(() -> {
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("catalogId", "1");
                params.add("user", "user" + num);
                String result = testRestTemplate.postForObject(url, params, String.class);
                System.out.println("-------------" + result);
            }
            ).start();
        }
	}

    @Test
    public void browseCatalogTestRetry() {
        String url = "http://localhost:8088/catalogRetry";
        for(int i = 0; i < 100; i++) {
            final int num = i;
            new Thread(() -> {
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("catalogId", "1");
                params.add("user", "user" + num);
                String result = testRestTemplate.postForObject(url, params, String.class);
                System.out.println("-------------" + result);
            }
            ).start();
        }
    }
}
