package com.icss.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.CopyUtils;
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

	// 获得Spring核心容器对象
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	PicMapper mapper = context.getBean(PicMapper.class);

	@Test
	public void insert() throws IOException {

		//输入流读取文件
		FileInputStream fis = new FileInputStream("e:\\张曼玉.jpg");

		//转换为字节数组
		byte[] picEmp = FileCopyUtils.copyToByteArray(fis);

		Pic pic = new Pic("张曼玉.jpg", 132497L, "张曼玉生活照", new Date(), "zhangsan", picEmp);

		mapper.insert(pic);
	}

	@Test
	public void queryById() throws IOException {
		
		Pic pic = mapper.queryById(1);		
		
		System.out.println(pic);
		
		//输出流
		FileOutputStream fos = new FileOutputStream("e:\\" + pic.getPicName());
		
		//字节数组复制到输出流
		FileCopyUtils.copy(pic.getPicData(), fos);
		
		fos.close();

	}

}