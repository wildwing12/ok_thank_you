package com.softenc.web.controller;

import io.swagger.annotations.Api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softenc.web.model.Member;
import com.softenc.web.service.MemberService;

@RestController
@Api(value="swag-rest-controller", description="MemberController")
public class MemberController extends AbstractController {
	
	@Autowired
	MemberService memberService;	
	
	@RequestMapping(value = "/members", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> selectList() {
		return makeQueryResultMap(memberService.selectList());
	}
	
	@RequestMapping(value = "/member/{rowId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> selectOne(@PathVariable String rowId) {
		
		return makeQueryResultMap(memberService.selectOne(rowId));
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
	
} // class


















