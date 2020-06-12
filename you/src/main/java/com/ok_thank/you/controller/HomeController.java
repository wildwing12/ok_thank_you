package com.ok_thank.you.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ok_thank.you.dto.TestsVO;
import com.ok_thank.you.service.TestsService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RestController
@Api(value="swag-rest-controller", description="HomeController")
public class HomeController extends AbstractController{
	
	@Autowired
	TestsService testsService;
	
	
	
	@GetMapping("/")
	@ResponseBody
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("hello");
		return mv;
	}
	
	
	@GetMapping("/list")
	@ResponseBody
	public Map<String, Object> selectList(Map<String, Object> map) {
		return makeQueryResultMap(testsService.list(map));
	}
	
	@PostMapping("/insert")
	@ResponseBody
	public Map<String, Object> insert(@RequestBody String paramStr) throws Exception {
		log.info("값이 무엇이냐?>>>>>>>>>>>>>>>>>"+paramStr);
		return makeResultMap(testsService.insert((TestsVO)getModel(paramStr, TestsVO.class)));
	}
	@DeleteMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestBody String paramStr) throws Exception{
		log.info("삭제되었다>>>>>>>>>>>>>>>>"+paramStr);
		return makeResultMap(testsService.delete((TestsVO)getModel(paramStr,TestsVO.class)));
	}
	
}
