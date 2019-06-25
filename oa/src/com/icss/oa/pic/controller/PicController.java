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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.icss.oa.pic.pojo.Pic;
import com.icss.oa.pic.service.PicService;

/**
 * ͼ�������
 * 
 * @author Administrator
 *
 */
@Controller
public class PicController {

	@Autowired
	private PicService service;

	/**
	 * ���ļ��ϴ�
	 * 
	 * @param request
	 * @param response
	 * @param picInfo
	 * @param picData
	 * @throws IOException
	 */
	@RequestMapping("/pic/add")
	public void add(@RequestParam("picData") MultipartFile picData, String picInfo, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		//��session�л���û���
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		//��session�л���û�id
		Integer empId = (Integer) session.getAttribute("empId");

		Pic pic = new Pic(picData.getOriginalFilename(), picData.getSize(), picInfo, new Date(), empLoginName,
				picData.getBytes());
		System.out.println(pic);
		service.addPic(pic);
	}

	/**
	 * ����id����ͼƬ
	 * 
	 * @param request
	 * @param response
	 * @param picId
	 * @return
	 */
	@RequestMapping("/pic/getPic")
	@ResponseBody
	public byte[] getPic(HttpServletRequest request, HttpServletResponse response, Integer picId) {

		Pic pic = service.queryPicById(picId);

		return pic.getPicData();
	}
	
	/**
	 * ����id����ͼƬ
	 * 
	 * @param request
	 * @param response
	 * @param picId
	 * @return
	 */
	@RequestMapping("/pic/get")
	@ResponseBody
	public Pic get(HttpServletRequest request, HttpServletResponse response, Integer picId) {

		Pic pic = service.queryPicById(picId);
		return pic;
	}
	

	/**
	 * ����id����ͼƬ
	 * 
	 * @param request
	 * @param response
	 * @param picId
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/pic/download")
	public void download(HttpServletRequest request, HttpServletResponse response, Integer picId) throws IOException {

		Pic pic = service.queryPicById(picId);
		
		//�����ļ���ת��
		String fileName = new String(pic.getPicName().getBytes(),"iso-8859-1");

		// ����������Ը�����ʽ�������ݣ������ļ���
		response.setHeader("content-disposition", "attachment;filename=" + fileName);

		// �����
		OutputStream out = response.getOutputStream();
		
		//�����ļ����ݵ����������Ӧ���ͻ���
		FileCopyUtils.copy(pic.getPicData(), out);

	}

}