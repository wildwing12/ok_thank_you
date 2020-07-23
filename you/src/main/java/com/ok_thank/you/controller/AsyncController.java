package com.ok_thank.you.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.service.DiaryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	@DeleteMapping("/async/delete")
	public  Map<String,Object> aDelete(@RequestBody String paramStr) throws Exception{
		log.info("화면에서 넘어오는 값 :{}",paramStr);
		String[] arr = paramStr.split("&");
		int length = arr.length;
		Map<String,Object> result = new HashMap<>();
		if(length == 1) {
			int val  = Integer.valueOf(arr[0].replaceAll("[^0-9]", ""));
			result = makeResultMap(diaryService.adelete(val));
		}else {
			for(int i = 0; i<length; i++) {
				int val = Integer.valueOf(arr[0].replaceAll("[^0-9]", ""));
				result = makeResultMap(diaryService.adelete(val));
			}
		}
		return result;
	}
}
