package com.ok_thank.you.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.dto.Pager;
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
	
	@GetMapping("/diaryLee")
	public ModelAndView list(ModelAndView mav, @RequestParam(defaultValue = "1") String curPage) throws Exception {
		String writer = "Lee";
		int count = diaryService.getRowCnt(writer);
		Pager pager = new Pager(count, Integer.parseInt(curPage));
		int pageScale = Pager.PAGE_SCALE;
		int begin = pager.getPageBegin()-1;
		List<Diary> list =diaryService.listLee(pageScale, begin);
		
		mav.addObject("list",list);
		mav.addObject("pager",pager);
		mav.setViewName("diary/diaryLee");
		return mav;
	}
	
	@PostMapping("/lee/insert")
	public void insert(@RequestBody Diary paramData) throws Exception {
		if(paramData != null) {
			diaryService.asyncInsert(paramData);
			log.info("controller insert => {}", paramData);
		}
	}
	
	@DeleteMapping("/lee/{idx}")
	public void delete(@PathVariable Integer idx) throws Exception {
		if(idx != null) {
			diaryService.asyncDelete(idx);
			log.info("controller delete idx: "+idx);
		}
	}
	
}
