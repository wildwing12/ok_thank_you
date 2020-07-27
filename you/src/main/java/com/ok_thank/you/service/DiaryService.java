package com.ok_thank.you.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.dto.DiarySearchReq;
import com.ok_thank.you.dto.TestFileTEst;

public interface DiaryService {

	public List<Diary> list();

	public void del(int idx);

	public void insert(String content);

	public List<Map<String, Object>> aList(Map<String, Object> map);

	public Integer insert(Diary model);
	
	public int rowCnt(String search);
	
	public List<Diary> List(int pageScale, int pageBegin, String search);
	
	
	//비동기(HYUNJOO)
	public List<Diary> asyncList(DiarySearchReq searchParam);
		
	public void asyncInsert(Diary diary);
	
	public void asyncDelete(Integer idx);

	public int getRowCnt(String writer);

	public List<Diary> listLee(int pageScale, int begin);

	public void ExcelPoi(HttpServletResponse response, Model model) throws Exception;

	public void uploadTest(TestFileTEst test);

	public List<TestFileTEst> downLoad();

	public void fileDownload(HttpServletRequest request, HttpServletResponse response, String filename, String oriname,
			String realFilename);

	//비동기 삭제
	public int adelete(int val);

	public int cnt();
}
