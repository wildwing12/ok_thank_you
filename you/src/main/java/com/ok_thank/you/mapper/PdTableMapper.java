package com.ok_thank.you.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PdTableMapper {

	List<Map<String, Object>> SelectList(Map<String, Object> map);


}
