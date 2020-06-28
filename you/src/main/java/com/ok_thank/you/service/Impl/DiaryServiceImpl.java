package com.ok_thank.you.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.mapper.DiaryMapper;
import com.ok_thank.you.service.DiaryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiaryServiceImpl implements DiaryService{

	@Autowired
	DiaryMapper mapper;
	
	@Override
	public List<Diary> list() {
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
	
	
	//비동기(HYUNJOO)
	@Override
	public List<Diary> asyncList() {
		List<Diary> list = null;
		try {
			list = mapper.asyncList();
			log.info(list.toString());
		} catch (Exception e) {
			log.error("리스트 조회 실패! => {}", e.getMessage());
			throw new RuntimeException();
		}
		return list;
	}

	@Override
	public void asyncInsert(Diary diary) {
		String writer = "Lee";
		try {
			if(diary != null) {
				diary.setWriter(writer);
				mapper.asyncInsert(diary);
				log.info("작성자: "+writer);
			}
		} catch (Exception e) {
			log.error("다이어리 입력 실패! => {}", e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public void asyncDelete(Integer idx) {
		try {
			if(idx != null) {
				mapper.asyncDelete(idx);
				log.info("글번호: "+idx);
			}
		} catch (Exception e) {
			log.error("삭제 실패! => {}", e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public int rowCnt(String search) {
		int rowCnt = mapper.rowCnt("%"+search+"%");
		return rowCnt;
	}

	@Override
	public List<Diary> List(int pageScale, int pageBegin, String search) {
		Map<String,Object> map = new HashMap<>();
		map.put("pageScale", pageScale);
		map.put("pageBegin",pageBegin);
		map.put("search","%"+search+"%");
		return mapper.plist(map);
	}
	
	@Override
	public int getRowCnt(String writer) {
		int count = 0;
		try {
			if(StringUtils.isNotEmpty(writer)) {
				count = mapper.getRowCnt(writer);
				log.info("글 갯수: "+count);
			}
		} catch (Exception e) {
			count = -1;
			log.error("조회 실패! => {}", e.getMessage());
		}
		return count;
	}

	@Override
	public List<Diary> listLee(int pageScale, int begin) {
		List<Diary> list = null;
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("pageScale",pageScale);
			map.put("begin", begin);
			list = mapper.listLee(map);
		} catch (Exception e) {
			log.error("paging 조회 실패! => {}", e.getMessage());
		}
		return list;
	}

}
