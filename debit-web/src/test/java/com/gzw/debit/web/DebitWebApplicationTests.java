package com.gzw.debit.web;

import com.gzw.debit.core.utils.FilePathUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DebitWebApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
		System.out.println(FilePathUtil.getUpDownFilePath(""));

	}

}
