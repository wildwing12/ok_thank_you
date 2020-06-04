package com.ok_thank.you.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ok_thank.you.dto.TestsVO;

@Repository
@Mapper
public interface TestsMapper {

	abstract public List<Map<String, Object>> list(Map<String, Object> map);

	public abstract Integer put(TestsVO model);

	public abstract Integer delete(TestsVO model);


}
