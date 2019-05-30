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
 * 图库控制器
 * @author Administrator
 *
 */
@Controller
public class PicController {
	
	@Autowired
	private PicService service;
	
	/**
	 * 上传图片 MultipartFile picData接收文件数据
	 * @param picData
	 * @param request
	 * @param response
	 * @param picInfo
	 * @throws IOException
	 */
	@RequestMapping("/pic/add")
	public void add(MultipartFile picData,HttpServletRequest request,HttpServletResponse response,String picInfo) throws IOException {
//		
//		System.out.println(picData.getOriginalFilename()); //文件名称
//		System.out.println(picData.getSize());//文件大小
//		System.out.println(picData.getContentType()); //文件MIME类型
//		System.out.println(picInfo);
		
		//转换文件数据为byte数组
		byte[] picDataCopy = FileCopyUtils.copyToByteArray( picData.getInputStream() );
		
		Pic pic = new Pic(picData.getOriginalFilename(), picData.getSize(), picInfo, new Date(), "tom", picDataCopy);
		
		service.addPic(pic);		
	}	
	
	/**
	 * 输出文件
	 */
	@RequestMapping("/pic/get")
	public void get(HttpServletRequest request,HttpServletResponse response,Integer picId) throws IOException {

		Pic pic = service.queryById(picId);
		
		OutputStream out = response.getOutputStream(); //输出流
		
		//把数据复制到输出流中
		FileCopyUtils.copy(pic.getPicData(), out);	
	}	
	
	
	/**
	 * 下载文件
	 */
	@RequestMapping("/pic/download")
	public void download(HttpServletRequest request,HttpServletResponse response,Integer picId) throws IOException {

		Pic pic = service.queryById(picId);
		
		//中文文件名称转码
		String fileName = new String(pic.getPicName().getBytes(),"iso-8859-1");
		
		//设置响应报头，浏览器以附件形式接收数据
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
				
		OutputStream out = response.getOutputStream(); //输出流
		
		//把数据复制到输出流中
		FileCopyUtils.copy(pic.getPicData(), out);	
	}
	
}