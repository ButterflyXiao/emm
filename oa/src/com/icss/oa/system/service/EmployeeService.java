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
 * Ա��ҵ��
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
	 * ��¼��֤ ����1 �û��������� 2 ������� 3 ��¼�ɹ�
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
	 * ����Ա��
	 */
	public void addEmp(Employee emp) {

		// �������ݿ�
		mapper.insert(emp);

		// ��ò���Ա�����Զ����
		int empId = mapper.getLastInsertId();

		try {
			/********** �������� *************/
			// ���������ĵ�
			Document document = new Document();
			document.add(new TextField("empId", String.valueOf(empId), Store.YES));
			document.add(new TextField("empName", emp.getEmpName(), Store.YES));
			document.add(new TextField("empPhone", emp.getEmpPhone(), Store.YES));
			document.add(new TextField("empInfo", emp.getEmpInfo(), Store.YES));

			// ��������dao
			indexDao.create(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �޸�Ա��
	 */
	public void updateEmp(Employee emp) {

		mapper.update(emp);

		// ����
		try {
			Term term = new Term("empId", String.valueOf(emp.getEmpId()));

			// ���������ĵ�
			Document document = new Document();
			document.add(new TextField("empId", String.valueOf(emp.getEmpId()), Store.YES));
			document.add(new TextField("empName", emp.getEmpName(), Store.YES));
			document.add(new TextField("empPhone", emp.getEmpPhone(), Store.YES));
			document.add(new TextField("empInfo", emp.getEmpInfo(), Store.YES));

			// ��������dao
			indexDao.update(term, document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ɾ��Ա��
	 */
	public void deleteEmp(Integer empId) {
		mapper.delete(empId);

		// ����
		try {
			Term term = new Term("empId", String.valueOf(empId));
			// ��������dao
			indexDao.delete(term);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ҳ��ѯԱ��
	 */
	@Transactional(readOnly = true)
	public List<Employee> queryEmpByPage(Pager pager) {

		return mapper.queryByPage2(pager.getStart(), pager.getPageSize());
	}

	/**
	 * ����Ա���ܼ�¼��
	 */
	@Transactional(readOnly = true)
	public int getEmpCount() {
		return mapper.getCount();
	}

	/**
	 * ���ݵ�¼������Ա������
	 */
	@Transactional(readOnly = true)
	public Employee queryEmpByName(String empLoginName) {

		return mapper.queryByName(empLoginName);
	}

	/**
	 * ����id����Ա������
	 */
	@Transactional(readOnly = true)
	public Employee queryEmpById(Integer empId) {

		return mapper.queryById(empId);
	}

	/**
	 * ������ҳ��ѯԱ��
	 */
	@Transactional(readOnly = true)
	public List<Employee> queryEmpByCondition(Pager pager, Integer deptId, Integer jobId, String empName) {
		return mapper.queryByCondition3(pager.getStart(), pager.getPageSize(), deptId, jobId, empName);
	}

	/**
	 * ������ҳ��ѯԱ��
	 */
	@Transactional(readOnly = true)
	public int getCountByCondition(Integer deptId, Integer jobId, String empName) {
		return mapper.getCountByCondition(deptId, jobId, empName);
	}

	/**
	 * ����Ա��ͷ��
	 */
	public void updateEmpHead(String empLoginName, String empPhoto) {
		mapper.updateHead(empLoginName, empPhoto);
	}

	/**
	 * ����Ա��ͷ��
	 */
	@Transactional(readOnly = true)
	public String queryEmpHead(String empLoginName) {
		return mapper.queryHead(empLoginName);
	}

	/**
	 * ȫ�ļ���Ա��
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException 
	 */
	@Transactional(readOnly = true)
	public List<Employee> queryEmpByIndex(String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {

		// ���ó���ͣ�ôʣ��ģ�ô��������֮��Ķ�����
		String[] self_stop_words = { "��", "��", "��", "ô", "��" , "��" };
		CharArraySet cas = new CharArraySet(Version.LUCENE_47, 0, true);
		for (int i = 0; i < self_stop_words.length; i++) {
			cas.add(self_stop_words[i]);
		}

		// ����ϵͳĬ��ͣ�ô�
		Iterator<Object> itor = StandardAnalyzer.STOP_WORDS_SET.iterator();
		while (itor.hasNext()) {
			cas.add(itor.next());
		}

		// ���ķִ���������ͣ�ôʣ�
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47,cas);

		// ������ѯ���������󣬶��ֶ�����
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "empId", "empName", "empPhone", "empInfo" }, analyzer);

		// ������ѯ����
		Query query = queryParser.parse(queryStr);

		// ��������dao
		List<Employee> list = indexDao.search(query);

		return list;
	}
	
	/**
	 * Ա������ף������
	 */
	public void happyBirthday() {
		
		//�������ݿ��ѯ���������յ�Ա��������ף����Ϣ
		
		System.out.println("����Ա������ף��" + new Date());		
	}
	
}