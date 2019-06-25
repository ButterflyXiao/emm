package com.icss.oa.meeting.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.mail.search.IntegerComparisonTerm;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Server;
import org.apache.catalina.mapper.Mapper;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.words.HtmlSaveOptions;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.icss.oa.common.Pager;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.meeting.service.MeetingRoomService;
import com.icss.oa.meeting.service.MeetingService;
import com.icss.oa.pic.pojo.Pic;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.DepartmentService;

@Controller
public class MeetingController {

	@Autowired
	private MeetingService service;
	 
	//根据会议开始时间，结束时间，判断该时间范围内该会议室是否被预约（繁忙），对于被预约会议室，在会议室下拉框对应列附上标记
	@RequestMapping("/meeting/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Meeting mee){
		HttpSession session = request.getSession();
		int empId=(Integer)session.getAttribute("empId");
		Employee employee=new Employee();
		employee.setEmpId(empId);
		mee.setMeeSponsor(employee);
		System.out.println(mee);
		service.addMee(mee);
	}


	@RequestMapping("/meeting/update")
	public void update(MultipartFile meeRecord,Integer empId,Integer roomId,String meeStartTime,Integer meeDuration,String meeRecordName,
			String meeApprovalStatus,String meeTopic,String meeContent,Integer meeId,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		//转换文件数据为byte数组
		byte[] fileDataCopy = FileCopyUtils.copyToByteArray( meeRecord.getInputStream() );
		Meeting mee=new Meeting();
		Employee sponsor=new Employee();
		MeetingRoom room=new MeetingRoom();
		
		sponsor.setEmpId(empId);
		room.setRoomId(roomId);
		
		mee.setMeeId(meeId);
		
		mee.setMeeSponsor(sponsor);
		mee.setMeeRoom(room);
		mee.setMeeDuration(meeDuration);
		mee.setMeeApprovalStatus(meeApprovalStatus);
		mee.setMeeTopic(meeTopic);
		mee.setMeeContent(meeContent);
		mee.setMeeRecord(fileDataCopy);
		mee.setMeeRecordName(meeRecordName);
		
        Date date = new Date();  
        //注意format的格式要与日期String的格式相匹配  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        date = sdf.parse(meeStartTime);  
        
		mee.setMeeStartTime(date);

				
		service.updateMee(mee);
	}
	
	@RequestMapping("/meeting/setApproval")
	public void setApproval(HttpServletRequest request,HttpServletResponse response, Meeting meeting){
		service.setApproval(meeting);
	}
	
	@RequestMapping("/meeting/showFile")
	@ResponseBody
	public HashMap<String, Object> putFile(HttpServletRequest request,HttpServletResponse response, Integer meeId ) throws Exception{
		
		if (!getLicense()) {
			return null;
		}
		
		Meeting meeting=service.queryMeeById(meeId);
		
		byte[] meeRecord=meeting.getMeeRecord();
		
		
		String meeRecordName=meeting.getMeeRecordName();
        //根据相对路径创建临时文件目录
    	String contextPath = request.getSession().getServletContext().getRealPath("");
    	String savePath=contextPath;
    	String fileName=meeId+"";//由于每个会议ID是唯一的，而且每个会议对应一个记录文件，所以该临时文件也唯一

        File file =new File(savePath );
        if(!file.exists()){
        	file.createNewFile();
        }

        //向空的文件中写入数据流
		FileOutputStream os = new FileOutputStream(savePath + "/" +fileName);
		os.write(meeRecord);
		os.close();

        //使用Aspose.Words的功能配合"Save"进行Word文档到HTML页面的转制工作
        com.aspose.words.Document doc = new com.aspose.words.Document(savePath + "/" + fileName);
        HtmlSaveOptions hso = new HtmlSaveOptions(SaveFormat.HTML);
        String htmlPath = savePath + meeId + ".html";
        System.out.println(htmlPath);
        doc.save(htmlPath, hso);
        //转制完成后，删除原有的Word文件
        file.delete();
        
		HashMap<String, Object> map = new HashMap<>();
		map.put("meeRecordName",meeRecordName);
		map.put("meeRecord", meeRecord);
		
		
		return map;

	}	
	
	@RequestMapping("/meeting/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer meeId){
		System.out.println("删除");
		service.deleteMee(meeId);
	}
	
	@RequestMapping("/meeting/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request,HttpServletResponse response,Integer pageSize,Integer pageNum) {
		
		if (pageSize == null)
			pageSize = 10;
		
		if (pageNum == null)
			pageNum = 1;
		
		Pager pager = new Pager(service.getMeeCount(), pageSize, pageNum);		
		List<Meeting> list = service.queryMeeByPage(pager);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;
	}
	
	/**
	 * 通过id返回员工
	 */
	@RequestMapping("/meeting/get")
	@ResponseBody
	public Meeting get(HttpServletRequest request,HttpServletResponse response,Integer meeId) {		
		Meeting mee = service.queryMeeById(meeId);
		return mee;			
	}
	
	
	@RequestMapping("/meeting/search")
	@ResponseBody
	public HashMap<String, Object> search(HttpServletRequest request,HttpServletResponse response,Integer pageSize,Integer pageNum,
			Integer roomId,String meeApprovalStatus,String meeTopic) {
		
		if (pageSize == null)
			pageSize = 10;
		
		if (pageNum == null)
			pageNum = 1;
		
		Pager pager = new Pager(service.getMeeCountByCondition(roomId,meeTopic,meeApprovalStatus), pageSize, pageNum);		
		List<Meeting> list = service.queryMeeByCondition(pager,roomId,meeTopic,meeApprovalStatus);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;
	}
	
	/**
	 * 全文检索查询员工	
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 * @throws org.apache.lucene.queryparser.classic.ParseException 
	 */
	@RequestMapping("/mee/queryByIndex")
	@ResponseBody
	public List<Meeting> queryByIndex(HttpServletRequest request,HttpServletResponse response,String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException, org.apache.lucene.queryparser.classic.ParseException {
		
		return service.queryMeeByIndex(queryStr);
	}
	
	@RequestMapping(value = "/meeting/download")
	@ResponseBody 
	public void download(Integer meeId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//中文文件名转码
		Meeting meeting=service.queryMeeById(meeId);

		// 设置浏览器以附件形式接收数据（下载文件）
		String fileName = new String(meeting.getMeeRecordName().getBytes(),"iso-8859-1");
		
		response.setHeader("content-disposition", "attachment;filename=" + fileName);

		// 输出流
		OutputStream out = response.getOutputStream();
		
		//复制文件数据到输出流中响应到客户端
		
		FileCopyUtils.copy(meeting.getMeeRecord(), out);
		
	}

	public static boolean getLicense() {
		boolean result = false;
		try {
			InputStream is = MeetingController.class.getResourceAsStream("license.xml");

			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//根据会议发起人获得会议
	@RequestMapping(value = "/meeting/parti")
	@ResponseBody 
	public List<Meeting> partiMee(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		int empId=(Integer)session.getAttribute("empId");
		System.out.println(empId);
		return service.queryMeeBySponsorId(empId);
		
	}
	
	

}
