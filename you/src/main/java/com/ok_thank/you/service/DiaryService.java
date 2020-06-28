package com.ok_thank.you.service;

import java.util.List;
import java.util.Map;

import com.ok_thank.you.dto.Diary;

public interface DiaryService {

	public List<Diary> List();

	public void del(int idx);

	public void insert(String content);

	public List<Map<String, Object>> aList(Map<String, Object> map);

	public Integer insert(Diary model);
	
	
	//비동기(HYUNJOO)
	public List<Diary> asyncList();
		
	public void asyncInsert(Diary diary);
	
	public void asyncDelete(Integer idx);

	public int rowCnt();

	public List<Diary> List(int pageScale, int pageBegin);

}
