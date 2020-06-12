package com.ok_thank.you.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ok_thank.you.dto.TestsVO;
import com.ok_thank.you.mapper.TestsMapper;
import com.ok_thank.you.service.TestsService;

@Service
public class TestsServiceImpl implements TestsService {

	@Autowired
	TestsMapper mapper;
	
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return mapper.list(map);
	}

	@Override
	public Integer insert(TestsVO model) {
		return mapper.put(model);
	}

	@Override
	public Integer delete(TestsVO model) {
		return mapper.delete(model);
	}


}
