package com.icss.oa.pic.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.icss.oa.pic.pojo.Pic;
import com.icss.oa.pic.service.PicService;

/**
 * ͼ�������
 * @author Administrator
 *
 */
@Controller
public class PicController {
	
	@Autowired
	private PicService service;
	
	/**
	 * �ϴ�ͼƬ MultipartFile picData�����ļ�����
	 * @param picData
	 * @param request
	 * @param response
	 * @param picInfo
	 * @throws IOException
	 */
	@RequestMapping("/pic/add")
	public void add(MultipartFile picData,HttpServletRequest request,HttpServletResponse response,String picInfo) throws IOException {
//		
//		System.out.println(picData.getOriginalFilename()); //�ļ�����
//		System.out.println(picData.getSize());//�ļ���С
//		System.out.println(picData.getContentType()); //�ļ�MIME����
//		System.out.println(picInfo);
		
		//ת���ļ�����Ϊbyte����
		byte[] picDataCopy = FileCopyUtils.copyToByteArray( picData.getInputStream() );
		
		Pic pic = new Pic(picData.getOriginalFilename(), picData.getSize(), picInfo, new Date(), "tom", picDataCopy);
		
		service.addPic(pic);		
	}	
	
	/**
	 * ����ļ�
	 */
	@RequestMapping("/pic/get")
	public void get(HttpServletRequest request,HttpServletResponse response,Integer picId) throws IOException {

		Pic pic = service.queryById(picId);
		
		OutputStream out = response.getOutputStream(); //�����
		
		//�����ݸ��Ƶ��������
		FileCopyUtils.copy(pic.getPicData(), out);	
	}	
	
	
	/**
	 * �����ļ�
	 */
	@RequestMapping("/pic/download")
	public void download(HttpServletRequest request,HttpServletResponse response,Integer picId) throws IOException {

		Pic pic = service.queryById(picId);
		
		//�����ļ�����ת��
		String fileName = new String(pic.getPicName().getBytes(),"iso-8859-1");
		
		//������Ӧ��ͷ��������Ը�����ʽ��������
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
				
		OutputStream out = response.getOutputStream(); //�����
		
		//�����ݸ��Ƶ��������
		FileCopyUtils.copy(pic.getPicData(), out);	
	}
	
}