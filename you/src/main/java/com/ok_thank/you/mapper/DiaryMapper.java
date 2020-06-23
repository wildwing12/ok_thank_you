package com.ok_thank.you.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ok_thank.you.dto.Diary;

@Repository
@Mapper
public interface DiaryMapper {

	public List<Diary> list();

	public void del(int idx);

	public void insert(String content);

	public List<Map<String, Object>> aList(Map<String, Object> map);

	public Integer insert(Diary model);
	
	
	//비동기(HYUNJOO)
	public List<Diary> asyncList();
	
	public void asyncInsert(Diary diary);
	
	public void asyncDelete(Integer idx);
	
}
