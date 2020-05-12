package com.ok_thank.you.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ok_thank.you.service.PdTableService;

import io.swagger.annotations.Api;

//@Slf4j
@Controller
@RestController
@Api(value="swag-rest-controller", description="PdTableController")
public class PdTableController extends AbstractController{
	
	@Autowired
	PdTableService pdTableService;
	
	@GetMapping("/pdlist")
	public Map<String, Object> pdList(Map<String,Object> map) {
		return makeQueryResultMap(pdTableService.pdList(map));
	}

	/*@RequestMapping(value = "/members", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> selectList() {
		return makeQueryResultMap(memberService.selectList());
	}
	
	@RequestMapping(value = "/member/{rowId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> selectOne(@PathVariable String rowId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowId", rowId);
		return makeQueryResultMap(memberService.selectOne(map));
	}

	@RequestMapping(value = "/member", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insert(String paramStr) throws Exception {
		return makeResultMap(memberService.insert((Member)getModel(paramStr, Member.class)));
	}
	
	@RequestMapping(value = "/member", method = RequestMethod.PATCH)
	@ResponseBody
	public Map<String, Object> update(String paramStr) throws Exception {
		return makeResultMap(memberService.update((Member)getModel(paramStr, Member.class)));
	}
	
	@RequestMapping(value = "/member", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(String paramStr) throws Exception {
		return makeResultMap(memberService.delete((Member)getModel(paramStr, Member.class)));
	}
	*/
}
