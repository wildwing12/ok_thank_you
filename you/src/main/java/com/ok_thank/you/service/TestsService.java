package com.ok_thank.you.service;

import java.util.List;
import java.util.Map;

import com.ok_thank.you.dto.TestsVO;

public interface TestsService {

	abstract public List<Map<String, Object>> list(Map<String, Object> map);

	public abstract Integer insert(TestsVO model);

	public abstract Integer delete(TestsVO model);


}
