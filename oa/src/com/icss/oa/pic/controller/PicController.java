package com.icss.oa.pic.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		//ת���ļ�����Ϊbyte����
		byte[] picDataCopy = FileCopyUtils.copyToByteArray( picData.getInputStream() );
		
		
		//��session��ȡ���û���
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		Pic pic = new Pic(picData.getOriginalFilename(), picData.getSize(), picInfo, new Date(), empLoginName, picDataCopy);
		
		service.addPic(pic);		
	}	
	
	/**
	 * id��ѯ
	 */
	@RequestMapping("/pic/get")
	@ResponseBody
	public Pic get(HttpServletRequest request,HttpServletResponse response,Integer picId) throws IOException {

		Pic pic = service.queryById(picId);
		
		return pic;
	}
	
	/**
	 * ����ļ�
	 */
	@RequestMapping("/pic/getImg")
	public void getImg(HttpServletRequest request,HttpServletResponse response,Integer picId) throws IOException {

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