package com.ok_thank.you.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.mapper.DiaryMapper;
import com.ok_thank.you.service.DiaryService;

@Service
public class DiaryServiceImpl implements DiaryService{

	@Autowired
	DiaryMapper mapper;
	
	@Override
	public List<Diary> List() {
		return mapper.list();
	}

	@Override
	public void del(int idx) {
		mapper.del(idx);
	}

	@Override
	public void insert(String content) {
		mapper.insert(content);
	}

	@Override
	public List<Map<String, Object>> aList(Map<String, Object> map) {
		return mapper.aList(map);
	}

	@Override
	public Integer insert(Diary model) {
		return mapper.insert(model);
	}

}
