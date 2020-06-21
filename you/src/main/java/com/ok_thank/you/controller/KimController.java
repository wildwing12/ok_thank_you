package com.ok_thank.you.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class KimController {

	@RequestMapping("/todo/kim")
	public ModelAndView diaryKim() {
		return new ModelAndView("diaryKim");
	}
}
