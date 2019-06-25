package com.icss.oa.system.service;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.Term;
import org.apache.lucene.document.TextField;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.EmployeeMapper;
import com.icss.oa.system.index.EmpIndexDao;
import com.icss.oa.system.pojo.Employee;

/**
 * 员工业务
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeService {

	@Autowired
	private EmployeeMapper mapper;

	@Autowired
	private EmpIndexDao indexDao;

	/**
	 * 登录验证 返回1 用户名不存在 2 密码错误 3 登录成功
	 */
	@Transactional(readOnly = true)
	public int checkLogin(String empLoginName, String empPwd) {

		Employee emp = mapper.queryByName(empLoginName);

		if (emp == null)
			return 1;
		else if (!empPwd.equals(emp.getEmpPwd()))
			return 2;
		else
			return 3;
	}

	/**
	 * 增加员工
	 */
	public void addEmp(Employee emp) {

		// 插入数据库
		mapper.insert(emp);

		// 获得插入员工的自动编号
		int empId = mapper.getLastInsertId();

		try {
			/********** 生成索引 *************/
			// 创建索引文档
			Document document = new Document();
			document.add(new TextField("empId", String.valueOf(empId), Store.YES));
			document.add(new TextField("empName", emp.getEmpName(), Store.YES));
			document.add(new TextField("empPhone", emp.getEmpPhone(), Store.YES));
			document.add(new TextField("empInfo", emp.getEmpInfo(), Store.YES));

			// 调用索引dao
			indexDao.create(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改员工
	 */
	public void updateEmp(Employee emp) {

		mapper.update(emp);

		// 索引
		try {
			Term term = new Term("empId", String.valueOf(emp.getEmpId()));

			// 创建索引文档
			Document document = new Document();
			document.add(new TextField("empId", String.valueOf(emp.getEmpId()), Store.YES));
			document.add(new TextField("empName", emp.getEmpName(), Store.YES));
			document.add(new TextField("empPhone", emp.getEmpPhone(), Store.YES));
			document.add(new TextField("empInfo", emp.getEmpInfo(), Store.YES));

			// 调用索引dao
			indexDao.update(term, document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除员工
	 */
	public void deleteEmp(Integer empId) {
		mapper.delete(empId);

		// 索引
		try {
			Term term = new Term("empId", String.valueOf(empId));
			// 调用索引dao
			indexDao.delete(term);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页查询员工
	 */
	@Transactional(readOnly = true)
	public List<Employee> queryEmpByPage(Pager pager) {

		return mapper.queryByPage2(pager.getStart(), pager.getPageSize());
	}

	/**
	 * 返回员工总记录数
	 */
	@Transactional(readOnly = true)
	public int getEmpCount() {
		return mapper.getCount();
	}

	/**
	 * 根据登录名返回员工数据
	 */
	@Transactional(readOnly = true)
	public Employee queryEmpByName(String empLoginName) {

		return mapper.queryByName(empLoginName);
	}

	/**
	 * 根据id返回员工数据
	 */
	@Transactional(readOnly = true)
	public Employee queryEmpById(Integer empId) {

		return mapper.queryById(empId);
	}

	/**
	 * 条件分页查询员工
	 */
	@Transactional(readOnly = true)
	public List<Employee> queryEmpByCondition(Pager pager, Integer deptId, Integer jobId, String empName) {
		return mapper.queryByCondition3(pager.getStart(), pager.getPageSize(), deptId, jobId, empName);
	}

	/**
	 * 条件分页查询员工
	 */
	@Transactional(readOnly = true)
	public int getCountByCondition(Integer deptId, Integer jobId, String empName) {
		return mapper.getCountByCondition(deptId, jobId, empName);
	}

	/**
	 * 更新员工头像
	 */
	public void updateEmpHead(String empLoginName, String empPhoto) {
		mapper.updateHead(empLoginName, empPhoto);
	}

	/**
	 * 返回员工头像
	 */
	@Transactional(readOnly = true)
	public String queryEmpHead(String empLoginName) {
		return mapper.queryHead(empLoginName);
	}

	/**
	 * 全文检索员工
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException 
	 */
	@Transactional(readOnly = true)
	public List<Employee> queryEmpByIndex(String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {

		// 设置常见停用词（的，么，啊，着之类的东东）
		String[] self_stop_words = { "的", "着", "啊", "么", "嘛" , "是" };
		CharArraySet cas = new CharArraySet(Version.LUCENE_47, 0, true);
		for (int i = 0; i < self_stop_words.length; i++) {
			cas.add(self_stop_words[i]);
		}

		// 加入系统默认停用词
		Iterator<Object> itor = StandardAnalyzer.STOP_WORDS_SET.iterator();
		while (itor.hasNext()) {
			cas.add(itor.next());
		}

		// 中文分词器（设置停用词）
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47,cas);

		// 创建查询解析器对象，多字段搜索
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "empId", "empName", "empPhone", "empInfo" }, analyzer);

		// 创建查询对象
		Query query = queryParser.parse(queryStr);

		// 调用索引dao
		List<Employee> list = indexDao.search(query);

		return list;
	}
	
	/**
	 * 员工生日祝福提醒
	 */
	public void happyBirthday() {
		
		//调用数据库查询出今天生日的员工，发送祝福信息
		
		System.out.println("发送员工生日祝福" + new Date());		
	}
	
}