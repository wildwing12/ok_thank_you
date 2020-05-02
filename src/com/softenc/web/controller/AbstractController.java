package com.softenc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected Object getModel(String str, Class cl) throws Exception {
		ObjectMapper om = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return om.readValue(str, cl);
	}
	
	// insert, update, delete
	protected Map<String, Object> makeResultMap(int result) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (result>0) {
			resultMap.put("result", Boolean.TRUE);
		} else {
			resultMap.put("result", Boolean.FALSE);
		}
		return resultMap;
	}
	
	// selectOne
	protected Map<String, Object> makeQueryResultMap(Object obj) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (obj!=null) {
			resultMap.put("result", Boolean.TRUE);
			resultMap.put("data", obj);
		} else {
			resultMap.put("result", Boolean.FALSE);
		}
		return resultMap;
	}
	
	// selectList
	protected Map<String, Object> makeQueryResultMap(List<Object> queryList) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (queryList!=null && queryList.size()!=0) {
			resultMap.put("result", Boolean.TRUE);
			for (int i=0; i<queryList.size(); i++) {
				dataMap.put(String.valueOf(i), queryList.get(i));
			}
			resultMap.put("data", dataMap);
		} else {
			resultMap.put("result", Boolean.FALSE);
		}
		return resultMap;
	}	
	
} // class















