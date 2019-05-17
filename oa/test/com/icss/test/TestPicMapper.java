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
 * ͼƬdao������
 * 
 * @author Administrator
 *
 */
public class TestPicMapper {

	// ����Spring�������Ķ���
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	PicMapper mapper = context.getBean(PicMapper.class);
	
	@Test
	public void testInsert() throws IOException {
	
		//������
		FileInputStream fis = new FileInputStream("e:\\���ٺ�.jpg");
		
		//��������ת��Ϊ�ֽ�����
		byte[] picData = FileCopyUtils.copyToByteArray(fis);
		
		Pic pic = new Pic("���ٺ�.jpg", 22563L, "��׵��ݳ���", new Date(), "zhagnsan", picData);
		
		mapper.insert(pic);
		
	}
	
	@Test
	public void testQueryById() throws IOException {
			
		Pic pic = mapper.queryById(2);
		
		System.out.println(pic);
		
		//�����
		FileOutputStream fos = new FileOutputStream("e:\\" + pic.getPicName());
			
		//�����ֽ����鵽�����
		FileCopyUtils.copy(pic.getPicData(), fos);
		
		fos.close();
	}

}