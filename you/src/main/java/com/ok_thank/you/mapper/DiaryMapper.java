package com.ok_thank.you.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.dto.TestFileTEst;

@Repository
@Mapper
public interface DiaryMapper {

	public List<Diary> list();

	public void del(int idx);

	public void insert(String content);

	public List<Map<String, Object>> aList(Map<String, Object> map);

	public Integer insert(Diary model);
	
	public int rowCnt(String search);
	
	public List<Diary> plist(Map<String, Object> map);
	
	
	//비동기(HYUNJOO)
	public List<Diary> asyncList(Map<String, Object> map);
	
	public void asyncInsert(Diary diary);
	
	public void asyncDelete(Integer idx);

	public int getRowCnt(String writer);

	public List<Diary> listLee(Map<String,Object> map);

	public void uploadTest(TestFileTEst test);

	public List<TestFileTEst> downLoad();
	//비동기 삭제
	public int adelete(int val);
	
}
