package com.ok_thank.you.controller;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.service.DiaryService;

@RestController
public class KimController {

	@Autowired
	DiaryService diaryService;
	
	@RequestMapping("/todo/kim")
	public ModelAndView diaryKim(ModelAndView mav) {
		List<Diary> list =diaryService.List();
		mav.addObject("list",list);
		mav.setViewName("diaryKim");
		return mav;
	}
	
	@RequestMapping("/todo/del/{idx}")
	public ModelAndView  del(@PathVariable int idx) {
		diaryService.del(idx);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/todo/kim"));
		return mav;
	}
	
	@PostMapping("/todo/insert")
	public ModelAndView insert(@RequestParam String content, ModelAndView mav) {
		diaryService.insert(content);
		mav.setView(new RedirectView("/todo/kim"));
		return mav;
	}
}
