package com.ok_thank.you.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.service.DiaryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/todo")
public class LeeController {
	
	@Autowired
	DiaryService diaryService;
	
	
	@GetMapping("/lee")
	public ModelAndView leeDiary(ModelAndView mav) {
		mav.setViewName("diary/lee");
		return mav;
	}
	
	@GetMapping("/lee/asyncList")
	public Map<String, Object> list() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Diary> list = diaryService.asyncList();
		if(list != null) {
			map.put("list", list);
			map.put("cnt", list.size());
			log.info("controller list => "+list.toString());
		}
		return map;
	}
	
	@PostMapping("/lee/insert")
	public void insert(@RequestBody Diary paramData) throws Exception {
		if(paramData != null) {
			diaryService.asyncInsert(paramData);
			log.info("controller insert => {}", paramData);
		}
	}
	
}
