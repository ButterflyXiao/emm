package com.icss.oa.meeting.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.Term;
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
import com.icss.oa.meeting.dao.MeetingMapper;
import com.icss.oa.meeting.index.MeeIndexDao;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.pic.pojo.Pic;
import com.icss.oa.system.index.EmpIndexDao;
import com.icss.oa.system.pojo.Employee;
/**
 * Ա��ҵ��
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MeetingService {
	
	@Autowired
	private MeetingMapper mapper;
	@Autowired
	private MeeIndexDao indexDao;

	/**
	 *���ӻ���
	 */
	public void addMee(Meeting mee) {
		mapper.insert(mee);
		// ����
		try {
			// ����Զ����ɵ�id
			int meeId = mapper.getLastInsertId();

			// ���������ĵ�
			Document document = new Document();

			document.add(new TextField("meeId", String.valueOf(meeId), Store.YES));
			document.add(new TextField("meeTopic", mee.getMeeTopic(), Store.YES));
			document.add(new TextField("meeContent", mee.getMeeContent(), Store.YES));

			// ��������dao
			indexDao.create(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMee(Integer meeId){
		mapper.delete(meeId);
		// ����
		try {
			Term term = new Term("meeId", String.valueOf(meeId));
			// ��������dao
			indexDao.delete(term);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMee(Meeting mee){
		mapper.update(mee);
		try {
			// ����Զ����ɵ�id
			int meeId = mapper.getLastInsertId();

			// ���������ĵ�
			Document document = new Document();

			document.add(new TextField("meeId", String.valueOf(meeId), Store.YES));
			document.add(new TextField("meeTopic", mee.getMeeTopic(), Store.YES));
			document.add(new TextField("meeContent", mee.getMeeContent(), Store.YES));

			// ��������dao
			indexDao.create(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setApproval(Meeting mee){
		mapper.setApproval(mee);
	}
	/**
	 * ��ҳ��ѯ����
	 */
	@Transactional(readOnly=true)
	public List<Meeting> queryMeeByPage(Pager pager) {
		return mapper.queryByPage(pager.getStart(), pager.getPageSize());
	}	
	
	@Transactional(readOnly=true)
	public List<Meeting> query(){
		return mapper.query();
	}
	
	@Transactional(readOnly=true)
	public int getMeeCount() {
		return mapper.getCount();
	}
	
	
	@Transactional(readOnly=true)
	List<Meeting> queryByPage(Integer start,Integer pageSize){
		return queryByPage(start, pageSize);
	}
	
	@Transactional(readOnly=true)
	List<Meeting> queryByTopic(String meeTopic){
		return queryByTopic(meeTopic);
	}
	@Transactional(readOnly=true)
	List<Meeting> queryByCondition(Integer start,Integer pageSize,Integer roomId,Integer empId,String meeTopic,String meeApprovalStatus,Date meeStartTime){
		return queryByCondition(start, pageSize, roomId, empId, meeTopic, meeApprovalStatus, meeStartTime);
	}
	
	@Transactional(readOnly=true)
	public int getMeeCountByCondition(Integer roomId,  String meeTopic, String meeApprovalStatus
			) {
		return mapper.getCountByCondition(roomId, meeTopic, meeApprovalStatus);
	}

	public List<Meeting> queryMeeByCondition(Pager pager, Integer roomId,  String meeTopic,
			String meeApprovalStatus) {
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(),roomId,meeTopic,meeApprovalStatus);
	}

	public Meeting queryMeeById(Integer meeId) {
		return mapper.queryById(meeId);
	}
	
	/**
	 * ȫ�ļ���Ա��
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	@Transactional(readOnly = true)
	public List<Meeting> queryMeeByIndex(String queryStr)
			throws ParseException, IOException, InvalidTokenOffsetsException {

		// ���ó���ͣ�ôʣ��ģ�ô��������֮��Ķ�����
		String[] self_stop_words = { "��", "��", "��", "��", "ô", "," };

		CharArraySet cas = new CharArraySet(Version.LUCENE_47, 0, true);
		for (int i = 0; i < self_stop_words.length; i++) {
			cas.add(self_stop_words[i]);
		}

		// ����ϵͳĬ��ͣ�ô�
		Iterator<Object> itor = StandardAnalyzer.STOP_WORDS_SET.iterator();
		while (itor.hasNext()) {
			cas.add(itor.next());
		}

		// ���ķִ���
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47,cas);

		// ������ѯ���������󣬶��ֶ�����
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "meeId","meeTopic", "meeContent" }, analyzer);
		// ������ѯ����
		Query query = queryParser.parse(queryStr);

		return indexDao.query(query);
	}

	@Transactional(readOnly=true)
	public Integer getRoomId(Integer meeId){
		Meeting meeting=mapper.queryById(meeId);
		Integer roomId=meeting.getMeeRoom().getRoomId();
		
		return roomId;
	}

	@Transactional(readOnly=true)
	public List<Meeting> queryMeeBySponsorId(Integer empId){
		return mapper.queryMeeBySponsorId(empId);
	}
	

	

}