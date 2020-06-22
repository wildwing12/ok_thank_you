package com.ok_thank.you.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ok_thank.you.dto.Diary;

@Repository
@Mapper
public interface DiaryMapper {

	public List<Diary> list();

	public void del(int idx);

	public void insert(String content);

	
}
