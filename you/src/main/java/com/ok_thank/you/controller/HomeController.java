package com.ok_thank.you.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ok_thank.you.service.GsonService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RestController
@Api(value="swag-rest-controller", description="HomeController")
public class HomeController {
	
	@Autowired
	private GsonService service;
	
	private String json;
	
	@GetMapping("/")
	@CrossOrigin
	public String Home() throws Exception {
		json = service.getJson();
		JsonElement element = new JsonParser().parse(json);
		JsonObject obj = element.getAsJsonObject();
		String idx = obj.get("idx").toString();
		String name = obj.get("name").toString();
		//System.out.println("idx: "+idx+", name: "+name);
		if(StringUtils.isNotEmpty(idx) && StringUtils.isNotEmpty(name)) {
			log.info(json);
			return json;
		}else {
			return "-";
		}
	}
}
