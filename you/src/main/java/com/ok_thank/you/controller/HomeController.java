package com.ok_thank.you.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ok_thank.you.dto.Test2VO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeController {
	
	private String json;
	
	@GetMapping("/")
	public String Home() {
		Test2VO test = new Test2VO();
		test.setIdx(3);
		test.setName("JJUD");
		Gson gson = new Gson();
		json = gson.toJson(test).toString();
		if(StringUtils.isNotEmpty(json)) {
			log.info(json);
			return json;
		}else {
			return "-";
		}
	}
}
