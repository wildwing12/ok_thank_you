package com.ok_thank.you.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String Home() {
		String str = "Jjud Mansei";
		if(StringUtils.isNotEmpty(str)) {
			return str;
		}else {
			return "-";
		}
	}
}
