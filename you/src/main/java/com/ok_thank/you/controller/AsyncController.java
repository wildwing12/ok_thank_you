package com.ok_thank.you.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.service.DiaryService;

@RestController
public class AsyncController extends AbstractController {

	@Autowired
	DiaryService diaryService;
	
	@GetMapping("/async/list")
	public ModelAndView gotoPage() {
		return new ModelAndView("asyncKim");
	}
	
	@GetMapping("/async/listLoad")
	public Map<String, Object> list(Map<String, Object> map){
		return makeQueryResultMap(diaryService.aList(map));
	}
	
	@PostMapping("/async/insert")
	public Map<String, Object> insert(@RequestBody String paramStr) throws Exception{
		return makeResultMap(diaryService.insert((Diary)getModel(paramStr, Diary.class)));
	}
}
