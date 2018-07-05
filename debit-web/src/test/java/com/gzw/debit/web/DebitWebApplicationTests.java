package com.gzw.debit.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DebitWebApplicationTests {

	@Test
	public void contextLoads() {
		//获取跟目录
		File path = null;
		try {
			path = new File(ResourceUtils.getURL("classpath:").getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(!path.exists()) path = new File("");
		System.out.println("path:"+path.getAbsolutePath());

//如果上传目录为/static/images/upload/，则可以如下获取：
		File upload = new File(path.getAbsolutePath(),"static/images/upload/");
		if(!upload.exists()) upload.mkdirs();
		System.out.println("upload url:"+upload.getAbsolutePath());

	}

}
