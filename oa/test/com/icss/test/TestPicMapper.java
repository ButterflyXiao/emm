package com.icss.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.FileCopyUtils;

import com.icss.oa.pic.dao.PicMapper;
import com.icss.oa.pic.pojo.Pic;

/**
 * 图片dao测试类
 * 
 * @author Administrator
 *
 */
public class TestPicMapper {

	// 创建Spring容器核心对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	PicMapper mapper = context.getBean(PicMapper.class);
	
	@Test
	public void testInsert() throws IOException {
	
		//输入流
		FileInputStream fis = new FileInputStream("e:\\李荣浩.jpg");
		
		//把输入流转换为字节数组
		byte[] picData = FileCopyUtils.copyToByteArray(fis);
		
		Pic pic = new Pic("李荣浩.jpg", 22563L, "李白的演唱者", new Date(), "zhagnsan", picData);
		
		mapper.insert(pic);
		
	}
	
	@Test
	public void testQueryById() throws IOException {
			
		Pic pic = mapper.queryById(2);
		
		System.out.println(pic);
		
		//输出流
		FileOutputStream fos = new FileOutputStream("e:\\" + pic.getPicName());
			
		//复制字节数组到输出流
		FileCopyUtils.copy(pic.getPicData(), fos);
		
		fos.close();
	}

}