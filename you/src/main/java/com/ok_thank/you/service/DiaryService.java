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

}
